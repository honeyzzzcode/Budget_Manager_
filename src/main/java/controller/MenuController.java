package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.AppData;
import service.UserService;

import java.io.IOException;

public class MenuController extends ViewController{

    public void addIncome(ActionEvent actionEvent) {
        try {
            AppData.getInstance().setLoggedInUserId(null);
            changeScene(actionEvent, "income");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    public void addExpense(ActionEvent actionEvent) {
        try {
            AppData.getInstance().setLoggedInUserId(null);
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
}
