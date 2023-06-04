package turismo.sistema;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	private double costo;
	private double duracion;
	private List<Sugerencia> sugAceptadas;
	private List<Atraccion> atracciones_aceptadas;

	public Itinerario() {
		this.costo = 0;
		this.duracion = 0;
		this.sugAceptadas = new ArrayList<Sugerencia>();
		this.atracciones_aceptadas = new ArrayList<Atraccion>();
	}

	public double getCostoItinerario() {
		return this.costo;
	}

	public double getDuracionItinerario() {
		return this.duracion;
	}

	public List<Sugerencia> getItinerario() {
		return this.sugAceptadas;
	}
	
	public List<Atraccion> getAtraccionesCompradas() {
		return this.atracciones_aceptadas;
	}

//	public void agregarPaquete(Paquete sugerencia) {
//		this.sugAceptadas.add(sugerencia);
//		this.costo += sugerencia.getCosto();
//		this.duracion += sugerencia.getDuracion();
//		atracciones_aceptadas.addAll(sugerencia.getAtracciones2());
//	}
//	
//	public void agregarAtraccion(Atraccion sugerencia) {
//		this.sugAceptadas.add(sugerencia);
//		this.costo += sugerencia.getCosto();
//		this.duracion += sugerencia.getDuracion();
//		atracciones_aceptadas.add(sugerencia);
//	}

	public void agregarSugerencia(Sugerencia sugerencia) {
		this.sugAceptadas.add(sugerencia);
		this.costo += sugerencia.getCosto();
		this.duracion += sugerencia.getDuracion();
	}
	
	public void mostrarAtrGratIter(PaqueteAxB paquete) {
		paquete.mostrarAtraccionesGratuitas();
	}

	public void imprimir() {
		System.out.println("Sugerencias aceptadas:");
		for(Sugerencia sugerencia : sugAceptadas) {
			System.out.println("\t" + sugerencia.getNombre());
		}
		System.out.println("");
		System.out.println("Duracion total: " + this.duracion);
		System.out.println("Costo total: " + this.costo + " monedas");
	}
	
	public void escribirItinerarioArchivo(String nombreUsuario)
	{
		FileWriter file = null;
		PrintWriter printerWriter = null;
	

		try {
			file = new FileWriter("salida/"+ nombreUsuario +".out");
			printerWriter = new PrintWriter(file);
			for (Sugerencia a : sugAceptadas)
			{
				printerWriter.println(a);
			}
			printerWriter.println("Costo total:"+this.costo);
			printerWriter.println("Duracion total:"+this.duracion);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		
	}
}
