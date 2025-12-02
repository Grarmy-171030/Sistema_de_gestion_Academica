import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import javax.swing.JOptionPane;
public class Main {
    //Agregar Estudiantes
    public static void agregarEstudiante(String nombre, String id) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoVacioException("El nombre no puede estar vacío.");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new DatoVacioException("El ID no puede estar vacío.");
        }
        listaEstudiantes.add(new Estudiante(nombre, id));
    }
    //Agregar calificacion
    public static void agregarCalificacion(String id, double calificacion)
            throws CalificacionFueraDeRangoException {
        if (calificacion < 0.0 || calificacion > 10.0) {
            throw new CalificacionFueraDeRangoException(
                    "La calificación debe estar entre 0.0 y 10.0");
        }
        registroCalificaciones.putIfAbsent(id, new ArrayList<>());
        registroCalificaciones.get(id).add(calificacion);
    }
    //Mostrar Estudiantes
    public static void mostrarEstudiante() {
        if (listaEstudiantes.isEmpty()) {
            System.out.println("Estudiante no encontrado");
            return;
        }
        System.out.println("\nLISTA DE ESTUDIANTES");
        for (Estudiante e : listaEstudiantes) {
            System.out.println(e);
        }
    }
    //Buscar Estudiante
    public static void buscarEstudiante(Scanner scanner) {
        System.out.println("Buscar por nombre o ID: ");
        String busqueda = scanner.nextLine();
        for (Estudiante e : listaEstudiantes) {
            if (e.getNombre().equalsIgnoreCase(busqueda)
                    || e.getId().equalsIgnoreCase(busqueda)) {
                System.out.println("Encontrado: " + e);
                return;
            }
        }
        System.out.println("Estudiante no encontrado");
    }
    //Eliminar Estudiante
    public static void eliminarEstudiante(Scanner scanner) {
        System.out.println("Eliminar por nombre o ID: ");
        String eliminar = scanner.nextLine();
        for (Estudiante e : listaEstudiantes) {
            if (e.getNombre().equalsIgnoreCase(eliminar)
                    || e.getId().equals(eliminar)) {
                listaEstudiantes.remove(e);
                registroCalificaciones.remove(e.getId());
                System.out.println("Estudiante eliminado.");
                return;
            }
        }
        System.out.println("No se encontró un estudiante con ese nombre o ID.");
    }
    //Registro de las Materias
    public static void registrarMateria(Scanner scanner) {
        System.out.println("Ingrese el nombre de la materia a registrar: ");
        String materia = scanner.nextLine();
        if (catalogoMaterias.add(materia)) {
            System.out.println("Materia registrada con éxito.");
        } else {
            System.out.println("La materia ya está registrada.");
        }
    }
    //Verificacion de Materias
    public static void verificarMaterias(Scanner scanner) {
        System.out.println("Ingrese el nombre de la materia : ");
        String materia = scanner.nextLine();
        if (catalogoMaterias.contains(materia)) {
            System.out.println("La materia está registrada.");
        } else {
            System.out.println("La materia no está registrada.");
        }
    }
    //Total de Materias Obtenidas
    public static void contarMaterias() {
        System.out.println("Total de materias registradas: " + catalogoMaterias.size());
    }
    // Calcular Promedio
    public static void calcularPromedio(Scanner scanner) {
        System.out.println("Ingrese el ID del estudiante: ");
        String id = scanner.nextLine();
        ArrayList<Double> calificaciones = registroCalificaciones.get(id);
        try {
            if (calificaciones == null) {
                System.out.println("Este estudiante no tiene calificaciones registradas.");
                return;
            }
            if (calificaciones.isEmpty()) {
                throw new ArithmeticException("No hay calificaciones para calcular el promedio.");
            }
            double suma = 0;
            for (double calificacion : calificaciones) {
                suma += calificacion;
            }
            double promedio = suma / calificaciones.size();
            System.out.println("Promedio de calificaciones: " + promedio);
        } catch (ArithmeticException e) {
            System.out.println("Error al calcular promedio: " + e.getMessage());
        }
    }
    //Ejercicio que prueba el try y catch
    public static void ejercicioArray(Scanner sc) {
        int[] numeros = {10, 20, 30, 40, 50};
        System.out.print("Ingrese un índice del array (0 a 4): ");
        try {
            int indice = sc.nextInt();
            sc.nextLine();

            System.out.println("Valor en el índice " + indice + ": " + numeros[indice]);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: índice fuera de rango. Debe estar entre 0 y 4.");

        } catch (java.util.InputMismatchException e) {
            System.out.println("Error de entrada: Debe ingresar un número entero.");
            sc.nextLine();
        } finally {
            System.out.println("Fin del intento de acceso al array.");
        }
    }
    // Creación de Listas
    public static  ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
    public static HashSet<String> catalogoMaterias = new HashSet<>();
    public static HashMap<String, ArrayList<Double>> registroCalificaciones = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n===========================");
            System.out.println(" 1. Agregar Estudiante ");
            System.out.println(" 2. Mostrar Estudiantes");
            System.out.println(" 3. Buscar Estudiante");
            System.out.println(" 4. Eliminar Estudiante");
            System.out.println(" 5. Registrar Materia");
            System.out.println(" 6. Verificar Materia");
            System.out.println(" 7. Contar Materias");
            System.out.println(" 8. Agregar Calificación ");
            System.out.println(" 9. Calcular Promedio ");
            System.out.println(" 10. Ver Promedio ");
            System.out.println(" 11. Probar Array con Excepción");
            System.out.println(" 0. Salir");
            System.out.println("Seleccione una opción:");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 1 -> SistemaAcademicoGUI.opcionAgregarEstudiante();
                    case 2 -> mostrarEstudiante();
                    case 3 -> buscarEstudiante(scanner);
                    case 4 -> eliminarEstudiante(scanner);
                    case 5 -> registrarMateria(scanner);
                    case 6 -> verificarMaterias(scanner);
                    case 7 -> contarMaterias();
                    case 8 -> SistemaAcademicoGUI.opcionRegistrarCalificacion();
                    case 9 -> calcularPromedio(scanner);
                    case 10 -> SistemaAcademicoGUI.opcionVerPromedio();
                    case 11 -> ejercicioArray(scanner);
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese solo números para la opción del menú.");
                scanner.nextLine();
                opcion = -1;
            }
        } while (opcion != 0);
        scanner.close();
    }
}