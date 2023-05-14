package turismo.sistema;

import java.io.FileNotFoundException;
import java.util.List;

import turismo.archivos.ArchivoAtraccion;
import turismo.archivos.ArchivoUsuario;

public class SistemaTurismo {
	private List<Usuario> usuarios;
	private List<Atraccion> atracciones;

	public SistemaTurismo(List<Usuario> usuarios, List<Atraccion> atracciones) {
		this.usuarios = usuarios;
		this.atracciones = atracciones;
	}
}
