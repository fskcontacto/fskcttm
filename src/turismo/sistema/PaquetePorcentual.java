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
		System.out.println(" ");
	}

}
