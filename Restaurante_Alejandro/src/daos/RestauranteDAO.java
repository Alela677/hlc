package daos;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import models.Empleado;
import models.HibernateUtil;
import models.Localidad;
import models.Restaurante;

public class RestauranteDAO extends ComunesDAO<Restaurante> {

	private Session sesion;

	public RestauranteDAO(Session session) {
		super(session);
		this.sesion = session;
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

		return sesion.createQuery("SELECT a,e FROM RestEmpleado r JOIN Restaurante a ON r.restaurante.codRest = a.codRest JOIN Empleado e ON r.empleado.dniEmpleado = e.dniEmpleado").list() ;

	}

	
	
	
	@SuppressWarnings("unchecked")
	public List<Restaurante> listaCampos(String nombre, String fecha , String horario){
		
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		
		
		return sesion.createQuery("FROM Restaurante r WHERE r."+nombre+" OR r.").list();
		
	} 
	
	
	public static void main(String[] args) {
		Session sesion = HibernateUtil.getSession();

		RestauranteDAO dao = new RestauranteDAO(sesion);

		List<Object[]> lista = dao.restauranteEmpleados();
		for (Object[] restaurante : lista) {
			Restaurante res = (Restaurante) restaurante[0];
			Empleado loc = (Empleado) restaurante[1];
			System.out.println(res.toString() + loc.toString());
		}
	}
}
