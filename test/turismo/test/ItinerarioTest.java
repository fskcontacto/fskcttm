package turismo.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import turismo.sistema.Atraccion;
import turismo.sistema.Itinerario;
import turismo.sistema.Sugerencia;

public class ItinerarioTest {
	private Itinerario itinerario;
	private double costo = 10;
	private double duracion = 2;
	private List<Sugerencia> sugAceptadas = new ArrayList<Sugerencia>();
	private Atraccion atraccion;

	@Before
	public void setUp() throws Exception {
		atraccion = new Atraccion("Erebor", "Paisaje", costo, duracion, 6);
		itinerario = new Itinerario();
		itinerario.agregarSugerencia(atraccion);
		sugAceptadas.add(atraccion);
	}

	@Test
	public void QueObtengaCosto() {
		Assert.assertEquals(costo, itinerario.getCostoItinerario(), 0.1);
	}

	@Test
	public void QueObtengaDuracion() {
		Assert.assertEquals(duracion, itinerario.getDuracionItinerario(), 0.1);
	}

	@Test
	public void QueObtengaSugerenciasAceptadas() {
		Assert.assertEquals(sugAceptadas, itinerario.getItinerario());
	}

}