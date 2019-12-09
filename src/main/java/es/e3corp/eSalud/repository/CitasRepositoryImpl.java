package es.e3corp.eSalud.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import es.e3corp.eSalud.model.Cita;
import es.e3corp.eSalud.model.Especialidad;
import es.e3corp.eSalud.model.Usuario;
import es.e3corp.eSalud.utilidades.Utilidades;

/**
 * Clase que se encarga de implementar los métodos de la interfaz
 * CitasRepository.
 * 
 * @author e3corp
 */
@Repository
public class CitasRepositoryImpl implements CitasRepository {
  /**
   * Invocación de la interfaz MongoOperations.
   * 
   * @author e3corp
   */
  private final MongoOperations mongoOperations;

  /**
   * Constructor de CitasRepositoryImpl.
   * 
   * @author e3corp
   */
  private static final String MEDICO = "medico";	
  @Autowired
  public CitasRepositoryImpl(MongoOperations mongoOperations) {
    Assert.notNull(mongoOperations, "notNull");
    this.mongoOperations = mongoOperations;
  }

  /**
   * Método que devuelve todas las citas.
   * 
   * @author e3corp
   */
  public Optional<List<Cita>> findAll() {
    final List<Cita> citas = this.mongoOperations.find(new Query(), Cita.class);
    return Optional.ofNullable(citas);
  }

  /**
   * Método que guarda las citas en la base de datos.
   * 
   * @author e3corp
   */
  public void saveCita(Cita cita) {
    this.mongoOperations.save(cita);
  }

  /**
   * Método que actualiza las citas.
   * 
   * @author e3corp
   */
  public void updateCita(Cita cita) {
    this.mongoOperations.save(cita);
  }

  /**
   * Método para borrar una cita de la base de datos.
   * 
   * @author e3corp
   */
  public void deleteCita(String citaId) {
    this.mongoOperations.findAndRemove(new Query(Criteria.where("_id").is(citaId)), Cita.class);
  }

  /**
   * Método que devuelve un cita mediante su id.
   * 
   * @author e3corp
   */
  public Optional<Cita> findOne(String id) {
    final Cita c = this.mongoOperations.findOne(new Query(Criteria.where("id").is(id)), Cita.class);
    final Optional<Cita> cita = Optional.ofNullable(c);
    return Utilidades.desencriptarOptionalCita(cita);
  }

  /**
   * Método que devuelve citas en función del paciente, medico, fecha y hora.
   * 
   * @author e3corp
   */
  public Cita findByPacienteMedicoFechaHora(final String idPaciente, final String idMedico, final String fecha,
      final String hora) {

    final Cita cita = this.mongoOperations.findOne(new Query(Criteria.where("paciente").is(idPaciente).and(MEDICO)
        .is(idMedico).and("fecha").is(fecha).and("hora").is(hora)), Cita.class);

    return cita;
  }

  @Override
  public List<Cita> findPaciente(final String dni) {
    final List<Cita> citas = this.mongoOperations
        .find(new Query(Criteria.where("paciente").is(Utilidades.encriptar(dni))), Cita.class);
    final List<Cita> citasDesencriptadas = Utilidades.desencriptarListaCitas(citas);
    return citasDesencriptadas;
  }

  @Override
  public List<Cita> findMedico(final String id) throws ParseException {
    final String medicoEncriptado = Utilidades.encriptar(id);
    final List<Cita> citas = this.mongoOperations.find(new Query(Criteria.where(MEDICO).is(medicoEncriptado)),
        Cita.class);

    final List<Cita> citasDesencriptadas = Utilidades.desencriptarListaCitas(citas);
    final List<Cita> citasNombrePaciente = new ArrayList<>();
    for (Cita cita : citasDesencriptadas) {
      Usuario u = this.mongoOperations
          .findOne(new Query(Criteria.where("dni").is(Utilidades.encriptar(cita.getPaciente()))), Usuario.class);
      Usuario usuarioDesencriptado = Utilidades.desencriptarUsuario(u);
      cita.setPaciente(usuarioDesencriptado.getNombre() + " " + usuarioDesencriptado.getApellidos());
      Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(cita.getFecha());

      if (fecha.after(new java.util.Date()) || fecha.equals(new java.util.Date())) {
        citasNombrePaciente.add(cita);
      }
    }

    return citasNombrePaciente;
  }

  @Override
  public List<Cita> getCitasDisponibles(String idmedico, String dia) {
    String idmedicoEncriptado = Utilidades.encriptar(idmedico);
    String diaEncriptado = Utilidades.encriptar(dia);

    final Usuario usuario = this.mongoOperations.findOne(new Query(Criteria.where("dni").is(idmedicoEncriptado)),
        Usuario.class);
    final Especialidad especialidad = this.mongoOperations
        .findOne(new Query(Criteria.where("especialidad").is(usuario.getEspecialidad())), Especialidad.class);
    final List<Cita> citas = this.mongoOperations
        .find(new Query(Criteria.where("fecha").is(diaEncriptado).and(MEDICO).is(idmedicoEncriptado)), Cita.class);
    final Especialidad especialidadDesencriptadas = Utilidades.desencriptarEspecialidad(especialidad);
    final List<Cita> citasDisponibles = new ArrayList<>();
    final List<Cita> citasTotalesDia = new ArrayList<>();

    LocalTime tiempoConsultaTime = LocalTime.parse(especialidadDesencriptadas.getTiempoConsulta());
    long tiempoConsultaMinutes = tiempoConsultaTime.getMinute();
    LocalTime horaInicio = LocalTime.parse(especialidadDesencriptadas.getHoraInicio());
    LocalTime horaFin = LocalTime.parse(especialidadDesencriptadas.getHoraFin());
    while (horaInicio.isBefore(horaFin.minusMinutes(tiempoConsultaMinutes))) {
      citasTotalesDia.add(new Cita("", "", "", "", "", "", horaInicio.toString()));
      horaInicio = horaInicio.plusMinutes(tiempoConsultaMinutes);
    }
    if (citas.isEmpty()) {
      citasDisponibles.addAll(citasTotalesDia);
    } else {
      for (Cita citaPosible : citasTotalesDia) {
        for (Cita citaRealizada : citas) {
          if (!(citaRealizada.getHora().equals(citaPosible.getHora()))) {
            citasDisponibles.add(citaPosible);
          }
        }
      }
    }
    List<Cita> citasFinales = new ArrayList<>();
    citasFinales.addAll(citasDisponibles);

    return citasFinales;
  }

}
