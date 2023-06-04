package turismo.sistema;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import turismo.archivos.ArchivoAtraccion;
import turismo.archivos.ArchivoSistemaTurismo;
import turismo.archivos.ArchivoUsuario;

public class App {

//	private static SistemaTurismo iniciarSistemaTurismo() {
//		ArchivoUsuario archivoUsuario = new ArchivoUsuario("usuarios");
//		ArchivoAtraccion archivoAtraccion = new ArchivoAtraccion("atracciones");
//		List<Usuario> usuarios;
//		List<Atraccion> atracciones;
//		SistemaTurismo sistema = null;
//		try {
//			usuarios = archivoUsuario.leer();
//			atracciones = archivoAtraccion.leer();
//			sistema = new SistemaTurismo(usuarios, atracciones);
//			for (Usuario usuario : usuarios) {
//				System.out.println(usuario);
//			}
//			for (Atraccion atraccion : atracciones) {
//				System.out.println(atraccion);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		return sistema;
//	}
	// PARA ELEGIR USUARIOS
	
//	public static void main(String[] args) {
//	ArchivoSistemaTurismo archivo = new ArchivoSistemaTurismo("itinerancias", "usuarios", "atracciones", "paquetes");
//	SistemaTurismo sistema = archivo.leer();//tengo todas las listas
//	System.out.println("Bienvenido a MediApp");
//	System.out.println("---------------------------");
//	boolean prendido=true;
//	Scanner entrada = new Scanner(System.in);
//	int opcion=0;
//	while(prendido)
//	{
//		while(sistema.usuarioVacio())
//		{
//			sistema.cambiarUsuario();
//		}
//		System.out.println("Te damos la bienvenida, " + sistema.getNombreUsuario());
//		System.out.println("\n 1) CAMBIAR USUARIO \n 2) VER MIS SUGERENCIAS \n 0) SALIR");
//		System.out.println("---------------------------");
//		opcion = entrada.nextInt();
//		if (opcion == 1) {
//			sistema.cambiarUsuario();
//		}
//		if (opcion == 2) {
//			sistema.sugerirUsuario();
//			sistema.escribirArchivoItinerario();
//		}
//		
//		if (opcion == 0) {
//			prendido=false;
//		}
//		
//		
//	}
//	System.out.println("Saliendo");
//	entrada.close();
//	}
//}
//	

		

//	//FUNCA
	public static void main(String[] args) {
		ArchivoSistemaTurismo archivo = new ArchivoSistemaTurismo("itinerancias", "usuarios", "atracciones", "paquetes");
		SistemaTurismo sistema = archivo.leer(); 
		sistema.sugerirUsuario();
	}
}


