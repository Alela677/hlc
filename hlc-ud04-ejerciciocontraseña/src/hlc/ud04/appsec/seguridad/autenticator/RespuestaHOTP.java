package hlc.ud04.appsec.seguridad.autenticator;

import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;

public class RespuestaHOTP implements RespuestaDesafio {

	private long codigo;

	public RespuestaHOTP(long codigo) {
		super();
		this.codigo = codigo;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

}
