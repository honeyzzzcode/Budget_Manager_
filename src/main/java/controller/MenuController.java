package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.AppData;
import model.User;
import service.MoneyService;
import service.UserService;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class MenuController extends ViewController  implements Initializable {
    ProfileController p = new ProfileController();
    public Label budgetField ;

    public void addIncome(ActionEvent actionEvent) {

        try {

            changeScene(actionEvent, "income");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    public void addExpense(ActionEvent actionEvent) {
        try {

            changeScene(actionEvent, "expense");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void handleLogout(ActionEvent actionEvent) {
        try {
            AppData.getInstance().setLoggedInUserId(null);
            changeScene(actionEvent, "login");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void backToPROF(ActionEvent actionEvent) {
        try {
            changeScene(actionEvent, "profile");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void showSimpleReport(ActionEvent actionEvent) {
        try {

            changeScene(actionEvent, "simpleReport");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void showTotalReport(ActionEvent actionEvent) {
        try {

            changeScene(actionEvent, "totalReport");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MoneyService moneyService = new MoneyService();

        try {
            String str = String.format("%.02f",  moneyService.getMoney(AppData.getInstance().getLoggedInUserId()));
            budgetField.setText(str+"$");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void categoryReport(ActionEvent actionEvent) {
        try {

            changeScene(actionEvent, "categoryReport");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    public void reportByDate(ActionEvent actionEvent) {
        try {

            changeScene(actionEvent, "reportbydate");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

}
