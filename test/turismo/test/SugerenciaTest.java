package turismo.test;

import org.junit.Test;

import turismo.excepciones.AtraccionExcepcion;
import turismo.excepciones.SugerenciaExcepcion;
import turismo.sistema.Atraccion;

public class SugerenciaTest {

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
