package hlc.ud04.appsec.seguridad.autenticator;

import hlc.ud04.appsec.seguridad.autenticacion.Desafio;

public class DesafioPassword implements Desafio {

	private String nombreUsuario;

	public DesafioPassword(String nombreUsuario) {
		super();
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}
