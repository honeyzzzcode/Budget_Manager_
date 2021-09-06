package controller;

import Types.Category;
import Types.InOrOut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.AppData;
import model.Money;
import service.MoneyService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class  ReportByDateController extends ViewController implements Initializable {

        @FXML
        private DatePicker myDatePicker;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            myDatePicker.setConverter(
                    new StringConverter<LocalDate>() {
                        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        @Override
                        public String toString(LocalDate date) {
                            return (date != null) ? dateFormatter.format(date) : "";
                        }

                        @Override
                        public LocalDate fromString(String string) {
                            return (string != null && !string.isEmpty())
                                    ? LocalDate.parse(string, dateFormatter)
                                    : null;
                        }
                    });

        }


    public ListView<String> tableView;

  /*  public void showMenu(ActionEvent actionEvent) {
        try {

            changeScene(actionEvent, "menu");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    MoneyService service = new MoneyService();
    @Override
    /*public void initialize(URL location, ResourceBundle resources) {




    }
    public void showMenu1(){

        try{
            ObservableList<String> recList = FXCollections.observableArrayList();

            ArrayList<Money>  moneyRecords = this.service.getCategoryRecord(AppData.getInstance().getLoggedInUserId(), DatePicker.getValue());

            for (Money money : moneyRecords) {
                recList.add(money.getInOrOut() + "   " + money.getAmount() );}

            tableView.setItems(recList);
        } catch (Exception e) {
            e.printStackTrace();
        }}*/
}

