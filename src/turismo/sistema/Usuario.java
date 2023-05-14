package turismo.sistema;

public class Usuario {
	private String nombre;
	private String tipo;
	private double presupuesto;
	private double tiempoDisp;

	public Usuario(String nombre, String tipo, double presupuesto, double tiempoDisp) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.presupuesto = presupuesto;
		this.tiempoDisp = tiempoDisp;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoDisp() {
		return tiempoDisp;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " Tipo: " + tipo + " Presupuesto: " + presupuesto + " Tiempo disponible: "
				+ tiempoDisp + " Horas";
	}
}
