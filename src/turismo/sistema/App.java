package turismo.sistema;

import turismo.archivos.ArchivoSistemaTurismo;
import turismo.excepciones.UsuarioExcepcion;

public class App {

	public static void main(String[] args) throws UsuarioExcepcion {
		ArchivoSistemaTurismo archivo = new ArchivoSistemaTurismo("usuarios", "atracciones",
				"paquetes");
		SistemaTurismo sistema = archivo.leer();

		sistema.sugerirUsuario();

		sistema.generarArchivoSalida();
	}
}
