/**
 * @author Norma Armijo
 * @version 1.0
 */
package prevencionDeRiesgos;


/**
 * Interfaz que define el contrato para análisis de usuarios.
 * 
 * Esta interfaz establece un contrato común para que las diferentes
 * clases puedan proporcionar análisis específico de usuarios según
 * su tipo (Administrativo, Profesional, Cliente, Usuario.).
 * 
 * @since 1.0
 */
public interface Asesoria {
	
    /**
     * Analiza la información del usuario y retorna un resumen.
     * ...
     * @return una cadena de texto con el análisis del usuario
     */
	String analizarUsuario();
	
}
