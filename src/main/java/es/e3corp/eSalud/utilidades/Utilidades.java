package es.e3corp.eSalud.utilidades;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import es.e3corp.eSalud.model.Cita;
import es.e3corp.eSalud.model.Especialidad;
import es.e3corp.eSalud.model.Usuario;

/**
 * Clase Utilidades.
 * 
 * @author e3corp
 */
public class Utilidades {
  private static final String UTF8 = "utf-8";	
  private static final String DESEDE = "DESede"; 
  private static final String TAMAÑO = "Tamaño de la lista normal: ";
  
  /**
   * Método para encriptar texto.
   * 
   * @author e3corp
   */
  public static String encriptar(final String texto) {

    final String secretKey = "esalud"; // llave para encriptar datos
    String base64EncryptedString = "";

    try {

      final MessageDigest md = MessageDigest.getInstance("MD5");
      final byte[] digestOfPassword = md.digest(secretKey.getBytes(UTF8));
      final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

      final SecretKey key = new SecretKeySpec(keyBytes, DESEDE);
      final Cipher cipher = Cipher.getInstance(DESEDE);
      cipher.init(Cipher.ENCRYPT_MODE, key);

      final byte[] plainTextBytes = texto.getBytes(UTF8);
      final byte[] buf = cipher.doFinal(plainTextBytes);
      final byte[] base64Bytes = Base64.encodeBase64(buf);
      base64EncryptedString = new String(base64Bytes);

    } catch (Exception ex) {
    }
    return base64EncryptedString;
  }

  /**
   * Método para desencriptar texto.
   * 
   * @author e3corp
   */

  public static String desencriptar(final String textoEncriptado) throws Exception {

    final String secretKey = "esalud"; // llave para desencriptar datos
    String base64EncryptedString = "";

    try {
      final byte[] message = Base64.decodeBase64(textoEncriptado.getBytes(UTF8));
      final MessageDigest md = MessageDigest.getInstance("MD5");
      final byte[] digestOfPassword = md.digest(secretKey.getBytes(UTF8));
      final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
      final SecretKey key = new SecretKeySpec(keyBytes, DESEDE);

      final Cipher decipher = Cipher.getInstance(DESEDE);
      decipher.init(Cipher.DECRYPT_MODE, key);

      final byte[] plainText = decipher.doFinal(message);

      base64EncryptedString = new String(plainText, "UTF-8");

    } catch (Exception ex) {
    }
    return base64EncryptedString;
  }

  /**
   * Método para desencriptar usuario.
   * 
   * @author e3corp
   */

  public static Optional<Usuario> desencriptarOptionalUsuario(final Optional<Usuario> user) {

    try {
    	
    	Usuario usu  = null;

		if (user.isPresent()) {
			usu = user.get();
		}
		
      user.get().setDni(desencriptar(user.get().getDni()));
      user.get().setNombre(desencriptar(user.get().getNombre()));
      user.get().setApellidos(desencriptar(user.get().getApellidos()));
      user.get().setcontrasena(desencriptar(user.get().getcontrasena()));
      user.get().setRol(desencriptar(user.get().getRol()));
      user.get().setEspecialidad(desencriptar(user.get().getEspecialidad()));
      user.get().setMedico(desencriptar(user.get().getMedico()));
      user.get().setNumTelefono(desencriptar(user.get().getNumTelefono()));
      user.get().setLocalidad(desencriptar(user.get().getLocalidad()));
      user.get().setCentro(desencriptar(user.get().getCentro()));
      user.get().setEmail(desencriptar(user.get().getEmail()));
      return user;
    } catch (Exception ex) {

      return null;
    }

  }

  /**
   * Método para desencriptar usuario.
   * 
   * @author e3corp
   */
  public static Usuario desencriptarUsuario(final Usuario user) {

    try {

      user.setDni(desencriptar(user.getDni()));
      user.setNombre(desencriptar(user.getNombre()));
      user.setApellidos(desencriptar(user.getApellidos()));
      user.setcontrasena(desencriptar(user.getcontrasena()));
      user.setRol(desencriptar(user.getRol()));
      user.setEspecialidad(desencriptar(user.getEspecialidad()));
      user.setMedico(desencriptar(user.getMedico()));
      user.setNumTelefono(desencriptar(user.getNumTelefono()));
      user.setLocalidad(desencriptar(user.getLocalidad()));
      user.setCentro(desencriptar(user.getCentro()));
      user.setEmail(desencriptar(user.getEmail()));
      return user;
    } catch (Exception ex) {

      return null;
    }

  }

  /**
   * Método para desencriptar una lista de usuarios.
   * 
   * @author e3corp
   */
  public static List<Usuario> desencriptarListaUsuarios(final Optional<List<Usuario>> users) {

    final List<Usuario> usersDesencriptado = new ArrayList<>();
    
    List<Usuario> usu  = null;

	if (users.isPresent()) {
		usu = users.get();
	}

    for (int i = 0; i < users.get().size(); i++) {
      final Usuario usuario = users.get().get(i);
      usersDesencriptado.add(desencriptarUsuario(usuario));

    }

    return usersDesencriptado;
  }

  /**
   * Método para desencriptar usuarios.
   * 
   * @author e3corp
   */
  public static List<Usuario> desencriptarUsuarios(final List<Usuario> users) {

    final List<Usuario> usersDesencriptado = new ArrayList<>();

    for (int i = 0; i < users.size(); i++) {
      final Usuario usuario = users.get(i);
      usersDesencriptado.add(desencriptarUsuario(usuario));

    }

    return usersDesencriptado;
  }

  /**
   * Método para desenciptar citas.
   * 
   * @author e3corp
   */

  public static Cita desencriptarCita(Cita cita) {
    try {
      Cita c = new Cita();

      c.setId(cita.getId());
      ;
      c.setPaciente(desencriptar(cita.getPaciente()));
      c.setPaciente(desencriptar(cita.getPaciente()));

      c.setMedico(desencriptar(cita.getMedico()));
      c.setMedico(desencriptar(cita.getMedico()));

      c.setFecha(desencriptar(cita.getFecha()));
      c.setFecha(desencriptar(cita.getFecha()));

      c.setHora(desencriptar(cita.getHora()));
      c.setHora(desencriptar(cita.getHora()));

      c.setTipo(desencriptar(cita.getTipo()));
      c.setTipo(desencriptar(cita.getTipo()));

      c.setCentro(desencriptar(cita.getCentro()));
      c.setCentro(desencriptar(cita.getCentro()));
      return c;
    }

    catch (Exception ex) {

      return null;
    }
  }

  /**
   * Método para desencriptar lista de citas.
   * 
   * @author e3corp
   */
  public static List<Cita> desencriptarListaCitas(final List<Cita> citas) {
    final List<Cita> citasDesencriptado = new ArrayList<>();
    for (Cita citasDesencriptadas : citas) {
      citasDesencriptado.add(desencriptarCita(citasDesencriptadas));
    }
    return citasDesencriptado;
  }

  /**
   * desenciptarCita.
   * 
   * @author e3corp
   */

  public static Optional<Cita> desencriptarOptionalCita(final Optional<Cita> cita) {
    try {
    	
    	Cita c  = null;

    	if (cita.isPresent()) {
    		c = cita.get();
    	}

      cita.get().setPaciente(desencriptar(cita.get().getPaciente()));
      cita.get().setPaciente(desencriptar(cita.get().getPaciente()));

      cita.get().setMedico(desencriptar(cita.get().getMedico()));
      cita.get().setMedico(desencriptar(cita.get().getMedico()));

      cita.get().setFecha(desencriptar(cita.get().getFecha()));
      cita.get().setFecha(desencriptar(cita.get().getFecha()));

      cita.get().setHora(desencriptar(cita.get().getHora()));
      cita.get().setHora(desencriptar(cita.get().getHora()));

      cita.get().setTipo(desencriptar(cita.get().getTipo()));
      cita.get().setTipo(desencriptar(cita.get().getTipo()));

      cita.get().setCentro(desencriptar(cita.get().getCentro()));
      cita.get().setCentro(desencriptar(cita.get().getCentro()));
      return cita;
    } catch (Exception ex) {

      return null;
    }
  }

  public static Optional<Especialidad> desencriptarOptionalEspecialidad(final Optional<Especialidad> especialidad) {
    try {

    	Especialidad e  = null;

    	if (especialidad.isPresent()) {
    		e = especialidad.get();
    	}
    	
      especialidad.get().setEspecialidad(desencriptar(especialidad.get().getEspecialidad()));
      especialidad.get().setHoraInicio(desencriptar(especialidad.get().getHoraInicio()));
      especialidad.get().setHoraFin(desencriptar(especialidad.get().getHoraFin()));
      especialidad.get().setTiempoConsulta(desencriptar(especialidad.get().getTiempoConsulta()));
      return especialidad;
    } catch (Exception ex) {
      return null;
    }
  }

  public static List<Especialidad> desencriptarListaEspecialidades(Optional<List<Especialidad>> especialidades) {
    final List<Especialidad> especialidadesDesencriptado = new ArrayList<>();

    List <Especialidad> e  = null;

	if (especialidades.isPresent()) {
		e = especialidades.get();
	}
    
    for (int i = 0; i < especialidades.get().size(); i++) {
      final Especialidad especialidad = especialidades.get().get(i);
      especialidadesDesencriptado.add(desencriptarEspecialidad(especialidad));
    }

    return especialidadesDesencriptado;

  }

  public static Especialidad desencriptarEspecialidad(Especialidad especialidad) {
    try {

      especialidad.setEspecialidad(desencriptar(especialidad.getEspecialidad()));
      especialidad.setHoraInicio(desencriptar(especialidad.getHoraInicio()));
      especialidad.setHoraFin(desencriptar(especialidad.getHoraFin()));
      especialidad.setTiempoConsulta(desencriptar(especialidad.getTiempoConsulta()));

      return especialidad;
    } catch (Exception ex) {

      return null;
    }

  }

}
