package turismo.sistema;

import turismo.archivos.ArchivoSistemaTurismo;

public class App {
	
	public static void main(String[] args) {
		ArchivoSistemaTurismo archivo = new ArchivoSistemaTurismo("itinerancias", "usuarios", "atracciones", "paquetes");
		SistemaTurismo sistema = archivo.leer(); //tengo todas las listas
		
		//paquete aXb no muestra las atracciones que se cobran
	
		sistema.sugerirUsuario();
		
		sistema.generarArchivoSalida();
	}

}
