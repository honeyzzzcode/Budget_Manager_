package controller;

import Types.Category;
import Types.InOrOut;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Money;
import service.MoneyService;
import service.UserService;

public class IncomeController extends ViewController{

    MoneyService moneyService = new MoneyService();

    public TextField amountField;
    public TextField categoryField;
    public void  addIncome(ActionEvent actionEvent){

        try {
            Money money = new Money
                    (       InOrOut.INCOME,
                            Category.valueOf(categoryField.getText()),
                            Float.parseFloat(amountField.getText()));


            moneyService.addIncome(money);

            showAlert("Success", "Income added successfully, continue", Alert.AlertType.CONFIRMATION);
            changeScene(actionEvent, "menu");
        }catch (Exception e) {
            showAlert("Adding Income Failed", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
