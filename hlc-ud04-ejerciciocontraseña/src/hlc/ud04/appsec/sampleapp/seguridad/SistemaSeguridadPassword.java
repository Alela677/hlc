package hlc.ud04.appsec.sampleapp.seguridad;

import java.util.Scanner;

import hlc.ud04.appsec.interfaz.consola.InterfazConsola;
import hlc.ud04.appsec.persistencia.GestorPersistenciaSqlite;
import hlc.ud04.appsec.sampleapp.controlacceso.DesafioPassword;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.autenticator.AutenticadorPassword;
import hlc.ud04.appsec.seguridad.autenticator.RespuestaDesafioPassword;
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
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el nombre de usuario");
		String nombreUsuario = sc.nextLine();

		hlc.ud04.appsec.seguridad.autenticator.DesafioPassword desafio = (hlc.ud04.appsec.seguridad.autenticator.DesafioPassword) autenticador
				.iniciaAutenticacion(nombreUsuario);

		System.out.println("Introduzca la contraseña");
		String contraseña = sc.nextLine();

		return autenticador.finalizaAutenticacion(desafio,new RespuestaDesafioPassword(contraseña));
	}

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {

		return controlAcceso.estaPermitido(usuario, operacion, recurso);
	}

}
