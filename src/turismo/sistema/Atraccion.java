package turismo.sistema;

public class Atraccion extends Sugerencia {
	
	private int cupoDisponible;
	
	public Atraccion(String nombre, String tipo, double costo, double duracion, int cupo) {
		super(nombre, tipo, costo, duracion, cupo);
		cupoDisponible = cupo;
	}
	
	public void reducirCupo() {
		if(cupoDisponible < 1)
			throw new RuntimeException("Cupo Insuficiente"); // Crear exception propia (CupoInsuficienteException) 
		
		cupoDisponible--;
	}
	
	public int getCupoDisponible() {
		return cupoDisponible;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " Tipo: " + tipo + " Costo: " + costo + " Duración: " + duracion + " Horas "
				+ "Cupo: " + cupo + " Cupo disponible: " + cupoDisponible;
	}
}
