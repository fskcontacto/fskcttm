package turismo.sistema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SistemaTurismo {
	private List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<Paquete> paquetes;

	public SistemaTurismo(List<Usuario> usuarios, List<Atraccion> atracciones, List<Paquete> paquetes) {
		this.usuarios = usuarios;
		this.atracciones = atracciones;
		this.paquetes = paquetes;
	}

	public void sugerirUsuario() {
		Scanner teclado = new Scanner(System.in);

		for (Usuario u : usuarios) {

			Set<String> atrTomadas = new HashSet<>();
			String respUsuario;
			Collections.sort(paquetes);
			Collections.sort(atracciones);
			List<Paquete> paqNoTipo = new ArrayList<>();
			List<Atraccion> atrNoTipo = new ArrayList<>();

			for (Paquete p : paquetes) {
				if (p.getTipo().equals(u.getTipo()) && p.hayCupoDisponible()
						&& u.puedeAdquirirSugerencia(p.getCosto(), p.getDuracion())) {
					p.imprimir();
					do {
						System.out.println("Acepta sugerencia? Ingrese S o N:");
						respUsuario = teclado.nextLine().toUpperCase();
					} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
					if (respUsuario.equals("S")) {
						p.reducirCupo();
						u.agregarSugerencia(p); // el paquete es una sug por eso no arroja error
						atrTomadas.addAll(p.getAtracciones());
					}
				} else if (!p.getTipo().equals(u.getTipo())) {
					paqNoTipo.add(p); // Una vez agotadas las ofertas que coincidan con sus intereses, se ofertarán
										// aquellas que no coincidan, bajo el mismo criterio.
				}
			}

			for (Atraccion a : atracciones) {
				if (a.getTipo().equals(u.getTipo()) && a.hayCupoDisponible()
						&& u.puedeAdquirirSugerencia(a.getCosto(), a.getDuracion())
						&& !atrTomadas.contains(a.getNombre())) {
					a.imprimir();
					do {
						System.out.println("Acepta sugerencia? Ingrese S o N:");
						respUsuario = teclado.nextLine().toUpperCase();
					} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
					if (respUsuario.equals("S")) {
						a.reducirCupo();
						u.agregarSugerencia(a);
						atrTomadas.add(a.getNombre());
					}
				} else if (!a.getTipo().equals(u.getTipo())) {
					atrNoTipo.add(a);
				}
			}

			for (Paquete p : paqNoTipo) {
				if (p.hayCupoDisponible() && u.puedeAdquirirSugerencia(p.getCosto(), p.getDuracion())) {
					p.imprimir();
					do {
						System.out.println("Acepta sugerencia? Ingrese S o N:");
						respUsuario = teclado.nextLine().toUpperCase();
					} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
					if (respUsuario.equals("S")) {
						p.reducirCupo();
						u.agregarSugerencia(p);
						atrTomadas.addAll(p.getAtracciones());
					}
				}
			}

			for (Atraccion a : atrNoTipo) {
				if (a.hayCupoDisponible() && u.puedeAdquirirSugerencia(a.getCosto(), a.getDuracion())
						&& atrTomadas.contains(a.getNombre())) {
					a.imprimir();
					do {
						System.out.println("Acepta sugerencia? Ingrese S o N:");
						respUsuario = teclado.nextLine().toUpperCase();
					} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
					if (respUsuario.equals("S")) {
						a.reducirCupo();
						u.agregarSugerencia(a);
						atrTomadas.add(a.getNombre());
					}
				}
			}

			System.out.println("\n¡ Han finalizado sus sugerencias del día !");

			System.out.println("¡Hola, " + u.getNombre() + "! Su itinerario es el siguiente: ");
			u.mostrarItinerario();
			System.out.println("\nFin de su itinerario. ¡ Hasta la proxima !\n");
		}

		/*
		 * System.out.println("Promocion");
		 * System.out.println("-Atracciones incluidas: [" + sug.getNombreSug() + "]");
		 * System.out.println("-Duración: " + sug.getDuracionSug());
		 * System.out.println("-Precio original: " + sug.getCostoSug());
		 */

	}

	/*
	 * public List<Sugerencia> buscarPrefSugerencia(){
	 * 
	 * List<Sugerencia> pa = new ArrayList<>();
	 * 
	 * for(Atraccion a: atracciones) {
	 * 
	 * if(u.preferenciaAtracc(a.nombre)) { pa.add(a); } }
	 * 
	 * return pa; }
	 */

}
