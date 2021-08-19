package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.User;
import service.UserService;

public class RegisterController extends ViewController{

  public TextField fullNameField;
  public TextField budgetField;
  public TextField usernameField;
  public PasswordField passwordField;
  public PasswordField confirmPasswordField;
  public TextField phoneField;
  public TextField emailField;


  UserService userService = new UserService();
  public void handleRegistration(ActionEvent actionEvent) {
    try {
      validateUserInfo();

      User user = new User(fullNameField.getText(), usernameField.getText(), passwordField.getText(),
       Float.parseFloat(budgetField.getText()) ,phoneField.getText(), emailField.getText());

      userService.registerUser(user);

      showAlert("Success", "Registration successful, login to continue", AlertType.CONFIRMATION);
      changeScene(actionEvent, "login");
    } catch (Exception e) {
      showAlert("Registration Failed", e.getMessage(), AlertType.ERROR);
      e.printStackTrace();
    }
  }

  private void validateUserInfo() throws Exception {
    if (!passwordField.getText().equals(confirmPasswordField.getText())) throw new Exception("Password does not match confirm password");
    if (passwordField.getText().length() < 4 ) throw new Exception("Password needs to be minimum 4 character");
    if (usernameField.getText().length() < 5) throw new Exception("Username needs to be minimum 5 characters");
  }

  public void handleLoadLogin(ActionEvent actionEvent) {
    try {
      changeScene(actionEvent, "login");
    } catch (IOException e) {
      showAlert("Problem loading scene", e.getMessage(), AlertType.ERROR);
    }
  }
}
