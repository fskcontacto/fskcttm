package turismo.archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import turismo.sistema.Atraccion;

public class ArchivoAtraccion {

	private String nombre;

	public ArchivoAtraccion(String nombre) {
		super();
		this.nombre = nombre;
	}

	public List<Atraccion> leer() throws FileNotFoundException {
		File archivo = new File(this.nombre + ".in");
		Scanner lector = new Scanner(archivo, "utf-8").useDelimiter("\n").useLocale(Locale.US);

		List<Atraccion> atracciones = new ArrayList<Atraccion>();

		String nombre;
		String tipo;
		double costo;
		double duracion;
		int cupo;

		while (lector.hasNextLine()) {
			nombre = lector.next();
			costo = lector.nextDouble();
			duracion = lector.nextDouble();
			cupo = lector.nextInt();
			tipo = lector.next();
			atracciones.add(new Atraccion(nombre, tipo, costo, duracion, cupo));
		}

		lector.close();
		return atracciones;
	}

}
