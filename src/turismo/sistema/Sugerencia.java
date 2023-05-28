package turismo.sistema;

public abstract class Sugerencia {
	protected String nombre;
	protected String tipo;
	protected double costo;
	protected double duracion;
	protected int cupo;
	protected int cupoDisponible;
	
	public final static String PAQ_ABS = "Paquete Absoluto";
	public final static String PAQ_PORC = "Paquete Porcentual";
	public final static String PAQ_AXB = "Paquete AXB";

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

	public String getNombreSug() {
		return this.nombre;
	}

	public String getTipoSug() {
		return this.tipo;
	}

	public double getCostoSug() {
		return this.costo;
	}

	public double getDuracionSug() {
		return this.duracion;
	}
	
	
	//logica de ordenamiento para enviar la info a la lista y sugerirla en SistemaTurismo:
//	 public static void ordenarAtracciones(List<Atraccion> atracciones) {
//	     
//	    }
		
		

}
