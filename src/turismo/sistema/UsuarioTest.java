package turismo.sistema;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	
	private Atraccion a;
	private Usuario u;
	
    @Before
    public void setUp() {
		this.a = new Atraccion("Recorrido Palermo","Aventura",10000,20.5,10);
		this.u = new Usuario("Juani","Aventura",50000,200.5);
    }
	
	@Test
	public void tieneFondos() {
		boolean tieneFondos = u.puedeCostearSugerencia(a.getCosto());
		assertEquals(tieneFondos,true);
	}
	
	@Test
	public void preferenciaCorrecta() {
		boolean preferencia = u.preferenciaAtracc(a.getTipo());
		assertEquals(preferencia,true);
	}	
	
	@Test
	public void tieneTiempoDisponible() {
		boolean tiempoDisp = u.tieneTiempoDispo(a.getDuracion());
		assertEquals(tiempoDisp,true);
	}
	
	@Test
	public void puedeAdquirirSugerencia() {
		boolean puedeAdquirirSugerencia = u.puedeAdquirirSugerencia(a.getCosto(),a.getDuracion());
		assertEquals(puedeAdquirirSugerencia,true);
	}
	
	@Test
	public void sugerenciaAgregada() {
		u.agregarSugerencia(a);
		boolean sugerenciaAgregada = (u.getPresupuestoDisp() == 40000) && (u.getTiempoDisp() == 180) && u.estaEnItinerario(a);
		assertEquals(sugerenciaAgregada,true);
		assertEquals(u.estaEnItinerario(a),true);//ESTE ASSERT FALLA POR QUE ESTA EN INTINERARIO DEVUELVE FALSE
	}
	
	public void escribirIntinerario() {
		u.escribirItinerario();
	}

}
