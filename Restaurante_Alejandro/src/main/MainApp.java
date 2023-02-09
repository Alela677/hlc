package main;

import java.util.Date;

import org.hibernate.Session;

import models.HibernateUtil;
import models.Localidad;
import models.Restaurante;

public class MainApp {
	public static void main(String[] args) {

		Session sesion = HibernateUtil.getSession();

		Localidad n = new Localidad(812, "Málaga");
		Date fecha = new Date("2022/12/23");
		Restaurante rest = new Restaurante("8523", "Rest1", "IJ48721396", fecha, "L-D 10:00 a 21:00");
		Restaurante rest2 = new Restaurante("8522", "Rest1", "IJ48721394", fecha, "L-D 10:00 a 21:00");
		rest.setLocalidad(n);
		rest2.setLocalidad(n);

		sesion.beginTransaction();

		sesion.save(n);
		sesion.save(rest);
		sesion.save(rest2);

		sesion.getTransaction().commit();
		sesion.close();

	}
}
