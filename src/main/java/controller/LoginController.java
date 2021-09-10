package controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.AppData;
import model.Property;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import service.UserService;

public class LoginController extends ViewController  {

  public Label notificationLabel;
  public TextField usernameField;
  public Integer userID;

  public Integer getUserID() {
    return userID;
  }


  @FXML
  PasswordField passwordField;
  UserService userService = new UserService();

  public void handleUserLogin(ActionEvent actionEvent) {
    Property prop = new Property();
    try {
      prop.PropertiesLoad();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      int userId = userService.verifyUserDetails(usernameField.getText(), passwordField.getText());
      AppData.getInstance().setLoggedInUserId(userId);
      userID= userId;
      notificationLabel.setText("Login successful");
      changeScene(actionEvent, "profile");
      prop.setProperty("userID",String.valueOf(userId));
      prop.saveProp();
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
