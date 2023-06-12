package turismo.test;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import turismo.excepciones.AtraccionExcepcion;
import turismo.excepciones.SugerenciaExcepcion;
import turismo.excepciones.UsuarioExcepcion;
import turismo.sistema.Usuario;
import turismo.sistema.Atraccion;

import java.util.Map;
import java.util.HashMap;

public class UsuarioTest {
	private Atraccion atraccion1, atraccion2;
	private HashMap<String, Atraccion> atracciones;
	private Usuario usuario;
	private final double presupuestoUsuario = 50;
	private final double tiempoUsuario = 6;
	private final String preferenciaUsuario = "Degustación";
	private final double costoAtr1 = 10;
	private final double costoAtr2 = 6;
	private final double duracionAtr1 = 3;
	private final double duracionAtr2 = 1.5;
	private final int cupoAtr1 = 4;
	private final int cupoAtr2 = 5;

	@Before
	public void setUp() throws Exception {
		atracciones = new HashMap<String, Atraccion>();
		try {
			atraccion1 = new Atraccion("Moria", "Paisaje", costoAtr1, duracionAtr1, cupoAtr1);
			atraccion2 = new Atraccion("Fortuna", "Paisaje",costoAtr2 , duracionAtr2, cupoAtr2);
		} catch (AtraccionExcepcion e) {
			e.printStackTrace();
		} catch (SugerenciaExcepcion e) {
			e.printStackTrace();
		}
		
		atracciones.put("Moria", atraccion1);
		atracciones.put("Moria", atraccion2);
		
		
		try {
			usuario = new Usuario("Franco", "Degustación", presupuestoUsuario, tiempoUsuario);
		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}
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

	@Test
	public void queAgregaSugerenciasCorrectamente() {
		try {
			usuario.agregarSugerencia(atraccion1);
			usuario.agregarSugerencia(atraccion2);
		}catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoAtr1 - costoAtr2); 
		Assert.assertEquals(true, totalValido);
		
		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionAtr1 - duracionAtr2);
		Assert.assertEquals(true, totalValido);
		
		Assert.assertEquals(false, usuario.itinerarioVacio());
		
	}
	
	//public void queRestaMaxTiempo
	//public void queRestaMaxPresupuesto

}
