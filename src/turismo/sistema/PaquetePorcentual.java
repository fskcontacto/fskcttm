package turismo.sistema;

import java.util.List;

public class PaquetePorcentual extends Paquete {
	
	private double porcentaje;
	
	public PaquetePorcentual(String tipo, double porcentaje, List<Atraccion> atracciones) {
		super(tipo, atracciones);
		this.porcentaje = porcentaje;
		this.costo = calcularCosto();
	}
	
	@Override
	protected double calcularCosto() {
		return this.costoOriginal * (1 - this.porcentaje);
	}

	public double getPorcentaje() {
		return this.porcentaje;
	}
	
}
