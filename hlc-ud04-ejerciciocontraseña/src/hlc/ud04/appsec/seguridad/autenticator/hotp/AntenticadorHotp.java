package hlc.ud04.appsec.seguridad.autenticator.hotp;

import hlc.ud04.appsec.persistencia.hotp.GestorPersistenciaHotp;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.hotp.GeneradorHOTP;

public class AntenticadorHotp implements Autenticador {

	final static String BBDD = "db/base.db";

	public AntenticadorHotp() {

	}
	
	/**
	 * Devuelve un desafio que almacena el nombre de usuario
	 */
	@Override
	public Desafio iniciaAutenticacion(String identificador) {

		return new DesafioHotp(identificador);
	}
	
	/**
	 * Finaliza la autentificacion comprobando los valores introducidos en la base de datos
	 * Si son correcto devolvera un usario con unos permisos especificos segun su id 
	 */
	@Override
	public Usuario finalizaAutenticacion(Desafio desafio, RespuestaDesafio respuesta) {
		DesafioHotp desafioHOTP = (DesafioHotp) desafio;
		RespuestaHotp respuestaHOTP = (RespuestaHotp) respuesta;
		GestorPersistenciaHotp gestor = new GestorPersistenciaHotp(BBDD);

		String secreto = gestor.traerSecretoDelUsuario(desafioHOTP.getNombreUsuario());
		long codigo = generarCodigo(secreto);
		long id = gestor.traerIdDelUsuario(desafioHOTP.getNombreUsuario(), secreto);

		if (secreto != null) {
			if (esCorrecto(codigo, String.valueOf(respuestaHOTP.getCodigo()))) {
				return new Usuario(id);
			}
		}
		return null;
	}
	
	/**
	 * Genera el codigo de comprobacion 
	 * @param secreto Secreto que le pasamos del usuario 
	 * @return codigo 
	 */
	private static long generarCodigo(String secreto) {

		GeneradorHOTP generador = new GeneradorHOTP();
		long contador = System.currentTimeMillis() / 1000;
		long codigo = Long.parseLong(generador.genera(secreto, contador / 30));

		return codigo;
	}
	
	/**
	 * Comprueba si el codigo introducido por el usuario es el mismo que ha generado 
	 * el autenticador
	 * @param codigo Codigo generado
	 * @param respuesta Respuesta del usuario
	 * @return true si es correcto
	 */
	private static boolean esCorrecto(long codigo, String respuesta) {
		if (codigo == Long.parseLong(respuesta)) {
			return true;
		}
		return false;

	}

}
