package hlc.ud04.appsec.sampleapp.seguridad;

import java.util.Scanner;

import hlc.ud04.appsec.interfaz.consola.InterfazConsola;
import hlc.ud04.appsec.persistencia.GestorPersistenciaSqlite;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.autenticator.AutenticadorPassword;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;

public class SistemaSeguridadPassword implements SistemaSeguridad {

	private Autenticador autenticador = new AutenticadorPassword();
	private ControlAcceso controlAcceso;

	
	public SistemaSeguridadPassword(ControlAcceso controlAcceso) {
		super();
		
		this.controlAcceso = controlAcceso;
	}

	@Override
	public Usuario autentica() {
		GestorPersistenciaSqlite gestor = new GestorPersistenciaSqlite("db/base.db");
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduzca la contraseña");
		String contraseña = sc.nextLine();

		hlc.ud04.appsec.core.Usuario usuario = gestor.obtenerUsuariosPassword(contraseña);

		return autenticador.finalizaAutenticacion(usuario);
	}

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {
		
		return controlAcceso.estaPermitido(usuario, operacion, recurso);
	}

}
