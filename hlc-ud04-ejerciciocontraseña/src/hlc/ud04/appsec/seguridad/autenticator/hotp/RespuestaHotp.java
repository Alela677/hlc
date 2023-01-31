package hlc.ud04.appsec.seguridad.autenticator.hotp;

import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;

public class RespuestaHotp implements RespuestaDesafio {

	private long codigo;

	public RespuestaHotp(long codigo) {
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
