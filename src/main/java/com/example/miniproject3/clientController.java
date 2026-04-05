package com.example.miniproject3;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.time.LocalDate;

public class clientController {

    @FXML private TextField clientName;
    @FXML private TextField cityName;
    @FXML private TextField orderName;
    @FXML private DatePicker dateSelector;
    @FXML private TextField price;

    @FXML private Button deleteButton;
    @FXML private Button insertButton;
    @FXML private Button updateButton;
    @FXML private Button returnButton;

    @FXML private TableView<client> tableView;
    @FXML private TableColumn<client, String> nameCl;
    @FXML private TableColumn<client, String> adressCl;
    @FXML private TableColumn<client, String> orderCl;
    @FXML private TableColumn<client, LocalDate> dataCl;
    @FXML private TableColumn<client, Number> priceCl;

    @FXML
    public void initialize() {
        dateSelector.setValue(LocalDate.now());

        nameCl.setCellValueFactory(cell -> cell.getValue().nameProperty());
        adressCl.setCellValueFactory(cell -> cell.getValue().cityProperty());
        orderCl.setCellValueFactory(cell -> cell.getValue().orderNumberProperty());
        dataCl.setCellValueFactory(cell -> cell.getValue().dateProperty());
        priceCl.setCellValueFactory(cell -> cell.getValue().priceProperty());

        updateButton.setDisable(true);
        deleteButton.setDisable(true);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            boolean selected = newSel != null;
            updateButton.setDisable(!selected);
            deleteButton.setDisable(!selected);

            if (selected) {
                clientName.setText(newSel.getName());
                cityName.setText(newSel.getCity());
                orderName.setText(newSel.getOrderNumber());
                price.setText(Double.toString(newSel.getPrice()));
                dateSelector.setValue(newSel.getDate());
            } else {
                clearFields();
            }
        });
    }

    @FXML
    public void insertClient() {
        if (!clientName.getText().isEmpty()) {
            double pr = price.getText().isEmpty() ? 0 : Double.parseDouble(price.getText());
            client newClient = new client(
                    clientName.getText(),
                    cityName.getText(),
                    orderName.getText(),
                    dateSelector.getValue(),
                    pr
            );
            tableView.getItems().add(newClient);
            clearFields();
        }
    }

    @FXML
    public void updateClient() {
        client selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setName(clientName.getText());
            selected.setCity(cityName.getText());
            selected.setOrderNumber(orderName.getText());
            selected.setDate(dateSelector.getValue());
            selected.setPrice(price.getText().isEmpty() ? 0 : Double.parseDouble(price.getText()));
            tableView.refresh();
            clearFields();
        }
    }

    @FXML
    public void deleteClient() {
        client selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            tableView.getItems().remove(selected);
            clearFields();
        }
    }

    @FXML
    private void goToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 500, 500); // <- set width and height here
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void clearFields() {
        clientName.clear();
        cityName.clear();
        orderName.clear();
        price.clear();
        dateSelector.setValue(LocalDate.now());
    }
}