package controller;

import Types.Category;
import Types.InOrOut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class  CategoryReportController extends ViewController implements Initializable {

    @FXML
    private ChoiceBox<String> CBCategory;

    public TableView<Money> tableView;
    public TableColumn<Money,Float> amountCol;
    public TableColumn<Money, String> typeCol;
    public TableColumn<Money, String> createdCol;

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

        CBCategory.getItems().add(String.valueOf(Category.CAR));
        CBCategory.getItems().add(String.valueOf(Category.EATING_OUT));
        CBCategory.getItems().add(String.valueOf(Category.ENTERTAINMENT));
        CBCategory.getItems().add(String.valueOf(Category.GIFTS));
        CBCategory.getItems().add(String.valueOf(Category.GROCERIES));
        CBCategory.getItems().add(String.valueOf(Category.TRANSPORT));
        CBCategory.getItems().add(String.valueOf(Category.SHOPPING));
        CBCategory.getItems().add(String.valueOf(Category.GOALS));
        CBCategory.getItems().add(String.valueOf(Category.CASH));
        CBCategory.getItems().add(String.valueOf(Category.BANK));
    }
    public void showMenu1(){
        try {
            ObservableList<Money> recList = FXCollections.observableArrayList();

            ArrayList<Money> moneyRecords = this.service.getCategoryRecord(AppData.getInstance().getLoggedInUserId(),CBCategory.getValue());

            for (Money money : moneyRecords) {
                recList.add(new Money(money.getCreatedAt(), money.getInOrOut(),money.getAmount()));
            }

            createdCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("InOrOut"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
            tableView.setItems(recList);
        } catch (Exception e) {
            e.printStackTrace();
        }
}}