package turismo.sistema;

import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	private double costo;
	private double duracion;
	private List<Sugerencia> sugAceptadas;

	public Itinerario() {
		this.costo = 0;
		this.duracion = 0;
		this.sugAceptadas = new ArrayList<Sugerencia>();
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

	public void agregarSugerencia(Sugerencia sugerencia) {
		this.sugAceptadas.add(sugerencia);
		this.costo += sugerencia.getCosto();
		this.duracion += sugerencia.getDuracion();
	}

	public void mostrarAtrGratIter(PaqueteAxB paquete) {
		paquete.mostrarAtraccionesGratuitas();
	}

}
