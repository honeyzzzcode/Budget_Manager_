package controller;

import Types.Category;
import Types.InOrOut;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.AppData;
import model.Money;
import model.User;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import service.MoneyService;
import service.UserService;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class ExpenseController extends ViewController  {
    MoneyService moneyService = new MoneyService();
    public TextField amountField;
    public TextField categoryField;


    public void addExpense(ActionEvent actionEvent) {
        PropertiesConfiguration p = new PropertiesConfiguration();
        try {
            p.load("userID.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        try {
            Money money = new Money
                    (InOrOut.EXPENSE,
                            Category.valueOf(categoryField.getText()),
                            Float.parseFloat(amountField.getText()));

            moneyService.addExpense(money);


               int id = Integer.parseInt(p.getString("userID"));
                User user = moneyService.getUserProfile(id);
                user.setBudget(user.getBudget()-Float.parseFloat(amountField.getText()));

            moneyService.updateBudget(user, user.getId());


            showAlert("Success", "Expense added successfully, continue", Alert.AlertType.CONFIRMATION);
            changeScene(actionEvent, "menu");
        } catch (Exception e) {
            showAlert("Adding Expense Failed", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }



}
