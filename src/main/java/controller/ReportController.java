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

public class  ReportController extends ViewController implements Initializable {


    public ListView<String> tableView;
   // public TableView<String> tableView;


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
            ObservableList<String> recList = FXCollections.observableArrayList();

            ArrayList<Money>  moneyRecords = this.service.getTotalReport(AppData.getInstance().getLoggedInUserId());

            for (Money money : moneyRecords) {
                recList.add(money.getCategory() + "   " + money.getInOrOut() + "   " + money.getAmount() );}

            tableView.setItems(recList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        



    }
}
