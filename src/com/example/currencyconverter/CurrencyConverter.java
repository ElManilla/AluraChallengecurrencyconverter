package com.example.currencyconverter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends Application {
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Agregar tasas de cambio actualizadas aquí
        exchangeRates.put("USD", 1.00);   // Dólar estadounidense (USD) como moneda base
        exchangeRates.put("EUR", 0.90);   // Euro (EUR) respecto al Dólar estadounidense (USD)
        exchangeRates.put("CNY", 7.15);   // Renminbi (CNY) respecto al Dólar estadounidense (USD)
        exchangeRates.put("MXN", 16.87);  // Peso mexicano (MXN) respecto al Dólar estadounidense (USD)
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conversor de monedas");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label amountLabel = new Label("Cantidad:");
        GridPane.setConstraints(amountLabel, 0, 0);

        TextField amountField = new TextField();
        GridPane.setConstraints(amountField, 1, 0);

        Label sourceLabel = new Label("Divisa :");
        GridPane.setConstraints(sourceLabel, 0, 1);

        ComboBox<String> sourceComboBox = new ComboBox<>();
        sourceComboBox.getItems().addAll(exchangeRates.keySet());
        GridPane.setConstraints(sourceComboBox, 1, 1);

        Label targetLabel = new Label("Convertir a :");
        GridPane.setConstraints(targetLabel, 0, 2);

        ComboBox<String> targetComboBox = new ComboBox<>();
        targetComboBox.getItems().addAll(exchangeRates.keySet());
        GridPane.setConstraints(targetComboBox, 1, 2);

        Button convertButton = new Button("Convertir");
        GridPane.setConstraints(convertButton, 1, 3);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 0, 4, 2, 1);

        convertButton.setOnAction(e -> {
            String sourceCurrency = sourceComboBox.getValue();
            String targetCurrency = targetComboBox.getValue();

            if (sourceCurrency != null && targetCurrency != null) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    double sourceRate = exchangeRates.get(sourceCurrency);
                    double targetRate = exchangeRates.get(targetCurrency);
                    double convertedAmount = amount * (targetRate / sourceRate);

                    resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, sourceCurrency, convertedAmount, targetCurrency));
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Cantidad Invalida.");
                }
            }
        });

        gridPane.getChildren().addAll(amountLabel, amountField, sourceLabel, sourceComboBox, targetLabel, targetComboBox, convertButton, resultLabel);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
