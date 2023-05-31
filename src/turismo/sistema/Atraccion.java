package turismo.sistema;

import java.util.List;

public class Atraccion extends Sugerencia {

	public Atraccion(String nombre, String tipo, double costo, double duracion, int cupo) {
		super(nombre, tipo, costo, duracion, cupo);
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " Tipo: " + tipo + " Costo: " + costo + " Duración: " + duracion + " Horas "
				+ "Cupo: " + cupo + " Cupo disponible: " + super.cupoDisponible;
	}
	
	
	//no me cuadra el tema del costo, tiene que ser un constructor totalmente de Sugerencia?

	/*
	public void buscarPrefUsuario(Usuario u, List<Atraccion> atr) {

		if (u.preferenciaAtracc(this.tipo)) {
			
			
		}

	}*/

}
