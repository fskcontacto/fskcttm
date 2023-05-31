package turismo.sistema;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String nombre;
	private String tipo;
	private double presupuesto;
	private double tiempoDisp;
	private List<Atraccion> sugDiaria;
	private Itinerario itinerario;

	public Usuario(String nombre, String tipo, double presupuesto, double tiempoDisp) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.presupuesto = presupuesto;
		this.tiempoDisp = tiempoDisp;
		sugDiaria = new ArrayList<Atraccion>();
		itinerario = new Itinerario();
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

	public boolean puedeCostearAtraccion(double costo) {
		return this.presupuesto >= costo;
	}
	
	public boolean tieneTiempoDispo(double duracion) {
		return this.tiempoDisp >= duracion;
	}
	
	public boolean preferenciaAtracc(String tipoAtracc) {
		return tipoAtracc.equals(this.tipo);
	}
	
	public void agregarSugDiaria(Atraccion atraccion) { //esto para el SistemaTurismo
		sugDiaria.add(atraccion);
	}
	
	public List<Atraccion> obtenerSugDiaria() {
		return sugDiaria;
	}
	
	// definir si vamos a recibir como sugerencia o como atraccion
	public void agregarSugerencia(Sugerencia sugerencia) {
		if(!this.puedeCostearAtraccion(sugerencia.getCostoSug()))
			throw new RuntimeException("Presupuesto insuficiente del Usuario: " + this.nombre + "."); // PresupuestoInsuficienteException();
		
		if(this.tieneTiempoDispo(sugerencia.getDuracionSug())) 
			throw new RuntimeException("Tiempo insuficiente del Usuario: " + this.nombre + "."); //TiempoInsuficienteException();
		
		this.presupuesto -= sugerencia.getCostoSug();
		this.tiempoDisp -= sugerencia.getDuracionSug();
		this.itinerario.agregarSugerencia(sugerencia);
	}
	
	
	
}
