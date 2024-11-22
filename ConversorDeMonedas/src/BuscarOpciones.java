import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscarOpciones {

    public Moneda BuscarOpciones(int tipoDeConversion) {
        String base = null;
        String target = null;

        switch (tipoDeConversion) {
            case 1:
                base = "USD";
                target = "ARS";
                break;
            case 2:
                base = "ARS";
                target = "USD";
                break;
            case 3:
                base = "BRL";
                target = "USD";
                break;
            case 4:
                base = "USD";
                target = "BRL";
                break;
            case 5:
                base = "COP";
                target = "USD";
                break;
            case 6:
                base = "USD";
                target = "COP";
                break;
            default:
                throw new RuntimeException("Opción no válida.");
        }

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/919bfe58049fbea507701f30/latest/" + base);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parsear la respuesta JSON
            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");

            double rate = conversionRates.get(target).getAsDouble(); // Obtener el tipo de cambio

            Moneda moneda = new Moneda();
            moneda.setBase_code(base);
            moneda.setTarget_code(target);
            moneda.setConversion_rate(rate);

            return moneda;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la conexión o la API: " + e.getMessage(), e);
        }
    }
}

