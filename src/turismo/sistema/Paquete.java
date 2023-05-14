package turismo.sistema;

import java.util.List;

public class Paquete extends Sugerencia {

	protected List<Atraccion> atracciones;
	protected double costoOriginal;
	
	public Paquete(String nombre, String tipo, double costo, double duracion, int cupo) {
		super(nombre, tipo, costo, duracion, cupo);
	}

}
