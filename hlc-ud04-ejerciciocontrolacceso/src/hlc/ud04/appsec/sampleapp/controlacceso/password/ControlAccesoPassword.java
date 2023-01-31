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
		Usuario comprobarExiste = gestor.comprobarUsuario(usuario.getUid());

		if (comprobarExiste != null) {

			int permisoLectura = gestor.permisoLectura(comprobarExiste.getUid());
			int permisoEscritura = gestor.permisoEscritura(comprobarExiste.getUid());

			if (operacion == Operacion.LECTURA && permisoLectura == 1) {
				return true;
			} else if (operacion == Operacion.ESCRITURA && permisoEscritura == 1) {
				return true;
			} else if (operacion == Operacion.LECTURA && permisoLectura == 0) {
				return false;
			} else if (operacion == Operacion.ESCRITURA && permisoEscritura == 0) {
				return false;
			}

		} else {
			System.err.println("El usuario no existe");
			return false;
		}

		return false;
	}

}
