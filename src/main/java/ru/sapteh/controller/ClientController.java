package ru.sapteh.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import ru.sapteh.entity.Client;

import java.util.Date;

public class ClientController {
    @FXML
    TableColumn <Client, Integer> id;

    @FXML
    TableColumn <Client, String> gender;

    @FXML
    TableColumn <Client, String> firstName;

    @FXML
    TableColumn <Client, String> lastName;

    @FXML
    TableColumn <Client, String> patronymic;

    @FXML
    TableColumn <Client, Date> birthday;

    @FXML
    TableColumn <Client, String> phone;

    @FXML
    TableColumn <Client, String> email;

    @FXML
    TableColumn <Client, Date> registrationDate;

    @FXML
    TableColumn <Client, Date> lastVisitDate;

    @FXML
    TableColumn <Client, Integer> countVisit;

    @FXML
    TableColumn <Client, String> tags;


}
