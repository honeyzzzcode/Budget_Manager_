package controller;

import Types.Category;
import Types.InOrOut;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Money;
import model.User;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import service.MoneyService;
import service.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class IncomeController extends ViewController implements Initializable {
    @FXML
    private ChoiceBox<String> CBCategory;
    MoneyService moneyService = new MoneyService();

    public TextField amountField;
    int result;



    public void   addIncome(ActionEvent actionEvent){
        PropertiesConfiguration p = new PropertiesConfiguration();
        try {
            p.load("userID.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        try {
            Money money = new Money
                    (       InOrOut.INCOME,
                            Category.valueOf(CBCategory.getValue()),
                            Float.parseFloat(amountField.getText()));


            int id = Integer.parseInt(p.getString("userID"));
            User user = moneyService.getUserProfile(id);
            user.setBudget(user.getBudget() + Float.parseFloat(amountField.getText()));
            moneyService.addIncome(money);
            moneyService.updateBudget(user, user.getId());



            showAlert("Success", "Income added successfully, continue", Alert.AlertType.CONFIRMATION);
            changeScene(actionEvent, "menu");
        }catch (Exception e) {
            showAlert("Adding Income Failed", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CBCategory.getItems().add(String.valueOf(Category.CASH));
        CBCategory.getItems().add(String.valueOf(Category.CREDIT_CARD));
            }
}
