package com.example.exam;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.exam.DbFunctions.DbFunctions;
import com.example.exam.DbFunctions.Variables;
import com.example.exam.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnLogin;

    @FXML
    private TableColumn<?, ?> columnPassword;

    @FXML
    private TableColumn<?, ?> columnRole;

    @FXML
    private TableColumn<?, ?> columnStatus;

    @FXML
    private ImageView imgArrow;

    @FXML
    private ImageView imgUpdate;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<User> tableView;

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        columnRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(dbFunctions.getAllUsers());

        if (Variables.ROLE_USER.equals("admin")) {
            tableView.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {
                    User user = tableView.getSelectionModel().getSelectedItem();

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/exam/update.fxml"));
                        Parent parent = loader.load();
                        Update update = loader.getController();
                        update.setData(user.getLogin(), user.getPassword(), user.getRole(), user.getStatus(), user.getId());
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(new Scene(parent));
                        stage.setTitle("Редактирование пользователя");
                        stage.showAndWait();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }

        imgUpdate.setOnMouseClicked(e -> tableView.setItems(dbFunctions.getAllUsers()));

        imgArrow.setOnMouseClicked(e -> {
            new Loader().openNewScene(rootPane, "/com/example/exam/authorization.fxml", "");
        });
    }

}
