package com.example.zadaniepliki;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        TextField pathInput = new TextField();
        pathInput.setPromptText("podaj sceszkę...");
        pathInput.setPrefWidth(2.0 / 3.0);

        Button czytajButton = new Button("Czytaj");

        HBox topInput = new HBox(10, pathInput, czytajButton);
        topInput.setHgrow(pathInput, Priority.ALWAYS);


        TextArea readPanel = new TextArea();
        readPanel.setEditable(false);
        readPanel.setPrefHeight(150);

        TextArea writeArea = new TextArea();

        Button closeButton = new Button("zamknij");
        Button zapisButton = new Button("Zapis");

        HBox bottomButtons = new HBox(10, zapisButton, closeButton);
        bottomButtons.setHgrow(zapisButton, Priority.ALWAYS);
        bottomButtons.setStyle("-fx-alignment: bottom-right;");

        VBox layout = new VBox(10, topInput, readPanel, writeArea, bottomButtons);
        layout.setStyle("-fx-padding: 10;");

        czytajButton.setOnAction(event -> {
            String path = pathInput.getText();
            try {
                String content = Files.readString(Path.of(path));
                readPanel.setText(content);
            } catch (IOException e) {
                showError("Error", "nie mogłem odczytać pliku: " + e.getMessage());
            }
        });

        zapisButton.setOnAction(event -> {
            String path = pathInput.getText();
            String content = writeArea.getText();
            try {
                Files.writeString(Path.of(path), content);
                showInfo("Success", "plik zapisany");
            } catch (IOException e) {
                showError("Error", "niemogłem zapisać pliku: " + e.getMessage());
            }
        });

        closeButton.setOnAction(event -> primaryStage.close());

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zadanie Pliki");
        primaryStage.show();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}