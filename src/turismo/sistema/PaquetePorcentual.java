package turismo.sistema;

import java.util.Map;

public class PaquetePorcentual extends Paquete {

	private double porcentaje;

	public PaquetePorcentual(String tipo, double porcentaje, Map<String, Atraccion> atracciones) {
		super(tipo, atracciones);
		this.porcentaje = porcentaje;
		this.costo = calcularCosto();
	}

	protected double calcularCosto() {
		return this.costoOriginal * (1 - this.porcentaje);
	}

	public double getPorcentaje() {
		return this.porcentaje;
	}

	protected void imprimir() {
		System.out.println("*PAQUETE*" + 
							"\n\tNombre atracciones: " + this.getAtracciones() +
							"\n\tTipo: " + this.getTipo() +
							"\n\tDuracion: " + this.getDuracion() + 
							"\n\tCosto original: " + this.costoOriginal +
							"\n\tCosto con descuento: " + this.getCosto() );
	}

}
