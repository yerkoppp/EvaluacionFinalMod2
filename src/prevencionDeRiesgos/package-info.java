/**
 * Sistema de gestión de prevención de riesgos laborales.
 *
 * Este paquete contiene las clases principales del sistema, diseñado para ayudar a empresas en sectores industriales, mineros y de construcción a administrar procesos de prevención de accidentes y cumplir con normativas legales. El sistema permite:
 * <ul>
 *   <li>Registrar y gestionar clientes, profesionales, administrativos y capacitaciones.</li>
 *   <li>Gestionar visitas en terreno, revisiones y accidentes.</li>
 *   <li>Validar datos críticos como RUT, fechas, horas y formatos específicos.</li>
 *   <li>Generar reportes y estadísticas para mejorar la toma de decisiones.</li>
 * </ul>
 *
 * El sistema está organizado en varias clases clave:
 * <ul>
 *   <li><b>Principal</b>: Clase principal que inicia el programa y gestiona el menú interactivo.</li>
 *   <li><b>Contenedor</b>: Almacena y gestiona usuarios (clientes, profesionales, administrativos) y capacitaciones.</li>
 *   <li><b>Usuario</b>: Clase base para todos los tipos de usuarios.</li>
 *   <li><b>Cliente</b>, <b>Profesional</b>, <b>Administrativo</b>: Subclases de Usuario con funcionalidades específicas.</li>
 *   <li><b>Capacitacion</b>: Gestiona detalles de las capacitaciones realizadas.</li>
 *   <li><b>VisitaEnTerreno</b>: Registra visitas realizadas en terreno.</li>
 *   <li><b>Revision</b>: Gestionar revisiones asociadas a visitas en terreno.</li>
 *   <li><b>Accidente</b>: Registrar y gestionar accidentes ocurridos.</li>
 *   <li><b>Asesoria</b>: Interfaz que define métodos comunes para analizar usuarios.</li>
 *   <li><b>Validacion</b>: Clase utilitaria para validar entradas de datos (fechas, RUT, etc.).</li>
 * </ul>
 *
 *
 * @author Luis Guevara
 * @author Yerko Osorio
 * @author Jhoseph Quiroga
 * @author Norma Armijo
 * @version 1.0
 * @since 08/14/2025
 * 
 * <br /><br /><a href="https://github.com/yerkoppp/EvaluacionFinalMod2.git">Ver Repositorio en Github</a><br />
 * 
 */
package prevencionDeRiesgos;