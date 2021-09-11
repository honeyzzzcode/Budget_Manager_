package controller;

import Types.InOrOut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AppData;
import model.Money;
import service.MoneyService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class  ReportController extends ViewController implements Initializable {


  public TableView<Money> tableView;

    public TextField dateTF;
    public TextField categoryTF;
    public TextField typeTF;
    public TextField amountTF;
    public TextField idTF;

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<Money, String> tvDate;
    @FXML
    private TableColumn<Money, Integer> tvID;

    @FXML
    private TableColumn<Money, InOrOut> tvInOrOut;
    @FXML
    private TableColumn<Money, Float> tvAmount;
    @FXML
    private TableColumn<Money, String> tvCategory;



    public void showMenu(ActionEvent actionEvent) {
        try {

            changeScene(actionEvent, "menu");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    MoneyService service = new MoneyService();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            ObservableList<Money> recList = FXCollections.observableArrayList();

            ArrayList<Money>  moneyRecords = this.service.getAllMoneyRecords(AppData.getInstance().getLoggedInUserId());

            for (Money money : moneyRecords) {
                recList.add(new Money( money.getID(), money.getCreatedAt(),money.getInOrOut(), money.getAmount(), money.getCategory()));
            }
            tvID.setCellValueFactory(new PropertyValueFactory<Money, Integer >("ID"));
            tvDate.setCellValueFactory(new PropertyValueFactory<Money, String>("createdAt"));
            tvInOrOut.setCellValueFactory(new PropertyValueFactory<Money, InOrOut>("inOrOut"));
            tvAmount.setCellValueFactory(new PropertyValueFactory<Money, Float>("amount"));
            tvCategory.setCellValueFactory(new PropertyValueFactory<Money, String>("category"));

            tableView.setItems(recList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void handleButtonOnAction(ActionEvent actionEvent) throws Exception {

            System.out.println("hello");
           service.deleteRecord(Integer.parseInt(idTF.getText()),AppData.getInstance().getLoggedInUserId() );
        ObservableList<Money> recList = FXCollections.observableArrayList();

        ArrayList<Money>  moneyRecords = this.service.getAllMoneyRecords(AppData.getInstance().getLoggedInUserId());
        for (Money money : moneyRecords) {
            recList.add(new Money( money.getID(), money.getCreatedAt(),money.getInOrOut(), money.getAmount(), money.getCategory()));
        }
        tvID.setCellValueFactory(new PropertyValueFactory<Money, Integer >("ID"));
        tvDate.setCellValueFactory(new PropertyValueFactory<Money, String>("createdAt"));
        tvInOrOut.setCellValueFactory(new PropertyValueFactory<Money, InOrOut>("inOrOut"));
        tvAmount.setCellValueFactory(new PropertyValueFactory<Money, Float>("amount"));
        tvCategory.setCellValueFactory(new PropertyValueFactory<Money, String>("category"));

        tableView.setItems(recList);
            showAlert("Success ", "Record deleted , continue", Alert.AlertType.CONFIRMATION);
        }
    }

