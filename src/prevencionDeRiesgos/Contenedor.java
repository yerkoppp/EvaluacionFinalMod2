package prevencionDeRiesgos;

import java.util.ArrayList;

public class Contenedor {

	// Atributos
	private ArrayList<Asesoria> asesorias = new ArrayList<>();
	private ArrayList<Capacitacion> capacitaciones = new ArrayList<>();

	public Contenedor() {

	}

	// Métodos

	public void almacenarCliente(Cliente cliente) {
		asesorias.add(cliente);
	}

	public void almacenarProfecional(Profesional profesional) {
		asesorias.add(profesional);
	}
	
	public void almacenarAdministrativo(Administrativo administrativo) {
		asesorias.add(administrativo);
	}
	
	public void almacenarCapacitacion(Capacitacion capacitacion) {
		capacitaciones.add(capacitacion);
	}
	
	public void eliminarUsuario(String rut) {
		
		try {
	        rut = Validacion.validarRut(rut);
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	        return;
	    }
		
		for(int i = 0; i < asesorias.size(); i++) {
			Asesoria asesoria = asesorias.get(i);
			
			if(asesoria instanceof Usuario) {
				Usuario usuario = (Usuario) asesoria;
				
				if (usuario.getRun().equalsIgnoreCase(rut)) {
					asesorias.remove(i);
					return;
				}
			}
			
		}
		
	}
	
	public String listarUsuarios() {
		
		StringBuilder resultado = new StringBuilder();
		
		for(Asesoria asesoria: asesorias) {
			if (asesoria instanceof Usuario) {
				Usuario usuario = (Usuario) asesoria;
				resultado.append(usuario.toString()).append("\n");
			}
			
		}
		
		return resultado.toString();
	}
	
	public String listarUsuariosPorTipo(String tipo) {
		StringBuilder resultado = new StringBuilder();
		
		for(Asesoria asesoria: asesorias) {
			if (asesoria instanceof Administrativo) {
				Administrativo administrativo = (Administrativo) asesoria;
				resultado.append(administrativo.toString()).append("\n");
			} else if (asesoria instanceof Cliente) {
				Cliente cliente = (Cliente) asesoria;
				resultado.append(cliente.toString()).append("\n");
			} else if (asesoria instanceof Profesional) {
				Profesional profesional = (Profesional) asesoria;
				resultado.append(profesional.toString()).append("\n");
			}
			
		}
		
		return resultado.toString();
	}
	
	public String listarCapacitaciones() {
		
		StringBuilder resultado = new StringBuilder();
		
		for(Capacitacion capacitacion: capacitaciones) {
			resultado.append(capacitacion.toString()).append("\n");
		}
		return "";
	}

}
