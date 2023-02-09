package models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rest_empleado")
public class RestEmpleado implements java.io.Serializable {

	@EmbeddedId
	private RestEmpleadoId id;

	@Column
	private String funcion;

	public RestEmpleado() {
	}

	public RestEmpleado(RestEmpleadoId id, String funcion) {
		this.id = id;
		this.funcion = funcion;

	}

	public RestEmpleadoId getId() {
		return this.id;
	}

	public void setId(RestEmpleadoId id) {
		this.id = id;
	}

	public String getFuncion() {
		return this.funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(funcion, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestEmpleado other = (RestEmpleado) obj;
		return Objects.equals(funcion, other.funcion) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "RestEmpleado [id=" + id + ", funcion=" + funcion + "]";
	}
	
	
}
