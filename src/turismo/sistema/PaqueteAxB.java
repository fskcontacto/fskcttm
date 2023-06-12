package turismo.sistema;

import java.util.Map;
import java.util.Set;

import turismo.excepciones.PaqueteExcepcion;
import turismo.excepciones.SugerenciaExcepcion;

public class PaqueteAxB extends Paquete {

	private Map<String,Atraccion> atracGratuitas;

	public PaqueteAxB(String tipo, Map<String,Atraccion> atracciones, Map<String,Atraccion> atracGratuitas) throws SugerenciaExcepcion, PaqueteExcepcion {
		super(tipo, atracciones);
		this.atracGratuitas = verificarAtraccionesGratuitas(atracGratuitas);
		this.costo = calcularCosto();
	}
	
	private Map<String,Atraccion> verificarAtraccionesGratuitas(Map<String,Atraccion> atraccionesGratuitas) throws PaqueteExcepcion {
		if(atraccionesGratuitas.isEmpty())
			throw new PaqueteExcepcion("Debe haber al menos una atraccion gratuita en Paquetes AxB");
		
		return atraccionesGratuitas;
	}

	protected double calcularCosto() {
		double costoGratuito = 0;
		for (Atraccion atraccion : this.atracGratuitas.values()) {
			costoGratuito += atraccion.getCosto();
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
    
    public Set<String> getAtraccionesGratuitas() {
    	return atracGratuitas.keySet();
    }

    protected void imprimir() {
		System.out.println("*PAQUETE*" + 
							"\n\tNombre atracciones totales: " + this.getAtracciones() +
							"\n\tNombre atracciones gratuitas: " + this.getAtraccionesGratuitas() +
							"\n\tTipo: " + this.getTipo() +
							"\n\tDuracion: " + this.getDuracion() +
							"\n\tCosto original: " + this.costoOriginal +
							"\n\tCosto final: " + this.getCosto() );
	}
	

}
