package hlc.ud04.appsec.sampleapp.seguridad.password;

import java.util.Scanner;

import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.autenticator.password.AutenticadorPassword;
import hlc.ud04.appsec.seguridad.autenticator.password.DesafioPassword;
import hlc.ud04.appsec.seguridad.autenticator.password.RespuestaDesafioPassword;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;

public class SistemaSeguridadPassword implements SistemaSeguridad {

	private Autenticador autenticador;
	private ControlAcceso controlAcceso;

	public SistemaSeguridadPassword(Autenticador autenticador, ControlAcceso controlAcceso) {
		super();
		this.autenticador = autenticador;
		this.controlAcceso = controlAcceso;
	}

	@Override
	public Usuario autentica() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduzca el nombre de usuario");
		String nombreUsuario = sc.nextLine();// Nombre que introduce el usuario
		
		// Iniciamos un desafio con el valor introducido por el usuario
		DesafioPassword desafio = (DesafioPassword) autenticador.iniciaAutenticacion(nombreUsuario);

		System.out.println("Introduzca la contraseña");
		String contraseña = sc.nextLine();// Password que introduce el usuario
		
		// Comprobamos si la autenticacion es correcta 
		return autenticador.finalizaAutenticacion(desafio, new RespuestaDesafioPassword(contraseña));
	}

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {

		return controlAcceso.estaPermitido(usuario, operacion, recurso);
	}

}
