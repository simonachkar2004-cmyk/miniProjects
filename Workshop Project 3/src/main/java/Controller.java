
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Stock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller {

    @FXML private TableView<Stock> stockTable;
    @FXML private TableColumn<Stock, String> nameColumn;
    @FXML private TableColumn<Stock, Integer> quantityColumn;
    @FXML private TableColumn<Stock, Double> priceColumn;
    @FXML private TableColumn<Stock, String> categoryColumn;
    @FXML private TableColumn<Stock, String> supplierColumn;
    @FXML private TableColumn<Stock, String> dateColumn;

    @FXML private TextField nameField;
    @FXML private TextField quantityField;
    @FXML private TextField priceField;
    @FXML private TextField categoryField;
    @FXML private TextField supplierField;

    private ObservableList<Stock> stockList;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        supplierColumn.setCellValueFactory(cellData -> cellData.getValue().supplierProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().lastUpdatedProperty());

        stockList = Stock.getInitialStockData();  // preload data
        stockTable.setItems(stockList);
    }

    @FXML
    public void addStock() {
        try {
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            String category = categoryField.getText();
            String supplier = supplierField.getText();

            stockList.add(new Stock(name, quantity, price, category, supplier, LocalDate.now()));
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid input", "Quantity and Price must be numbers.");
        }
    }

    @FXML
    public void updateStock() {
        Stock selected = stockTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                selected.setName(nameField.getText());
                selected.setQuantity(Integer.parseInt(quantityField.getText()));
                selected.setPrice(Double.parseDouble(priceField.getText()));
                selected.setCategory(categoryField.getText());
                selected.setSupplier(supplierField.getText());
                selected.setLastUpdated(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
                stockTable.refresh();
                clearFields();
            } catch (NumberFormatException e) {
                showAlert("Invalid input", "Quantity and Price must be numbers.");
            }
        } else {
            showAlert("Selection Error", "Select a stock to update.");
        }
    }

    @FXML
    public void deleteStock() {
        Stock selected = stockTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            stockList.remove(selected);
            clearFields();
        } else {
            showAlert("Selection Error", "Select a stock to delete.");
        }
    }

    @FXML
    public void handleRowSelect() {
        Stock selected = stockTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nameField.setText(selected.getName());
            quantityField.setText(String.valueOf(selected.getQuantity()));
            priceField.setText(String.valueOf(selected.getPrice()));
            categoryField.setText(selected.getCategory());
            supplierField.setText(selected.getSupplier());
        }
    }

    private void clearFields() {
        nameField.clear();
        quantityField.clear();
        priceField.clear();
        categoryField.clear();
        supplierField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
