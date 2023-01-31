package hlc.ud04.appsec.sampleapp.password;

import hlc.ud04.appsec.core.Clientes;
import hlc.ud04.appsec.core.GestorPersistencia;

import hlc.ud04.appsec.interfaz.Interfaz;
import hlc.ud04.appsec.interfaz.consola.InterfazConsola;
import hlc.ud04.appsec.persistencia.GestorPersistenciaSqlite;
import hlc.ud04.appsec.sampleapp.controlacceso.ControlAccesoSimple;
import hlc.ud04.appsec.sampleapp.controlacceso.password.ControlAccesoPassword;
import hlc.ud04.appsec.sampleapp.seguridad.password.SistemaSeguridadPassword;

import hlc.ud04.appsec.seguridad.autenticator.password.AutenticadorPassword;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;

public class MainApp {

	// Ruta a la base de datos en disco
	private static final String DATABASE_PATH = "db/base.db";

	public static void main(String[] args) {
		// Creamos el gestor de persistencia SQLite
		GestorPersistencia gestor = new GestorPersistenciaSqlite(DATABASE_PATH);

		// Y lo inyectamos en el core
		Clientes clientes = new Clientes(gestor);
		// Creamos interfaz de usuario de tipo consola
		SistemaSeguridad sistemaSeguridad = new SistemaSeguridadPassword(new AutenticadorPassword(),
				new ControlAccesoPassword());
		Interfaz interfaz = new InterfazConsola(clientes, sistemaSeguridad);
		// Lanzamos la interfaz de usuario
		interfaz.run();
	}

}
