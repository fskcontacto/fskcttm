package turismo.sistema;

import java.util.Map;

public class PaqueteAbsoluto extends Paquete {

	private double costoFinal;

	public PaqueteAbsoluto(String tipo, double costoFinal, Map<String, Atraccion> atracciones) {
		super(tipo, atracciones);
		this.costoFinal = costoFinal;
		this.costo = calcularCosto();
	}

	protected double calcularCosto() {
		return this.costoFinal;
	}

	protected void imprimir() {
		System.out.println(" ");
	}

}
