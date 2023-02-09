package main;

import java.util.Date;
import java.util.Set;

import org.hibernate.Session;

import daos.RestauranteDAO;
import models.Empleado;
import models.HibernateUtil;
import models.Localidad;
import models.RestEmpleado;
import models.RestEmpleadoId;
import models.Restaurante;

public class MainApp {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		
		
		Session sesion = HibernateUtil.getSession();
		RestauranteDAO gestorRestaurante = new RestauranteDAO(sesion);
		
		sesion.beginTransaction();
		
		
		Localidad localidad = new Localidad(812, "Málaga");
		sesion.save(localidad);
		
		
		Date fecha = new Date("2022/12/23");
		Restaurante restaurante1 = new Restaurante("8523", "Rest1", "IJ48721396", fecha, "L-D 10:00 a 21:00");
		Restaurante restaurante2 = new Restaurante("8522", "Rest1", "IJ48721394", fecha, "L-D 10:00 a 21:00");
		restaurante1.setLocalidad(localidad);
		restaurante2.setLocalidad(localidad);
		sesion.save(restaurante1);
		sesion.save(restaurante2);
		
		
		
		
		Empleado empleado = gestorRestaurante.buscarporDNI("1111111H") ;
		RestEmpleadoId iden = new RestEmpleadoId(empleado, restaurante1);
		RestEmpleado restempleado = new RestEmpleado(iden, "CAMARERO");
		
		sesion.save(restempleado);

		sesion.getTransaction().commit();
		sesion.close();

	}
}
