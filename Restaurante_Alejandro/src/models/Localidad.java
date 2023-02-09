package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "localidad")
public class Localidad implements java.io.Serializable {

	@Id
	@Column(name = "cod_localidad")
	private int codLocalidad;

	@Column
	private String nombre;

	@OneToMany(mappedBy = "codRest", cascade = CascadeType.ALL)
	private Set<Restaurante> restaurantes = new HashSet<Restaurante>(0);

	public Localidad() {
	}

	public Localidad(int codLocalidad, String nombre) {
		this.codLocalidad = codLocalidad;
		this.nombre = nombre;
	}

	public Localidad(int codLocalidad, String nombre, Set<Restaurante> restaurantes) {
		this.codLocalidad = codLocalidad;
		this.nombre = nombre;
		this.restaurantes = restaurantes;
	}

	public int getCodLocalidad() {
		return this.codLocalidad;
	}

	public void setCodLocalidad(int codLocalidad) {
		this.codLocalidad = codLocalidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getRestaurantes() {
		return this.restaurantes;
	}

	public void setRestaurantes(Set<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	@Override
	public String toString() {
		return "Localidad [codLocalidad=" + codLocalidad + ", nombre=" + nombre + "]";
	}
	
	

}
