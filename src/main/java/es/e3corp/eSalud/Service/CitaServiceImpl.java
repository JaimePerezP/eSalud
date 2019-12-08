package es.e3corp.eSalud.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.e3corp.eSalud.exception.CitaNotFoundException;
import es.e3corp.eSalud.model.Cita;
import es.e3corp.eSalud.repository.CitasRepository;

@Service("CitaService")
/**
 * @author e3corp
 */
@Transactional
public class CitaServiceImpl implements CitaService {
  /**
   * Variable de Log.
   * 
   * @author e3corp
   */
  private static final Log LOG = LogFactory.getLog(CitaServiceImpl.class);
  /**
   * Declaración del repositorio de citas.
   * 
   * @author e3corp
   */
  private CitasRepository citaRepository;

  /**
   * Constructor de la clase.
   * 
   * @author e3corp
   */
  @Autowired
  public CitaServiceImpl(CitasRepository citaRepository) {
    this.citaRepository = citaRepository;
  }

  /**
   * Método para obtener todas las citas.
   * 
   * @author e3corp
   */
  public List<Cita> findAll() {
	  
    Optional<List<Cita>> cita = citaRepository.findAll();
    
    if (cita.isPresent()) {
    	List<Cita> stringValue = cita.get();
    	}
    
    return cita.get();
  }

  /**
   * Método para obtener las citas en función del id.
   * 
   * @author e3corp
   */

  public Cita findByCitaId(String citaId) {
    Optional<Cita> cita = citaRepository.findOne(citaId);
    if (cita.isPresent()) {
      LOG.debug(String.format("Read citaId '{}'", citaId));
      return cita.get();
    } else {
      throw new CitaNotFoundException(citaId);
    }
  }

  /**
   * Método para guardar citas.
   * 
   * @author e3corp
   */

  public void saveCita(Cita cita) {
    citaRepository.saveCita(cita);
  }

  /**
   * Método para actualizar citas.
   * 
   * @author e3corp
   */

  public void updateCita(Cita cita) {
    citaRepository.updateCita(cita);
  }

  /**
   * Método para eliminar citas en función del id.
   * 
   * @author e3corp
   */

  public void deleteCita(String citaId) {
    citaRepository.deleteCita(citaId);
  }

  /**
   * Método para encontrar citas en función del id de paciente, fecha y hora.
   * 
   * @author e3corp
   */

  public Cita findCitaByPacienteMedicoFechaHora(String idPaciente, String idMedico, String fecha, String hora) {
    LOG.info("[SERVER] Id paciente recibido: " + idPaciente);
    LOG.info("[SERVER] Id medico recibido: " + idMedico);
    LOG.info("[SERVER] Fecha recibida: " + fecha);
    LOG.info("[SERVER] Hora recibida: " + hora);
    return citaRepository.findByPacienteMedicoFechaHora(idPaciente, idMedico, fecha, hora);
  }

  @Override
  public List<Cita> getCitasByPaciente(String dni) {
    return citaRepository.findPaciente(dni);
  }

  @Override
  public List<Cita> getCitasByMedico(String id) throws ParseException {
    return citaRepository.findMedico(id);
  }

  @Override
  public List<Cita> getCitasDisponibles(String idmedico, String dia) {
    return citaRepository.getCitasDisponibles(idmedico, dia);
  }

}
