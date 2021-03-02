package ru.sapteh.controller;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Client;
import ru.sapteh.entity.ClientService;
import ru.sapteh.service.ClientDaoImp;
import ru.sapteh.service.ClientServiceDaoImp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javafx.print.*;

public class ClientController {



    private final ObservableList<Character> observableListGender = FXCollections.observableArrayList('м', 'ж');
    private final ObservableList<String> observableListGeneral = FXCollections.observableArrayList("Name", "Email", "Phone");
    private final SessionFactory factory;

    public ClientController(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    private final ObservableList<Client> clientObservableList = FXCollections.observableArrayList();

    @FXML
    private AnchorPane printPane;

    //Table view data clients
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

    //Filtered
    @FXML
    private ComboBox<Character> comboBoxGender;
    @FXML
    private ComboBox<String> comboBoxGeneral;

    //ComboBox filter size
    @FXML
    private ComboBox<Integer> comboBox;
    @FXML
    private Label numberOfRecordsLbl;

    //Pagination pages
    @FXML
    private Pagination pagination;

    //Export data
    @FXML
    private Button btnSaveToPdf;
    @FXML
    private Button btnExportToExcel;

    private int valuesFromDatabaseSize;

    //initialize method
    @FXML
    public void initialize(){

        //Init tableView
        initData();
        initTableView();

        //Filtered
        initGenderFilter();
        initGenderFilter();

        //ComboBox and pagination changed pages
        initComboBoxPagination();

        //Number of records label
        numberOfRecordsLbl.setText("number of records: " + valuesFromDatabaseSize);

//        System.out.println(Printer.getAllPrinters());
//        initPrinter();
    }

    @FXML
    public void onActionSaveToPdf(ActionEvent event) throws IOException, DocumentException {

        String fileName = "test.pdf";
//        Document document = new Document(PageSize.A4.rotate()); //landscape orientation
        Document document = new Document(); //portrait orientation
        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        document.open();

        //add image in pdf
        Image image = Image.getInstance("./src/main/resources/images/as_logo_pdf.png");
        image.scaleAbsoluteHeight(70);
        image.scaleAbsoluteWidth(130);
        image.setAlignment(Element.ALIGN_RIGHT);
        document.add(image);

        //add paragraph
//        BaseFont helvetica =
//                BaseFont.createFont(
//                        BaseFont.HELVETICA,
//                        BaseFont.CP1250,
//                        BaseFont.NOT_EMBEDDED);
//        Font font = new Font(helvetica, 12);

        Paragraph paragraph = new Paragraph("Car service customers Привет hello");
        paragraph.setSpacingAfter(20);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        //--------------------------table--------------------------------
        //получить количество столбцов
        int numColumns = tableViewClient.getColumns().size();
        PdfPTable table = new PdfPTable(12);
        //получить имена столбцов
        ObservableList<TableColumn<Client, ?>> columns = tableViewClient.getColumns();

        columns.forEach(c ->
                table.addCell(new PdfPCell(new Phrase(c.getText())))
        );

//        for(TableColumn<Client, ?> column : columns){
//            table.addCell(new PdfPCell(new Phrase(column.getText())));
//            System.out.println(column.getText());
//        }

        table.setHeaderRows(1);

        tableViewClient.getColumns().forEach(c -> {
        });

        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));
        table.addCell(new PdfPCell(new Phrase("1")));

        document.add(table);

        document.close();
        System.out.println("finished");
    }

    @FXML
    public void onActionExportToExcel(ActionEvent event) {

    }

    private void initGenderFilter(){
        comboBoxGender.setItems(observableListGender);
        comboBoxGender.valueProperty().addListener(
                (obj, oldValue, newValue) -> {
                    FilteredList<Client> filteredData = new FilteredList<>(clientObservableList,
                            s -> newValue.equals(s.getGender().getCode()));
                    tableViewClient.setItems(filteredData);
                });
    }

    private void initGeneralFilter(){
        comboBoxGeneral.setItems(observableListGeneral);
    }

    private void initData(){
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
            } return new SimpleObjectProperty<>("Не обслуживался !");
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

    private void initComboBoxPagination(){
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
                                    clientObservableList.subList(pagination.getCurrentPageIndex(), newValue)));
                    pagination.currentPageIndexProperty()
                            .addListener((observable1, oldValue1, newValue1) -> {
                                tableViewClient.setItems(
                                        FXCollections.observableArrayList(
                                                clientObservableList.subList(
                                                        comboBoxValue * (newValue1.intValue() + 1) - comboBoxValue,
                                                        comboBoxValue * (newValue1.intValue() + 1))));
                            });
                });

        //style pagination
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
    }




//    public void initPrinter(){
//        Printer printer = Printer.getDefaultPrinter();
//        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
//        PrinterJob printerJob = PrinterJob.createPrinterJob();
//        if(printerJob != null && printerJob.showPrintDialog(printPane.getScene().getWindow())){
//            boolean success = printerJob.printPage(pageLayout, printPane);
//            if(success){
//                printerJob.endJob();
//            }
//        }
//    }
}
