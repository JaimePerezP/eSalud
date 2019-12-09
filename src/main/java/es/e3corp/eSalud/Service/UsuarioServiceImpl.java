package es.e3corp.eSalud.Service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.e3corp.eSalud.exception.UserNotFoundException;
import es.e3corp.eSalud.model.Usuario;
import es.e3corp.eSalud.repository.UsuarioRepository;
import es.e3corp.eSalud.utilidades.Utilidades;

@Service("UsuarioService")
/**
 * @author e3corp
 */
@Transactional

public class UsuarioServiceImpl implements UsuarioService {
  /**
   * @author e3corp
   */
  private static final Log log = LogFactory.getLog(UsuarioServiceImpl.class);
  /**
   * @author e3corp
   */
  private UsuarioRepository userRepository;

  /**
   * @author e3corp
   */
  @Autowired

  public UsuarioServiceImpl(final UsuarioRepository userRepository) {

    this.userRepository = userRepository;

  }

  /**
   * @author e3corp
   */
  public Usuario findByUserDni(final String userDni) {

    final Optional<Usuario> user = userRepository.findOne(userDni);

    if (user.isPresent()) {

      log.debug(String.format("Read userId '{%s}'", userDni));

      final Optional<Usuario> userDesencriptado = Utilidades.desencriptarOptionalUsuario(user);
      
      Usuario usu  = null;

		if (userDesencriptado.isPresent()) {
			usu = userDesencriptado.get();
		}

      return usu;

    } else {

      throw new UserNotFoundException(userDni);

    }

  }

  /**
   * @author e3corp
   */
  public List<Usuario> findAll() {

    final Optional<List<Usuario>> users = userRepository.findAll();

    return Utilidades.desencriptarListaUsuarios(users);

  }

  /**
   * @author e3corp
   */
  public void saveUsuario(final Usuario usuario) {

    userRepository.saveUsuario(usuario);

  }

  /**
   * @author e3corp
   */
  public void updateUsuario(final Usuario user) {

    userRepository.updateUsuario(user);

  }

  /**
   * @author e3corp
   */
  public void deleteUsuario(final String userId) {

    userRepository.deleteUsuario(userId);

  }

  @Override
  public Usuario getUserByDniAndPassword(final String dni, final String password) {
    final Usuario usuario = userRepository.findByDniAndContrasena(dni, password);
    return Utilidades.desencriptarUsuario(usuario);
  }

  @Override
  public List<Usuario> getUsersByRol(final String rol) {
    final List<Usuario> usersRol = userRepository.findByRol(rol);
    return usersRol;
  }

}
