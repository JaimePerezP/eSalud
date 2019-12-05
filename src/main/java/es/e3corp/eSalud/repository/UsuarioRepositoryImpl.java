package es.e3corp.eSalud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import es.e3corp.eSalud.model.Usuario;

/**
 * Clase que implementa la interfaz UsuarioRepository.
 * 
 * @author e3corp
 */
@Repository

public class UsuarioRepositoryImpl implements UsuarioRepository {
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

  public UsuarioRepositoryImpl(final MongoOperations mongoOperations) {
    Assert.notNull(mongoOperations, "notNull");
    this.mongoOperations = mongoOperations;

  }

  /**
   * Devuelve todos los usuarios.
   * 
   * @author e3corp
   */
  public Optional<List<Usuario>> findAll() {

    List<Usuario> users = this.mongoOperations.find(new Query(), Usuario.class);

    return Optional.ofNullable(users);

  }

  /**
   * Devuelve un usuario en función de su dni.
   * 
   * @author e3corp
   */
  public Optional<Usuario> findOne(final String dni) {
    System.out.println("el usuario buscado encriptado es: " + dni);
    Usuario d = this.mongoOperations.findOne(new Query(Criteria.where("dni").is(dni)), Usuario.class);
    return Optional.ofNullable(d);
  }

  /**
   * Guarda un usuario en la base de datos.
   * 
   * @author e3corp
   */
  public void saveUsuario(final Usuario usuario) {
    this.mongoOperations.save(usuario);
  }

  /**
   * Actualiza un usuario en la base de datos.
   * 
   * @author e3corp
   */
  public void updateUsuario(final Usuario usuario) {

    this.mongoOperations.save(usuario);

  }

  /**
   * Borra un usuario en la base de datos.
   * 
   * @author e3corp
   */
  public void deleteUsuario(final String id) {

    this.mongoOperations.findAndRemove(new Query(Criteria.where("id").is(id)), Usuario.class);

  }

  @Override
  public Usuario findByDniAndContrasena(final String dni, final String contrasena) {
    return this.mongoOperations
            .findOne(new Query(Criteria.where("dni").is(dni).and("contrasena").is(contrasena)), Usuario.class);
  }

  @Override
  public List<Usuario> findByRol(final String rol) {
    return this.mongoOperations.find(new Query(Criteria.where("rol").is(rol)), Usuario.class);
  }

}
