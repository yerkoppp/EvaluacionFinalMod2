package prevencionDeRiesgos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class zBORRAR_JQA {

	static Scanner sc = new Scanner(System.in);
	static ArrayList<Cliente> clientes = new ArrayList<>();
	static Contenedor contenedor = new Contenedor();


	public static void main(String[] args) {

		
		BaseDatos.ingresarDatos(contenedor);
		//ingresarCliente();

//		pruebasContenedor();
		

		//Principal.ingresarCapacitacion();
	}

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
				System.out.println("Ingese su teléfono (obligatorio):");
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
		
		System.out.println(cliente.analizarUsuario());
		


	}

	public static void pruebasContenedor() {

		// Imprime lista de capacitaciones
		System.out.println("Imprime Lista de capacitaciones");
		System.out.println(contenedor.listarCapacitaciones());

		// Imprime solo clientes
		System.out.println("Lista de clientes");
		System.out.println("-".repeat(25));
		System.out.println(contenedor.listarUsuariosPorTipo("cliente"));

		// Imprime solo Administrativo
		System.out.println("Lista de administrativos");
		System.out.println("-".repeat(25));
		System.out.println(contenedor.listarUsuariosPorTipo("administrativo"));

		// Imprime solo Profesionales
		System.out.println("Lista de profesionales");
		System.out.println("-".repeat(25));
		System.out.println(contenedor.listarUsuariosPorTipo("profesional"));

		// Lista de todos los usuarios
		System.out.println("Lista de usuarios");
		System.out.println("-".repeat(25));
		System.out.println(contenedor.listarUsuarios());

		// Eliminar usuario por rut en este caso un cliente
		contenedor.eliminarUsuario("16.715.932-K");
		// Eliminar usuario por rut en este caso un administrativo
		contenedor.eliminarUsuario("14.256.789-K");
		// Elimimar usuario por rut en este caso un profesional
		contenedor.eliminarUsuario("17.345.210-1");

		// Lista de todos los usuarios con las eliminaciones
		System.out.println("Lista de usuarios sin los eliminados");
		System.out.println("-".repeat(25));
		System.out.println(contenedor.listarUsuarios());
	}


}
