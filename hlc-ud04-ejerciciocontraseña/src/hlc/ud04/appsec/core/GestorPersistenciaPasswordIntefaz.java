package hlc.ud04.appsec.core;

public interface GestorPersistenciaPasswordIntefaz {

	String consultarNombre(String nombre);

	String consultarPassword(String password);
	
	Long consultarId(String nombre , String password);
	
	int consultaLectura(Long id);
	
	int consultaEscritura(Long id);
}
