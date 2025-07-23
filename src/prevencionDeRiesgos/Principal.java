package prevencionDeRiesgos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
	
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Cliente> clientes = new ArrayList<>();
	
	public static void main(String[] args) {
		
		pedirDatosCliente();

	}

	public static void pedirDatosCliente() {

		Cliente cliente = new Cliente();
		String rut, nombres, fechaNacimiento, apellidos, telefono, afp, direccion, comuna;
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
				System.out.println("Ingrese su fecha de naciento DD/MM/AAAA (Obligatorio):");
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
			} catch (InputMismatchException  e) {
				System.out.println("⚠️ Ingrese un número válido.");
				sc.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		//Impresiones
		System.out.println("Cliente ingresado con éxito.");

		System.out.println(cliente.toString());
		
		System.out.println(cliente.analizarUsuario());
		
		
	}

}
