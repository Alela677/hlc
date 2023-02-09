package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rest_empleado" )
public class RestEmpleado implements java.io.Serializable {

	@EmbeddedId
	private RestEmpleadoId id;

	@ManyToOne
	private Empleado empleado;
	
	@Column
	private String funcion;
	
	@ManyToOne
	private Restaurante restaurante;

	public RestEmpleado() {
	}

	public RestEmpleado(RestEmpleadoId id, Empleado empleado, Restaurante restaurante) {
		this.id = id;
		this.empleado = empleado;
		this.restaurante = restaurante;
	}

	public RestEmpleado(RestEmpleadoId id, String funcion) {
		this.id = id;
		this.empleado = empleado;
		this.funcion = funcion;
		this.restaurante = restaurante;
	}

	public RestEmpleadoId getId() {
		return this.id;
	}

	public void setId(RestEmpleadoId id) {
		this.id = id;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getFuncion() {
		return this.funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public Restaurante getRestaurante() {
		return this.restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

}
