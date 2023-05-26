package turismo.sistema;

import java.util.List;

public class PaqueteAxB extends Paquete {

	private List<Atraccion> atracGratuitas;

	public PaqueteAxB(String tipo, List<Atraccion> atracciones, List<Atraccion> atracGratuitas) {
		super(tipo, atracciones);
		this.atracGratuitas = atracGratuitas;
		this.costo = calcularCosto();
	}

	@Override
	protected double calcularCosto() {
		double costoGratuito = 0;

		for (Atraccion atraccion : this.atracGratuitas) {
			costoGratuito += atraccion.costo;
		}

		return this.costoOriginal - costoGratuito;
	}

	public List<Atraccion> getAtracGratuitas() {
		return atracGratuitas;
	}
	
    public void mostrarAtraccionesGratuitas() {
        System.out.println("Atracciones gratuitas:");
        for (Atraccion atraccion : atracGratuitas) {
            System.out.println("-Nombre: " + atraccion.getNombreSug());
            System.out.println("-Duración: " + atraccion.getDuracionSug());
            System.out.println("-Costo: " + atraccion.getCostoSug());
        }
    }

}
