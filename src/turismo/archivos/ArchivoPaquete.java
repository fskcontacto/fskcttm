package turismo.archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import turismo.sistema.Atraccion;
import turismo.sistema.Paquete;
import turismo.sistema.PaqueteAbsoluto;
import turismo.sistema.PaqueteAxB;
import turismo.sistema.PaquetePorcentual;

public class ArchivoPaquete {

	private String nombre;

	public ArchivoPaquete(String nombre) {
		this.nombre = nombre;
	}

	public List<Paquete> leer(Map<String,Atraccion> atracciones) throws FileNotFoundException {
		File archivo = new File(this.nombre + ".in");
		try (Scanner lector = new Scanner(archivo, "utf-8").useDelimiter("\n").useLocale(Locale.US)) {
			List<Paquete> paquetes = new ArrayList<Paquete>();

			int tipoPaquete;
			String nombresAtrac;
			String tipo;
			List<Atraccion> atracPaquete = new ArrayList<>();

			while (lector.hasNextLine()) {
				tipoPaquete = lector.nextInt();
				tipo = lector.next();
				nombresAtrac = lector.next();
				
				switch (tipoPaquete) {
				case Paquete.ABSOLUTO: {
					double costoAbsoluto = lector.nextDouble();
					String[] nombres = nombresAtrac.split(";");
					for (String nombre : nombres) {
						atracPaquete.add(atracciones.get(nombre));
					}
					paquetes.add(new PaqueteAbsoluto(tipo, costoAbsoluto, atracPaquete));
					break;
				}
				case Paquete.PORCENTUAL: {
					double porcentaje = lector.nextDouble();
					String[] nombres = nombresAtrac.split(";");
					for (String nombre : nombres) {
						atracPaquete.add(atracciones.get(nombre));
					}
					paquetes.add(new PaquetePorcentual(tipo, porcentaje, atracPaquete));
					break;
				}
				case Paquete.AXB: {
					String[] nombres = nombresAtrac.split(";");
					for (String nombre : nombres) {
						atracPaquete.add(atracciones.get(nombre));
					}
					
					nombres = lector.next().split(";");
					List<Atraccion> atracGratuitas = new ArrayList<>();
					
					for (String nombre : nombres) {
						atracGratuitas.add(atracciones.get(nombre));
					}
					atracPaquete.addAll(atracGratuitas);
					paquetes.add(new PaqueteAxB(tipo, atracPaquete, atracGratuitas));
					break;
				}
				default:
					continue;
				}
				atracPaquete.clear();
			}
			return paquetes;
		}
	}

}
