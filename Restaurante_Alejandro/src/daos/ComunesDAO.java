package daos;

import java.util.List;

import org.hibernate.Session;

import interfaces.ComunesIn;

public class ComunesDAO<T> implements ComunesIn<T> {

	/** Sesión de conexión a BD */
	protected static Session session;

	public ComunesDAO(Session session) {
		super();

		this.session = session;
	}

	/**
	 * Metodo insert, que inserta un objeto en la base de datos
	 */
	@Override
	public void insert(final T paramT) {

		// Verificación de sesión abierta
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		// Inserta el objeto en la base de datos
		session.save(paramT);

		// Commit
		session.getTransaction().commit();

	}

	/**
	 * Metodo que modifica un objeto de la base de datos
	 */
	@Override
	public void update(final T paramT) {

		// Verificación de sesión abierta
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		// Updatea el objeto en la base de datos
		session.update(paramT);

		// Commit
		session.getTransaction().commit();
	}

	/**
	 * Metodo que elimina un objeto de la base de datos
	 */
	@Override
	public void delete(final T paramT) {

		// Verificación de sesión abierta
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		// Elimina el objeto
		session.delete(paramT);

		// Commit
		session.getTransaction().commit();
	}

	/**
	 * Metodo que lista todos los objetos de la base de datos
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> searchAll(String objeto) {
		// Verificación de sesión abierta
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		// Devuelve todos los objetos
		return session.createQuery("FROM " + objeto + "").list();
	}

}
