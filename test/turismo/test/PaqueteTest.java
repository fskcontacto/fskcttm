package turismo.test;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
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

	private Atraccion atraccion, atraccion2, atraccion3, atraccion4, atraccion5;
	private Map<String, Atraccion> atracciones;
	private Map<String, Atraccion> atraccionesGratuitas;
	private Paquete paqueteAbs;
	private Paquete paqueteAXB;
	private Paquete paquetePorc;
	private List<Paquete> paquetes;
	private int cupoAtr1 = 6;
	private int cupoAtr2 = 5;
	private int cupoAtr3 = 4;
	private int cupoAtrGratis = 6;
	private int cupoMin = Math.min(cupoAtrGratis, Math.min(cupoAtr3, Math.min(cupoAtr1, cupoAtr2)));
	private double costoAtr1 = 10;
	private double costoAtr2 = 20;
	private double costoAtr3 = 25;
	private double costoAtrGratuita1 = 60;
	private double costoAtrGratuita2 = 60;
	private double costoTotal = costoAtr1 + costoAtr2 + costoAtr3;
	private double costoOriginalAXB = costoTotal + costoAtrGratuita1 + costoAtrGratuita2;
	private double porcentajeDescontado = 0.99;
	private double precioAbsoluto = costoTotal - 10;
	private double totalConDescuento = costoTotal - costoTotal * porcentajeDescontado;

	@Before
	public void setup() {
		atracciones = new HashMap<String, Atraccion>();
		atraccionesGratuitas = new HashMap<String, Atraccion>();
		paquetes = new ArrayList<Paquete>();
		try {
			atraccion = new Atraccion("Moria", "Paisaje", costoAtr1, 2, cupoAtr1);
			atraccion2 = new Atraccion("Fortuna", "Paisaje", costoAtr2, 3, cupoAtr2);
			atraccion3 = new Atraccion("Giratoria", "Paisaje", costoAtr3, 4, cupoAtr3);
			atraccion4 = new Atraccion("Mirador", "Paisaje", costoAtrGratuita1, 5, cupoAtrGratis);
			atraccion5 = new Atraccion("Telesferico", "Paisaje", costoAtrGratuita2, 6, cupoAtrGratis);
		} catch (AtraccionExcepcion e) {
			e.printStackTrace();
		} catch (SugerenciaExcepcion e) {
			e.printStackTrace();
		}

		atracciones.put("1", atraccion);
		atracciones.put("2", atraccion2);
		atracciones.put("3", atraccion3);

		atraccionesGratuitas.put("4", atraccion4);
		atraccionesGratuitas.put("5", atraccion5);

		try {
			paqueteAbs = new PaqueteAbsoluto("Paisaje", precioAbsoluto, atracciones);
			paqueteAXB = new PaqueteAxB("Aventura", atracciones, atraccionesGratuitas);
			paquetePorc = new PaquetePorcentual("Degustacion", porcentajeDescontado, atracciones); // Hacer clases de test para el resto de las clases
		} catch (PaqueteExcepcion e) {
			e.printStackTrace();
		} catch (SugerenciaExcepcion e) {
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
	public void porcentajeDescontado() {
		boolean totalValido = paquetePorc.getCosto() == totalConDescuento;
		Assert.assertEquals(totalValido, true);
	}

	@Test
	public void axbDescontada() {
		boolean totalValido = paqueteAXB.getCosto() == costoTotal;
		Assert.assertEquals(totalValido, true);
	}

	@Test
	public void absolutoDescontado() {
		boolean totalValido = paqueteAbs.getCosto() == precioAbsoluto;
		Assert.assertEquals(totalValido, true);
	}

	
	@Test
	public void queObtengocostoOriginal() {
		boolean originalAbs = paqueteAbs.getMontoOrigPaquete() == costoTotal;
		boolean originalAXB = paqueteAXB.getMontoOrigPaquete() == costoOriginalAXB;
		boolean originalPorc = paquetePorc.getMontoOrigPaquete() == costoTotal;
		
		Assert.assertEquals(originalAbs, true);
		Assert.assertEquals(originalAXB, true);
		Assert.assertEquals(originalPorc, true);
	}
	

	@Test
	public void menorCupo() {
		Assert.assertEquals(cupoMin, paqueteAbs.getCupoDisponible());
		Assert.assertEquals(cupoMin, paqueteAXB.getCupoDisponible());
		Assert.assertEquals(cupoMin, paquetePorc.getCupoDisponible());
		
	}
	
	@Test(expected = AtraccionExcepcion.class)
	public void queNoReduzcaMaxCuposPermitidos() {

		for (Paquete paquete: paquetes) {
			
			try {
				
				for (int i = 0; i < paquete.getCupoTotal(); ++i) {
					paquete.reducirCupo();
				}
				
				paquete.reducirCupo();
			} catch (AtraccionExcepcion e) {
				Assert.assertTrue(e.getMessage().contains("No hay cupo disponible."));
			}
		}
	}

	@Test
	public void queReduzcaCuposDeAtracciones() {
		for (Paquete paquete: paquetes ) {
			try {
				paquete.reducirCupo();
			} catch (AtraccionExcepcion e) {
				e.printStackTrace();
			}
		}
		
		Assert.assertEquals(cupoAtr1 - paquetes.size(), atraccion.getCupoDisponible());
		Assert.assertEquals(cupoAtr2 - paquetes.size(), atraccion2.getCupoDisponible());
		Assert.assertEquals(cupoAtr3 - paquetes.size(), atraccion3.getCupoDisponible());
	}
	
	@Test
	public void queReduzcaMaxCupos() {

		for (Paquete paquete: paquetes) {
			
			try {
				
				int cupoDisponible = paquete.getCupoDisponible();
				for (int i = 0; i < cupoDisponible; ++i) {
					paquete.reducirCupo();
				}
				
				Assert.assertEquals(0, paquete.getCupoDisponible());
				
			} catch (AtraccionExcepcion e) {
				e.printStackTrace();
			}
		}
	}
}


