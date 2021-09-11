package controller;
import Types.Category;
import Types.InOrOut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class  ReportController extends ViewController implements Initializable {


  public TableView<Money> tableView;

    public TextField dateTF;
    public TextField categoryTF;
    public TextField typeTF;
    public TextField amountTF;
    public TextField idTF;
    public ChoiceBox<String> CBCategory;
    public ChoiceBox<String> CBType;
    public DatePicker date;
    @FXML
    private TableColumn<Money, String> tvDate;
    @FXML
    private TableColumn<Money, Integer> tvID;

    @FXML
    private TableColumn<Money, InOrOut> tvInOrOut;
    @FXML
    private TableColumn<Money, Float> tvAmount;
    @FXML
    private TableColumn<Money, String> tvCategory;



    public void showMenu(ActionEvent actionEvent) {
        try {

            changeScene(actionEvent, "menu");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    MoneyService service = new MoneyService();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        date.setConverter(
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

        CBType.getItems().add(String.valueOf(InOrOut.EXPENSE));
        CBType.getItems().add(String.valueOf(InOrOut.INCOME));
        CBCategory.getItems().add(String.valueOf(Category.CAR));
        CBCategory.getItems().add(String.valueOf(Category.EATING_OUT));
        CBCategory.getItems().add(String.valueOf(Category.ENTERTAINMENT));
        CBCategory.getItems().add(String.valueOf(Category.GIFTS));
        CBCategory.getItems().add(String.valueOf(Category.GROCERIES));
        CBCategory.getItems().add(String.valueOf(Category.TRANSPORT));
        CBCategory.getItems().add(String.valueOf(Category.SHOPPING));
        CBCategory.getItems().add(String.valueOf(Category.GOALS));
        ObservableList<Money> recList = FXCollections.observableArrayList();
        try {


            ArrayList<Money>  moneyRecords = this.service.getAllMoneyRecords(AppData.getInstance().getLoggedInUserId());

            for (Money money : moneyRecords) {
                recList.add(new Money( money.getID(), money.getCreatedAt(),money.getInOrOut(), money.getAmount(), money.getCategory()));
            }
            tvID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            tvDate.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
            tvInOrOut.setCellValueFactory(new PropertyValueFactory<>("inOrOut"));
            tvAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            tvCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

            tableView.setItems(recList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleButtonOnAction(ActionEvent actionEvent) throws Exception {

           service.deleteRecord(Integer.parseInt(idTF.getText()),AppData.getInstance().getLoggedInUserId() );
        ObservableList<Money> recList = FXCollections.observableArrayList();

        ArrayList<Money>  moneyRecords = this.service.getAllMoneyRecords(AppData.getInstance().getLoggedInUserId());
        for (Money money : moneyRecords) {
            recList.add(new Money( money.getID(), money.getCreatedAt(),money.getInOrOut(), money.getAmount(), money.getCategory()));
        }
        tvID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tvDate.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        tvInOrOut.setCellValueFactory(new PropertyValueFactory<>("inOrOut"));
        tvAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tvCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        tableView.setItems(recList);
            showAlert("Success ", "Record deleted , continue", Alert.AlertType.INFORMATION);
        }

    public void handleButtonOnAction1(ActionEvent actionEvent) throws Exception {
        service.updateRecord(
                date.getValue()
                ,
                Integer.parseInt(idTF.getText()),
                Float.parseFloat(amountTF.getText()),
                CBType.getValue(),
                CBCategory.getValue(),
                AppData.getInstance().getLoggedInUserId() );

        ObservableList<Money> recList = FXCollections.observableArrayList();

        ArrayList<Money>  moneyRecords = this.service.getAllMoneyRecords(AppData.getInstance().getLoggedInUserId());
        for (Money money : moneyRecords) {
            recList.add(new Money( money.getID(), money.getCreatedAt(),money.getInOrOut(), money.getAmount(), money.getCategory()));
        }
        tvID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tvDate.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        tvInOrOut.setCellValueFactory(new PropertyValueFactory<>("inOrOut"));
        tvAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tvCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        tableView.setItems(recList);
        showAlert("Success ", "Record updated , continue", Alert.AlertType.INFORMATION);
    }
}

