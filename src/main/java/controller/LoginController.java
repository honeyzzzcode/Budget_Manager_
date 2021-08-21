package controller;
import java.io.IOException;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.AppData;
import model.Property;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import service.UserService;

public class LoginController extends ViewController {

  public Label notificationLabel;
  public TextField usernameField;
  @FXML
  PasswordField passwordField;
  UserService userService = new UserService();

  public void handleUserLogin(ActionEvent actionEvent) {
    PropertiesConfiguration p = new PropertiesConfiguration();
    try {
      p.load("userID.properties");
    } catch (ConfigurationException e) {
      e.printStackTrace();
    }
    try {
      int userId = userService.verifyUserDetails(usernameField.getText(), passwordField.getText());
      AppData.getInstance().setLoggedInUserId(userId);
      notificationLabel.setText("Login successful");
      changeScene(actionEvent, "profile");
      p.setProperty("userID",String.valueOf(userId));
    }catch (Exception ex){
      showAlert("Login Failed", ex.getMessage(), AlertType.ERROR);
    }
  }

  public void handleLoadRegister(ActionEvent actionEvent) {
    try {
      changeScene(actionEvent, "register");
    } catch (IOException e) {
      showAlert("Problem loading scene", e.getMessage(), AlertType.ERROR);
    }
  }
}
