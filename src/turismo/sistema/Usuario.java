package turismo.sistema;

import turismo.excepciones.UsuarioExcepcion;

public class Usuario {
	private final String nombre;
	private final String tipo;
	private double presupuestoDisp;
	private double tiempoDisp;
	private Itinerario itinerario;

	public Usuario(String nombre, String tipo, double presupuestoTotal, double tiempoTotal) throws UsuarioExcepcion {
		this.nombre = verificarNombre(nombre);
		this.tipo = verificarPreferencia(tipo);
		this.presupuestoDisp = verificarPresupDisp(presupuestoTotal);
		this.tiempoDisp = verificarTiempoDisp(tiempoTotal);
		itinerario = new Itinerario();
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public double getPresupuestoDisp() {
		return this.presupuestoDisp;
	}

	public double getTiempoDisp() {
		return this.tiempoDisp;
	}

	private String verificarNombre(String nombreUsuario) throws UsuarioExcepcion {
		if (nombreUsuario.equals(""))
			throw new UsuarioExcepcion("Usuario no identificado, error");

		return nombreUsuario;
	}

	private double verificarPresupDisp(double presupDisp) throws UsuarioExcepcion {
		if (presupDisp < 0)
			throw new UsuarioExcepcion("El presupuesto disponible del usuario no puede ser menor a 0");

		return presupDisp;
	}

	private double verificarTiempoDisp(double tiempoDispon) throws UsuarioExcepcion {
		if (tiempoDispon < 0)
			throw new UsuarioExcepcion("El tiempo disponible del usuario no puede ser menor a 0");

		return tiempoDispon;
	}

	private String verificarPreferencia(String pref) throws UsuarioExcepcion {
		if (!pref.equalsIgnoreCase("Paisaje") && !pref.equalsIgnoreCase("Degustación")
				&& !pref.equalsIgnoreCase("Aventura"))
			throw new UsuarioExcepcion(pref + " no es una preferencia válida para el usuario");

		return pref;
	}

	public boolean puedeAdquirirSugerencia(double costo, double duracion) {
		return this.puedeCostearSugerencia(costo) && this.tieneTiempoDispo(duracion);
	}

	private boolean puedeCostearSugerencia(double costo) {
		return this.presupuestoDisp >= costo;
	}

	private boolean tieneTiempoDispo(double duracion) {
		return this.tiempoDisp >= duracion;
	}

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
		return "Nombre: " + this.nombre + "\nTipo preferencia: " + this.tipo + "\n"
				+ this.itinerario.imprimirEnArchivo();
	}
}