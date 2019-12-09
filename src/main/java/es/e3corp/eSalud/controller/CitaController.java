
package es.e3corp.eSalud.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import es.e3corp.eSalud.Service.CitaService;
import es.e3corp.eSalud.Service.UsuarioService;
import es.e3corp.eSalud.exception.CitaNotFoundException;
import es.e3corp.eSalud.exception.UserNotFoundException;
import es.e3corp.eSalud.model.Cita;
import es.e3corp.eSalud.model.Usuario;
import es.e3corp.eSalud.utilidades.Utilidades;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = { "http://localhost:4200", "https://esalud.herokuapp.com" }, allowedHeaders = "*")
/**
 * @author e3corp
 */
public class CitaController {
  private static final String PACIENTE = "paciente";
  private static final String MEDICO = "medico";
  /**
   * Campo LOG.
   * 
   * @author e3corp
   */
  private static final Log LOG = LogFactory.getLog(CitaController.class);
  /**
   * Interfaz CitasService.
   * 
   * @author e3corp
   */
  private final CitaService citasService;
  /**
   * Interfaz UsuarioService.
   * 
   * @author e3corp
   */
  private final UsuarioService usuarioService;

  @Autowired
  /**
   * Contructor CitaController.
   * 
   * @author e3corp
   */
  public CitaController(final CitaService citasService, final UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
    this.citasService = citasService;
  }

  /**
   * Obtiene cita por fecha.
   * 
   * @return
   */

  @GetMapping
  public ResponseEntity<Cita> getCitaFecha(@RequestParam(required = false) String paciente,
      @RequestParam(required = false) String medico, @RequestParam(required = false) String fecha,
      @RequestParam(required = false) String hora) {

    String pacienteEncriptado = Utilidades.encriptar(paciente);
    String medicoEncriptado = Utilidades.encriptar(medico);
    String fechaEncriptado = Utilidades.encriptar(fecha);
    String horaEncriptado = Utilidades.encriptar(hora);

    Cita cita = citasService.findCitaByPacienteMedicoFechaHora(pacienteEncriptado, medicoEncriptado, fechaEncriptado,
        horaEncriptado);
    if (cita != null) {
      return ResponseEntity.ok(cita);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping(value = "paciente/{dni}")
  /**
   * @author e3corp
   */
  public ResponseEntity<List<Cita>> getListadoCitasByPaciente(@PathVariable(required = true) final String dni) {
    final List<Cita> citas = citasService.getCitasByPaciente(dni);
    return ResponseEntity.ok(citas);
  }

  @GetMapping(value = "medico/{id}")
  /**
   * @author e3corp
   */
  public ResponseEntity<List<Cita>> getListadoCitasByMedico(@PathVariable("id") final String idmedico)
      throws ParseException {
    final List<Cita> citas = citasService.getCitasByMedico(idmedico);
    return ResponseEntity.ok(citas);
  }

  /**
   * RequestMapping de citas.
   * 
   * @author e3corp
   */
  @PutMapping(value = "/{citaId}")

  /**
   * ApiOperation update cita.
   * 
   * @author e3corp
   */
  @ApiOperation(value = "Update cita", notes = "Finds a cita ID and updates its fields")
  /**
   * Actualiza cita mediante su id y una nueva cita.
   * 
   * @author e3corp
   */
  public ResponseEntity<Cita> updateCita(@RequestBody final String mensajerecibido, @PathVariable final String citaId) {
    LOG.info("[SERVER] Actualizando cita...");
    final JSONObject jso = new JSONObject(mensajerecibido);
    final Cita cita = citasService.findByCitaId(citaId);
    if (cita == null) {
      LOG.info("[SERVER] Error: la cita no existe.");
      return ResponseEntity.badRequest().build();
    } else {
      try {
        LOG.info("[SERVER] Actualizando cita...");

        // Depende de los campos que queramos que puedan actualizarse
        final String paciente = jso.getString(PACIENTE);
        final String medico = jso.getString(MEDICO);

        final String fecha = jso.getString("fecha");
        final String hora = jso.getString("hora");
        final String tipo = jso.getString("tipo");
        final String centro = jso.getString("centro");

        final String pacienteEncriptado = Utilidades.encriptar(paciente);
        final String medicoEncriptado = Utilidades.encriptar(medico);
        final String fechaEncriptado = Utilidades.encriptar(fecha);
        final String horaEncriptado = Utilidades.encriptar(hora);
        final String centroEncriptado = Utilidades.encriptar(centro);
        final String tipoEncriptado = Utilidades.encriptar(tipo);

        try {
          final Usuario usuarioPaciente = usuarioService.findByUserDni(pacienteEncriptado);
          final Usuario usuarioMedico = usuarioService.findByUserDni(medicoEncriptado);
          if (!usuarioPaciente.getRol().equals(PACIENTE)) {
            LOG.error("[SERVER] El usuario paciente no es válido.");
            return ResponseEntity.badRequest().build();
          }
          if (!usuarioMedico.getRol().equals(MEDICO)) {
            LOG.error("[SERVER] El usuario medico no es válido.");
            return ResponseEntity.badRequest().build();
          }

        } catch (UserNotFoundException u) {
          LOG.error("[SERVER] El usuario paciente o medico no se ha encontrado.");
          return ResponseEntity.badRequest().build();
        }

        cita.setPaciente(pacienteEncriptado);
        cita.setMedico(medicoEncriptado);
        cita.setFecha(fechaEncriptado);
        cita.setHora(horaEncriptado);
        cita.setTipo(tipoEncriptado);
        cita.setCentro(centroEncriptado);
      } catch (JSONException j) {
        LOG.error("[SERVER] Error en la lectura del JSON.");
        LOG.info(j.getMessage());
        return ResponseEntity.badRequest().build();
      }

      citasService.updateCita(cita);
      LOG.info("[SERVER] Cita actualizada.");
      LOG.info("[SERVER] " + cita.toString());
      return ResponseEntity.ok().build();
    }
  }

  /**
   * RequestMapping /{citaid}. ApiOperation find cita.
   */
  @GetMapping(value = "/{citaId}")
  @ApiOperation(value = "Find cita", notes = "Return a cita by Id")
  /**
   * Obtiene una cita mediante su id.
   * 
   * @author e3corp
   */
  public ResponseEntity<Cita> citaById(@PathVariable final String citaId) throws CitaNotFoundException {
    LOG.info("[SERVER] Buscando cita...");
    Cita cita;
    try {
      cita = citasService.findByCitaId(citaId);
      LOG.info("[SERVER] " + cita.toString());
    } catch (CitaNotFoundException e) {
      cita = null;
    }
    return ResponseEntity.ok(cita);
  }

  /**
   * RequestMapping {citaid}. ApiOperation delete cita.
   */
  @DeleteMapping(value = "{citaId}")
  @ApiOperation(value = "Delete cita", notes = "Delete cita")
  /**
   * Borra una cita mediante su id.
   * 
   * @author e3corp
   */
  public ResponseEntity<Void> deleteCita(@PathVariable final String citaId) {
    LOG.info("[SERVER] Borrando cita:  " + citaId);
    citasService.deleteCita(citaId);
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/disponibilidad")
  public ResponseEntity<List<Cita>> disponibilidadCitasEnUnDia(@RequestParam("idmedico") String idmedico,
      @RequestParam("dia") String dia) {
    List<Cita> citas = this.citasService.getCitasDisponibles(idmedico, dia);
    return ResponseEntity.ok(citas);
  }

  /**
   * Registra o guarda una cita en la base de datos.
   * 
   * @author e3corp
   */
  @PostMapping
  public ResponseEntity<Cita> registrarCita(@RequestBody String cita) {
    final JSONObject jso = new JSONObject(cita);
    final String paciente = jso.getString(PACIENTE);
    final String medico = jso.getString(MEDICO);
    final String fecha = jso.getString("fecha");
    final String hora = jso.getString("hora");
    final String tipo = jso.getString("tipo");
    final String centro = jso.getString("centro");
    LOG.info("el paciente que se recibe es:" + paciente);
    Cita citaFinal = new Cita("", paciente, tipo, fecha, centro, medico, hora);
    citasService.saveCita(citaFinal);
    return ResponseEntity.ok(citaFinal);
  }
}
