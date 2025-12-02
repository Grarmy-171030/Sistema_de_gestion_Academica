import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
public class SistemaAcademicoGUI {
    private static void mostrarError(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
    public static void opcionAgregarEstudiante() {
        try {
            String nombre = JOptionPane.showInputDialog("1. Agregar Nombre del estudiante:");
            if (nombre == null) return;
            String id = JOptionPane.showInputDialog("ID del estudiante:");
            if (id == null) return;
            Main.agregarEstudiante(nombre, id);
            JOptionPane.showMessageDialog(null,
                    "Estudiante agregado correctamente.");
        } catch (DatoVacioException e) {
            mostrarError("Error: " + e.getMessage(), "Error de datos");
        }
    }
    public static void opcionMostrarEstudiantes() {
        if (Main.listaEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No hay estudiantes registrados.",
                    "2. Mostrar Estudiantes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder("LISTA DE ESTUDIANTES:\n");
        for (Estudiante e : Main.listaEstudiantes) {
            sb.append(e.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(),
                "2. Mostrar Estudiantes", JOptionPane.PLAIN_MESSAGE);
    }
    public static void opcionBuscarEstudiante() {
        String busqueda = JOptionPane.showInputDialog("3. Buscar Estudiante" +
                "\n\nBuscar por nombre o ID:");
        if (busqueda == null) return;
        for (Estudiante e : Main.listaEstudiantes) {
            if (e.getNombre().equalsIgnoreCase(busqueda) || e.getId().equalsIgnoreCase(busqueda)) {
                JOptionPane.showMessageDialog(null,
                        "Encontrado: " + e.toString(),
                        "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null,
                "Estudiante no encontrado.",
                "Búsqueda Fallida", JOptionPane.WARNING_MESSAGE);
    }
    public static void opcionEliminarEstudiante() {
        String eliminar = JOptionPane.showInputDialog("4. Eliminar Estudiante\n\n" +
                "Eliminar por nombre o ID:");
        if (eliminar == null) return;
        for (Estudiante e : Main.listaEstudiantes) {
            if (e.getNombre().equalsIgnoreCase(eliminar) || e.getId().equals(eliminar)) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Seguro que desea eliminar a " + e.getNombre() + "?",
                        "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Main.listaEstudiantes.remove(e);
                    Main.registroCalificaciones.remove(e.getId());
                    JOptionPane.showMessageDialog(null, "Estudiante eliminado.",
                            "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null,
                "No se encontró un estudiante con ese nombre o ID.",
                "Eliminación Fallida", JOptionPane.WARNING_MESSAGE);
    }
    public static void opcionRegistrarMateria() {
        String materia = JOptionPane.showInputDialog("5. Registrar Materia" +
                "\n\nIngrese el nombre de la materia a registrar:");
        if (materia == null) return;
        if (Main.catalogoMaterias.add(materia)) {
            JOptionPane.showMessageDialog(null,
                    "Materia registrada con éxito.",
                    "Registro de Materia", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null
                    , "La materia ya está registrada.",
                    "Registro de Materia", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void opcionVerificarMateria() {
        String materia = JOptionPane.showInputDialog("6. Verificar Materia" +
                "\n\nIngrese el nombre de la materia a verificar:");
        if (materia == null) return;
        if (Main.catalogoMaterias.contains(materia)) {
            JOptionPane.showMessageDialog(null
                    , "La materia está registrada.",
                    "Verificación", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null
                    , "La materia no está registrada.",
                    "Verificación", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void opcionContarMaterias() {
        JOptionPane.showMessageDialog(null,
                "Total de materias registradas: " + Main.catalogoMaterias.size(),
                "7. Contar Materias", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void opcionRegistrarCalificacion() {
        String id = JOptionPane.showInputDialog("8. Agregar Calificación" +
                "\n\nID del estudiante:");
        if (id == null) return;
        String inputCalificacion = JOptionPane.showInputDialog("Ingrese la calificación:");
        if (inputCalificacion == null) return;
        try {
            double calificacion = Double.parseDouble(inputCalificacion);
            Main.agregarCalificacion(id, calificacion);
            JOptionPane.showMessageDialog(null, "Calificación registrada.",
                    "Registro de Nota", JOptionPane.INFORMATION_MESSAGE);
        } catch (CalificacionFueraDeRangoException e) {
            mostrarError("Error: " + e.getMessage(), "Calificación inválida");
        } catch (NumberFormatException e) {
            mostrarError("Debe ingresar un número válido.", "Error de formato");
        }
    }
    public static void opcionVerPromedio() {
        String id = JOptionPane.showInputDialog("9. Calcular Promedio" +
                "\n\nID del estudiante: ");
        if (id == null) return;
        HashMap<String, ArrayList<Double>> registro = Main.registroCalificaciones;
        ArrayList<Double> cal = registro.get(id);

        if (cal == null || cal.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El estudiante no tiene calificaciones.");
            return;
        }
        try {
            double suma = 0;
            for (double c : cal) suma += c;
            double promedio = suma / cal.size();
            JOptionPane.showMessageDialog(null,
                    "Promedio: " + String.format("%.2f", promedio),
                    "Resultado Promedio", JOptionPane.INFORMATION_MESSAGE);
        } catch (ArithmeticException e) {
            mostrarError("Error: No se pudo realizar el cálculo del promedio."
                    , "Error de Cálculo");
        }
    }
    public static void opcionProbarArray() {
        String inputIndice = JOptionPane.showInputDialog("10. Probar Array con Excepción" +
                "\n\nIngrese un índice del array (0 a 4):");
        if (inputIndice == null) return;
        try {
            int indice = Integer.parseInt(inputIndice);
            int[] numeros = {10, 20, 30, 40, 50};
            String resultado = "Valor en el índice " + indice + ": " + numeros[indice];
            JOptionPane.showMessageDialog(null, resultado,
                    "Acceso Exitoso", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            mostrarError("Error: Debe ingresar un número entero."
                    , "Error de Entrada");

        } catch (ArrayIndexOutOfBoundsException e) {
            mostrarError("Error: Índice fuera de rango. Debe estar entre 0 y 4."
                    , "Índice Inválido");

        } finally {
            JOptionPane.showMessageDialog(null,
                    "Fin del intento de acceso al array (try-catch-finally)."
                    , "Proceso Finalizado", JOptionPane.PLAIN_MESSAGE);
        }
    }
    public static void main(String[] args) {
        while (true) {
            String menu = "=== Sistema Académico ===\n"
                    + " 1. Agregar Estudiante\n"
                    + " 2. Mostrar Estudiantes\n"
                    + " 3. Buscar Estudiante\n"
                    + " 4. Eliminar Estudiante\n"
                    + " 5. Registrar Materia\n"
                    + " 6. Verificar Materia\n"
                    + " 7. Contar Materias\n"
                    + " 8. Agregar Calificación\n"
                    + " 9. Calcular Promedio\n"
                    + " 10. Probar Array con Excepción\n"
                    + " 0. Salir\n"
                    + "Seleccione una opción:";
            String opcion = JOptionPane.showInputDialog(null
                    , menu, "Menú Principal", JOptionPane.PLAIN_MESSAGE);
            if (opcion == null) break;
            switch (opcion.trim()) {
                case "1" -> opcionAgregarEstudiante();
                case "2" -> opcionMostrarEstudiantes();
                case "3" -> opcionBuscarEstudiante();
                case "4" -> opcionEliminarEstudiante();
                case "5" -> opcionRegistrarMateria();
                case "6" -> opcionVerificarMateria();
                case "7" -> opcionContarMaterias();
                case "8" -> opcionRegistrarCalificacion();
                case "9" -> opcionVerPromedio();
                case "10" -> opcionProbarArray();
                case "0" -> System.exit(0);
                default -> mostrarError("Opción no válida.", "Error");
            }
        }
    }
}