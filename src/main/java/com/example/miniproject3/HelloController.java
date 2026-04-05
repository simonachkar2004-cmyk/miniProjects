package com.example.miniproject3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

public class HelloController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        String username = usernameField.getText().trim().toLowerCase();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            welcomeText.setText("Please fill all fields");
            return;
        }

        if ((username.equals("admin") || username.equals("risherd") || username.equals("james")) &&
                password.equals(username.toUpperCase())) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproject3/home-view.fxml"));
                Parent root = loader.load();

                Image icon = new Image(
                        HelloApplication.class.getResource("/image/2083417.png").toExternalForm()
                );

                Stage homeStage = new Stage(); // new window
                homeStage.setScene(new Scene(root, 500, 500));
                homeStage.setTitle("Home");
                homeStage.getIcons().add(icon);
                homeStage.show();

                Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                loginStage.close();

            } catch (IOException e) {
                e.printStackTrace();
                welcomeText.setText("Error loading Home screen");
            }

        } else {
            welcomeText.setText("Invalid username or password");
        }
    }
}