package ru.sapteh.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.User;
import ru.sapteh.service.UserDaoImpl;

import java.io.*;
import java.util.List;
import java.util.function.Predicate;

public class MainController {

    private final SessionFactory factory;
    private List<User> users;

    public MainController(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    @FXML
    private ImageView mainImage;
    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonOpenProgram;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label alertLbl;

    @FXML
    public void onActionOpenProgram(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        String err = "";

        if(login.isEmpty()) err += "login is empty ";
        if(password.isEmpty()) err += "password is empty";
        if(!err.isEmpty()){
            alertLbl.setText(err);
        } else {
            for (User user : users) {
                if (login.equalsIgnoreCase(user.getLogin()) && password.equals(user.getPassword()))
                    initStage();
                else
                    alertLbl.setText("login or password incorrect");
            }
        }
    }

    @FXML
    public void onActionExit(ActionEvent event){

        buttonExit.getScene().getWindow().hide();

    }

    @FXML
    public void initialize(){

        initLogo();
        initData();

    }

    private void initStage() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/client.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Autoservice_Client");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    private void initLogo(){
        try {
            InputStream pathImage = new FileInputStream("./src/main/resources/images/service_logo.png");
            Image image = new Image(pathImage);
            mainImage.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initData(){
        DAO<User, Integer> userDaoImpl = new UserDaoImpl(factory);
        users = userDaoImpl.findByAll();
    }
}