/**
 * 
 */
/**
 * 
 */
module CurrencyConverter {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;

    opens com.example.currencyconverter to javafx.fxml;
    exports com.example.currencyconverter;
}
