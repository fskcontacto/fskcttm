package turismo.sistema;

import java.util.Collections;
import java.util.List;

public abstract class Sugerencia implements Comparable<Sugerencia> {
	protected String nombre;
	protected String tipo;
	protected double costo;
	protected double duracion;

	public Sugerencia(String nombre, String tipo, double costo, double duracion, int cupo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.costo = costo;
		this.duracion = duracion;
	}

	protected Sugerencia(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public double getCosto() { // generar métodos abstractos
		return this.costo;
	}

	public double getDuracion() {
		return this.duracion;
	}

	//public abstract void reducirCupo();

	public abstract int getCupoDisponible();
	public abstract int getCupoTotal();
	public abstract boolean hayCupoDisponible();

	public static void ordenarSugerencias(List<Sugerencia> sugerencias) {
		Collections.sort(sugerencias);
	}

	public int compareTo(Sugerencia o) {
		if (this.costo != o.costo)
			return Double.compare(this.costo, o.costo);

		return Double.compare(this.duracion, o.duracion);
	}
	
	protected abstract void imprimir();
}