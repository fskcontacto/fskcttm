package turismo.sistema;

import java.util.List;
import java.util.Locale;

public abstract class Paquete extends Sugerencia {

	protected List<Atraccion> atracciones;
	protected double costoOriginal;
	public static final int ABSOLUTO = 0; // preguntar -> se hace una clase enum???
	public static final int PORCENTUAL = 1;
	public static final int AXB = 2;

	public Paquete(String tipo, List<Atraccion> atracciones) {
		super(tipo);
		this.atracciones = atracciones;
		inicializarValores();
		this.cupoDisponible = this.cupo;
		this.costo = costoOriginal;
	}

	private void inicializarValores() {
		String nombre = "";
		double costoOriginal = 0;
		double duracion = 0;
		int menorCupo = Integer.MAX_VALUE;

		for (Atraccion atraccion : this.atracciones) {
			nombre += atraccion.nombre + ", ";
			duracion += atraccion.duracion;
			costoOriginal += atraccion.costo;
			if (atraccion.cupo < menorCupo)
				menorCupo = atraccion.cupo;
		}

		this.nombre = nombre.substring(0, nombre.length() - 2);
		this.duracion = duracion;
		this.costoOriginal = costoOriginal;
		this.cupo = menorCupo;
	}

	protected abstract double calcularCosto();

	@Override
	public String toString() {
		return "Nombre: " + nombre + " Costo: " + String.format(Locale.US, "%.2f", costo) + " Costo original: "
				+ String.format(Locale.US, "%.2f", costoOriginal);
	}

//	private String calcularNombre() {
//		String nombre = "";
//		int tope = this.atracciones.size() - 1;
//		int i = 0;
//
//		for (i = 0; i < tope; i++) {
//			nombre += this.atracciones.get(i).nombre + ", ";
//		}
//
//		nombre += this.atracciones.get(i).nombre;
//
//		return nombre;
//	}
//	
//	private double calcularDuracion() {
//		double duracion = 0;
//		
//		for (Atraccion atraccion : this.atracciones) {
//			duracion += atraccion.duracion;
//		}
//
//		return duracion;
//	}
//
//	private int calcularCupo() {
//		int menorCupo = Integer.MAX_VALUE;
//
//		for (Atraccion atraccion : this.atracciones) {
//			if (atraccion.cupo < menorCupo)
//				menorCupo = atraccion.cupo;
//		}
//
//		return menorCupo;
//	}
//
//	private double calcularCostoOriginal() {
//		double costoOriginal = 0;
//
//		for (Atraccion atraccion : this.atracciones) {
//			costoOriginal += atraccion.costo;
//		}
//
//		return costoOriginal;
//	}
}
