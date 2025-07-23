package prevencionDeRiesgos;

import java.text.SimpleDateFormat;

public class BaseDatos {

	public static void ingresarDatos(Contenedor contenedor) {

		ingresarCapacitaciones(contenedor);
		ingresarAdministrativos(contenedor);
		ingresarClientes(contenedor);
		ingresarProfesionales(contenedor);
		System.out.println("Base de datos cargada.");

	}

	public static void ingresarCapacitaciones(Contenedor contenedor) {
		// Crear capacitaciones
		Capacitacion capacitacion1 = new Capacitacion(1001, // identificador
				"18.234.567-5", // rutCliente
				"Lunes", // día
				"10:00", // hora
				"Sala de reuniones 3", // lugar
				"90 minutos", // duración
				25 // cantidad de asistentes
		);

		Capacitacion capacitacion2 = new Capacitacion(1002, // identificador
				"16.715.932-K", // rutCliente
				"Miércoles", // día
				"14:30", // hora
				"Oficina Central Piso 2", // lugar
				"120 minutos", // duración
				30 // cantidad de asistentes
		);

		// Almacena primera capacitacion
		contenedor.almacenarCapacitacion(capacitacion1);
		// Almacena segunda capacitacion
		contenedor.almacenarCapacitacion(capacitacion2);
	}

	public static void ingresarAdministrativos(Contenedor contenedor) {
		// Creacion de administrativos

		try {
			Administrativo admin1 = new Administrativo("Luis Torres", // nombre
					"1980-04-15", // fechaNacimiento
					"14.256.789-K", // run (válido)
					"Recursos Humanos", // área
					"Más de 10 años en selección" // experienciaPrevia
			);

			contenedor.almacenarAdministrativo(admin1);

			Administrativo admin2 = new Administrativo("Camila Rojas", // nombre
					"1992-09-30", // fechaNacimiento
					"16.543.210-K", // run (válido)
					"Finanzas", // área
					"Experiencia en contabilidad y gestión presupuestaria");

			contenedor.almacenarAdministrativo(admin2);

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void ingresarClientes(Contenedor contenedor) {
		// Creacion de clientes
		try {
			Cliente cliente = new Cliente("2.222.222-8", // run
					"Juanito", // nombres
					"1990-05-12", // fechaNacimiento
					"Pérez González", // apellidos
					"+56912345678", // teléfono
					"Cuprum", // afp
					1, // sistemaSalud (1 para Fonasa, 2 para Isapre)
					"Av. Siempre Viva 742", // dirección
					"Santiago", // comuna
					34 // edad
			);

			// Almacena cliente 1
			contenedor.almacenarCliente(cliente);

			Cliente cliente2 = new Cliente("18.234.567-9", // run
					"María Fernanda", // nombres
					"1985-10-22", // fechaNacimiento
					"López Salinas", // apellidos
					"+56987654321", // teléfono
					"Habitat", // afp
					2, // sistemaSalud (2 para Isapre)
					"Calle Falsa 123", // dirección
					"Providencia", // comuna
					39 // edad
			);

			// Almacena cliente 2
			contenedor.almacenarCliente(cliente2);

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void ingresarProfesionales(Contenedor contenedor) {

		// Creacion de profesionales
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Profesional prof1 = new Profesional("María José Contreras", // nombre
					"1985-06-20", // fechaNacimiento
					"14.256.780-6", // run (válido)
					"Ingeniera en Prevención de Riesgos", // título
					sdf.parse("2020-03-15") // fechaIngreso
			);
			contenedor.almacenarProfesional(prof1);

			Profesional prof2 = new Profesional("Andrés Salazar", // nombre
					"1990-11-10", // fechaNacimiento
					"17.345.210-1", // run (válido)
					"Psicólogo Laboral", // título
					sdf.parse("2018-07-01") // fechaIngreso
			);
			contenedor.almacenarProfesional(prof2);

		} catch (Exception e) {
			System.out
					.println("❌ Error al crear profesional: " + e.getMessage());
		}
	}

}
