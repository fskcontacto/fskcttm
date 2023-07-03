package turismo.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import turismo.excepciones.AtraccionExcepcion;
import turismo.excepciones.PaqueteExcepcion;
import turismo.excepciones.SugerenciaExcepcion;
import turismo.sistema.Atraccion;
import turismo.sistema.Paquete;
import turismo.sistema.PaqueteAbsoluto;
import turismo.sistema.PaqueteAxB;
import turismo.sistema.PaquetePorcentual;

public class PaqueteTest {

	private Atraccion atraccionPaisaje1, atraccionPaisaje2, atraccionDegustacion1, atraccionDegustacion2,
			atraccionAventura;
	private Map<String, Atraccion> atracciones;
	private Map<String, Atraccion> atraccionesGratuitas;
	private Map<String, Atraccion> atraccionesAXB;
	private Map<String, Atraccion> sinAtracciones;
	private Paquete paqueteAbs;
	private Paquete paqueteAXB;
	private Paquete paquetePorc;
	private List<Paquete> paquetes;
	private static final int cupoAtrPaisaje1 = 6;
	private static final int cupoAtrPaisaje2 = 5;
	private static final int cupoAtrDegustacion = 4;
	private static final int cupoAtrGratis = 6;
	private static final int cupoMin = Math.min(cupoAtrGratis,
			Math.min(cupoAtrDegustacion, Math.min(cupoAtrPaisaje1, cupoAtrPaisaje2)));
	private static final double costoAtrPaisaje1 = 10;
	private static final double costoAtrPaisaje2 = 20;
	private static final double costoAtrDegustacion = 25;
	private static final double costoAtrGratuita1 = 60;
	private static final double costoAtrGratuita2 = 60;
	private static final double costoTotal = costoAtrPaisaje1 + costoAtrPaisaje2 + costoAtrDegustacion;
	private static final double costoOriginalAXB = costoTotal + costoAtrGratuita1 + costoAtrGratuita2;
	private static final double porcentajeDescontado = 0.1;
	private static final double precioAbsoluto = costoTotal - 10;
	private static final double totalConDescuento = costoTotal - costoTotal * porcentajeDescontado;

	@Before
	public void setup() {
		atracciones = new HashMap<String, Atraccion>();
		atraccionesGratuitas = new HashMap<String, Atraccion>();
		atraccionesAXB = new HashMap<String, Atraccion>();
		sinAtracciones = new HashMap<String, Atraccion>();
		paquetes = new ArrayList<Paquete>();
		try {
			atraccionPaisaje1 = new Atraccion("Moria", "Paisaje", costoAtrPaisaje1, 2, cupoAtrPaisaje1);
			atraccionPaisaje2 = new Atraccion("Fortuna", "Paisaje", costoAtrPaisaje2, 3, cupoAtrPaisaje2);
			atraccionDegustacion1 = new Atraccion("Giratoria", "Degustación", costoAtrDegustacion, 4,
					cupoAtrDegustacion);
			atraccionDegustacion2 = new Atraccion("Mirador", "Degustación", costoAtrGratuita1, 5, cupoAtrGratis);
			atraccionAventura = new Atraccion("Telesferico", "Paisaje", costoAtrGratuita2, 6, cupoAtrGratis);
		} catch (AtraccionExcepcion | SugerenciaExcepcion e) {
			e.printStackTrace();
		}

		atracciones.put("1", atraccionPaisaje1);
		atracciones.put("2", atraccionPaisaje2);
		atracciones.put("3", atraccionDegustacion1);

		atraccionesGratuitas.put("4", atraccionDegustacion2);
		atraccionesGratuitas.put("5", atraccionAventura);

		atraccionesAXB.putAll(atracciones);
		atraccionesAXB.putAll(atraccionesGratuitas);
		try {
			paqueteAbs = new PaqueteAbsoluto("Paisaje", precioAbsoluto, atracciones);
			paqueteAXB = new PaqueteAxB("Aventura", atraccionesAXB, atraccionesGratuitas);
			paquetePorc = new PaquetePorcentual("Degustación", porcentajeDescontado, atracciones);
		} catch (PaqueteExcepcion | SugerenciaExcepcion e) {
			e.printStackTrace();
		}
		paquetes.add(paqueteAXB);
		paquetes.add(paqueteAbs);
		paquetes.add(paquetePorc);

	}

	@Test
	public void queTieneElementos() {
		Assert.assertEquals(3, atracciones.size());
	}

	@Test
	public void quePorcentajeSeDescuente() {
		boolean totalValido = paquetePorc.getCosto() == totalConDescuento;
		Assert.assertEquals(totalValido, true);
	}

	@Test
	public void quePaqueteAXBDescuenteCosto() {
		boolean totalValido = paqueteAXB.getCosto() == costoTotal;
		Assert.assertEquals(totalValido, true);
	}

	@Test
	public void quePaqueteAbsolutoDescuenteCosto() {
		boolean totalValido = paqueteAbs.getCosto() == precioAbsoluto;
		Assert.assertEquals(totalValido, true);
	}

	@Test
	public void queObtengocostoOriginal() {
		boolean originalAbs = paqueteAbs.getMontoOrigPaquete() == costoTotal;
		boolean originalAXB = paqueteAXB.getMontoOrigPaquete() == costoOriginalAXB;
		boolean originalPorc = paquetePorc.getMontoOrigPaquete() == costoTotal;

		Assert.assertEquals(true, originalAbs);
		Assert.assertEquals(true, originalAXB);
		Assert.assertEquals(true, originalPorc);
	}

	@Test
	public void queObtengaMenorCupo() {
		Assert.assertEquals(cupoMin, paqueteAbs.getCupoDisponible());
		Assert.assertEquals(cupoMin, paqueteAXB.getCupoDisponible());
		Assert.assertEquals(cupoMin, paquetePorc.getCupoDisponible());

	}

	@Test
	public void queReduzcaCuposDeAtracciones() {
		for (Paquete paquete : paquetes) {
			try {
				paquete.reducirCupo();
			} catch (AtraccionExcepcion e) {
				Assert.fail(e.getMessage());
			}
		}

		Assert.assertEquals(cupoAtrPaisaje1 - paquetes.size(), atraccionPaisaje1.getCupoDisponible());
		Assert.assertEquals(cupoAtrPaisaje2 - paquetes.size(), atraccionPaisaje2.getCupoDisponible());
		Assert.assertEquals(cupoAtrDegustacion - paquetes.size(), atraccionDegustacion1.getCupoDisponible());
	}

	@Test
	public void queReduzcaMaxCupos() {

		for (Paquete paquete : paquetes) {

			try {

				int cupoDisponible = paquete.getCupoDisponible();
				for (int i = 0; i < cupoDisponible; ++i) {
					paquete.reducirCupo();
				}

				Assert.assertEquals(0, paquete.getCupoDisponible());

			} catch (AtraccionExcepcion e) {
				Assert.fail(e.getMessage());
			}
		}
	}

	@Test
	public void queGenereBienNombre() {
		String nombreEsperado = "Moria, Fortuna, Giratoria";
		Assert.assertEquals(nombreEsperado, paqueteAbs.getNombre());
	}

	@Test(expected = PaqueteExcepcion.class)
	public void queNoCreePaqueteAXBSinAtraccionesGratuitas() throws SugerenciaExcepcion, PaqueteExcepcion {
		new PaqueteAxB("Degustación", atracciones, sinAtracciones);
	}

	@Test(expected = PaqueteExcepcion.class)
	public void quePaqueteAbsolutoNoTengaCostoFinalMayorAlOriginal() throws SugerenciaExcepcion, PaqueteExcepcion {
		new PaqueteAbsoluto("Degustación", costoTotal + 10, atracciones);
	}

	@Test(expected = PaqueteExcepcion.class)
	public void quePaqueteAbsolutoNoAcepteCostoNoPositivo() throws SugerenciaExcepcion, PaqueteExcepcion {
		new PaqueteAbsoluto("Degustación", -10, atracciones);
	}

	@Test
	public void queSeOrdenePorPrecioYTiempo() {
		paquetes.sort(Comparator.reverseOrder());
		List<Paquete> paquetesOrdenados = new ArrayList<Paquete>();
		paquetesOrdenados.add(paqueteAXB);
		paquetesOrdenados.add(paquetePorc);
		paquetesOrdenados.add(paqueteAbs);

		Assert.assertEquals(paquetes, paquetesOrdenados);

	}
}