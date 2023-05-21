package turismo.sistema;

import java.util.List;

public class PaqueteAbsoluto extends Paquete {

	private double costoFinal;
	
	public PaqueteAbsoluto(String tipo, double costoFinal, List<Atraccion> atracciones) {
		super(tipo, atracciones);
		this.costoFinal = costoFinal;
		this.costo = calcularCosto();
	}

	@Override
	protected double calcularCosto() {
		return this.costoFinal;
	}
}

