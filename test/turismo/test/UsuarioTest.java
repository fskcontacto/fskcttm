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
	private Usuario usuario;
	private final double presupuestoUsuario = 50;
	private final double tiempoUsuario = 6;

	private Atraccion atraccionPaisaje, atraccionPaisaje2, atraccionAventura, atraccionAventura2, atraccionDegustacion,
			atraccionDegustacion2, atraccionCostosa, atraccionLarga;
	private HashMap<String, Atraccion> atraccionesPaisaje, atraccionesAventura,atraccionesDegustacion,
	atraccionesCostosas,atraccionesLargas;
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

	private Paquete paqueteAbsPaisaje, paqueteAbsDegustacion,paqueteCostosoDegust,paqueteLargoDegust;
	private final double costoPaquetePaisaje = costoAtr1 + costoAtr2;
	private final double duracionPaquetePaisaje = duracionAtr1 + duracionAtr2;

	private final double costoPaqueteDegustacion = costoAtr3 + costoAtr3;
	private final double duracionPaqueteDegustacion = duracionAtr3 + duracionAtr3;
	
	private final double costoPaqueteCostosoDegust = costoAtrCostosa + costoAtr1;


	@Before
	public void setUp() {
		atraccionesPaisaje = new HashMap<String, Atraccion>();
		atraccionesAventura = new HashMap<String, Atraccion>();
		atraccionesDegustacion = new HashMap<String, Atraccion>();
		atraccionesCostosas = new HashMap<String, Atraccion>();
		atraccionesLargas = new HashMap<String, Atraccion>();


		try {

			usuario = new Usuario("Franco", "Degustación", presupuestoUsuario, tiempoUsuario);

			atraccionPaisaje = new Atraccion("Moria", "Paisaje", costoAtr1, duracionAtr1, cupoAtr1);
			atraccionPaisaje2 = new Atraccion("Amoria", "Paisaje", costoAtr2, duracionAtr2, cupoAtr2);
			atraccionesPaisaje.put("1", atraccionPaisaje);
			atraccionesPaisaje.put("2", atraccionPaisaje2);

			atraccionAventura = new Atraccion("Fortuna", "Aventura", costoAtr1, duracionAtr1, cupoAtr1);
			atraccionAventura2 = new Atraccion("Hazzard", "Aventura", costoAtr2, duracionAtr2, cupoAtr2);
			atraccionesAventura.put("1", atraccionAventura);
			atraccionesAventura.put("2", atraccionAventura2);

			atraccionDegustacion = new Atraccion("Infortuna", "Degustación", costoAtr3, duracionAtr3, cupoAtr3);
			atraccionDegustacion2 = new Atraccion("Restaurant inf", "Degustación", costoAtr3, duracionAtr3, cupoAtr3);

			atraccionesDegustacion.put("1", atraccionDegustacion);
			atraccionesDegustacion.put("2", atraccionDegustacion2);

			atraccionCostosa = new Atraccion("Minas Tirith", "Aventura", costoAtrCostosa, duracionAtr1, cupoAtr1);
			
			atraccionesCostosas.put("1",atraccionCostosa);

			atraccionLarga = new Atraccion("Minas Tirith2", "Aventura", costoAtr1, duracionAtrLarga, cupoAtr1);
			atraccionesLargas.put("1",atraccionLarga);

			paqueteAbsPaisaje = new PaqueteAbsoluto("Paisaje", costoPaquetePaisaje, atraccionesPaisaje);
			paqueteAbsDegustacion = new PaqueteAbsoluto("Degustación", costoPaqueteDegustacion, atraccionesDegustacion);
			paqueteCostosoDegust = new PaqueteAbsoluto("Degustación", costoPaqueteCostosoDegust, atraccionesCostosas);
			paqueteLargoDegust = new PaqueteAbsoluto("Degustación", costoPaqueteDegustacion, atraccionesLargas);

			
		} catch (AtraccionExcepcion | SugerenciaExcepcion | PaqueteExcepcion | UsuarioExcepcion e) {
			e.printStackTrace();
		}
	}

	@Test(expected = UsuarioExcepcion.class)
	public void queNoPermitaPreferenciasInvalidas() throws UsuarioExcepcion {
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
	public void queChequeePreferenciaUsuarioDegustacion() {
		Assert.assertEquals(true, usuario.getTipo() == "Degustación");
	}

	@Test
	public void queChequeeNoPreferenciaUsuarioDegustacion() {
		Assert.assertEquals(false, usuario.getTipo() == "Aventura");
	}

	@Test
	public void queChequeePreferenciaUsuarioAventura() throws UsuarioExcepcion {
		Usuario usuario2 = new Usuario("Juan", "Aventura", presupuestoUsuario, tiempoUsuario);
		Assert.assertEquals(true, usuario2.getTipo() == "Aventura");
		usuario2 = null;
	}

	@Test
	public void queChequeePreferenciaUsuarioPaisaje() throws UsuarioExcepcion {
		Usuario usuario2 = new Usuario("Pedro", "Paisaje", presupuestoUsuario, tiempoUsuario);
		Assert.assertEquals(true, usuario2.getTipo() == "Paisaje");
		usuario2 = null;
	}

	@Test
	public void queAgregaUnaAtraccionCorrectamentePreferenciaUsuario() {
		try {
			usuario.agregarSugerencia(atraccionDegustacion);
		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		String tipoAtraccion = "Degustación";
		boolean esPrefencia = usuario.getTipo() == tipoAtraccion;
		Assert.assertEquals(true, esPrefencia);

		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoAtr3);
		Assert.assertEquals(true, totalValido);

		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionAtr3);
		Assert.assertEquals(true, totalValido);

		Assert.assertEquals(false, usuario.itinerarioVacio());

	}

	@Test
	public void queAgregaAtraccionesCorrectamentePreferenciaUsuario() {
		try {

			usuario.agregarSugerencia(atraccionDegustacion);
			usuario.agregarSugerencia(atraccionDegustacion2);

		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		String tipoAtracciones = "Degustación";
		boolean esPrefencia = usuario.getTipo() == tipoAtracciones;
		Assert.assertEquals(true, esPrefencia);

		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoAtr3 - costoAtr3);
		Assert.assertEquals(true, totalValido);

		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionAtr3 - duracionAtr3);
		Assert.assertEquals(true, totalValido);

		Assert.assertEquals(false, usuario.itinerarioVacio());

	}

	@Test
	public void queAgregaUnaAtraccionCorrectamenteNoPreferenciaUsuario() {
		try {
			usuario.agregarSugerencia(atraccionPaisaje);
		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		String tipoAtraccion = "Paisaje";
		boolean esPrefencia = usuario.getTipo() == tipoAtraccion;
		Assert.assertEquals(false, esPrefencia);

		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoAtr1);
		Assert.assertEquals(true, totalValido);

		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionAtr1);
		Assert.assertEquals(true, totalValido);

		Assert.assertEquals(false, usuario.itinerarioVacio());

	}

	@Test
	public void queAgregaAtraccionesCorrectamenteNoPreferenciaUsuario() {
		try {
			usuario.agregarSugerencia(atraccionPaisaje);
			usuario.agregarSugerencia(atraccionPaisaje2);

		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		String tipoAtraccion = "Paisaje";
		boolean esPrefencia = usuario.getTipo() == tipoAtraccion;
		Assert.assertEquals(false, esPrefencia);

		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoAtr1 - costoAtr2);
		Assert.assertEquals(true, totalValido);

		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionAtr1 - duracionAtr2);
		Assert.assertEquals(true, totalValido);

		Assert.assertEquals(false, usuario.itinerarioVacio());

	}

	@Test
	public void queAgregaUnPaqueteCorrectamentePreferenciaUsuario() {
		try {
			usuario.agregarSugerencia(paqueteAbsDegustacion);

		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		String tipoAtraccionPaquete = "Degustación";
		boolean esPrefencia = usuario.getTipo() == tipoAtraccionPaquete;
		Assert.assertEquals(true, esPrefencia);

		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoPaqueteDegustacion);
		Assert.assertEquals(true, totalValido);

		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionPaqueteDegustacion);
		Assert.assertEquals(true, totalValido);

		Assert.assertEquals(false, usuario.itinerarioVacio());

	}

	@Test
	public void queAgregaPaquetesCorrectamentePreferenciaUsuario() {
		try {
			usuario.agregarSugerencia(paqueteAbsDegustacion);
			usuario.agregarSugerencia(paqueteAbsDegustacion);

		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		String tipoAtraccionesPaquete = "Degustación";
		boolean sonPrefencias = usuario.getTipo() == tipoAtraccionesPaquete;
		Assert.assertEquals(true, sonPrefencias);

		double costoPaquetes = costoPaqueteDegustacion * 2;

		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoPaquetes);
		Assert.assertEquals(true, totalValido);

		double duracionPaquetes = duracionPaqueteDegustacion * 2;

		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionPaquetes);
		Assert.assertEquals(true, totalValido);

		Assert.assertEquals(false, usuario.itinerarioVacio());

	}

	@Test
	public void queAgregaUnPaqueteCorrectamenteNoPreferenciaUsuario() {
		try {
			usuario.agregarSugerencia(paqueteAbsPaisaje);

		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		String tipoAtraccionPaquete = "Paisaje";
		boolean sonPrefencias = usuario.getTipo() == tipoAtraccionPaquete;
		Assert.assertEquals(false, sonPrefencias);

		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoPaquetePaisaje);
		Assert.assertEquals(true, totalValido);

		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionPaquetePaisaje);
		Assert.assertEquals(true, totalValido);

		Assert.assertEquals(false, usuario.itinerarioVacio());

	}

	@Test
	public void queAgregaPaquetesCorrectamenteNoPreferenciaUsuario() {
		try {
			usuario.agregarSugerencia(paqueteAbsPaisaje);
			usuario.agregarSugerencia(paqueteAbsPaisaje);

		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		String tipoAtraccionPaquete = "Paisaje";
		boolean sonPrefencias = usuario.getTipo() == tipoAtraccionPaquete;
		Assert.assertEquals(false, sonPrefencias);

		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoPaquetePaisaje);
		Assert.assertEquals(true, totalValido);

		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionAtr1 - duracionAtr2);
		Assert.assertEquals(true, totalValido);

		Assert.assertEquals(false, usuario.itinerarioVacio());

	}

	@Test
	public void queAgregaDifSugerenciasCorrectamente() {
		try {
			usuario.agregarSugerencia(atraccionDegustacion);
			usuario.agregarSugerencia(paqueteAbsPaisaje);
		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		boolean totalValido = usuario.getPresupuestoDisp() == (presupuestoUsuario - costoAtr3 - costoPaquetePaisaje);
		Assert.assertEquals(true, totalValido);

		totalValido = usuario.getTiempoDisp() == (tiempoUsuario - duracionAtr3 - duracionPaqueteAbsoluto);
		Assert.assertEquals(true, totalValido);

		Assert.assertEquals(false, usuario.itinerarioVacio());

	}

	
	@Test(expected = UsuarioExcepcion.class)
	public void queNoAgregueAtraccionSiNoPuedeCosto() throws UsuarioExcepcion {
		try {
			usuario.agregarSugerencia(atraccionDegustacion);
			usuario.agregarSugerencia(atraccionAventura);
			usuario.agregarSugerencia(atraccionPaisaje);

		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		usuario.agregarSugerencia(atraccionCostosa);
	}
	
	@Test(expected = UsuarioExcepcion.class)
	public void queNoAgreguePaqueteSiNoPuedeCosto() throws UsuarioExcepcion {
		try {
			usuario.agregarSugerencia(paqueteAbsDegustacion);
			usuario.agregarSugerencia(paqueteAbsPaisaje);

		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		usuario.agregarSugerencia(paqueteCostosoDegust);
	}
		
	@Test(expected = UsuarioExcepcion.class)
	public void queNoAgregueAtraccionSiNoPuedeTiempo() throws UsuarioExcepcion {
		try {
			usuario.agregarSugerencia(atraccionDegustacion);
			usuario.agregarSugerencia(atraccionAventura);
			usuario.agregarSugerencia(atraccionPaisaje);

		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		usuario.agregarSugerencia(atraccionLarga);
	}
	
	@Test(expected = UsuarioExcepcion.class)
	public void queNoAgreguePaqueteSiNoPuedeTiempo() throws UsuarioExcepcion {
		try {
			usuario.agregarSugerencia(paqueteAbsDegustacion);
			usuario.agregarSugerencia(paqueteAbsPaisaje);

		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		usuario.agregarSugerencia(paqueteLargoDegust);
	}
	
	
	
	
	@Test(expected = UsuarioExcepcion.class)
	public void queNoAgregaSugerenciasSiNoPuede() throws UsuarioExcepcion {
		try {
			usuario.agregarSugerencia(atraccionDegustacion);
			usuario.agregarSugerencia(paqueteAbsPaisaje);
		} catch (UsuarioExcepcion e) {
			e.printStackTrace();
		}

		usuario.agregarSugerencia(paqueteCostosoDegust);
	}
}