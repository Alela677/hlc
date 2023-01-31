package hlc.ud04.appsec.core;

public interface GestorPersistenciaHotpIntefaz {

	String traerSecretoDelUsuario(String nombre);
	
	Long traerIdDelUsuario(String nombre, String secreto);
}
