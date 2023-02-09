package daos;

import java.util.List;

import org.hibernate.Session;

import models.Empleado;
import models.HibernateUtil;
import models.Restaurante;

public class RestauranteDAO extends ComunesDAO<Restaurante> {

	private Session sesion;

	public RestauranteDAO(Session session) {
		super(session);
		this.sesion = session;
	}

	public Empleado buscarporDNI(String dni) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return (Empleado) sesion.createQuery("FROM Empleado e WHERE e.dniEmpleado ='" + dni + "'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> restaurantePorNombre(String valor) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery(
				"FROM Restaurante r JOIN Localidad l ON r.localidad = l.codLocalidad WHERE l.nombre = '" + valor + "'")
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> restauranteEmpleados() {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery(
				"SELECT a,b FROM RestEmpleado r JOIN Restaurante a ON r.id.restaurante.codRest = a.codRest "
						+ "JOIN Empleado b ON r.id.empleado.dniEmpleado = b.dniEmpleado ")
				.list();

	}

	public static void main(String[] args) {
		Session sesion = HibernateUtil.getSession();

		RestauranteDAO dao = new RestauranteDAO(sesion);
		List<Object[]> lista1 = dao.restaurantePorNombre("Málaga");
		System.out.println("**********Consulta 2**********");
		for (Object[] objects : lista1) {
			Restaurante res = (Restaurante) objects[0];
			System.out.println("-------------------------------------");
			System.out.println(res.toString());
			System.out.println("-------------------------------------");
		}

		List<Object[]> lista = dao.restauranteEmpleados();
		System.out.println("**********Consulta 3**********");
		for (Object[] restaurante : lista) {
			Restaurante res = (Restaurante) restaurante[0];
			Empleado emple = (Empleado) restaurante[1];
			System.out.println("-------------------------------------");
			System.out.println(res.toString() + res.getEmpleados().toString() + "\n" + emple.toString());
			System.out.println("-------------------------------------");
		}

	}

}
