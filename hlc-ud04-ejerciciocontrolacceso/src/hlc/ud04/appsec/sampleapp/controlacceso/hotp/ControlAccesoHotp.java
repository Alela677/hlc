package hlc.ud04.appsec.sampleapp.controlacceso.hotp;

import java.io.File;

import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;

public class ControlAccesoHotp implements ControlAcceso {

	final static String FICHERO_PERMISOS = "permisoshotp/permisosUsuarios";

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {

		File fichero = new File(FICHERO_PERMISOS);

		if (fichero.exists()) {

			LeeFicheroPermisos permisos = new LeeFicheroPermisos(fichero, usuario.getUid());

			if (permisos.getId() != null && permisos.getPermisos() != null) {

				if (operacion == Operacion.ESCRITURA && permisos.getPermisos().contains("w")) {
					return true;
				} else if (operacion == Operacion.LECTURA && permisos.getPermisos().contains("r")) {
					return true;
				} else if (operacion == Operacion.ESCRITURA && !permisos.getPermisos().contains("w")) {
					return false;
				} else if (operacion == Operacion.LECTURA && !permisos.getPermisos().contains("r")) {
					return false;
				}
			} else {
				System.err.println("El usuario no existe");
				return false;
			}
		} else {
			System.err.println("El fichero no existe");
			return false;
		}
		return false;
	}

}
