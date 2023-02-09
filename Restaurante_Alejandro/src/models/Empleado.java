package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado implements java.io.Serializable {

	@Id
	@Column(name = "dni_empleado")
	private String dniEmpleado;

	@Column
	private String nombre;
	@Column
	private String domicilio;
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	
	@OneToMany(mappedBy = "id.empleado",cascade = CascadeType.ALL)
	private Set<RestEmpleado> restaurante = new HashSet<>(0);	
	
	public Set<RestEmpleado> getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Set<RestEmpleado> restaurante) {
		this.restaurante = restaurante;
	}

	public Empleado() {
	}

	public Empleado(String dniEmpleado, String nombre, Date fechaNacimiento) {
		this.dniEmpleado = dniEmpleado;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Empleado(String dniEmpleado, String nombre, String domicilio, Date fechaNacimiento,
			Set<RestEmpleado> restEmpleados) {
		this.dniEmpleado = dniEmpleado;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.fechaNacimiento = fechaNacimiento;

	}

	public String getDni_empleado() {
		return this.dniEmpleado;
	}

	public void setDni_empleado(String dniEmpleado) {
		this.dniEmpleado = dniEmpleado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dniEmpleado, domicilio, fechaNacimiento, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(dniEmpleado, other.dniEmpleado) && Objects.equals(domicilio, other.domicilio)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(nombre, other.nombre);
	}
		
	@Override
	public String toString() {
		return "Empleado [dniEmpleado=" + dniEmpleado + ", nombre=" + nombre + ", domicilio=" + domicilio
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
}
