package hlc.ud04.appsec.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hlc.ud04.appsec.core.GestorPersistenciaException;
import hlc.ud04.appsec.core.GestorPersistenciaPasswordIntefaz;

public class GestorPesistenciaPassword implements GestorPersistenciaPasswordIntefaz {

	private String database;
	private final static String JDBC_PREFIX = "jdbc:sqlite:";

	public GestorPesistenciaPassword(String database) {
		this.database = database;
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_PREFIX + database);
	}

	@Override
	public String consultarNombre(String nombre) {

		Connection conn = null;
		ResultSet rs = null;
		String resultado = null;
		try {
			conn = getConnection();
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("SELECT user FROM users WHERE user = '" + nombre + "'");

			while (rs.next()) {
				resultado = rs.getString("user");
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

	@Override
	public String consultarPassword(String password) {
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

	@Override
	public Long consultarId(String nombre, String password) {
		Connection conn = null;
		ResultSet rs = null;
		Long resultado = null;
		try {
			conn = getConnection();
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(
					"SELECT idUser FROM users WHERE user = '" + nombre + "' AND passwd = '" + password + "'");

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
