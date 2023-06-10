package turismo.sistema;

import turismo.excepciones.AtraccionExcepcion;
import turismo.excepciones.SugerenciaExcepcion;

public class Atraccion extends Sugerencia {
	private int cupoTotal;
	private int cupoDisponible;

	public Atraccion(String nombre, String tipo, double costo, double duracion, int cupo) throws AtraccionExcepcion, SugerenciaExcepcion {
		super(nombre, tipo, costo, duracion);
		
		cupoTotal = verificarCupo(cupo, "No se puede crear atracciones sin cupo");
		cupoDisponible = cupoTotal;
	}

	private int verificarCupo(int cupo, String msgExcepcion) throws AtraccionExcepcion {
		if(cupo < 0) //SAQUÉ EL < 1 YA QUE SINO NO TENÍA SENTIDO PROBAR LA REDUCCIÓN MIENTRAS HAYA CUPOS DISPONIBLES
			throw new AtraccionExcepcion(msgExcepcion);
		
		return cupo;
	}

	public void reducirCupo() throws AtraccionExcepcion {
		verificarCupo(cupoDisponible - 1, "No hay cupo disponible.");
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
