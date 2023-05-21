package turismo.sistema;

import java.io.FileNotFoundException;
import java.util.List;

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
	
	public static void main(String[] args) {
		ArchivoSistemaTurismo archivo = new ArchivoSistemaTurismo("itinerancias", "usuarios", "atracciones", "paquetes");
		SistemaTurismo sistema = archivo.leer();
	}

}
