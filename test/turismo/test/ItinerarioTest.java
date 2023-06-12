package turismo.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import turismo.sistema.Itinerario;
import turismo.sistema.Sugerencia;

public class ItinerarioTest {
	private Itinerario itinerario;
	private double costo;
	private double duracion;
	private List<Sugerencia> sugAceptadas = new ArrayList<Sugerencia>();
	
	this.sugAceptadas.add("Erebor", "Paisaje", 12, 2);
	
	@Before
	public void setUp() throws Exception {
		itinerario = new Itinerario(12, 2, sugAceptadas);
	}

	@Test
	public void QueObtengaCosto() {
		
	}
	
	@Test
	public void QueObtengaDuracion() {
	}
	
	@Test
	public void QueObtengaSugerenciasAceptadas() {
	}

}