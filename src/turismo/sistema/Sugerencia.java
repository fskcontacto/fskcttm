package turismo.sistema;

import turismo.excepciones.AtraccionExcepcion;
import turismo.excepciones.SugerenciaExcepcion;

public abstract class Sugerencia implements Comparable<Sugerencia> {
	protected String nombre;
	protected String tipo;
	protected double costo;
	protected double duracion;

	public Sugerencia(String nombre, String tipo, double costo, double duracion) throws SugerenciaExcepcion {
		this.nombre = verificarNombre(nombre);
		this.tipo = verificarTipo(tipo);
		this.costo = verificarCosto(costo);
		this.duracion = verificarDuracion(duracion);
	}

	protected Sugerencia(String tipo) throws SugerenciaExcepcion {
		this.tipo = verificarTipo(tipo);
	}

	private double verificarCosto(double costo) throws SugerenciaExcepcion {
		if (costo < 0)
			throw new SugerenciaExcepcion("No puede generar sugerencias con costo menor que 0.");

		return costo;
	}

	private double verificarDuracion(double duracion) throws SugerenciaExcepcion {
		if (duracion <= 0)
			throw new SugerenciaExcepcion("No puede generar sugerencias con duracion menor o igual a 0");

		return duracion;
	}

	private String verificarTipo(String tipo) throws SugerenciaExcepcion {
		if (!tipo.equalsIgnoreCase("Paisaje") && !tipo.equalsIgnoreCase("Degustación")
				&& !tipo.equalsIgnoreCase("Aventura"))
			throw new SugerenciaExcepcion("Tipo de sugerencia invalida.");

		return tipo;
	}

	private String verificarNombre(String nombre) throws SugerenciaExcepcion {
		if (nombre.equals(""))
			throw new SugerenciaExcepcion("No se ingreso nombre.");

		return nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public double getCosto() {
		return this.costo;
	}

	public double getDuracion() {
		return this.duracion;
	}

	public abstract void reducirCupo() throws AtraccionExcepcion;

	public abstract int getCupoDisponible();

	public abstract int getCupoTotal();

	public abstract boolean hayCupoDisponible();

	public int compareTo(Sugerencia otra) {
		if (this.costo != otra.costo)
			return Double.compare(this.costo, otra.costo);

		return Double.compare(this.duracion, otra.duracion);
	}

	protected abstract void imprimir();
}