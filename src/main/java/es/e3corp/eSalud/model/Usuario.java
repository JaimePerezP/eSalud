package es.e3corp.eSalud.model;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import es.e3corp.eSalud.utilidades.Utilidades;

/**
 * Documente usuario en la base de datos.
 * 
 * @author e3corp
 */
@Document(collection = "usuarios")
public class Usuario {
  /**
   * ID.
   * 
   * @author e3corp
   */
  @Id
  private String id;
  /**
   * DNI.
   * 
   * @author e3corp
   */
  @NotNull
  private String dni;
  /**
   * Nombre.
   * 
   * @author e3corp
   */
  private String nombre;
  /**
   * Apellidos.
   * 
   * @author e3corp
   */
  private String apellidos;
  /**
   * Contraseña.
   * 
   * @author e3corp
   */
  @NotNull
  private String contrasena;
  /**
   * Rol.
   * 
   * @author e3corp
   */
  private String rol;
  /**
   * Especialidad.
   * 
   * @author e3corp
   */
  private String especialidad;
  /**
   * Médico.
   * 
   * @author e3corp
   */
  private String medico;
  /**
   * NumTeléfono.
   * 
   * @author e3corp
   */
  private String numTelefono;
  /**
   * Localidad.
   * 
   * @author e3corp
   */
  private String localidad;
  /**
   * Centro.
   * 
   * @author e3corp
   */
  private String centro;
  /**
   * Email.
   * 
   * @author e3corp
   */
  private String email;

  /**
   * Constructor de Usuario.
   * 
   * @author e3corp
   */
  public Usuario(@NotNull final String dni, final String nombre, final String apellidos,
      @NotNull final String contrasena, final String rol, final String especialidad, final String medico,
      final String numTelefono, final String localidad, final String centro, final String email) {
    super();
    this.id = UUID.randomUUID().toString();
    this.dni = Utilidades.encriptar(dni);
    this.nombre = Utilidades.encriptar(nombre);
    this.apellidos = Utilidades.encriptar(apellidos);
    this.contrasena = Utilidades.encriptar(contrasena);
    this.rol = Utilidades.encriptar(rol);
    this.especialidad = Utilidades.encriptar(especialidad);
    this.medico = Utilidades.encriptar(medico);
    this.numTelefono = Utilidades.encriptar(numTelefono);
    this.localidad = Utilidades.encriptar(localidad);
    this.centro = Utilidades.encriptar(centro);
    this.email = Utilidades.encriptar(email);
  }

  /**
   * Constructor vacío de Usuario.
   * 
   * @author e3corp
   */
  public Usuario() {
	this.dni = "Sin definir";
	this.contrasena = "Sin definir";

  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(final String dni) {
    this.dni = dni;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(final String apellidos) {
    this.apellidos = apellidos;
  }

  /**
   * Método getcontrasena.
   * 
   * @author e3corp
   */
  public String getcontrasena() {
    return contrasena;
  }

  /**
   * Método setcontrasena.
   * 
   * @author e3corp
   */
  public void setcontrasena(final String contrasena) {
    this.contrasena = contrasena;
  }

  public String getRol() {
    return rol;
  }

  public void setRol(final String rol) {
    this.rol = rol;
  }

  public String getEspecialidad() {
    return especialidad;
  }

  public void setEspecialidad(final String especialidad) {
    this.especialidad = especialidad;
  }

  public String getMedico() {
    return medico;
  }

  public void setMedico(final String medico) {
    this.medico = medico;
  }

  public String getNumTelefono() {
    return numTelefono;
  }

  public void setNumTelefono(final String numTelefono) {
    this.numTelefono = numTelefono;
  }

  public String getLocalidad() {
    return localidad;
  }

  public void setLocalidad(final String localidad) {
    this.localidad = localidad;
  }

  public String getCentro() {
    return centro;
  }

  public void setCentro(final String centro) {
    this.centro = centro;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
    result = prime * result + ((centro == null) ? 0 : centro.hashCode());
    result = prime * result + ((contrasena == null) ? 0 : contrasena.hashCode());
    result = prime * result + ((dni == null) ? 0 : dni.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((especialidad == null) ? 0 : especialidad.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
    result = prime * result + ((medico == null) ? 0 : medico.hashCode());
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
    result = prime * result + ((nombre == null) ? 0 : numTelefono.hashCode());
    result = prime * result + ((rol == null) ? 0 : rol.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Usuario other = (Usuario) obj;
    if (apellidos == null) {
      if (other.apellidos != null) {
        return false;
      }
    } else if (!apellidos.equals(other.apellidos)) {
      return false;
    }
    if (centro == null) {
      if (other.centro != null) {
        return false;
      }
    } else if (!centro.equals(other.centro)) {
      return false;
    }
    if (contrasena == null) {
      if (other.contrasena != null) {
        return false;
      }
    } else if (!contrasena.equals(other.contrasena)) {
      return false;
    }
    if (dni == null) {
      if (other.dni != null) {
        return false;
      }
    } else if (!dni.equals(other.dni)) {
      return false;
    }
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    if (especialidad == null) {
      if (other.especialidad != null) {
        return false;
      }
    } else if (!especialidad.equals(other.especialidad)) {
      return false;
    }
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    if (localidad == null) {
      if (other.localidad != null) {
        return false;
      }
    } else if (!localidad.equals(other.localidad)) {
      return false;
    }
    if (medico == null) {
      if (other.medico != null) {
        return false;
      }
    } else if (!medico.equals(other.medico)) {
      return false;
    }
    if (nombre == null) {
      if (other.nombre != null) {
        return false;
      }
    } else if (!nombre.equals(other.nombre)) {
      return false;
    }
    if (numTelefono != other.numTelefono) {
      return false;
    }
    if (rol == null) {
      if (other.rol != null) {
        return false;
      }
    } else if (!rol.equals(other.rol)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Usuario [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", contrasena="
        + contrasena + ", rol=" + rol + ", especialidad=" + especialidad + ", medico=" + medico + ", numTelefono="
        + numTelefono + ", localidad=" + localidad + ", centro=" + centro + ", email=" + email + "]";
  }

}