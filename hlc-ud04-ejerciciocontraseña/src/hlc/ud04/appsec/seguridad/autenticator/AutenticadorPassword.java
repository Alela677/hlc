package hlc.ud04.appsec.seguridad.autenticator;

import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

public class AutenticadorPassword implements Autenticador {

	private static DatosUsuario[] USUARIOS = null;

	public AutenticadorPassword() {
		iniciaUsuarios();
	}

	@Override
	public Desafio iniciaAutenticacion(String identificador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario finalizaAutenticacion(hlc.ud04.appsec.core.Usuario usuario) {
		// TODO Auto-generated method stub
		if (usuario != null) {
			for (int i = 0; i < USUARIOS.length;) {
				return new Usuario(USUARIOS[i].uid);
			}

		} else {
			return null;
		}
		return null;
	}

	private void iniciaUsuarios() {
		USUARIOS = new DatosUsuario[4];
		USUARIOS[0] = new DatosUsuario("usuario1", 1000, 1);
		USUARIOS[1] = new DatosUsuario("usuario2", 2000, 2);
		USUARIOS[2] = new DatosUsuario("usuario3", 3000, 3);
		USUARIOS[3] = new DatosUsuario("usuario4", 4000, 4);
	}

	class DatosUsuario {
		String nombre;
		int clave;
		long uid;

		DatosUsuario(String nombre, int clave, long uid) {
			this.nombre = nombre;
			this.clave = clave;
			this.uid = uid;
		}
	}
}
