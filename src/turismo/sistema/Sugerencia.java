package turismo.sistema;

import java.util.Collections;
import java.util.List;

public abstract class Sugerencia implements Comparable<Sugerencia>{
	protected String nombre;
	protected String tipo;
	protected double costo;
	protected double duracion;
	protected int cupo; //el cupo total lo tiene realmente la atracción - corregir
	protected int cupoDisponible;
		
	public final static String PAQ_ABS = "Paquete Absoluto"; //llevarlo con poli
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

	public double getCostoSug() { //generar métodos abstractos
		return this.costo;
	}

	public double getDuracionSug() {
		return this.duracion;
	}
	
	
//	logica de ordenamiento para enviar la info a la lista y sugerirla en SistemaTurismo:
//	1° paquetes mas caros y mas largos
	 public static void ordenarSugerencias(List<Sugerencia> sugerencias) {
		 Collections.sort(sugerencias);
	 }

	@Override
	public int compareTo(Sugerencia o) {
		if(this.costo != o.costo)
			return Double.compare(this.costo, o.costo);
		
		return Double.compare(this.duracion, o.duracion);
	}
	
	protected abstract void buscarPrefUsuario(Usuario u, List<Atraccion> atr);
	
	
//>>>>>>> branch 'master' of https://github.com/fskcontacto/fskcttm
		
		

}
