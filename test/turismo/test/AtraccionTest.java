package turismo.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import turismo.excepciones.AtraccionExcepcion;
import turismo.excepciones.SugerenciaExcepcion;
import turismo.sistema.Atraccion;

public class AtraccionTest {

	private Atraccion a;

//	@Before
//	public void setUp() throws AtraccionExcepcion, SugerenciaExcepcion {
//		//this.a = new Atraccion("Moria", "Paisaje", 10, 2, 6);
//		this.a = new Atraccion("", "", 0, 0, 0); // Atraccion vacia
//	}

	@Test(expected = SugerenciaExcepcion.class)
	public void atraccionNoVacia() throws AtraccionExcepcion, SugerenciaExcepcion {
		this.a = new Atraccion("", "", 0, 0, 0);
		//this.a = new Atraccion("Moria", "Paisaje", 10, 2, 6);
	}

//	@Test(expected = AtraccionExcepcion.class)
//	public void costoInvalido() {
//		boolean res = a.validarCosto(a.getCosto());
//		assertEquals(false, res);
//	}
//
//	@Test(expected = AtraccionExcepcion.class)
//	public void duracionInvalida() {
//		boolean res = a.validarDuracion(a.getDuracion());
//		assertEquals(false, res);
//	}
//
//	@Test(expected = AtraccionExcepcion.class)
//	public void cupoInvalido() {
//		boolean res = a.validarCupo(a.getCupoDisponible());
//		assertEquals(false, res);
//	}
}
