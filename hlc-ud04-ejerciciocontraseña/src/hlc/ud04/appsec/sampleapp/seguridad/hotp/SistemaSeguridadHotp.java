package hlc.ud04.appsec.sampleapp.seguridad.hotp;

import java.util.Scanner;

import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.autenticator.hotp.DesafioHotp;
import hlc.ud04.appsec.seguridad.autenticator.hotp.RespuestaHotp;
import hlc.ud04.appsec.seguridad.autenticator.password.RespuestaDesafioPassword;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;

public class SistemaSeguridadHotp implements SistemaSeguridad {

	private Autenticador autenticador;
	private ControlAcceso controlAcceso;

	public SistemaSeguridadHotp(Autenticador autenticador, ControlAcceso controlAcceso) {
		super();
		this.autenticador = autenticador;
		this.controlAcceso = controlAcceso;
	}

	@Override
	public Usuario autentica() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce un nombre de usuario");
		String nombreUsuario = sc.nextLine(); // Nombre que introduce el usuario
		
		// Iniciamos un desafio con el valor introducido por el usuario
		DesafioHotp desafio = (DesafioHotp) autenticador.iniciaAutenticacion(nombreUsuario);
		
		System.out.println("Escribe el codigo generado");
		long codigo = sc.nextLong(); // Codigo que introduce el usuario
		
		// Comprobamos si la autenticacion es correcta 
		return autenticador.finalizaAutenticacion(desafio, new RespuestaHotp(codigo));
	}	

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {
		// TODO Auto-generated method stub
		return controlAcceso.estaPermitido(usuario, operacion, recurso);

	}

}
