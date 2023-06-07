package turismo.sistema;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
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
		try (Scanner teclado = new Scanner(System.in)) {
			paquetes.sort(Comparator.reverseOrder());
			atracciones.sort(Comparator.reverseOrder());

			for (Usuario u : usuarios) {
				this.mensajeBienvenida(u);

				Set<String> atrTomadas = new HashSet<>();
				String respUsuario;
				List<Paquete> paqNoTipo = new ArrayList<>();
				List<Atraccion> atrNoTipo = new ArrayList<>();

				boolean esPreferencia;
				boolean puedeAdquirir;
				boolean hayCupo;
				for (Paquete p : paquetes) {
					esPreferencia = p.getTipo().equals(u.getTipo());
					puedeAdquirir = u.puedeAdquirirSugerencia(p.getCosto(), p.getDuracion());
					hayCupo = p.hayCupoDisponible();
					if (esPreferencia && hayCupo && puedeAdquirir) {
						p.imprimir();
						do {
							System.out.println("Acepta sugerencia? Ingrese S o N:");
							respUsuario = teclado.nextLine().toUpperCase();
						} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
						if (respUsuario.equals("S")) {
							p.reducirCupo();
							u.agregarSugerencia(p);
							atrTomadas.addAll(p.getAtracciones());
							System.out.println("¡Aceptada!");
						}
					} else if (!esPreferencia) {
						paqNoTipo.add(p);
					}
				}

				for (Atraccion a : atracciones) {
					esPreferencia = a.getTipo().equals(u.getTipo());
					puedeAdquirir = u.puedeAdquirirSugerencia(a.getCosto(), a.getDuracion());
					hayCupo = a.hayCupoDisponible();
					if (esPreferencia && hayCupo && puedeAdquirir && !atrTomadas.contains(a.getNombre())) {
						a.imprimir();
						do {
							System.out.println("Acepta sugerencia? Ingrese S o N:");
							respUsuario = teclado.nextLine().toUpperCase();
						} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
						if (respUsuario.equals("S")) {
							a.reducirCupo();
							u.agregarSugerencia(a);
							atrTomadas.add(a.getNombre());
							System.out.println("¡Aceptada!");
						}
					} else if (!esPreferencia) {
						atrNoTipo.add(a);
					}
				}

				for (Paquete p : paqNoTipo) {
					puedeAdquirir = u.puedeAdquirirSugerencia(p.getCosto(), p.getDuracion());
					hayCupo = p.hayCupoDisponible();
					if (hayCupo && puedeAdquirir) {
						p.imprimir();
						do {
							System.out.println("Acepta sugerencia? Ingrese S o N:");
							respUsuario = teclado.nextLine().toUpperCase();
						} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
						if (respUsuario.equals("S")) {
							p.reducirCupo();
							u.agregarSugerencia(p);
							atrTomadas.addAll(p.getAtracciones());
							System.out.println("¡Aceptada!");
						}
					}
				}

				for (Atraccion a : atrNoTipo) {
					puedeAdquirir = u.puedeAdquirirSugerencia(a.getCosto(), a.getDuracion());
					hayCupo = a.hayCupoDisponible();
					if (hayCupo && puedeAdquirir && !atrTomadas.contains(a.getNombre())) {
						a.imprimir();
						do {
							System.out.println("Acepta sugerencia? Ingrese S o N:");
							respUsuario = teclado.nextLine().toUpperCase();
						} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
						if (respUsuario.equals("S")) {
							a.reducirCupo();
							u.agregarSugerencia(a);
							atrTomadas.add(a.getNombre());
							System.out.println("¡Aceptada!");
						}
					}
				}

				System.out.println("\n¡ Han finalizado sus sugerencias del día !");

				System.out.println("¡Hola, " + u.getNombre() + "! Su itinerario es el siguiente: ");
				u.mostrarItinerario();
				System.out.println("\nFin de su itinerario. ¡ Hasta la proxima !\n");
			}
		}

		this.mensajeFinal();
	}

	public void generarArchivoSalida() {
		FileWriter file = null;
		PrintWriter printerWriter = null;

		try {
			// LocalDate hoy = LocalDate.now();
			file = new FileWriter(LocalDate.now() + ".out");
			printerWriter = new PrintWriter(file);

			printerWriter.println("++++++++++++++++++++++");
			printerWriter.println("+++ VENTAS DEL DIA +++");
			printerWriter.println("++++++++++++++++++++++");

			for (Usuario u : usuarios) {
				printerWriter.println(u.imprimirItinerarioEnArchivo());
				printerWriter.println("----------------------------------------");
			}
			/////////////////////////////////
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void mensajeBienvenida(Usuario usuario) {
		System.out.println("*******************************************************");
		System.out.println("Bienvendido/a " + usuario.getNombre());
		System.out.println("Presupuesto: " + String.format(Locale.US, "%.2f", usuario.getPresupuestoDisp()));
		System.out
				.println("Tiempo disponible: " + String.format(Locale.US, "%.2f", usuario.getTiempoDisp()) + " horas");
		System.out.println("*******************************************************");
	}

	private void mensajeFinal() {
		System.out.println("*******************************************");
		System.out.println("** FIN DEL PROCESAMIENTO DE LOS USUARIOS **");
		System.out.println("*******************************************");
	}
}
