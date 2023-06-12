package turismo.test;

import org.junit.Test;
import org.junit.Before;

import turismo.excepciones.UsuarioExcepcion;
import turismo.sistema.Usuario;

public class UsuarioTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = UsuarioExcepcion.class)
	public void queNoPermitaPreferenciaInvalidos() throws UsuarioExcepcion {
		new Usuario("Federico", "Comida", 20, 4);
	}

	@Test(expected = UsuarioExcepcion.class)
	public void queNoPermitaNombreVacio() throws UsuarioExcepcion {
		new Usuario("", "Degustación", 20, 1);
	}

	@Test(expected = UsuarioExcepcion.class)
	public void queNoPermitaPresupuestoNegativo() throws UsuarioExcepcion {
		new Usuario("Lucas", "Paisaje", -10, 11);
	}

	@Test(expected = UsuarioExcepcion.class)
	public void queNoPermitaTiempoNegativo() throws UsuarioExcepcion {
		new Usuario("Hernan", "Paisaje", 50, -1);
	}	

}
