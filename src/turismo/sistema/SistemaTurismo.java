package turismo.sistema;

import java.util.ArrayList;
import java.util.List;


public class SistemaTurismo {
	private List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<Paquete> paquetes;
	
	public SistemaTurismo(List<Usuario> usuarios, List<Atraccion> atracciones, List<Paquete> paquetes) {
		this.usuarios = usuarios;
		this.atracciones = atracciones;
		this.paquetes = paquetes;
	}
	
	public void sugerirUsuario(Usuario u,Sugerencia sug) {
		
		
		
		
		
		
		
		
		
		
		
		/////////
		
		List<Paquete> ppu = new ArrayList<>();
		double costoPaquete = paquetes.getMontoOrigPaquete(); //estoy mareado, tiene que ser el monto de la sugerencia o del paquete? -> rta, el precio es por c/entidad hija
		Paquete p = new Paquete(ppu,costoPaquete);

		//List<Paquete> ppu = buscarPrefEnPaquete(u,paquetes);
		//primero ofertar paquetes
		//pantallaItinerPromo(sug);
		
		//if(respuestaUsuario)
		
		
		// Lógica de agregar atracciones
		
		
		
		//despues ofertar atracciones
		
		
	}
	
	
	/*
	public List<Sugerencia> buscarPrefSugerencia(){
		
		List<Sugerencia> pa = new ArrayList<>();
		
		for(Atraccion a: atracciones) {
			
			if(u.preferenciaAtracc(a.nombre)) {
				pa.add(a);
			}
		}
		
		return pa;
	}*/
	
	
}
