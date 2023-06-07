package turismo.sistema;

import turismo.excepciones.AtraccionExcepcion;

public class Atraccion extends Sugerencia {
	private int cupoTotal;
	private int cupoDisponible;

	public Atraccion(String nombre, String tipo, double costo, double duracion, int cupo) throws AtraccionExcepcion {
		super(nombre, tipo, costo, duracion, cupo);
		if(cupoTotal < 0 || cupoDisponible < 0) {
			throw new AtraccionExcepcion("No puede generar atracciones sin cupo"); 
		}
		
		cupoDisponible = cupo;
	}

	public void reducirCupo() throws AtraccionExcepcion {
		if (cupoDisponible < 1)
			throw new AtraccionExcepcion("Cupo Insuficiente"); // Crear exception propia (CupoInsuficienteException)

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
		return "Nombre: " + nombre + " Tipo: " + tipo + " Costo: " + costo + " Duración: " + duracion + " Horas "
				+ "Cupo: " + cupoTotal + " Cupo disponible: " + cupoDisponible;
	}
	
	
	protected void imprimir() {
		System.out.println("*ATRACCION*" +
								"\n\tNombre: " + this.nombre + 
								"\n\t Tipo: " + this.tipo + 
								"\n\t Costo: " + this.costo + 
								"\n\t Duración: " + this.duracion + " horas");
	}
	
	public boolean hayCupoDisponible() {
		return this.cupoDisponible > 0;
	}

}
