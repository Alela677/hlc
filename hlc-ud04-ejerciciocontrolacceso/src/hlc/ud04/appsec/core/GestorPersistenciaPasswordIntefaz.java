package hlc.ud04.appsec.core;

import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

public interface GestorPersistenciaPasswordIntefaz {

	String consultarNombre(String nombre);

	String consultarPassword(String password);

	Long consultarId(String nombre, String password);

	int permisoLectura(Long id);

	int permisoEscritura(Long id);

	Usuario comprobarUsuario(Long uid);
}
