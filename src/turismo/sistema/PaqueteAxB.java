package turismo.sistema;

import java.util.Map;

public class PaqueteAxB extends Paquete {

	private Map<String,Atraccion> atracGratuitas;

	public PaqueteAxB(String tipo, Map<String,Atraccion> atracciones, Map<String,Atraccion> atracGratuitas) {
		super(tipo, atracciones);
		this.atracGratuitas = atracGratuitas;
		this.costo = calcularCosto();
	}

	protected double calcularCosto() {
		double costoGratuito = 0;

		for (Atraccion atraccion : this.atracGratuitas.values()) {
			costoGratuito += atraccion.costo;
		}

		return this.costoOriginal - costoGratuito;
	}

	
    public void mostrarAtraccionesGratuitas() {
        System.out.println("Atracciones gratuitas:");
        for (Atraccion atraccion : atracGratuitas.values()) {
            System.out.println("-Nombre: " + atraccion.getNombre());
            System.out.println("-Duración: " + atraccion.getDuracion());
            System.out.println("-Costo: " + atraccion.getCosto());
        }
    }
    

	protected void imprimir() {
		System.out.println(" ");
	}
	

}
