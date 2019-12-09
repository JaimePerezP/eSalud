package es.e3corp.eSalud.model;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import es.e3corp.eSalud.utilidades.Utilidades;

@Document(collection = "citas")
/**
 * author: e3corp
 */
public class Cita {

  @Id
  /**
   * ID. author: e3corp
   */
  private String id;
  /**
   * Paciente. author: e3corp
   */
  @NotNull
  private String paciente;
  /**
   * Tipo. author: e3corp
   */
  private String tipo;
  /**
   * Fecha. author: e3corp
   */
  private String fecha;
  /**
   * Centro. author: e3corp
   */
  private String centro;
  /**
   * Médico. author: e3corp
   */
  private String medico;
  /**
   * Hora. author: e3corp
   */
  private String hora;

  /**
   * Constructor de cita. author: e3corp
   */
  public Cita(final String id, @NotNull final String paciente, final String tipo, final String fecha,
      final String centro, final String medico, final String hora) {
    super();
    this.id = UUID.randomUUID().toString();
    this.paciente = Utilidades.encriptar(paciente);
    this.tipo = Utilidades.encriptar(tipo);
    this.fecha = Utilidades.encriptar(fecha);
    this.centro = Utilidades.encriptar(centro);
    this.medico = Utilidades.encriptar(medico);
    this.hora = Utilidades.encriptar(hora);
  }

  public Cita() {
    // TODO Auto-generated constructor stub
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String createId() {
    return UUID.randomUUID().toString();
  }

  public String getPaciente() {
    return paciente;
  }

  public void setPaciente(final String paciente) {
    this.paciente = paciente;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(final String tipo) {
    this.tipo = tipo;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(final String fecha) {
    this.fecha = fecha;
  }

  public String getCentro() {
    return centro;
  }

  public void setCentro(final String centro) {
    this.centro = centro;
  }

  public String getMedico() {
    return medico;
  }

  public void setMedico(final String medico) {
    this.medico = medico;
  }

  public String getHora() {
    return hora;
  }

  public void setHora(final String hora) {
    this.hora = hora;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((centro == null) ? 0 : centro.hashCode());
    result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
    result = prime * result + ((hora == null) ? 0 : hora.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((medico == null) ? 0 : medico.hashCode());
    result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
    result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
    Cita other = (Cita) obj;
    if (centro == null) {
      if (other.centro != null)
        return false;
    } 
    else if (!centro.equals(other.centro))
    	return false;
    if (fecha == null) {
      if (other.fecha != null)
        return false;
    } 
    else if (!fecha.equals(other.fecha))
    	return false;
    if (hora == null) {
      if (other.hora != null)
        return false;
    } 
    else if (!hora.equals(other.hora))
    	return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } 
    else if (!id.equals(other.id))
      return false;
    if (medico == null) {
      if (other.medico != null)
        return false;
    } 
    else if (!medico.equals(other.medico))
    	return false;
    if (paciente == null) {
      if (other.paciente != null)
        return false;
    } 
    else if (!paciente.equals(other.paciente))
    	return false;
    if (tipo == null) {
      if (other.tipo != null)
        return false;
    } 
    else if (!tipo.equals(other.tipo))
    	return false;
    return true;
  }

  @Override
  public String toString() {
    return "Cita [id=" + id + ", paciente=" + paciente + ", tipo=" + tipo + ", fecha=" + fecha + ", centro=" + centro
        + ", médico=" + medico + ", hora=" + hora + "]";
  }

}
