import java.util.*;

public class PrincipalAnimales {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> animales = new ArrayList<>();
        Map<String, List<Animal>> clasificacion = new TreeMap<>();

        System.out.println("=== Ingreso de 6 animales ===");

        for (int i = 0; i < 6; i++) {
            System.out.println("Animal #" + (i + 1));
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Tipo (terrestre/aéreo/acuático): ");
            String tipo = scanner.nextLine().toLowerCase();

            System.out.print("Género (masculino/femenino): ");
            String genero = scanner.nextLine().toLowerCase();

            Animal animal = new Animal(nombre, tipo, genero);
            animales.add(animal);

            clasificacion.putIfAbsent(tipo, new ArrayList<>());
            clasificacion.get(tipo).add(animal);
        }

        System.out.println("\n=== CLASIFICACIÓN DE ANIMALES ===");
        clasificacion.forEach((tipo, lista) -> {
            System.out.println(tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + ":");
            lista.forEach(a -> System.out.println("  " + a.getNombre()));
        });
    }
}
