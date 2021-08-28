package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.AppData;
import model.User;
import service.UserService;

public class ProfileController extends ViewController implements Initializable {
  public Label userNameLabel;
  public Label phoneNumberLabel;
  public Label emailAddressLabel;
  public Label nameLabel;
  public Label createdAtLabel;
  public Label updatedAtLabel;
  public Label budgetLabel;
  DecimalFormat df = new DecimalFormat("#.##");


  UserService userService = new UserService();

  public void handleLogout(ActionEvent actionEvent) {
    try {
      AppData.getInstance().setLoggedInUserId(null);
      changeScene(actionEvent, "login");
    } catch (IOException e) {
      showAlert("Problem with navigation", e.getMessage(), AlertType.ERROR);
      e.printStackTrace();
    }
  }

  public Label getBudgetLabel() {
    return budgetLabel;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {

      User user = this.userService.getUserProfile(AppData.getInstance().getLoggedInUserId());
      nameLabel.setText(user.getName());
      userNameLabel.setText(user.getUserName());
      phoneNumberLabel.setText(String.valueOf(user.getPhone()));
      emailAddressLabel.setText(user.getEmail());
      budgetLabel.setText((df.format(user.getBudget())));
      AppData.getInstance().setBudget(df.format(user.getBudget()));
      createdAtLabel.setText(String.valueOf(user.getCreatedAt()));
      updatedAtLabel.setText(String.valueOf(user.getUpdatedAt()));
    } catch (Exception e) {
      e.printStackTrace();
      showAlert("User not found", e.getMessage(), AlertType.ERROR);
    }

  }

    public void showMenu(ActionEvent actionEvent) {
      try {

        changeScene(actionEvent, "menu");
      } catch (IOException e) {
        showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
        e.printStackTrace();
      }
    }
}
