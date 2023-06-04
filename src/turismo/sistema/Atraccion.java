package turismo.sistema;

public class Atraccion extends Sugerencia {
	private int cupoTotal;
	private int cupoDisponible;

	public Atraccion(String nombre, String tipo, double costo, double duracion, int cupo) {
		super(nombre, tipo, costo, duracion, cupo);
		cupoDisponible = cupo;
	}

	public void reducirCupo() {
		if (cupoDisponible < 1)
			throw new RuntimeException("Cupo Insuficiente"); // Crear exception propia (CupoInsuficienteException)

		cupoDisponible--;
	}

	public int getCupoDisponible() {
		return cupoDisponible;
	}

	public int getCupoTotal() {
		return cupoTotal;
	}

	@Override
	public String toString() {
		return "*ATRACCION* Nombre: " + nombre + 
				"\n Tipo: " + tipo + 
				"\n Costo: " + costo + 
				"\n Duración: " + duracion + " horas "
				+"\n Cupo: " + cupoTotal + " -- Cupo disponible: " + cupoDisponible;
	}
	
	
	protected void imprimir() {
		System.out.println(" ");
	}
	
	public boolean hayCupoDisponible() {
		return this.cupoDisponible > 0;
	}

}
