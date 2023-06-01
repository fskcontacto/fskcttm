package turismo.sistema;

public class Usuario {
	private String nombre;
	private String tipo;
	private double presupuestoTotal;
	private double presupuestoDisp;
	private double tiempoTotal;
	private double tiempoDisp;
	private Itinerario itinerario;

	public Usuario(String nombre, String tipo, double presupuestoTotal, double tiempoTotal) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.presupuestoTotal = presupuestoTotal;
		this.presupuestoDisp = presupuestoTotal;
		this.tiempoTotal = tiempoTotal;
		this.tiempoDisp = tiempoTotal;
		itinerario = new Itinerario();
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public double getPresupuestoTotal() {
		return this.presupuestoTotal;
	}

	public double getTiempoDisp() {
		return tiempoDisp;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " Tipo: " + tipo + " Presupuesto: " + presupuestoTotal + " Tiempo disponible: "
				+ tiempoTotal + " Horas";
	}
	
	public boolean puedeAdquirirSugerencia(double costo, double duracion) {
		return this.puedeCostearSugerencia(costo) && this.tieneTiempoDispo(duracion);
	}

	////// se pueden cambiar a private /////////////////
	public boolean puedeCostearSugerencia(double costo) {
		return this.presupuestoDisp >= costo;
	}

	public boolean tieneTiempoDispo(double duracion) {
		return this.tiempoDisp >= duracion;
	}
	////////////////////////////////////////////////////

	public boolean preferenciaAtracc(String tipoAtracc) {
		return tipoAtracc.equals(this.tipo);
	}

	// definir si vamos a recibir como sugerencia o como atraccion

	public void agregarSugerencia(Sugerencia sugerencia) {
		if (!this.puedeCostearSugerencia(sugerencia.getCosto()))
			throw new RuntimeException("Presupuesto insuficiente del Usuario: " + this.nombre + "."); // PresupuestoInsuficienteException();

		if (this.tieneTiempoDispo(sugerencia.getDuracion()))
			throw new RuntimeException("Tiempo insuficiente del Usuario: " + this.nombre + "."); // TiempoInsuficienteException();

		this.presupuestoDisp -= sugerencia.getCosto();
		this.tiempoDisp -= sugerencia.getDuracion();
		this.itinerario.agregarSugerencia(sugerencia);
	}

	public void mostrarItinerario() {
		this.itinerario.imprimir();
	}
}
