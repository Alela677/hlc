package hlc.ud04.appsec.seguridad.autenticator.password;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hlc.ud04.appsec.core.GestorPersistenciaException;
import hlc.ud04.appsec.core.GestorPersistenciaPasswordIntefaz;
import hlc.ud04.appsec.persistencia.password.GestorPesistenciaPassword;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

public class AutenticadorPassword implements Autenticador {

	final static String BBDD = "db/base.db";

	public AutenticadorPassword() {

	}
	
	
	/**
	 * Devuelve un desafio que almacena el nombre de usuario
	 */
	@Override
	public Desafio iniciaAutenticacion(String identificador) {
		return new DesafioPassword(identificador);
	}
	
	
	/**
	 * Finaliza la autentificacion comprobando los valores introducidos en la base de datos
	 * Si son correcto devolvera un usuario con unos permisos especificos segun su id 
	 */
	@Override
	public Usuario finalizaAutenticacion(Desafio desafio, RespuestaDesafio respuesta) {
		DesafioPassword desafioD = (DesafioPassword) desafio;
		RespuestaDesafioPassword respuestaD = (RespuestaDesafioPassword) respuesta;
		GestorPesistenciaPassword gestor = new GestorPesistenciaPassword(BBDD);

		String usuario = gestor.consultarNombre(desafioD.getNombreUsuario());
		String password = gestor.consultarPassword(respuestaD.getPassword());
		Long uid = gestor.consultarId(usuario, password);

		if (usuario != null && password != null) {
			if (usuario.equals(usuario) && password.equals(password)) {
				System.out.println("Usuario correcto");
				return new Usuario(uid);

			} else {
				return null;
			}
		}
		return null;
	}

}
