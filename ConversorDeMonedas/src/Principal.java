import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        BuscarOpciones consulta = new BuscarOpciones();
        Conversor convertir = new Conversor(teclado);
        int opcion = 0;

        while (opcion != 9) {
            System.out.println("""
                1. Convertir Dólar Estado unidense a Pesos Argentinos.
                2. Convertir Pesos Argentinos a Dólar estadounidense.
                3. Convertir Real Brasileño a a Dólar estadounidense.
                4. Convertir Dólar Estado unidense a Real Brasileño.
                5. Convertir Peso Colombiano a Dólar estadounidense.
                6. Convertir Dólar Estado unidense a Peso Colombiano.
                9. Salir
                
                Pulse la opción que desea realizar:""");

            opcion = obtenerOpcionValida(teclado);

            try {
                Moneda moneda = consulta.BuscarOpciones(opcion);
                convertir.Convertir(moneda);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Método para manejar la validación de la opción ingresada
    private static int obtenerOpcionValida(Scanner teclado) {
        int opcion;
        while (true) {
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                if (opcion == 1 || opcion == 2 || opcion == 3 || opcion == 4 || opcion == 5 || opcion == 6 || opcion == 9) {
                    return opcion;
                } else {
                    System.out.println("Opción no válida. Intente nuevamente.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.next();  // Limpiar el buffer
            }
        }
    }
}

