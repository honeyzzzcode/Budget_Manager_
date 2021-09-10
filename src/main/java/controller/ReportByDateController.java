package controller;

import javafx.scene.control.DatePicker;
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

public class ReportByDateController extends ViewController implements Initializable {

    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        startDate.setConverter(
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
        endDate.setConverter(
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

    public TableView<Money> tableView;

   public TableColumn<Money,Float> amountCol;
    public TableColumn<Money, String> typeCol;
    public TableColumn<Money, String> createdCol;
    public TableColumn<Money, Integer> categoryCol;
    MoneyService service = new MoneyService();


    public void showMenu1() {

        try {
            ObservableList<Money> recList = FXCollections.observableArrayList();

            ArrayList<Money> moneyRecords = this.service.getReportByDate(AppData.getInstance().getLoggedInUserId(), startDate.getValue(), endDate.getValue());

            for (Money money : moneyRecords) {
                recList.add(new Money(money.getCreatedAt(),money.getCategory(),money.getAmount() , money.getInOrOut()));
            }

            createdCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("InOrOut"));
            categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
            tableView.setItems(recList);
        } catch (Exception e) {
            e.printStackTrace();
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

