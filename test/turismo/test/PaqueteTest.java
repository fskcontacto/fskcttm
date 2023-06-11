package turismo.test;

import java.util.HashMap;
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

	private Atraccion atraccion,atraccion2,atraccion3,atraccion4,atraccion5;
	private Map<String, Atraccion> atracciones;
	private Map<String, Atraccion> atraccionesGratuitas;
	private Paquete paqueteAbs;
	private Paquete paqueteAXB;
	private Paquete paquetePorc;
	private int cupoAbs = 6;
	private int cupoAXB = 5;
	private int cupoPorc = 4;
	private int cupoAtrGratis = 6;
	private double costoAtr1 = 10;
	private double costoAtr2 = 20;
	private double costoAtr3 = 25;
	private double costoAtrGratiuita1 = 60;
	private double costoAtrGratiuita2 = 60;
	private double costoTotal = costoAtr1 + costoAtr2 + costoAtr3;
	private double porcentajeDescontado = 0.1;
	private double axbDescontado = costoAtr1 + costoAtr2 + costoAtr3 - costoAtrGratiuita1 - costoAtrGratiuita2;
	private double totalConDescuento = costoTotal - costoTotal * porcentajeDescontado;
	
	@Before
	public void setup() {
		atracciones = new HashMap<String, Atraccion>();
		atraccionesGratuitas = new HashMap<String, Atraccion>();
		try {
			atraccion = new Atraccion("Moria", "Paisaje", costoAtr1, 2, cupoAbs);
			atraccion2 = new Atraccion("Fortuna", "Paisaje", costoAtr2, 3, cupoAXB);
			atraccion3 = new Atraccion("Giratoria", "Paisaje", costoAtr3, 4, cupoPorc);
			atraccion4 = new Atraccion("Mirador", "Paisaje", costoAtrGratiuita1, 5, cupoAtrGratis);
			atraccion5 = new Atraccion("Telesferico", "Paisaje", costoAtrGratiuita2, 6, cupoAtrGratis);
		} catch (AtraccionExcepcion e) {
						e.printStackTrace();
		} catch (SugerenciaExcepcion e) {
			e.printStackTrace();
		}
		
		atracciones.put("1",atraccion);
		atracciones.put("2",atraccion2);
		atracciones.put("3",atraccion3);
		

		atraccionesGratuitas.put("4",atraccion4);
		atraccionesGratuitas.put("5",atraccion5);
		
		try {
			paqueteAbs = new PaqueteAbsoluto("Paisaje",totalConDescuento,atracciones);
			paqueteAXB = new PaqueteAxB("Aventura",atracciones,atraccionesGratuitas);
			paquetePorc = new PaquetePorcentual("Degustacion",porcentajeDescontado,atracciones); //Hacer clases de test para el resto de las clases
		} catch (PaqueteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SugerenciaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		boolean totalValido = paqueteAXB.getCosto() == axbDescontado;   
		Assert.assertEquals(totalValido, true);
	}
	public void absolutoDescontado() {
		boolean totalValido = paqueteAbs.getCosto() == costoTotal;   
		Assert.assertEquals(totalValido, true);
	}
	
	@Test
	public void menorCupo() {
		boolean menorCupo = paqueteAbs.getCupoDisponible() == cupoPorc;
		Assert.assertEquals(menorCupo, true);
		System.out.println(paqueteAXB.getCosto());
	}
	
	/*@Test
	public void queContieneElemento() {
		Assert.assertTrue(atracciones.containsKey("1"));
		Assert.assertTrue(atracciones.containsKey("2"));
		Assert.assertTrue(atracciones.containsKey("3"));
	}
	
	@Test
	public void queObtengocostoOriginal() {
		
		
	
	
	}*/

}
