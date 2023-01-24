package hlc.ud04.appsec.sampleapp.seguridad;

import java.util.Scanner;

import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.autenticator.DesafioHOTP;
import hlc.ud04.appsec.seguridad.autenticator.RespuestaDesafioPassword;
import hlc.ud04.appsec.seguridad.autenticator.RespuestaHOTP;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;

public class SistemaSeguridadTOTP implements SistemaSeguridad {

	private Autenticador autenticador;
	private ControlAcceso controlAcceso;

	public SistemaSeguridadTOTP(Autenticador autenticador, ControlAcceso controlAcceso) {
		super();
		this.autenticador = autenticador;
		this.controlAcceso = controlAcceso;
	}

	@Override
	public Usuario autentica() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce un nombre de usuario");
		String nombreUsuario = sc.nextLine();
		DesafioHOTP desafio = (DesafioHOTP) autenticador.iniciaAutenticacion(nombreUsuario);

		System.out.println("Escribe el codigo generado");
		String codigo = sc.nextLine();
		return autenticador.finalizaAutenticacion(desafio, new RespuestaHOTP(Long.parseLong(codigo)));
	}

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {
		// TODO Auto-generated method stub
		return controlAcceso.estaPermitido(usuario, operacion, recurso);

	}

}
