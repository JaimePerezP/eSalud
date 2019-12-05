package es.e3corp.eSalud.Service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.e3corp.eSalud.model.Especialidad;
import es.e3corp.eSalud.repository.EspecialidadRepository;
import es.e3corp.eSalud.utilidades.Utilidades;

@Service("EspecialidadService")
/**
 * @author e3corp
 */
@Transactional
public class EspecialidadServiceImpl implements EspecialidadService {
  /**
   * @author e3corp
   */
  private static final Log LOG = LogFactory.getLog(EspecialidadServiceImpl.class);
  /**
   * @author e3corp
   */
  private EspecialidadRepository especialidadRepository;

  /**
   * @author e3corp
   */
  @Autowired
  public EspecialidadServiceImpl(EspecialidadRepository especialidadRepository) {
    this.especialidadRepository = especialidadRepository;
  }

  @Override
  public List<Especialidad> findAll() {
    final Optional<List<Especialidad>> especialidades = especialidadRepository.findAll();
    return Utilidades.desencriptarListaEspecialidades(especialidades);
  }

  @Override
  public Especialidad findByName(String name) {
    final Optional<Especialidad> especialidad = especialidadRepository.findOne(name);

    if (especialidad.isPresent()) {

      LOG.debug(String.format("Read especialidadName '{}'", name));

      final Optional<Especialidad> especialidadDesencriptado = Utilidades
          .desencriptarOptionalEspecialidad(especialidad);

      return especialidadDesencriptado.get();

    } else {

      return null;

    }
  }

  @Override
  public void saveEspecialidad(Especialidad especialidad) {
    especialidadRepository.saveEspecialidad(especialidad);

  }

  @Override
  public void updateEspecialidad(Especialidad especialidad) {
    especialidadRepository.updateEspecialidad(especialidad);

  }

  @Override
  public void deleteEspecialidad(String nombre) {
    especialidadRepository.deleteEspecialidad(nombre);
  }

}
