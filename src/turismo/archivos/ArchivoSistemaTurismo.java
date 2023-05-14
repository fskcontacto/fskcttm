package turismo.archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import turismo.sistema.Atraccion;
import turismo.sistema.SistemaTurismo;
import turismo.sistema.Usuario;

public class ArchivoSistemaTurismo {
	private String nombre;
	private String archUsuario;
	private String archAtrac;
	private String archPaquetes;

	
	public ArchivoSistemaTurismo(String nombre, String archUsuario, String archAtrac, String archPaquetes) {
		super();
		this.nombre = nombre;
		this.archUsuario = archUsuario;
		this.archAtrac = archAtrac;
		this.archPaquetes = archPaquetes;
	}
	
	public SistemaTurismo leer() {
		ArchivoUsuario archivoUsuario = new ArchivoUsuario(this.archUsuario);
		ArchivoAtraccion archivoAtraccion = new ArchivoAtraccion(this.archAtrac);
		List<Usuario> usuarios;
		List<Atraccion> atracciones;
		SistemaTurismo sistema = null;
		try {
			usuarios = archivoUsuario.leer();
			atracciones = archivoAtraccion.leer();
			sistema = new SistemaTurismo(usuarios, atracciones);
			for (Usuario usuario : usuarios) {
				System.out.println(usuario);
			}
			for (Atraccion atraccion : atracciones) {
				System.out.println(atraccion);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return sistema;
	}

}
