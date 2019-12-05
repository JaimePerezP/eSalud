package es.e3corp.eSalud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import es.e3corp.eSalud.model.Especialidad;

/**
 * Clase que implementa la interfaz EspecialidadRepository.
 * 
 * @author e3corp
 */
@Repository
public class EspecialidadRepositoryImpl implements EspecialidadRepository {
  /**
   * Instancia de la interfaz MongoOperations.
   * 
   * @author e3corp
   */

  private final MongoOperations mongoOperations;

  /**
   * Constructor de la clase.
   * 
   * @author e3corp
   */
  @Autowired
  public EspecialidadRepositoryImpl(final MongoOperations mongoOperations) {
    Assert.notNull(mongoOperations, "notNull");
    this.mongoOperations = mongoOperations;
  }

  /**
   * Devuelve todas las especialidades.
   * 
   * @author e3corp
   */
  @Override
  public Optional<List<Especialidad>> findAll() {
    List<Especialidad> especialidades = this.mongoOperations.find(new Query(), Especialidad.class);
    return Optional.ofNullable(especialidades);
  }

  /**
   * Guarda una especialidad en la base de datos.
   * 
   * @author e3corp
   */
  @Override
  public void saveEspecialidad(Especialidad especialidad) {
    this.mongoOperations.save(especialidad);

  }

  /**
   * Actualiza una especialidad en la base de datos.
   * 
   * @author e3corp
   */
  @Override
  public void updateEspecialidad(Especialidad especialidad) {
    this.mongoOperations.save(especialidad);

  }

  /**
   * Borra una especialidad en la base de datos.
   * 
   * @author e3corp
   */
  @Override
  public void deleteEspecialidad(String nombre) {
    this.mongoOperations.findAndRemove(new Query(Criteria.where("especialidad").is(nombre)), Especialidad.class);

  }

  /**
   * Devuelve una especialidad en función de su nombre.
   * 
   * @author e3corp
   */
  @Override
  public Optional<Especialidad> findOne(String nombre) {
    Especialidad e = this.mongoOperations.findOne(new Query(Criteria.where("especialidad").is(nombre)),
        Especialidad.class);
    return Optional.ofNullable(e);
  }

}
