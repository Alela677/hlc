package interfaces;

import java.util.List;

public interface ComunesIn<T> {

	/**
	 * Inserta un objeto a la base de datos
	 * 
	 * @param paramT Objeto que queremos insertar en la base de datos
	 */
	public void insert(final T paramT);

	/**
	 * Actualiza un objeto de la base de datos
	 * 
	 * @param paramT Objeto que queremos actualizar
	 */
	public void update(final T paramT);

	/**
	 * Elimina un objeto de la base de datos
	 * 
	 * @param paramT Objeto de la base de datos que queremos eliminar
	 */
	public void delete(final T paramT);

	/**
	 * Metodo que lista todos los objetos de la base de datos
	 */
	List<T> searchAll(String objeto);
}
