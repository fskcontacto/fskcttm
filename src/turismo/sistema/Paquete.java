package turismo.sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;



public abstract class Paquete extends Sugerencia {

	protected Map<String, Atraccion> atracciones;
	protected double costoOriginal;
	protected List<Atraccion> atracciones_incluidas;
	public static final int ABSOLUTO = 0; // preguntar -> se hace una clase enum??? ->
	public static final int PORCENTUAL = 1;
	public static final int AXB = 2;

	public Paquete(String tipo, Map<String, Atraccion> atracciones) {
		super(tipo);
		this.atracciones = atracciones;
		this.atracciones_incluidas = new ArrayList<Atraccion>();
		inicializarValores();
		this.costo = costoOriginal;
	}

	private void inicializarValores() {
		String nombre = "";
		double costoOriginal = 0;
		double duracion = 0;
		int menorCupo = Integer.MAX_VALUE;

		for (Atraccion atraccion : this.atracciones.values()) {
			nombre += atraccion.nombre + ", ";
			duracion += atraccion.duracion;
			costoOriginal += atraccion.costo;
			int cupoTotal = atraccion.getCupoTotal();
			if (cupoTotal < menorCupo)
				menorCupo = cupoTotal;
			this.atracciones_incluidas.add(atraccion);
		}

		this.nombre = nombre.substring(0, nombre.length() - 2);
		this.duracion = duracion;
		this.costoOriginal = costoOriginal;
		
		
	}
	
	//devuelve vacio
	public Set<String> getAtracciones() {
		return this.atracciones.keySet();
		
	}
	
	public List<Atraccion> getAtracciones2() {
		return this.atracciones_incluidas;
	}
	
	public void reducirCupo() {
		for (Atraccion atraccion : this.atracciones.values())
		{
			atraccion.reducirCupo();
		}
			
	}

	public int getCupoDisponible() {
		int cupoDisponible=0;

		for (Atraccion atraccion : this.atracciones_incluidas) {
				cupoDisponible += atraccion.getCupoDisponible();
		}

		return cupoDisponible;
	}

	protected abstract double calcularCosto();

	@Override
	public String toString() {
		return "*PAQUETE*"
				+"\n Nombre: " + nombre
				+"\n Costo: " + String.format(Locale.US, "%.2f", costo) + 
				"\n Costo original: "+ String.format(Locale.US, "%.2f", costoOriginal);
	}

	public double getMontoOrigPaquete() {
		return this.costoOriginal;
	}


	
	public boolean hayCupoDisponible() { //el padre lo implementó, los hijos ya saben hacerlo
		return getCupoDisponible() > 0;
	}
	
	protected abstract void imprimir();


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
