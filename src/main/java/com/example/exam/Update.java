package com.example.exam;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.exam.DbFunctions.DbFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Update {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBoxRole;

    @FXML
    private ComboBox<String> comboBoxStatus;

    @FXML
    private Label labelError;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonSave;

    @FXML
    private PasswordField textFieldPassword;

    String idUser = "";

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        ObservableList<String> role = FXCollections.observableArrayList("admin", "user");
        comboBoxRole.setItems(role);

        ObservableList<String> status = FXCollections.observableArrayList("true", "false");
        comboBoxStatus.setItems(status);

        buttonSave.setOnAction(e -> {
            updateDataUser();
        });

        buttonDelete.setOnAction(e -> {
            deleteDataUser();
        });
    }

    private void deleteDataUser() {
        if (idUser.equals("")) {
            labelError.setText("Повторите попытку позже");
        } else {
            dbFunctions.deleteDataUser(idUser);
            buttonDelete.getScene().getWindow().hide();

        }

    }
    private void updateDataUser() {
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();
        String role = comboBoxRole.getValue();
        String status = comboBoxStatus.getValue();
        int codeError = dbFunctions.check_login(login);
        if (login.isEmpty()) {
            labelError.setText("Логин пустой");
        } else if (password.isEmpty()) {
            labelError.setText("Пароль пустой");
        } else if (role.isEmpty()) {
            labelError.setText("Роль пустая");
        } else if (status.isEmpty()) {
            labelError.setText("Статус пустой");
        } else if (idUser.equals("")) {
            labelError.setText("Повторите попытку позже");
        }  else if (codeError == 404) {
            labelError.setText("Какая-то ошибка");
        } else {
            dbFunctions.updateDataUser(login, password, role, status, idUser);
            buttonSave.getScene().getWindow().hide();
        }
    }
    public void setData (String login, String password, String role, String status, String id){
        textFieldLogin.setText(login);
        textFieldPassword.setText(password);
        comboBoxRole.setValue(role);
        comboBoxStatus.setValue(status);
        idUser = id;
    }

}
