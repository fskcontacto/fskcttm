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

	private Atraccion atraccion;
	private Map<String, Atraccion> atracciones;
	private Map<String, Atraccion> atraccionesGratuitas;
	private Paquete paqueteAbs;
	private Paquete paqueteAXB;
	private Paquete paquetePorc;
	
	@Before
	public void setup() {
		atracciones = new HashMap<String, Atraccion>();
		try {
			atraccion = new Atraccion("Moria", "Paisaje", 10, 2, 6);
		} catch (AtraccionExcepcion e) {
						e.printStackTrace();
		} catch (SugerenciaExcepcion e) {
			e.printStackTrace();
		}
		
		atracciones.put("1",atraccion);
		atracciones.put("2",atraccion);
		atracciones.put("3",atraccion);

		atraccionesGratuitas.put("4",atraccion);
		atraccionesGratuitas.put("5",atraccion);
		
		try {
			paqueteAbs = new PaqueteAbsoluto("Paisaje",33.33,atracciones);
			paqueteAXB = new PaqueteAxB("Aventura",atracciones,atraccionesGratuitas);
			paquetePorc = new PaquetePorcentual("Degustación",5.00,atracciones); //Hacer clases de test para el resto de las clases
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
	public void queContieneElemento() {
		Assert.assertTrue(atracciones.containsKey("1"));
		Assert.assertTrue(atracciones.containsKey("2"));
		Assert.assertTrue(atracciones.containsKey("3"));
	}
	
	@Test
	public void queObtengocostoOriginal() {
		
		
	
	
	}

}
