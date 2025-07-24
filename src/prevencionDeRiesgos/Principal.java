/**
 * 
 * @author Yerko Osorio
 * @author Luis Guevara
 * @author Jhoseph Quiroga
 * @author Norma Armijo
 * @version 1.0
 */
package prevencionDeRiesgos;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	static Scanner sc = new Scanner(System.in);
	static Usuario usuario = new Usuario();
	static Contenedor contenedor = new Contenedor();
	static boolean continuarMain = true;

	public static void main(String[] args) {

		mostrarBienvenida();

		do {
			mostrarMenu();
			int opcionMenu = elegirOpcionMenu();
			ejecutarOpcion(opcionMenu);

		} while (continuarMain);

	}

	public static void mostrarBienvenida() {
		System.out.println("=".repeat(50));
		System.out.println("-".repeat(6)
				+ " BIENVENIDO AL SISTEMA DE GESTIÓN DE PREVENCIÓN DE RIESGOS "
				+ "-".repeat(6));
		System.out.println("=".repeat(50));
	}

	public static void mostrarMenu() {
		System.out.println("\n" + "-".repeat(22) + " MENÚ " + "-".repeat(22));
		System.out.println("(1) Almacenar cliente");
		System.out.println("(2) Almacenar profesional");
		System.out.println("(3) Almacenar administrativo");
		System.out.println("(4) Almacenar capacitación");
		System.out.println("(5) Eliminar usuario");
		System.out.println("(6) Listar usuarios");
		System.out.println("(7) Listar usuarios por tipo");
		System.out.println("(8) Listar capacitaciones");
		System.out.println("(9) Salir del programa");
	}

	public static int elegirOpcionMenu() {
		try {
			System.out
					.println("\nIngrese el número de la opción seleccionada: ");
			int opcionSeleccionada = sc.nextInt();
			sc.nextLine();
			return opcionSeleccionada;
		} catch (InputMismatchException e) {
			System.out.println("⚠️ Entrada inválida. Debe ingresar un número.");
			sc.nextLine();
			return -1000;
		}

	}

	public static void ejecutarOpcion(int opcion) {
		switch (opcion) {
		case 0: // CARGAR DATOS DE PRUEBA
			BaseDatos.ingresarDatos(contenedor); // OPCIONAL
			break;
		case 1: // INGRESAR CLIENTE
			ingresarCliente();
			break;
		case 2: // INGRESAR PROFESIONAL
			ingresarProfesional();
			break;
		case 3: // INGRESAR ADMINISTRATIVO
			ingresarAdministrativo();
			break;
		case 4: // ALMACENAR CAPACITACIÓN
			// ingresarCapacitacion;
			break;
		case 5: // ELIMINAR USUARIO
			eliminarUsuario();
			break;
		case 6: // LISTAR USUARIOS
			listarUsuarios();
			break;
		case 7: // LISTAR USUARIOS POR TIPO
			listarUsuariosTipo();
			break;
		case 8: // LISTAR CAPACITACIONES
			listarCapacitaciones();
			break;
		case 9: // SALIR DEL PROGRAMA
			continuarMain = false;
			System.out.printf("El programa ha finalizado.\n");
			break;
		default: // ÓPCION INGRESADA NO ES DE 1 A 9
			System.out.printf("Opción no válida, intente nuevamente.\n");
			break;
		}

	}

	private static void agregarAccidente() {
		// TODO Auto-generated method stub

	}

	public static void ingresarCapacitacion() {

		Capacitacion capacitacion = new Capacitacion();

		int cantidadAsistentes;
		String rutCliente, dia, hora, lugar, duracion;

		LocalDate fechaHoraActual = LocalDate.now();
		String momentoActual = fechaHoraActual.toString();
		int identificador = momentoActual.hashCode();

		while (true) {
			try {
				System.out.println("Ingrese el RUT del cliente (obligatorio):");
				rutCliente = sc.nextLine();
				capacitacion.setRutCliente(rutCliente);
				;
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		while (true) {
			try {
				System.out.println(
						"Ingrese el día de la semana que se realizará la capacitación (obligatorio):");
				dia = sc.nextLine();
				capacitacion.setDia(dia);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		while (true) {
			try {
				System.out.println(
						"Ingrese la hora de la capacitación [formato HH:MM] (obligatorio): ");
				hora = sc.nextLine();
				capacitacion.setHora(hora);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			try {
				System.out.println(
						"Ingrese el lugar de la capacitación (obligatorio): ");
				lugar = sc.nextLine();
				capacitacion.setLugar(lugar);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			try {
				System.out.println(
						"Ingrese la duración de la capacitación (obligatorio):");
				duracion = sc.nextLine();
				capacitacion.setDuracion(duracion);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			try {
				System.out.println(
						"Ingrese la cantidad de asistentes (obligatorio):");
				cantidadAsistentes = sc.nextInt();
				sc.nextLine();
				capacitacion.setCantidadAsistentes(cantidadAsistentes);
				break;
			} catch (InputMismatchException e) {
				System.out.println("⚠️ Ingrese un número válido.");
				sc.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		// Almacena la capacitación en el contenedor
		contenedor.almacenarCapacitacion(capacitacion);
		;

		// Impresiones de capacitación
		System.out.println("Capacitación ingresada con éxito.");

		System.out.println(capacitacion.mostrarDetalle());

	}

	/**
	 * Método estático que solicita los datos necesarios para crear un objeto
	 * Cliente a través de la entrada estándar (consola).
	 * 
	 * Valida cada campo obligatorio mediante estructuras while (true) con
	 * manejo de excepciones personalizadas, permitiendo reintentar hasta que la
	 * entrada sea válida.
	 * 
	 * Una vez creado y validado, el cliente es mostrado por consola usando
	 * 
	 * @throws IllegalArgumentException si algún campo obligatorio no cumple con
	 *                                  la validación definida en sus
	 *                                  respectivos setters.
	 * @throws InputMismatchException   si se ingresan caracteres no numéricos
	 *                                  donde se espera un número.
	 */
	public static void ingresarCliente() {

		Cliente cliente = new Cliente();
		String rut, nombres, fechaNacimiento, apellidos, telefono, afp,
				direccion, comuna;
		int sistemaSalud = 0;
		int edad = -1;

		while (true) {
			try {
				System.out.println("Ingrese su RUT (obligatorio):");
				rut = sc.nextLine();
				cliente.setRun(rut);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		while (true) {
			try {
				System.out.println(
						"Ingrese su fecha de naciento DD/MM/AAAA (Obligatorio):");
				fechaNacimiento = sc.nextLine();
				cliente.setFechaNacimiento(fechaNacimiento);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		while (true) {
			try {
				System.out.println("Ingrese sus nombres (obligatorio): ");
				nombres = sc.nextLine();
				cliente.setNombres(nombres);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			try {
				System.out.println("Ingrese sus apellidos (obligatorio): ");
				apellidos = sc.nextLine();
				cliente.setApellidos(apellidos);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			try {
				System.out.println("Ingrese su teléfono (obligatorio):");
				telefono = sc.nextLine();
				cliente.setTelefono(telefono);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		try {
			System.out.println("Ingrese su AFP: ");
			afp = sc.nextLine();
			cliente.setAfp(afp);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		while (true) {
			try {
				System.out.println("Seleccione una opción (obligatorio):");
				System.out.println("(1) Fonasa");
				System.out.println("(2) Isapre");
				sistemaSalud = sc.nextInt();
				sc.nextLine();
				cliente.setSistemaSalud(sistemaSalud);
				break;
			} catch (InputMismatchException e) {
				System.out.println("⚠️ Ingrese un número válido.");
				sc.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		try {
			System.out.println("Ingrese su direccion: ");
			direccion = sc.nextLine();
			cliente.setDireccion(direccion);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("Ingrese su comuna: ");
			comuna = sc.nextLine();
			cliente.setComuna(comuna);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		while (true) {
			try {
				System.out.println("Ingrese su edad (obligatorio): ");
				edad = sc.nextInt();
				sc.nextLine();
				cliente.setEdad(edad);
				break;
			} catch (InputMismatchException e) {
				System.out.println("⚠️ Ingrese un número válido.");
				sc.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		// Almacena el cliente en el contenedor
		contenedor.almacenarCliente(cliente);

		// Impresiones de cliente
		System.out.println("Cliente ingresado con éxito.");

		System.out.println(cliente.mostrarDatos());

	}

	public static void ingresarProfesional() {
		Profesional profesional = new Profesional();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String rut, nombre, fechaNacimiento, titulo, fechaIngresoString;
		LocalDate fechaIngreso;

		while (true) {
			try {
				System.out.println("Ingrese su RUT (obligatorio):");
				rut = sc.nextLine();
				profesional.setRun(rut);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		while (true) {
			try {
				System.out.println(
						"Ingrese su fecha de naciento DD/MM/AAAA (Obligatorio):");
				fechaNacimiento = sc.nextLine();
				profesional.setFechaNacimiento(fechaNacimiento);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		while (true) {
			try {
				System.out
						.println("Ingrese su nombre completo (obligatorio): ");
				nombre = sc.nextLine();
				profesional.setNombre(nombre);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		while (true) {
			try {
				System.out.println("Ingrese su título (obligatorio): ");
				titulo = sc.nextLine();
				profesional.setTitulo(titulo);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		try {
			System.out.println("Ingrese su fecha de ingreso DD/MM/AAAA: ");
			fechaIngresoString = sc.nextLine();
			fechaIngreso = LocalDate.parse(fechaIngresoString, formatter);
			profesional.setFechaIngreso(fechaIngreso);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (DateTimeException e) {

		}

		// Almacena el cliente en el contenedor
		contenedor.almacenarProfesional(profesional);

		// Impresiones de cliente
		System.out.println("Profesional ingresado con éxito.");

		System.out.println(profesional.mostrarDatos());

	}

	public static void ingresarAdministrativo() {
		Administrativo administrativo = new Administrativo();
		String rut, nombre, fechaNacimiento, area, experienciaPrevia;

		while (true) {
			try {
				System.out.println("Ingrese su RUT (obligatorio):");
				rut = sc.nextLine();
				administrativo.setRun(rut);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		while (true) {
			try {
				System.out.println(
						"Ingrese su fecha de naciento DD/MM/AAAA (Obligatorio):");
				fechaNacimiento = sc.nextLine();
				administrativo.setFechaNacimiento(fechaNacimiento);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		while (true) {
			try {
				System.out
						.println("Ingrese su nombre completo (obligatorio): ");
				nombre = sc.nextLine();
				administrativo.setNombre(nombre);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		while (true) {
			try {
				System.out.println("Ingrese su área (obligatorio): ");
				area = sc.nextLine();
				administrativo.setArea(area);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		try {
			System.out.println("Ingrese su experiencia previa: ");
			experienciaPrevia = sc.nextLine();
			administrativo.setExperienciaPrevia(experienciaPrevia);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		// Almacena el cliente en el contenedor
		contenedor.almacenarAdministrativo(administrativo);

		// Impresiones de cliente
		System.out.println("Administrativo ingresado con éxito.");

		System.out.println(administrativo.mostrarDatos());

	}

	public static void eliminarUsuario() {
		// Usuario usuario = new Usuario();
		Cliente cliente = new Cliente();
		String rut;

		while (true) {
			try {
				System.out.println(
						"Ingrese el RUT (99.999.999-9) del usuario a eliminar o menu:");
				rut = sc.nextLine();
				if (rut.equalsIgnoreCase("menu")) {
					return;
				}
				// usuario.setRun(rut);
				cliente.setRun(rut);
				contenedor.eliminarUsuario(rut);
				System.out.println("Usuario RUT: " + rut + " => ELIMINADO");
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}

	}

	public static void listarUsuarios() {
		try {
			String lista = contenedor.listarUsuarios();
			System.out.println("\n" + "-".repeat(16) + " LISTA DE USUARIOS "
					+ "-".repeat(16));
			System.out.println(lista);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void listarUsuariosTipo() {
		// DECLARACION DE VARIALBES
		String lista;
		// IMPRIME TITULO
		System.out.println("\n" + "-".repeat(11) + " LISTA DE USUARIOS POR TIPO"
				+ "-".repeat(11));

//		while (true) {
//			try {
//				String tipo;
//				System.out.println("Escriba el tipo de usuario o Menu");
//				System.out.println("- Administrativo");
//				System.out.println("- Cliente");
//				System.out.println("- Profesional");
//				System.out.println("- Menu");
//				tipo = sc.nextLine();
//				if(tipo.equalsIgnoreCase("menu")){
//					return;
//				}
//				
//				lista = contenedor.listarUsuariosPorTipo(tipo);
//				
//				//IMPRIME EL TITULO SEGÚN EL TIPO
//				if(tipo.equalsIgnoreCase("administrativo")) {
//					System.out.println("\n" + "-".repeat(13) + " LISTA DE "
//							+ tipo.toUpperCase() + "S" + "-".repeat(13));
//				} else if (tipo.equalsIgnoreCase("cliente")) {
//					System.out.println("\n" + "-".repeat(16) + " LISTA DE "
//							+ tipo.toUpperCase() + "S" + "-".repeat(16));
//				} else if (tipo.equalsIgnoreCase("profesional")) {
//					System.out.println("\n" + "-".repeat(14) + " LISTA DE "
//							+ tipo.toUpperCase() + "ES" + "-".repeat(14));
//				}
//				
//				//IMPRIME LA LISTA
//				System.out.println(lista);
//				break;
//			} catch (IllegalArgumentException e) {
//				System.out.println(e.getMessage());
//			}
//
//		}

		while (true) {
			try {
				int tipoInt;
				String tipoString;

				System.out.println("(1) Administrativos");
				System.out.println("(2) Clientes");
				System.out.println("(3) Profesionales");
				System.out.println("(4) Menu");
				System.out.println(
						"Elija una ópcion de tipos de usuario o Menu: ");
				tipoInt = sc.nextInt();
				sc.hasNextLine();

				// RETORNA AL MENÚ
				if (tipoInt == 4) {
					return;
				}

				tipoString = tipoInt == 1 ? "administrativo"
						: tipoInt == 2 ? "cliente"
								: tipoInt == 3 ? "profesional" : null;
				if (tipoString == null) {
					throw new IllegalArgumentException(
							"⚠️ Debe ingresar un número valido.");
				}

				lista = contenedor.listarUsuariosPorTipo(tipoString);

				// IMPRIME EL TITULO SEGÚN EL TIPO
				if (tipoString.equalsIgnoreCase("administrativo")) {
					System.out.println("\n" + "-".repeat(13) + " LISTA DE "
							+ tipoString.toUpperCase() + "S" + "-".repeat(13));
				} else if (tipoString.equalsIgnoreCase("cliente")) {
					System.out.println("\n" + "-".repeat(16) + " LISTA DE "
							+ tipoString.toUpperCase() + "S" + "-".repeat(16));
				} else if (tipoString.equalsIgnoreCase("profesional")) {
					System.out.println("\n" + "-".repeat(14) + " LISTA DE "
							+ tipoString.toUpperCase() + "ES" + "-".repeat(14));
				}

				// IMPRIME LA LISTA
				System.out.println(lista);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}

		}

	}

	public static void listarCapacitaciones() {
		try {
			String lista = contenedor.listarCapacitaciones();
			System.out.println("\n" + "-".repeat(13)
					+ " LISTA DE CAPACITACIONES " + "-".repeat(13));
			System.out.println(lista);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
