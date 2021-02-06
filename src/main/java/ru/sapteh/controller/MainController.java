package ru.sapteh.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;

public class MainController {

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonOpenProgram;

    @FXML
    public void onActionOpenProgram(ActionEvent event){

    }
    @FXML
    public void onActionExit(ActionEvent event){

    }

    @FXML
    public void initialize(){
        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();
        });

        buttonOpenProgram.setOnAction(event -> {
            buttonOpenProgram.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/view/client.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Autoservice_Client");
            assert root != null;
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } );

    }

}
