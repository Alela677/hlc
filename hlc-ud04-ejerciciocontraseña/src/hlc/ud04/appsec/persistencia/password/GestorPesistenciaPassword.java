package hlc.ud04.appsec.persistencia.password;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hlc.ud04.appsec.core.GestorPersistenciaException;
import hlc.ud04.appsec.core.GestorPersistenciaPasswordIntefaz;

public class GestorPesistenciaPassword implements GestorPersistenciaPasswordIntefaz {

	// Ruta del archivo ccon la base de datos
	private String database;
	// Prefijo que utilizamos para la conexiona al base de datos
	private final static String JDBC_PREFIX = "jdbc:sqlite:";

	/**
	 * Contructor del gestor
	 * 
	 * @param database
	 */
	public GestorPesistenciaPassword(String database) {
		this.database = database;
	}

	/**
	 * Conexion a la base de datos
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_PREFIX + database);
	}

	/**
	 * Consulta si existe el nombre que introducimos en la base de datos
	 */
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

	/**
	 * Consulta si existe la password que introducimos en la base de datos
	 */
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

	/**
	 * Consulta el identificador del nombre y la password que introducimos en la
	 * base de datos
	 */
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

	@Override
	public int consultaLectura(Long id) {
		Connection conn = null;
		ResultSet rs = null;
		int resultado = 0;
		try {
			conn = getConnection();
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("SELECT lectura FROM permisosPassword WHERE idUsuario = " + id + "");

			while (rs.next()) {
				resultado = rs.getInt("lectura");
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
	public int consultaEscritura(Long id) {
		Connection conn = null;
		ResultSet rs = null;
		int resultado = 0;
		try {
			conn = getConnection();
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("SELECT escritura FROM permisosPassword WHERE idUsuario = " + id + "");

			while (rs.next()) {
				resultado = rs.getInt("escritura");
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
