package turismo.sistema;

public abstract class Sugerencia {
	protected String nombre;
	protected String tipo;
	protected double costo;
	protected double duracion;
	protected int cupo;
	protected int cupoDisponible;

	public Sugerencia(String nombre, String tipo, double costo, double duracion, int cupo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.costo = costo;
		this.duracion = duracion;
		this.cupo = cupo;
		this.cupoDisponible = cupo;
	}
	
	protected Sugerencia(String tipo) {
		this.tipo = tipo;
	}
}
