package hlc.ud04.appsec.seguridad.autenticator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hlc.ud04.appsec.core.GestorPersistenciaException;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

public class AutenticadorPassword implements Autenticador {
	private static String database = "base.db";
	private static final String JDBC_PREFIX = "jdbc:sqlite:";
	private static DatosUsuario[] USUARIOS = null;

	public AutenticadorPassword() {
		iniciaUsuarios();
	}

	@Override
	public Desafio iniciaAutenticacion(String identificador) {
		return new DesafioPassword(identificador);
	}

	@Override
	public Usuario finalizaAutenticacion(Desafio desafio, RespuestaDesafio respuesta) {
		DesafioPassword desafioD = (DesafioPassword) desafio;
		RespuestaDesafioPassword respuestaD = (RespuestaDesafioPassword) respuesta;

		String usuario = consultaUsuario(desafioD.getNombreUsuario());
		String password = consultaPassword(respuestaD.getPassword());

		if (usuario != null && password != null) {

			for (int i = 0; i < USUARIOS.length;) {
				return new Usuario(USUARIOS[i].uid);

			}
		}

		return null;
	}

	private void iniciaUsuarios() {
		USUARIOS = new DatosUsuario[4];
		USUARIOS[0] = new DatosUsuario("usuario1", 1000, 1);
		USUARIOS[1] = new DatosUsuario("usuario2", 2000, 2);
		USUARIOS[2] = new DatosUsuario("usuario3", 3000, 3);
		USUARIOS[3] = new DatosUsuario("usuario4", 4000, 4);
	}

	class DatosUsuario {
		String nombre;
		int clave;
		long uid;

		DatosUsuario(String nombre, int clave, long uid) {
			this.nombre = nombre;
			this.clave = clave;
			this.uid = uid;
		}
	}

	private static String consultaUsuario(String nombreUsuario) {

		Connection conn = null;
		ResultSet rs = null;
		String resultado = null;
		try {
			conn = getConnection();
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("SELECT user FROM users WHERE user = '" + nombreUsuario + "'");

			while (rs.next()) {
				resultado = rs.getString("user ");
			}
			return resultado;
		} catch (SQLException e) {
			throw new GestorPersistenciaException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	private static String consultaPassword(String password) {
		Connection conn = null;
		ResultSet rs = null;
		String resultado = null;
		try {
			conn = getConnection();
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("SELECT passwd FROM users WHERE passwd = '" + password + "'");

			while (rs.next()) {
				resultado = rs.getString("passwd");
			}
			return resultado;
		} catch (SQLException e) {
			throw new GestorPersistenciaException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_PREFIX + database);
	}

}
