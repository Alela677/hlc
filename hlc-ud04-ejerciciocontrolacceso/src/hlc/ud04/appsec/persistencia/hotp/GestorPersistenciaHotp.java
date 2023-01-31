package hlc.ud04.appsec.persistencia.hotp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hlc.ud04.appsec.core.Cliente;
import hlc.ud04.appsec.core.GestorPersistenciaException;
import hlc.ud04.appsec.core.GestorPersistenciaHotpIntefaz;

public class GestorPersistenciaHotp implements GestorPersistenciaHotpIntefaz {
	// Ruta del archivo de la base de datos
	private String database;
	// Prefifo que utilizamos para la conexiona al base de datos
	private final static String JDBC_PREFIX = "jdbc:sqlite:";
	
	/**
	 * Contructor del gestor 
	 * @param database Ruta del archivo 
	 */
	public GestorPersistenciaHotp(String database) {
		this.database = database;
	}

	/**
	 * Conexion a la base de datos
	 * @return 
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_PREFIX + database);
	}
	
	/**
	 * Consuta el secreto de nombre de usuario introducido
	 */
	@Override
	public String traerSecretoDelUsuario(String nombre) {
		Connection conn = null;
		ResultSet rs = null;
		String secreto = null;
		try {
			conn = getConnection();
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("SELECT secreto FROM userHOTP WHERE user = '" + nombre + "'");

			if (rs.next()) {
				secreto = rs.getString("secreto");
			}
			return secreto;
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
	
	/**
	 * Consutal el identificador del usuario del nombre y secreto que introducimos
	 */
	@Override
	public Long traerIdDelUsuario(String nombre, String secreto) {
		Connection conn = null;
		ResultSet rs = null;
		Long resultado = null;
		try {
			conn = getConnection();
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("SELECT idUser FROM userHOTP WHERE user = '" + nombre + "'");

			while (rs.next()) {
				resultado = rs.getLong("idUser");
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
}
