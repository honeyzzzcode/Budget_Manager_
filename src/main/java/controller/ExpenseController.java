package controller;

import Types.Category;
import Types.InOrOut;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Money;
import service.UserService;

public class ExpenseController extends ViewController{
    UserService userService = new UserService();
    public TextField amountField;
    public TextField categoryField;
    public void  addExpense( ActionEvent actionEvent){

        try {
            Money money = new Money
                    (       InOrOut.EXPENSE,
                            Category.valueOf(categoryField.getText()),
                            Float.parseFloat(amountField.getText()));


            userService.addExpense(money);

            showAlert("Success", "Expense added successfully, continue", Alert.AlertType.CONFIRMATION);
            changeScene(actionEvent, "menu");
        }catch (Exception e) {
            showAlert("Adding Expense Failed", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
    }
}}
