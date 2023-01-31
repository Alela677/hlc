package hlc.ud04.appsec.seguridad.autenticator.password;

import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;

public class RespuestaDesafioPassword implements RespuestaDesafio {

	private String password;

	public RespuestaDesafioPassword(String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
