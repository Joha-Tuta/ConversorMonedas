import org.json.JSONObject;
import java.util.Iterator;

public class JSONParser {
    public static void parseAndFilterRates(JSONObject json) {
        JSONObject rates = json.getJSONObject("conversion_rates");
        Iterator<String> keys = rates.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            double value = rates.getDouble(key);
            // Filtrar y mostrar monedas de interés (puedes personalizar este filtro)
            System.out.println(key + ": " + value);
        }
    }
}

