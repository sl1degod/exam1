package com.example.exam;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.exam.DbFunctions.DbFunctions;
import com.example.exam.DbFunctions.Variables;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.sql.RowSetReader;

public class Authorization {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonSignIn;

    @FXML
    private ImageView imageClose;

    @FXML
    private Label labelError;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void onBackPressed() {
        Stage stage = (Stage) imageClose.getScene().getWindow();
        stage.close();
    }

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        buttonSignIn.setOnAction(e -> {
            validation();
        });
    }
    private void validation() {
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();
        int codeError = dbFunctions.signIn(login, password);
        if (login.isEmpty()) {
            labelError.setText("Введите логин");
        } else if (password.isEmpty()) {
            labelError.setText("Введите пароль");
        } else if (codeError == 0) {
            labelError.setText("Такого аккаунта не существует");
        } else if (codeError == 404) {
            labelError.setText("Ошибка");
        } else if ( Variables.ACTIVE_USER.equals("false")) {
            labelError.setText("Ваш аккаунт заблокирован");
        }
        else {
            labelError.setText("");
            new Loader().openNewScene(rootPane, "/com/example/exam/main.fxml", "Главный экран");
        }
    }

}
