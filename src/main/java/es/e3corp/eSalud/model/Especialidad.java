package es.e3corp.eSalud.model;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "horarios")
public class Especialidad {
  @Override
  public String toString() {
    return "Especialidad [id=" + id + ", especialidad=" + especialidad + ", horaInicio=" + horaInicio + ", horaFin="
        + horaFin + ", tiempoConsulta=" + tiempoConsulta + "]";
  }

  @Id
  /**
   * ID. author: e3corp
   */
  private String id;
  /**
   * especialidad. author: e3corp
   */
  @NotNull
  private String especialidad;
  /**
   * Hora de inicio. author: e3corp
   */
  private String horaInicio;
  /**
   * Hora de fin. author: e3corp
   */
  private String horaFin;
  /**
   * Tiempo de consulta. author: e3corp
   */
  private String tiempoConsulta;

  public Especialidad(@NotNull String especialidad, String horaInicio, String horaFin, String tiempoConsulta) {
    super();
    this.id = UUID.randomUUID().toString();
    this.especialidad = especialidad;
    this.horaInicio = horaInicio;
    this.horaFin = horaFin;
    this.tiempoConsulta = tiempoConsulta;
  }

  public Especialidad() {
    // TODO Auto-generated constructor stub
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEspecialidad() {
    return especialidad;
  }

  public void setEspecialidad(String especialidad) {
    this.especialidad = especialidad;
  }

  public String getHoraInicio() {
    return horaInicio;
  }

  public void setHoraInicio(String horaInicio) {
    this.horaInicio = horaInicio;
  }

  public String getHoraFin() {
    return horaFin;
  }

  public void setHoraFin(String horaFin) {
    this.horaFin = horaFin;
  }

  public String getTiempoConsulta() {
    return tiempoConsulta;
  }

  public void setTiempoConsulta(String tiempoConsulta) {
    this.tiempoConsulta = tiempoConsulta;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((horaFin == null) ? 0 : horaFin.hashCode());
    result = prime * result + ((horaInicio == null) ? 0 : horaInicio.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((especialidad == null) ? 0 : especialidad.hashCode());
    result = prime * result + ((tiempoConsulta == null) ? 0 : tiempoConsulta.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Especialidad other = (Especialidad) obj;
    if (horaFin == null) {
      if (other.horaFin != null)
        return false;
    } else if (!horaFin.equals(other.horaFin))
    	return false;
    if (horaInicio == null) {
      if (other.horaInicio != null)
        return false;
    } 
    else if (!horaInicio.equals(other.horaInicio))
    	return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } 
    else if (!id.equals(other.id))
    	return false;
    if (especialidad == null) {
      if (other.especialidad != null)
        return false;
    } 
    else if (!especialidad.equals(other.especialidad))
    	return false;
    if (tiempoConsulta == null) {
      if (other.tiempoConsulta != null)
        return false;
    } 
    else if (!tiempoConsulta.equals(other.tiempoConsulta))
    	return false;
    return true;
  }

}
