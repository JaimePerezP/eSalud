package es.e3corp.eSalud.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import es.e3corp.eSalud.Service.EspecialidadService;
import es.e3corp.eSalud.model.Especialidad;
import es.e3corp.eSalud.model.Usuario;
import es.e3corp.eSalud.utilidades.Utilidades;

@RestController
@RequestMapping("/especialidades")
@CrossOrigin(origins = { "http://localhost:4200", "https://esalud.herokuapp.com" }, allowedHeaders = "*")
/**
 * @author e3corp
 */
public class EspecialidadController {
  private static final String SERVER = "[SERVER] ";	
  /**
   * Campo LOG.
   * 
   * @author e3corp
   */
  private static final Log LOG = LogFactory.getLog(EspecialidadController.class);
  /**
   * Interfaz EspecialidadService.
   * 
   * @author e3corp
   */
  private final EspecialidadService especialidadService;

  @Autowired
  /**
   * Contructor EspecialidadController.
   * 
   * @author e3corp
   */
  public EspecialidadController(EspecialidadService especialidadService) {
    this.especialidadService = especialidadService;
  }

  /**
   * Obtiene la especialidad mediante su nombre
   * 
   * @author e3corp
   */

  @RequestMapping(method = RequestMethod.GET)

  public ResponseEntity<Especialidad> getEspecialidad(@RequestParam("nombre") final String nombre) {

    final Especialidad especialidad = especialidadService.findByName(nombre);
    if (especialidad != null) {
      LOG.info("[SERVER] Especialidad encontrada: " + especialidad.getEspecialidad());
      return ResponseEntity.ok(especialidad);
    } else {
      LOG.info("[SERVER] No se ha encontrado ninguna especialidad con ese nombre.");
      return ResponseEntity.badRequest().build();
    }
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ApiOperation(value = "Find all specialties", notes = "Return all specialties")

  public ResponseEntity<List<Especialidad>> allSpecialties() {
    LOG.info("Get allSpecialties");
    return ResponseEntity.ok(especialidadService.findAll());
  }

  /**
   * Borra una especialidad en funcion de su nombre.
   * 
   * @author e3corp
   */
  @RequestMapping(value = "/{especialidad}", method = RequestMethod.DELETE)
  @ApiOperation(value = "Delete an specialty", notes = "Delete a specialty by especialidad")

  public ResponseEntity<Void> deleteEspecialidad(@PathVariable final String especialidad) {
    LOG.info("Delete specialty " + especialidad);
    especialidadService.deleteEspecialidad(Utilidades.encriptar(especialidad));
    return ResponseEntity.noContent().build();
  }

  /**
   * Registramos una especialidad y guardamos esa especialidad en la base de
   * datos.
   * 
   * @author e3corp.
   */
  @RequestMapping(method = RequestMethod.POST)

  public ResponseEntity<Usuario> registrarEspecialidad(@RequestBody final String especialidad) {
    final JSONObject jso = new JSONObject(especialidad);
    final String nombre = jso.getString("especialidad");

    final String nombreEncriptado = Utilidades.encriptar(nombre);

    String horaInicio = "";
    String horaFin = "";
    String tiempoConsulta = "";

    Especialidad especialidad1 = especialidadService.findByName(nombreEncriptado);
    if (especialidad1 == null) {
      try {
        LOG.info("[SERVER] Registrando especialidad...");
        horaInicio = Utilidades.encriptar(jso.getString("horaInicio"));
        horaFin = Utilidades.encriptar(jso.getString("horaFin"));
        tiempoConsulta = Utilidades.encriptar(jso.getString("tiempoConsulta"));
      } catch (JSONException j) {
        LOG.info("[SERVER] Error en la lectura del JSON.");
        LOG.info(j.getMessage());
        return ResponseEntity.badRequest().build();
      }

      especialidad1 = new Especialidad(nombreEncriptado, horaInicio, horaFin, tiempoConsulta);
      especialidadService.saveEspecialidad(especialidad1);
      LOG.info("[SERVER] Especialidad registrada.");
      LOG.info(SERVER + especialidad1.toString());
      return ResponseEntity.ok().build();
    } else {
      LOG.info("[SERVER] Error: La especialidad ya está registrada.");
      LOG.info(SERVER + especialidad1.toString());
      return ResponseEntity.badRequest().build();
    }
  }

  @RequestMapping(value = "/{especialidadNombre}", method = RequestMethod.PUT)
  @ApiOperation(value = "Update especialidad", notes = "Finds a specialty name and updates its fields")
  public ResponseEntity<Usuario> updateEspecialidad(@RequestBody final String mensajerecibido,
      @PathVariable final String especialidadNombre) {
    final JSONObject jso = new JSONObject(mensajerecibido);
    final String nombreEncriptado = Utilidades.encriptar(especialidadNombre);
    final Especialidad especialidad = especialidadService.findByName(nombreEncriptado);
    if (especialidad == null) {
      LOG.info("[SERVER] Error: la especialidad no existe.");
      return ResponseEntity.badRequest().build();
    } else {
      try {
        LOG.info("[SERVER] Actualizando especialidad...");

        // Depende de los campos que queramos que puedan actualizarse
        final String horaInicio = jso.getString("horaInicio");
        final String horaFin = jso.getString("horaFin");
        final String tiempoConsulta = jso.getString("tiempoConsulta");

        final String horaInicioEncriptada = Utilidades.encriptar(horaInicio);
        final String horaFinEncriptada = Utilidades.encriptar(horaFin);
        final String tiempoConsultaEncriptado = Utilidades.encriptar(tiempoConsulta);
        especialidad.setEspecialidad(nombreEncriptado);
        especialidad.setHoraInicio(horaInicioEncriptada);
        especialidad.setHoraFin(horaFinEncriptada);
        especialidad.setTiempoConsulta(tiempoConsultaEncriptado);

      } catch (JSONException j) {
        LOG.error("[SERVER] Error en la lectura del JSON.");
        LOG.info(j.getMessage());
        return ResponseEntity.badRequest().build();
      }

      especialidadService.updateEspecialidad(especialidad);
      LOG.info("[SERVER] Especialidad actualizada.");
      LOG.info(SERVER + especialidad.toString());
      return ResponseEntity.ok().build();
    }
  }

}
