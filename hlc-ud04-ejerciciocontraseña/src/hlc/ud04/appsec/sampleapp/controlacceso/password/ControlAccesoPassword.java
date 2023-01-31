package hlc.ud04.appsec.sampleapp.controlacceso.password;

import hlc.ud04.appsec.persistencia.password.GestorPesistenciaPassword;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;

public class ControlAccesoPassword implements ControlAcceso {

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {

		GestorPesistenciaPassword gestor = new GestorPesistenciaPassword("db/base.db");

		int permisoLectura = gestor.consultaLectura(usuario.getUid());
		int permisoEscritura = gestor.consultaEscritura(usuario.getUid());

		if (permisoEscritura == 1) {
			
			return true;
		
		} else if (permisoEscritura == 1 && permisoEscritura == 0) {
			return operacion == Operacion.ESCRITURA;
		} else if (permisoEscritura == 0 & permisoLectura == 1) {
			return operacion == Operacion.LECTURA;

		} else {
			return false;
		}

	}

}
