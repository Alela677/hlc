package hlc.ud04.appsec.seguridad.autenticator.hotp;

import hlc.ud04.appsec.seguridad.autenticacion.Desafio;

public class DesafioHotp implements Desafio {
	
	
	private String nombreUsuario;

	
	
	public DesafioHotp(String nombreUsuario) {
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
