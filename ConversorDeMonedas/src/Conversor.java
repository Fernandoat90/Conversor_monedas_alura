import java.util.Scanner;

public class Conversor {
    private Scanner teclado;

    public Conversor(Scanner teclado) {
        this.teclado = teclado;
    }

    public void Convertir(Moneda moneda) {
        System.out.println("Indique la cantidad de " + moneda.getBase_code() + " que desea convertir:");

        double cantidad = obtenerCantidadValida();

        double total = cantidad * moneda.getConversion_rate();
        System.out.println("El total es " + total + " " + moneda.getTarget_code()+"\n");
    }

    // Método para validar la entrada de la cantidad
    private double obtenerCantidadValida() {
        while (true) {
            if (teclado.hasNextDouble()) {
                double cantidad = teclado.nextDouble();
                if (cantidad > 0) {
                    return cantidad;
                } else {
                    System.out.println("Por favor ingrese un monto positivo.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.next();
            }
        }
    }
}

