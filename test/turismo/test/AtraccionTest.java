package turismo.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import turismo.excepciones.AtraccionExcepcion;
import turismo.excepciones.SugerenciaExcepcion;
import turismo.sistema.Atraccion;

public class AtraccionTest {

	private Atraccion atraccion;

	@Before
	public void setUp() {
		try {
			atraccion = new Atraccion("Moria", "Paisaje", 10, 2, 6);
		} catch (AtraccionExcepcion e) {
			e.printStackTrace();
		} catch (SugerenciaExcepcion e) {
			e.printStackTrace();
		}
	}

	@Test(expected = AtraccionExcepcion.class)
	public void queAtraccionNoTengaCupoInvalido() throws AtraccionExcepcion, SugerenciaExcepcion {

		new Atraccion("Atraccion1", "Paisaje", 10.0, 3, 0);
	}

	@Test
	public void queVerifiqueCupoDispCorrecto() {
		Assert.assertEquals(6, atraccion.getCupoDisponible());
	}

	@Test
	public void queVerifiqueCupoTotalCorrecto() {
		Assert.assertEquals(6, atraccion.getCupoTotal());
	}

	@Test
	public void queCupoTotalYDispSeanIgualesInicio() {
		Assert.assertEquals(atraccion.getCupoDisponible(), atraccion.getCupoTotal());
	}

	@Test
	public void queReduzcaMasDeUnCupo() {
		try {
			atraccion.reducirCupo();
			atraccion.reducirCupo();
			atraccion.reducirCupo();
			Assert.assertEquals(3, atraccion.getCupoDisponible());
		} catch (AtraccionExcepcion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void queReduzcaMaxCupos() {
		try {
			for (int i = 0; i < atraccion.getCupoTotal(); ++i) {
				atraccion.reducirCupo();
			}
			Assert.assertEquals(0, atraccion.getCupoDisponible());
		} catch (AtraccionExcepcion e) {
			e.printStackTrace();
		}
	}

	@Test(expected = AtraccionExcepcion.class)
	public void queNoReduzcaMasCupoDelPermitido() throws AtraccionExcepcion {
		for (int i = 0; i < atraccion.getCupoTotal(); ++i) {
			atraccion.reducirCupo();
		}
		atraccion.reducirCupo();
	}

	@Test
	public void queNoReduzcaMinCuposPermitidos() throws AtraccionExcepcion {
		atraccion.reducirCupo();
		Assert.assertEquals(5, atraccion.getCupoDisponible());
	}
	
	@Test(expected = SugerenciaExcepcion.class)
	public void queNoPermitaTipoInvalidos() throws AtraccionExcepcion, SugerenciaExcepcion {
		new Atraccion("Moria", "Comida", 10, 3, 4);
	}
	
	@Test(expected = SugerenciaExcepcion.class)
	public void queNoPermitaNombreVacio() throws AtraccionExcepcion, SugerenciaExcepcion {
		new Atraccion("", "Degustación", 10, 3, 1);
	}
	
	@Test(expected = SugerenciaExcepcion.class)
	public void queNoPermitaCostoNegativo() throws AtraccionExcepcion, SugerenciaExcepcion {
		new Atraccion("Fortuna", "Paisaje", -10, 3, 11);
	}
	
	@Test(expected = SugerenciaExcepcion.class)
	public void queNoPermitaDuracionNoPositiva() throws AtraccionExcepcion, SugerenciaExcepcion {
		new Atraccion("Fortuna", "Paisaje", 3, 0, 11);
	}
	
}

/*
 * 
 * proponer testear Usuario e ¿Itinerario? -> se testea el comportamiento de la clase, no tiene nada que ver con los files.
 * proponer separar las cosas que testean cosas generales en un SugerenciaTest
 * "final" en variables que no se pueden modificar ej -> todas las de Sugerencia
 * chequear si falta alguna otra verificacion
 * chequear si no hay try-catch innecesarios
 * 
*/