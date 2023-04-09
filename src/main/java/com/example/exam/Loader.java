package com.example.exam;

import com.example.exam.DbFunctions.DbFunctions;
import com.example.exam.DbFunctions.Variables;
import com.example.exam.Models.SceneModel;
import com.example.exam.Models.StageModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Loader extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StageModel.setMyStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(Loader.class.getResource("authorization.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        SceneModel.setMyScene(scene);
        stage.setTitle("Способ входа");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }




    public void openNewScene(AnchorPane root, String window, String title) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(window)));
            root.getChildren().setAll(anchorPane);
            StageModel.getMyStage().setTitle(title);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}