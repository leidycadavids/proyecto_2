import java.util.*;
import java.util.stream.*;

public class PrincipalPersonas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Persona> personas = new ArrayList<>();

        System.out.println("=== Ingreso de personas ===");
        for (int i = 0; i < 6; i++) {
            System.out.println("Persona #" + (i + 1));
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = Integer.parseInt(scanner.nextLine());
            System.out.print("Género (masculino/femenino): ");
            String genero = scanner.nextLine().toLowerCase();
            System.out.print("Sueldo por hora: ");
            double sueldoHora = Double.parseDouble(scanner.nextLine());
            System.out.print("Cargo: ");
            String cargo = scanner.nextLine().toLowerCase();

            personas.add(new Persona(nombre, apellido, edad, genero, sueldoHora, cargo));
            System.out.println();
        }

        // === Operaciones con Lambdas ===
        System.out.println("Cantidad total de personas: " + personas.size());

        double promedioEdad = personas.stream()
                .mapToInt(Persona::getEdad)
                .average()
                .orElse(0);
        System.out.println("Promedio de edades: " + promedioEdad);

        long mayoresEdad = personas.stream()
                .filter(p -> p.getEdad() >= 18)
                .count();
        System.out.println("Cantidad de personas mayores de edad: " + mayoresEdad);

        System.out.println("Personas cuyo nombre empieza con 'A':");
        personas.stream()
                .filter(p -> p.getNombre().startsWith("A"))
                .forEach(p -> System.out.println(p.getNombre() + " " + p.getApellido()));

        System.out.println("Personas cuyos apellidos contienen la letra 'M':");
        personas.stream()
                .filter(p -> p.getApellido().toUpperCase().contains("M"))
                .forEach(p -> System.out.println(p.getNombre() + " " + p.getApellido()));

        // === Parte 3: Más operaciones con lambdas ===

        System.out.println("\nSueldos por 8 horas (directores masculinos):");
        personas.stream()
                .filter(p -> p.getGenero().equals("masculino"))
                .filter(p -> p.getCargo().equals("director"))
                .peek(p -> System.out.print("Nombre: " + p.getNombre() + " " + p.getApellido() + " "))
                .map(p -> p.getSueldoHora() * 8)
                .forEach(sueldo -> System.out.println("Sueldo por 8 horas: $" + sueldo));

        System.out.println("\nPrimera desarrolladora (femenina):");
        personas.stream()
                .filter(p -> p.getCargo().equals("desarrollador"))
                .filter(p -> p.getGenero().equals("femenino"))
                .findFirst()
                .ifPresent(p -> System.out.println(p.getNombre() + " " + p.getApellido()));

        System.out.println("\nDesarrollador con mejor sueldo por hora:");
        Optional<Persona> masGana = personas.stream()
                .filter(p -> p.getCargo().equals("desarrollador"))
                .max(Comparator.comparingDouble(Persona::getSueldoHora));
        if (masGana.isPresent()) {
            Persona p = masGana.get();
            System.out.println(p.getNombre() + " " + p.getApellido() + " $" + p.getSueldoHora() + " por hora");
        }

        System.out.println("\nMujeres ordenadas por nombre:");
        personas.stream()
                .filter(p -> p.getGenero().equals("femenino"))
                .sorted(Comparator.comparing(Persona::getNombre))
                .forEach(p -> System.out.println(p.getNombre() + " " + p.getApellido()));
    }
}

