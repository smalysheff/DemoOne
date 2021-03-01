package ru.sapteh.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Client;
import ru.sapteh.entity.ClientService;
import ru.sapteh.service.ClientDaoImp;
import ru.sapteh.service.ClientServiceDaoImp;

import java.text.SimpleDateFormat;
import java.util.*;

public class ClientController {

//    void test(){
//        IntegerProperty integerProperty = new SimpleIntegerProperty();
//        integerProperty.
//    }

    private final SessionFactory factory;

    public ClientController(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    private final ObservableList<Client> clientObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Client> tableViewClient;
    @FXML
    private TableColumn <Client, Integer> id;
    @FXML
    private TableColumn <Client, Character> gender;
    @FXML
    private TableColumn <Client, String> firstName;
    @FXML
    private TableColumn <Client, String> lastName;
    @FXML
    private TableColumn <Client, String> patronymic;
    @FXML
    private TableColumn <Client, Date> birthday;
    @FXML
    private TableColumn <Client, String> phone;
    @FXML
    private TableColumn <Client, String> email;
    @FXML
    private TableColumn <Client, Date> registrationDate;
    @FXML
    private TableColumn <Client, String> lastVisitDate;
    @FXML
    private TableColumn <Client, Integer> countVisit;
    @FXML
    private TableColumn <Client, String> tags;

    //ComboBox size
    @FXML
    private ComboBox<Integer> comboBox;
    @FXML
    private Label numberOfRecordsLbl;

    //Pagination pages
    @FXML
    private Pagination pagination;

    private int valuesFromDatabaseSize;


    //initialize method
    @FXML
    public void initialize(){

        //Init tableView
        initDataFromDatabase();
        initTableView();

        //ComboBox and pagination changed pages
        valuesFromDatabaseSize = clientObservableList.size();
        ObservableList<Integer> options = FXCollections.observableArrayList( 10, 20, 50, 200);
        comboBox.setItems(options);
        comboBox.setValue(options.get(0));
        comboBox.valueProperty().addListener(
            (obj, oldValue, newValue) -> {
                int comboBoxValue = comboBox.getValue();
                int totalPage = (int) (Math.ceil(valuesFromDatabaseSize * 1.0 / comboBoxValue));

                //Pagination pages
                pagination.setPageCount(totalPage);
                pagination.setCurrentPageIndex(0);
                tableViewClient.setItems(
                        FXCollections.observableArrayList(
                                clientObservableList.subList(pagination.getCurrentPageIndex(), newValue))

                );
                pagination.currentPageIndexProperty()
                        .addListener((observable1, oldValue1, newValue1) -> {
                            tableViewClient.setItems(
                                    FXCollections.observableArrayList(
                                            clientObservableList.subList(
                                                    comboBoxValue * (newValue1.intValue() + 1)
                                                            - comboBoxValue,
                                                    comboBoxValue * (newValue1.intValue() + 1)))
                            );
                        });
    });

        //style pagination
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);

        //Number of records label
        numberOfRecordsLbl.setText("number of records: " + valuesFromDatabaseSize);

    }

    private void initDataFromDatabase(){
        DAO<Client, Integer> clientDaoImpl = new ClientDaoImp(factory);
        DAO<ClientService, Integer> clientServiceDaoImpl = new ClientServiceDaoImp(factory);
        clientServiceDaoImpl.findByAll();
        List<Client> listClientsFromDatabase = clientDaoImpl.findByAll();
        clientObservableList.addAll(listClientsFromDatabase);
    }

    private void initTableView(){

        tableViewClient.setItems(clientObservableList);
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
            Set<ClientService> clientServiceSet = c.getValue().getClientServiceSet();
            if(clientServiceSet.size() != 0){
                Date startTime = clientServiceSet.stream()
                        .max(Comparator.comparing(ClientService::getStartTime))
                        .get().getStartTime();
                return new SimpleObjectProperty<>(
                        new SimpleDateFormat("dd.MM.yyyy").format(startTime)
                );
            } return new SimpleObjectProperty<>("");
        });
        countVisit.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getClientServiceSet().size()));

        tags.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTags().iterator().next().getColor()));
//        tags.setCellFactory(column -> new TableCell<>() {
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                if(item != null || !empty){
//                    if(item.equalsIgnoreCase("green"))
//                        setStyle("-fx-background-color: #80ee80");
//                    if(item.equalsIgnoreCase("red"))
//                        setStyle("-fx-background-color: #db9898");
//                }
//            }
//        });
    }

//    private int

}
