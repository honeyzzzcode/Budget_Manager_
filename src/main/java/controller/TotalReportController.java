package controller;

import Types.Category;
import Types.InOrOut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.AppData;
import model.Money;
import service.MoneyService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class  TotalReportController extends ViewController implements Initializable {



     public TableView<Money> tableView;
    public TableColumn<Money,String > typeCol;
    public TableColumn<Money,Float> amountCol;

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

            ArrayList<Money>  moneyRecords = this.service.getTotalReport(AppData.getInstance().getLoggedInUserId());

            for (Money money : moneyRecords) {
                recList.add(new Money(money.getInOrOut(),money.getTotal()) ); }
            typeCol.setCellValueFactory(new PropertyValueFactory<>("inOrOut"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("total"));
            tableView.setItems(recList);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
