package turismo.sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Itinerario {
	
	private double costo;
	private double duracion;
	private List<Atraccion> atrItinerario;
	private static final String RES_SI = "S";
	private static final String RES_NO = "N";

	public Itinerario() {
		this.costo = 0;
		this.duracion = 0;
		atrItinerario = new ArrayList<Atraccion>();
	}

	public double getCostoItinerario() {
		return this.costo;
	}

	public double getDuracionItinerario() {
		return this.duracion;
	}

	public List<Atraccion> getItinerario() {
		return atrItinerario;
	}


	public void mostrarAtrGratIter(PaqueteAxB paquete) {
		paquete.mostrarAtraccionesGratuitas();
	}

	public void pantallaItinerPromo(Sugerencia sug) {

		System.out.println("Promocion");
		System.out.println("-Atracciones incluidas: [" + sug.getNombreSug() + "]");
		System.out.println("-Duración: " + sug.getDuracionSug());
		System.out.println("-Precio original: " + sug.getCostoSug());

		if (sug.tipo.equals(Sugerencia.PAQ_PORC)) {

			if (sug instanceof PaquetePorcentual) {
				PaquetePorcentual paquete = (PaquetePorcentual) sug;
				double precioDescuento = paquete.calcularCosto();
				System.out.println("-Precio con descuento: " + precioDescuento);
			}
		} else if (sug.tipo.equals(Sugerencia.PAQ_AXB)) {

			if (sug instanceof PaqueteAxB) {
				System.out.println("-Si acepta tiene gratis: ");
				PaqueteAxB paquete = (PaqueteAxB) sug;
				mostrarAtrGratIter(paquete);
				System.out.println("-Precio para aprovechar: " + paquete.calcularCosto());
			}

		}

	}

	public void pantallaItinerAtraccion(Sugerencia sug,Usuario u) {

		System.out.println("Atracción");
		System.out.println("Nombre: [" + sug.getNombreSug() + "]");
		System.out.println("-Precio : " + sug.getCostoSug());
		System.out.println("-Duración: " + sug.getDuracionSug());
	}

	public void respuestaUsuario(Sugerencia sug) {
		Scanner scanner = new Scanner(System.in);
		String respuesta;

		do {
			System.out.println("¿Acepta la sugerencia? (S/N): ");
			respuesta = scanner.nextLine();

			if (respuesta.equals(RES_SI)) {
				//
				//lógica para descontar a cada usuario:
						
			}

		} while (!respuesta.equals(RES_SI) && !respuesta.equals(RES_NO));

		scanner.close();
	}

	public void agregarSugerencia(Sugerencia sugerencia) {
		// TODO: definir como lo vamos a implementar
	}
}
