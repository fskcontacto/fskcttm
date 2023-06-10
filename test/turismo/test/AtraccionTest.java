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

	@Test(expected = AtraccionExcepcion.class) //tal vez es con una RunTimeException.class o modificando algo de las clases perso
	public void queAtraccionNoTengaCupoInvalido() throws AtraccionExcepcion, SugerenciaExcepcion {

		try {

			Atraccion atraccion = new Atraccion("Atraccion1", "Paisaje", 10.0, 0, 0);
			// Assert.fail("Debería haber lanzado una excepción de AtraccionExcepcion");
		} catch (AtraccionExcepcion e) {
			Assert.assertTrue(e.getMessage().contains("No se puede crear atracciones sin cupo"));
		} catch (SugerenciaExcepcion s) {
			Assert.fail("Se produjo una excepción inesperada: " + s.getMessage());
		}
	} // NO FUNCA, REVISAR

	@Test
	public void queVerifiqueCupoDispCorrepto() {
		Assert.assertEquals(6, atraccion.getCupoDisponible());

	}

	@Test
	public void queVerifiqueCupoTotalCorrepto() {

		Assert.assertEquals(6, atraccion.getCupoTotal());

	}

//	@Test CREO QUE ESTE ES UN EJEMPLO INNECESARIO - REVISAR
//	public void queCupoTotalYDispSeanIgualesInicio()  {
//
//		try {
//			Atraccion atraccion = new Atraccion("Moria", "Paisaje", 10, 2, 6);
//			Assert.assertEquals(atraccion.getCupoDisponible(), atraccion.getCupoTotal());
//			atraccion = null;
//		} catch (AtraccionExcepcion e) {
//			e.printStackTrace();
//		} catch (SugerenciaExcepcion e) {
//			e.printStackTrace();
//		}
//	}

// reducirCupo

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
			atraccion = null;
		} catch (AtraccionExcepcion e) {
			e.printStackTrace();
		}
	}

	@Test(expected = AtraccionExcepcion.class)
	public void queNoReduzcaMaxCuposPermitidos() {

		try {

			for (int i = 0; i < atraccion.getCupoTotal(); ++i) {
				atraccion.reducirCupo();
			}

			atraccion.reducirCupo();
		} catch (AtraccionExcepcion e) {
			Assert.assertTrue(e.getMessage().contains("No hay cupo disponible."));
		}

	}

	@Test
	public void queNoReduzcaMinCuposPermitidos() {

		try {
			atraccion.reducirCupo();
			Assert.assertEquals(5, atraccion.getCupoDisponible());

		} catch (AtraccionExcepcion e) {
			e.printStackTrace();

		}
	}
}
