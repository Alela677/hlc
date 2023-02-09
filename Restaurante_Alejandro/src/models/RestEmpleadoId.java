package models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RestEmpleadoId implements java.io.Serializable {

	@ManyToOne
	@JoinColumn(name = "dni_empleado")
	private Empleado empleado;

	@ManyToOne
	@JoinColumn(name = "cod_rest")
	private Restaurante restaurante;

	public RestEmpleadoId() {
	}

	public RestEmpleadoId(Empleado empleado, Restaurante restaurante) {
		super();
		this.empleado = empleado;
		this.restaurante = restaurante;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(empleado, restaurante);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestEmpleadoId other = (RestEmpleadoId) obj;
		return Objects.equals(empleado, other.empleado) && Objects.equals(restaurante, other.restaurante);
	}

	@Override
	public String toString() {
		return "RestEmpleadoId [empleado=" + empleado + ", restaurante=" + restaurante + "]";
	}

}
