package turismo.sistema;

public class Atraccion extends Sugerencia {

	public Atraccion(String nombre, String tipo, double costo, double duracion, int cupo) {
		super(nombre, tipo, costo, duracion, cupo);
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " Tipo: " + tipo + " Costo: " + costo + " Duración: " + duracion + " Horas "
				+ "Cupo: " + cupo + " Cupo disponible: " + super.cupoDisponible;
	}
}
