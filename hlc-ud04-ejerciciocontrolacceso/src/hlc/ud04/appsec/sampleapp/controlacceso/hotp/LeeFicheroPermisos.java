package hlc.ud04.appsec.sampleapp.controlacceso.hotp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeeFicheroPermisos {

	private Long id;
	private String permisos;

	public LeeFicheroPermisos(File fichero, Long uid) {

		try {
			
			BufferedReader br = new BufferedReader(new FileReader(fichero));
			String linea = br.readLine();

			while (linea != null) {

				if (linea.contains(Long.toString(uid))) {
					id = Long.parseLong(linea.split(":")[0]);
					permisos = linea.substring(linea.indexOf(":") +1);
				}
				linea = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("No se encontro el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("No se puedo leer el archivo");
			e.printStackTrace();
		}
	}

	public Long getId() {
		return id;
	}

	public String getPermisos() {
		return permisos;
	}

}
