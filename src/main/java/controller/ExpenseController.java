package controller;

import Types.Category;
import Types.InOrOut;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
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

public class ExpenseController extends ViewController implements Initializable {
    @FXML
    private ChoiceBox<String> CBCategory;
    MoneyService moneyService = new MoneyService();

    public TextField amountField;


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
                            Category.valueOf(CBCategory.getValue()),
                            Float.parseFloat(amountField.getText()));
            int id = Integer.parseInt(p.getString("userID"));
            User user = moneyService.getUserProfile(id);
            user.setBudget(user.getBudget() - Float.parseFloat(amountField.getText()));
            moneyService.addExpense(money);
            moneyService.updateBudget(user, user.getId());


            showAlert("Success", "Expense added successfully, continue", Alert.AlertType.INFORMATION);
            changeScene(actionEvent, "menu");
        } catch (Exception e) {
            showAlert("Adding Expense Failed", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


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
    }
}
