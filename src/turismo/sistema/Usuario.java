package turismo.sistema;

public class Usuario {
	private String nombre;
	private String tipo;
	private double presupuestoDisp;
	private double tiempoDisp;
	private Itinerario itinerario;

	public Usuario(String nombre, String tipo, double presupuestoTotal, double tiempoTotal) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.presupuestoDisp = presupuestoTotal;
		this.tiempoDisp = tiempoTotal;
		itinerario = new Itinerario();
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public double getPresupuestoDisp() {
		return this.presupuestoDisp;
	}

	public double getTiempoDisp() {
		return tiempoDisp;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " Tipo: " + tipo + " Presupuesto: " + presupuestoDisp + " Tiempo disponible: "
				+ tiempoDisp + " Horas";
	}
	
	public boolean puedeAdquirirSugerencia(double costo, double duracion) {
		return this.puedeCostearSugerencia(costo) && this.tieneTiempoDispo(duracion);
	}

	////// se pueden cambiar a private /////////////////
	private boolean puedeCostearSugerencia(double costo) {
		return this.presupuestoDisp >= costo;
	}

	private boolean tieneTiempoDispo(double duracion) {
		return this.tiempoDisp >= duracion;
	}
	////////////////////////////////////////////////////

	public boolean preferenciaAtracc(String tipoAtracc) {
		return tipoAtracc.equals(this.tipo);
	}

	public void agregarSugerencia(Sugerencia sugerencia) {
		if (!this.puedeCostearSugerencia(sugerencia.getCosto()))
			throw new RuntimeException("Presupuesto insuficiente del Usuario: " + this.nombre + "."); // PresupuestoInsuficienteException();

		if (!this.tieneTiempoDispo(sugerencia.getDuracion()))
			throw new RuntimeException("Tiempo insuficiente del Usuario: " + this.nombre + "."); // TiempoInsuficienteException();

		this.presupuestoDisp -= sugerencia.getCosto();
		this.tiempoDisp -= sugerencia.getDuracion();
		this.itinerario.agregarSugerencia(sugerencia);
	}

	public void mostrarItinerario() {
		this.itinerario.imprimir();
	}
	
	public boolean itinerarioVacio() {
		return this.itinerario.getItinerario().isEmpty();
	}
	
	public String imprimirItinerarioEnArchivo() {
		return 	"Nombre: " + this.nombre +
				"\nTipo preferencia: " + this.tipo + "\n" +
				this.itinerario.imprimirEnArchivo();
	}
}
