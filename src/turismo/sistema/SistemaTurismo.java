package turismo.sistema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SistemaTurismo {
	private List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<Paquete> paquetes;
	//private Usuario u=null;
	
	public SistemaTurismo(List<Usuario> usuarios, List<Atraccion> atracciones, List<Paquete> paquetes) {
		this.usuarios = usuarios;
		this.atracciones = atracciones;
		this.paquetes = paquetes;
	}
	
//	public void cambiarUsuario() {
//		
//		Scanner entrada = new Scanner(System.in);
//		boolean usuarioEncontrado = false;
//		System.out.print("Por favor, ingresá tu usuario \n");
//		String usuarioEntrada = entrada.next();
//		System.out.println("---------------------------");
//		for (Usuario usr : usuarios)
//		{
//			if(usr.getNombre().equals(usuarioEntrada))
//			{
//				this.u=usr;
//				usuarioEncontrado=true;
//			}
//			
//		}
//			if (!usuarioEncontrado)
//				System.err.println("Nombre de usuario no encontrado");
//		}
//	public boolean usuarioVacio()
//	{
//		return this.u==null;
//	}
//	
//	public String getNombreUsuario()
//	{
//		return this.u.getNombre();
//	}

	public void sugerirUsuario() {
			Scanner teclado = new Scanner(System.in);
			Set<Atraccion> atrTomadas = new HashSet<>();
			String respUsuario;
			paquetes.sort(Comparator.reverseOrder());
			atracciones.sort(Comparator.reverseOrder());
			List<Paquete> paqNoTipo = new ArrayList<>();
			List<Atraccion> atrNoTipo = new ArrayList<>();
			boolean preferencia;
			boolean puedePagarloYTieneTiempo;
			boolean yaFueComprada; 
			for(Usuario u : usuarios)
			{
			System.out.println(u.mensajeBienvenida());
			for (Paquete p : paquetes) {
				
				 preferencia = u.getTipo().equals(p.getTipo());
				 puedePagarloYTieneTiempo=u.puedeAdquirirSugerencia(p.getCosto(), p.getDuracion());		 
				if (preferencia && puedePagarloYTieneTiempo && p.hayCupoDisponible())
						 {
					System.out.println(p);
					
					do {
						System.out.println("Acepta sugerencia? Ingrese S o N:");
						respUsuario = teclado.nextLine().toUpperCase();
					} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
					if (respUsuario.equals("S")) {
						//u.comprarPaquete(p);
						p.reducirCupo();
						u.agregarSugerencia(p); // el paquete es una sug por eso no arroja error
						atrTomadas.addAll(p.getAtracciones2());
					
					}
				} else if (!p.getTipo().equals(u.getTipo())) {
					paqNoTipo.add(p); // Una vez agotadas las ofertas que coincidan con sus intereses, se ofertarán
										// aquellas que no coincidan, bajo el mismo criterio.
				}
			}

			for (Atraccion a : atracciones) {
				 preferencia = u.getTipo().equals(a.getTipo());
				 puedePagarloYTieneTiempo=u.puedeAdquirirSugerencia(a.getCosto(), a.getDuracion());		 
				 yaFueComprada =atrTomadas.contains(a);
				if (preferencia && !yaFueComprada && puedePagarloYTieneTiempo && a.hayCupoDisponible()){
					System.out.println(a);
					do {
						System.out.println("Acepta sugerencia? Ingrese S o N:");
						respUsuario = teclado.nextLine().toUpperCase();
					} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
					if (respUsuario.equals("S")) {
						a.reducirCupo();
						//u.comprarAtraccion(a);
						u.agregarSugerencia(a);
						atrTomadas.add(a);
					}
				} else if (!a.getTipo().equals(u.getTipo())) {
					atrNoTipo.add(a);
				}
			}

			for (Paquete p : paqNoTipo) {
				
				 puedePagarloYTieneTiempo=u.puedeAdquirirSugerencia(p.getCosto(), p.getDuracion());		 
				 

				if (puedePagarloYTieneTiempo && p.hayCupoDisponible())
				{
					System.out.println(p);
					do {
						System.out.println("Acepta sugerencia? Ingrese S o N:");
						respUsuario = teclado.nextLine().toUpperCase();
					} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
					if (respUsuario.equals("S")) {
						p.reducirCupo();
						//u.comprarPaquete(p);
						u.agregarSugerencia(p);
						atrTomadas.addAll(p.getAtracciones2());
					}
				}
			}

			for (Atraccion a : atrNoTipo) {
				 puedePagarloYTieneTiempo=u.puedeAdquirirSugerencia(a.getCosto(), a.getDuracion());		 
				 yaFueComprada =atrTomadas.contains(a);
						
				if (puedePagarloYTieneTiempo && !yaFueComprada && a.hayCupoDisponible()){
					System.out.println(a);
					do {
						System.out.println("Acepta sugerencia? Ingrese S o N:");
						respUsuario = teclado.nextLine().toUpperCase();
					} while (!respUsuario.equals("S") && !respUsuario.equals("N"));
					if (respUsuario.equals("S")) {
						a.reducirCupo();
						u.agregarSugerencia(a);
						//u.comprarAtraccion(a);
						
					
						atrTomadas.add(a);
					}
				}
			}
			

			System.out.println("\n¡ Han finalizado sus sugerencias del día !");

			System.out.println("¡Hola, " + u.getNombre() + "! Su itinerario es el siguiente: ");
			u.mostrarItinerario();
			u.escribirItinerario();
			System.out.println("\nFin de su itinerario. ¡ Hasta la proxima !\n");
			atrTomadas.clear();
			paqNoTipo.clear();
			atrNoTipo.clear();
			//teclado.close();
			}
		}
	
//	public void escribirArchivoItinerario()
//	{
//		u.escribirItinerario();
//	}
		

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


