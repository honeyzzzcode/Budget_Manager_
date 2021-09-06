package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.AppData;
import model.User;
import service.UserService;

import java.io.IOException;
import java.net.URL;
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

    public void showReport(ActionEvent actionEvent) {
        try {

            changeScene(actionEvent, "Report");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        budgetField.setText(AppData.getInstance().getBudget() +"€");
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

            changeScene(actionEvent, "reportByDate");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

}
