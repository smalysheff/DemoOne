package ru.sapteh.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Client;
import ru.sapteh.entity.ClientService;
import ru.sapteh.entity.Tag;
import ru.sapteh.service.ClientDaoImp;
import ru.sapteh.service.ClientServiceDaoImp;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class ClientController {

    ObservableList<Client> clientObservableList = FXCollections.observableArrayList();

    @FXML
    TableView<Client> tableViewClient;
    @FXML
    TableColumn <Client, Integer> id;
    @FXML
    TableColumn <Client, Character> gender;
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
    TableColumn <Client, String> lastVisitDate;
    @FXML
    TableColumn <Client, Integer> countVisit;
    @FXML
    TableColumn <Client, String> tags;

    @FXML
    public void initialize(){

        //Init tableView
        initDataToDatabase();

        id.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getId()));
        gender.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getGender().getCode()));
        firstName.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getFirstName()));
        lastName.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getLastName()));
        patronymic.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getPatronymic()));
        birthday.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getBirthday()));
        phone.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getPhone()));
        email.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getEmail()));
        registrationDate.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getRegistrationDate()));

        lastVisitDate.setCellValueFactory(c -> {
           Set<ClientService> clientServices = c.getValue().getClientServiceSet();
           if(clientServices.size() != 0){

               Date startTime = clientServices.stream()
                       .max(Comparator.comparing(ClientService::getStartTime))
                       .get().getStartTime();
               return new SimpleObjectProperty<>(
                       new SimpleDateFormat("dd-MM-yyyy").format(startTime));
           } else
                return new SimpleObjectProperty<>("");
        });

        countVisit.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getClientServiceSet().size()));



        tags.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTags().iterator().next().getColor()));
        tags.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if(item != null || !empty){
                    if(item.equalsIgnoreCase("green"))
                        setStyle("-fx-background-color: #80ee80");
                    if(item.equalsIgnoreCase("red"))
                        setStyle("-fx-background-color: #db9898");
                }
            }
        });

        tableViewClient.setItems(clientObservableList);




    }

    void initDataToDatabase(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<Client, Integer> clientDaoImpl = new ClientDaoImp(factory);
        DAO<ClientService, Integer> clientServiceDaoImpl = new ClientServiceDaoImp(factory);
        clientServiceDaoImpl.findByAll();
        clientObservableList.addAll(clientDaoImpl.findByAll());

        factory.close();
    }


}
