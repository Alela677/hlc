package hlc.ud04.appsec.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hlc.ud04.appsec.core.Cliente;
import hlc.ud04.appsec.core.GestorPersistenciaException;
import hlc.ud04.appsec.core.GestorPersistenciaHOTPIntefaz;

public class GestorPersistenciaHOTP implements GestorPersistenciaHOTPIntefaz {

	private String database;
	private final static String JDBC_PREFIX = "jdbc:sqlite:";

	public GestorPersistenciaHOTP(String database) {
		this.database = database;
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_PREFIX + database);
	}

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

}
