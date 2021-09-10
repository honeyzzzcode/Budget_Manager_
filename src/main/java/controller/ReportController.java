package controller;

import Types.InOrOut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @FXML
    private TableColumn<Money, String> tvDate;
    @FXML
    private TableColumn<Money, InOrOut> tvInOrOut;
    @FXML
    private TableColumn<Money, Float> tvAmount;
    @FXML
    private TableColumn<Money, String> tvCategory;

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

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
                recList.add(new Money(money.getCreatedAt(),money.getInOrOut(), money.getAmount(), money.getCategory()));
            }
            tvDate.setCellValueFactory(new PropertyValueFactory<Money, String>("createdAt"));
            tvInOrOut.setCellValueFactory(new PropertyValueFactory<Money, InOrOut>("inOrOut"));
            tvAmount.setCellValueFactory(new PropertyValueFactory<Money, Float>("amount"));
            tvCategory.setCellValueFactory(new PropertyValueFactory<Money, String>("category"));

            tableView.setItems(recList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleButtonAction(ActionEvent actionEvent) {
        /*if(actionEvent.getSource() == btnUpdate){
            service.updateRecord();
        }
        else if(actionEvent.getSource() == btnDelete){
            service.deleteRecord(ID);
        }*/
    }
}