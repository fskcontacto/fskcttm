package turismo.archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import turismo.sistema.Atraccion;

public class ArchivoAtraccion {

	private String nombre;

	public ArchivoAtraccion(String nombre) {
		this.nombre = nombre;
	}

	public Map<String,Atraccion> leer() throws FileNotFoundException {
		
		File archivo = new File(this.nombre + ".in");
		try (Scanner lector = new Scanner(archivo, "utf-8").useDelimiter("\r\n").useLocale(Locale.ENGLISH)){
			Map<String,Atraccion> atracciones = new HashMap<>();

			String nombre;
			String tipo;
			double costo;
			double duracion;
			int cupo;
		

			while (lector.hasNext()) {
				nombre = lector.next();
				
				costo = lector.nextDouble();
				//costo = Double.parseDouble(lector.next());
				
				duracion = lector.nextDouble();
				//duracion = Double.parseDouble(lector.next());
				
				cupo = lector.nextInt();
				//cupo = Double.parseDouble(lector.next());
				//aux=(int)cupo;
				
				tipo = lector.next();
				
				atracciones.put(nombre, new Atraccion(nombre, tipo, costo, duracion, cupo));
			}
			
			return atracciones;
		}
	}
		
		
		
	}
	

