package hlc.ud04.appsec.seguridad.autenticator;

import hlc.ud04.appsec.persistencia.GestorPersistenciaHOTP;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.hotp.GeneradorHOTP;

public class AntenticadorHOTP implements Autenticador {

	public AntenticadorHOTP() {

	}

	@Override
	public Desafio iniciaAutenticacion(String identificador) {

		return new DesafioHOTP(identificador);
	}

	@Override
	public Usuario finalizaAutenticacion(Desafio desafio, RespuestaDesafio respuesta) {
		DesafioHOTP desafioHOTP = (DesafioHOTP) desafio;
		RespuestaHOTP respuestaHOTP = (RespuestaHOTP) respuesta;
		GestorPersistenciaHOTP gestor = new GestorPersistenciaHOTP("db/base.db");

		String secreto = gestor.traerSecretoDelUsuario(desafioHOTP.getNombreUsuario());
		long codigo = generarCodigo(secreto);

		if (esCorrecto(codigo, String.valueOf(respuestaHOTP.getCodigo()))) {
			return new Usuario(1);
		}

		return null;
	}

	private static long generarCodigo(String secreto) {

		GeneradorHOTP generador = new GeneradorHOTP();
		long contador = System.currentTimeMillis() / 1000;
		long codigo = Long.parseLong(generador.genera(secreto, contador / 30));

		return codigo;
	}

	private static boolean esCorrecto(long codigo, String respuesta) {
		if (codigo == Long.parseLong(respuesta)) {
			return true;
		}
		return false;

	}

}
