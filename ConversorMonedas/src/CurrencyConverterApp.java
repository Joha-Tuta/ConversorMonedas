import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {

    private CurrencyConverter currencyConverter = new CurrencyConverter();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conversor de Monedas");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label amountLabel = new Label("Monto:");
        GridPane.setConstraints(amountLabel, 0, 0);
        TextField amountInput = new TextField();
        GridPane.setConstraints(amountInput, 1, 0);

        Label fromCurrencyLabel = new Label("De:");
        GridPane.setConstraints(fromCurrencyLabel, 0, 1);
        TextField fromCurrencyInput = new TextField();
        GridPane.setConstraints(fromCurrencyInput, 1, 1);

        Label toCurrencyLabel = new Label("A:");
        GridPane.setConstraints(toCurrencyLabel, 0, 2);
        TextField toCurrencyInput = new TextField();
        GridPane.setConstraints(toCurrencyInput, 1, 2);

        Button convertButton = new Button("Convertir");
        GridPane.setConstraints(convertButton, 1, 3);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 1, 4);

        convertButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountInput.getText());
                String fromCurrency = fromCurrencyInput.getText().toUpperCase();
                String toCurrency = toCurrencyInput.getText().toUpperCase();
                JSONObject rates = currencyConverter.getExchangeRates().getJSONObject("conversion_rates");
                double fromRate = rates.getDouble(fromCurrency);
                double toRate = rates.getDouble(toCurrency);
                double result = (amount / fromRate) * toRate;
                resultLabel.setText(String.format("%.2f %s", result, toCurrency));
            } catch (Exception ex) {
                resultLabel.setText("Error en la conversión");
            }
        });

        grid.getChildren().addAll(amountLabel, amountInput, fromCurrencyLabel, fromCurrencyInput, toCurrencyLabel, toCurrencyInput, convertButton, resultLabel);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

