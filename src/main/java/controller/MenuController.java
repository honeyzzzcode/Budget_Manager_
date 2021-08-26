package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.AppData;
import service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends ViewController  {
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
}
