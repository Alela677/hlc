package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurante")
public class Restaurante implements java.io.Serializable {

	@Id
	@Column(name = "cod_rest")
	private String codRest;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cod_localidad")
	private Localidad localidad;

	@Column
	private String nombre;
	@Column(name = "licencia_fiscal")
	private String licenciaFiscal;

	@Column
	private String domicilio;
	@Column(name = "fecha_apertura")
	private Date fechaApertura;

	@Column
	private String horario;
	
	@OneToMany(mappedBy = "id.restaurante",cascade = CascadeType.ALL)
	private Set<RestEmpleado> empleados = new HashSet<>(0);	
	
	public Set<RestEmpleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Set<RestEmpleado> empleados) {
		this.empleados = empleados;
	}

	@OneToMany(mappedBy = "dniTitular", cascade = CascadeType.ALL)
	private Set<Titular> titulars = new HashSet<Titular>(0);

	@OneToMany(mappedBy = "codArticulo", cascade = CascadeType.ALL)
	private Set<Existencias> existenciases = new HashSet<Existencias>(0);

	public Restaurante() {
	}

	public Restaurante(String codRest, String nombre, String licenciaFiscal, Date fechaApertura, String horario) {
		this.codRest = codRest;
		this.nombre = nombre;
		this.licenciaFiscal = licenciaFiscal;
		this.fechaApertura = fechaApertura;
		this.horario = horario;
	}

	public Restaurante(String codRest, Localidad localidad, String nombre, String licenciaFiscal, String domicilio,
			Date fechaApertura, String horario, Set<RestEmpleado> restEmpleados, Set<Titular> titulars,
			Set<Existencias> existenciases) {
		this.codRest = codRest;
		this.localidad = localidad;
		this.nombre = nombre;
		this.licenciaFiscal = licenciaFiscal;
		this.domicilio = domicilio;
		this.fechaApertura = fechaApertura;
		this.horario = horario;
		this.titulars = titulars;
		this.existenciases = existenciases;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codRest, domicilio, existenciases, fechaApertura, horario, licenciaFiscal, localidad,
				nombre, titulars);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurante other = (Restaurante) obj;
		return Objects.equals(codRest, other.codRest) && Objects.equals(domicilio, other.domicilio)
				&& Objects.equals(existenciases, other.existenciases)
				&& Objects.equals(fechaApertura, other.fechaApertura) && Objects.equals(horario, other.horario)
				&& Objects.equals(licenciaFiscal, other.licenciaFiscal) && Objects.equals(localidad, other.localidad)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(titulars, other.titulars);
	}

	public String getCodRest() {
		return this.codRest;
	}

	public void setCodRest(String codRest) {
		this.codRest = codRest;
	}

	public Localidad getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLicenciaFiscal() {
		return this.licenciaFiscal;
	}

	public void setLicenciaFiscal(String licenciaFiscal) {
		this.licenciaFiscal = licenciaFiscal;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFechaApertura() {
		return this.fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public String getHorario() {
		return this.horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Set getTitulars() {
		return this.titulars;
	}

	public void setTitulars(Set<Titular> titulars) {
		this.titulars = titulars;
	}

	public Set getExistenciases() {
		return this.existenciases;
	}

	public void setExistenciases(Set<Existencias> existenciases) {
		this.existenciases = existenciases;
	}

	@Override
	public String toString() {
		return "Restaurante [codRest=" + codRest + " , nombre = " + nombre + "]";
	}

}
