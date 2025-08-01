/**
 * @author Jhoseph Quiroga
 * @version 1.0
 */
package prevencionDeRiesgos;

import java.util.ArrayList;

/**
 * Clase que representa el contenedor principal del sistema. Almacena listas de
 * objetos que implementan de la interfaz Asesoria, incluyendo clientes,
 * administrativos y profesionales, además de capacitaciones.
 */
public class Contenedor {

	// ======================= ATRIBUTOS =======================

	/**
	 * Lista de objetos que implementan la interfaz Asesoria (Clientes,
	 * Profesionales y Administrativos).
	 */
	private ArrayList<Asesoria> asesorias = new ArrayList<>();
	/**
	 * Lista de capacitaciones almacenadas.
	 */
	private ArrayList<Capacitacion> capacitaciones = new ArrayList<>();

	// ======================= CONSTRUCTOTES =======================

	/**
	 * Constructor vacío de la clase Contenedor.
	 */
	public Contenedor() {
	}
	
	// =========================== GETTER ===========================
	
	/**
	 * @return the asesorias
	 */
	public ArrayList<Asesoria> getAsesorias() {
		return asesorias;
	}



	/**
	 * @return the capacitaciones
	 */
	public ArrayList<Capacitacion> getCapacitaciones() {
		return capacitaciones;
	}
	
	
	// ======================= MÉTODOS ESPECIALES =======================


	/**
	 * Almacena un objeto de tipo Cliente en la lista de asesorías.
	 * 
	 * @param cliente Cliente a almacenar
	 */
	public void almacenarCliente(Cliente cliente) {
		if (cliente == null) {
	        throw new IllegalArgumentException("⚠️ Cliente no puede ser nulo");
	    }
		asesorias.add(cliente);
	}

	/**
	 * Almacena un objeto de tipo Profesional en la lista de asesorías.
	 * 
	 * @param profesional Profesional a almacenar
	 */
	public void almacenarProfesional(Profesional profesional) {
		if (profesional == null) {
	        throw new IllegalArgumentException("⚠️ Profesional no puede ser nulo");
	    }
		asesorias.add(profesional);
	}

	/**
	 * Almacena un objeto de tipo Administrativo en la lista de asesorías.
	 * 
	 * @param administrativo Administrativo a almacenar
	 */
	public void almacenarAdministrativo(Administrativo administrativo) {
		if (administrativo == null) {
	        throw new IllegalArgumentException("⚠️ Administrativo no puede ser nulo");
	    }
		asesorias.add(administrativo);
	}

	/**
	 * Almacena una capacitación en la lista de capacitaciones.
	 * 
	 * @param capacitacion Capacitacion a almacenar
	 */
	public void almacenarCapacitacion(Capacitacion capacitacion) {
		if (capacitacion == null) {
	        throw new IllegalArgumentException("⚠️ Capacitacion no puede ser nulo");
	    }
		capacitaciones.add(capacitacion);
	}

	/**
	 * Elimina un usuario del sistema según su RUT validado. Si el RUT no es
	 * válido, muestra un mensaje de error.
	 * 
	 * @param rut RUT del usuario a eliminar (en formato 99.999.999-9)
	 */
	public void eliminarUsuario(String rut) {

		
			rut = Validacion.validarRut(rut);
		

		if (asesorias == null || asesorias.isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ No hay usuarios agregados a asesorias.");
		}
		
		boolean eliminado = false;
		for (int i = 0; i < asesorias.size(); i++) {
			Asesoria asesoria = asesorias.get(i);

			if (asesoria instanceof Usuario) {
				Usuario usuario = (Usuario) asesoria;

				if (usuario.getRun().equalsIgnoreCase(rut)) {
					asesorias.remove(i);
					eliminado = true;
					return;
				}
			}

		}
		
		if (eliminado == false) {
			throw new IllegalArgumentException(
					"⚠️ RUT no existe en usuarios.");
		}

	}

	/**
	 * Lista todos los usuarios almacenados en el sistema (clientes,
	 * profesionales y administrativos).
	 * 
	 * @return Cadena con la información de los usuarios
	 */
	public String listarUsuarios() {
		
		if (asesorias == null || asesorias.isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ No hay usuarios agregados a asesorias.");
		}

		StringBuilder resultado = new StringBuilder();

		for (Asesoria asesoria : asesorias) {
			if (asesoria instanceof Usuario) {
				Usuario usuario = (Usuario) asesoria;
				resultado.append(usuario.analizarUsuario()).append("\n");
			}

		}

		return resultado.toString();
	}

	/**
	 * Lista los usuarios de un tipo específico: cliente, profesional o
	 * administrativo.
	 * 
	 * @param tipo Tipo de usuario a listar (cliente, profesional o
	 *             administrativo)
	 * @return Cadena con la información de los usuarios del tipo indicado
	 */
	public String listarUsuariosPorTipo(String tipo) {
		
		if (asesorias == null || asesorias.isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ No hay usuarios agregados a asesorias.");
		}
		
		if (!"administrativo".equalsIgnoreCase(tipo) 
				&& !"cliente".equalsIgnoreCase(tipo) 
				&& !"profesional".equalsIgnoreCase(tipo)) {
			throw new IllegalArgumentException(
					"⚠️ El tipo ingresado es inválido.");
		}
		
		StringBuilder resultado = new StringBuilder();

		for (Asesoria asesoria : asesorias) {
			if ("administrativo".equalsIgnoreCase(tipo)
					&& asesoria instanceof Administrativo) {
				Administrativo administrativo = (Administrativo) asesoria;
				resultado.append(administrativo.analizarUsuario()).append("\n");
			} else if ("cliente".equalsIgnoreCase(tipo)
					&& asesoria instanceof Cliente) {
				Cliente cliente = (Cliente) asesoria;
				resultado.append(cliente.analizarUsuario()).append("\n");
			} else if ("profesional".equalsIgnoreCase(tipo)
					&& asesoria instanceof Profesional) {
				Profesional profesional = (Profesional) asesoria;
				resultado.append(profesional.analizarUsuario()).append("\n");
			}

		}
		
		if (resultado.toString() == null || resultado.toString().isEmpty()) {
			if ("administrativo".equalsIgnoreCase(tipo)) {
				throw new IllegalArgumentException(
						"⚠️ No hay usuarios tipo administrativos.");
			}
			if ("cliente".equalsIgnoreCase(tipo)) {
				throw new IllegalArgumentException(
						"⚠️ No hay usuarios tipo clientes.");
			}
			if ("profesional".equalsIgnoreCase(tipo)) {
				throw new IllegalArgumentException(
						"⚠️ No hay usuarios tipo profesionales.");
			}
		}

		return resultado.toString();
	}

	/**
	 * Lista todas las capacitaciones almacenadas en el sistema.
	 * 
	 * @return Cadena con la información de las capacitaciones
	 */
	public String listarCapacitaciones() {

		if (capacitaciones == null || capacitaciones.isEmpty()) {
			throw new IllegalArgumentException(
					"⚠️ No hay capacitaciones agregadas.");
		}
		
		StringBuilder resultado = new StringBuilder();

		for (Capacitacion capacitacion : capacitaciones) {
			resultado.append(capacitacion.toString()).append("\n");
		}
		return resultado.toString();
	}

}
