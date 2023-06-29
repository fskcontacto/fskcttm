package turismo.test;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import turismo.excepciones.AtraccionExcepcion;
import turismo.excepciones.PaqueteExcepcion;
import turismo.excepciones.SugerenciaExcepcion;
import turismo.excepciones.UsuarioExcepcion;
import turismo.sistema.Atraccion;
import turismo.sistema.Paquete;
import turismo.sistema.PaqueteAbsoluto;
import turismo.sistema.Usuario;

public class UsuarioTest {
	private Atraccion atraccion1, atraccion2, atraccion3, atraccionCostosa, atraccionLarga;
	private HashMap<String, Atraccion> atracciones;
	private Paquete paqueteAbsoluto;
	private Usuario usuario;
	private final double presupuestoUsuario = 50;
	private final double tiempoUsuario = 6;
	private final double costoPaqueteAbsoluto = 12;
	private final double costoAtr1 = 10;
	private final double costoAtr2 = 6;
	private final double costoAtr3 = 5;
	private final double costoAtrCostosa = 60;
	private final double duracionAtr1 = 3;
	private final double duracionAtr2 = 1.5;
	private final double duracionAtr3 = 1;
	private final double duracionPaqueteAbsoluto = duracionAtr1 + duracionAtr2;
	private final double duracionAtrLarga = 10;
	private final int cupoAtr1 = 4;
	private final int cupoAtr2 = 5;
	private final int cupoAtr3 = 6;

	@Before
	public void setUp() {
		atracciones = new HashMap<String, Atraccion>();
		try {
			atraccion1 = new Atraccion("Moria", "Paisaje", costoAtr1, duracionAtr1, cupoAtr1);
			atraccion2 = new Atraccion("Fortuna", "Paisaje", costoAtr2, duracionAtr2, cupoAtr2);
			atraccion3 = new Atraccion("Fortuna", "Paisaje", costoAtr3, duracionAtr3, cupoAtr3);
			atraccionCostosa = new Atraccion("Minas Tirith", "Aventura", costoAtrCostosa, duracionAtr1, cupoAtr1);
			atraccionLarga = new Atraccion("Minas Tirith", "Aventura", costoAtr1, duracionAtrLarga, cupoAtr1);
			atracciones.put("1", atraccion1);
			atracciones.put("2", atraccion2);
			paqueteAbsoluto = new PaqueteAbsoluto("Paisaje", costoPaqueteAbsoluto, atracciones);
			usuario = new Usuario("Franco", "Degustación", presupuestoUsuario, tiempoUsuario);
		} catch (AtraccionExcepcion | SugerenciaExcepcion | PaqueteExcepcion | UsuarioExcepcion e) {
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
	public void queAgregaUnaSugerenciaCorrectamente() {
		try {
			usuario.agregarSugerencia(atraccion1);
		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoAtr1);
		Assert.assertEquals(true, totalValido);;

		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionAtr1);
		Assert.assertEquals(true, totalValido);

		Assert.assertEquals(false, usuario.itinerarioVacio());

	}
	
	@Test
	public void queAgregaSugerenciasCorrectamente() {
		try {
			usuario.agregarSugerencia(atraccion3);
			usuario.agregarSugerencia(paqueteAbsoluto);
		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoAtr3 - costoPaqueteAbsoluto);
		Assert.assertEquals(true, totalValido);;

		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionAtr3 - duracionPaqueteAbsoluto);
		Assert.assertEquals(true, totalValido);

		Assert.assertEquals(false, usuario.itinerarioVacio());

	}
	
	@Test(expected = UsuarioExcepcion.class)
	public void queNoAgregaSugerenciasSiNoPuede() throws UsuarioExcepcion {
		try {
			usuario.agregarSugerencia(atraccion3);
			usuario.agregarSugerencia(paqueteAbsoluto);
		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		usuario.agregarSugerencia(atraccionCostosa);
	}
	
	
	@Test(expected = UsuarioExcepcion.class)
	public void queNoCompreSiNoPuedeCostear() throws UsuarioExcepcion {
		usuario.agregarSugerencia(atraccionCostosa);
	}
	
	@Test(expected = UsuarioExcepcion.class)
	public void queNoCompreSiNoTieneTiempo() throws UsuarioExcepcion {
		usuario.agregarSugerencia(atraccionLarga);
	}

}