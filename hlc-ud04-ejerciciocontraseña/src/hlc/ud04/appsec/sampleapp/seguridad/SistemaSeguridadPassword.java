package hlc.ud04.appsec.sampleapp.seguridad;

import java.util.List;
import java.util.Scanner;

import hlc.ud04.appsec.core.GestorPersistencia;
import hlc.ud04.appsec.persistencia.GestorPersistenciaSqlite;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;

public class SistemaSeguridadPassword implements SistemaSeguridad {

	private Autenticador autenticador;
	private ControlAcceso controlAcceso;

	@Override
	public Usuario autentica() {
		GestorPersistenciaSqlite gestor = new GestorPersistenciaSqlite("db/base.db");
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduzca la contraseña");
		String contraseña = sc.nextLine();

		List<hlc.ud04.appsec.core.Usuario> users = gestor.obtenerUsuarios();

		for (hlc.ud04.appsec.core.Usuario usuario : users) {

			if (usuario.getPassword().equals(contraseña)) {
				System.out.println("usuario correcto");
				autenticador.finalizaAutenticacion(null, null);
			} else {
				System.out.println("usuario incorrecto");
			}
		}

		return null;
	}

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {
		// TODO Auto-generated method stub
		return controlAcceso.estaPermitido(usuario, operacion, recurso);
	}

}
