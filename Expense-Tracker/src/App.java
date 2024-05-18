import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.time.LocalDate;

public class App extends Application {
    private TableView<Expense> expenseTable = new TableView<>();
    private final ObservableList<Expense> expenseData = FXCollections.observableArrayList();
    private final TextField descriptionField = new TextField();
    private final TextField amountField = new TextField();
    private final ComboBox<String> categoryComboBox = new ComboBox<>();
    private final DatePicker datePicker = new DatePicker();
    private final TextField budgetField = new TextField();

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        GridPane addExpensePane = new GridPane();
        addExpensePane.setHgap(10);
        addExpensePane.setVgap(5);
        addExpensePane.setPadding(new Insets(10));

        addExpensePane.add(new Label("Description:"), 0, 0);
        addExpensePane.add(descriptionField, 1, 0);

        addExpensePane.add(new Label("Amount:"), 0, 1);
        addExpensePane.add(amountField, 1, 1);

        addExpensePane.add(new Label("Category:"), 0, 2);
        categoryComboBox.getItems().addAll("Food", "Transport", "Entertainment", "Shopping", "Utilities", "Other");
        addExpensePane.add(categoryComboBox, 1, 2);

        addExpensePane.add(new Label("Date:"), 0, 3);
        datePicker.setValue(LocalDate.now());
        addExpensePane.add(datePicker, 1, 3);

        Button addButton = new Button("Add Expense");
        addButton.setOnAction(e -> addExpense());
        addExpensePane.add(addButton, 1, 4);

        root.setTop(addExpensePane);

        expenseTable.setEditable(false);
        TableColumn<Expense, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        TableColumn<Expense, String> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        TableColumn<Expense, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        TableColumn<Expense, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        expenseTable.getColumns().addAll(descriptionColumn, amountColumn, categoryColumn, dateColumn);
        expenseTable.setItems(expenseData);
        root.setCenter(expenseTable);

        HBox budgetBox = new HBox(10);
        budgetBox.setPadding(new Insets(10));
        budgetBox.getChildren().addAll(new Label("Set Budget:"), budgetField);
        root.setBottom(budgetBox);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Expense Tracker Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addExpense() {
        String description = descriptionField.getText().trim();
        String amount = amountField.getText().trim();
        String category = categoryComboBox.getValue();
        String date = datePicker.getValue().toString();
        if (!description.isEmpty() && !amount.isEmpty() && category != null && !date.isEmpty()) {
            expenseData.add(new Expense(description, amount, category, date));
            descriptionField.clear();
            amountField.clear();
            categoryComboBox.getSelectionModel().clearSelection();
            datePicker.setValue(LocalDate.now());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Information");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
        }
    }

    public static class Expense {
        private final String description;
        private final String amount;
        private final String category;
        private final String date;

        public Expense(String description, String amount, String category, String date) {
            this.description = description;
            this.amount = amount;
            this.category = category;
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public String getAmount() {
            return amount;
        }

        public String getCategory() {
            return category;
        }

        public String getDate() {
            return date;
        }

        public StringProperty descriptionProperty() {
            return new SimpleStringProperty(description);
        }

        public StringProperty amountProperty() {
            return new SimpleStringProperty(amount);
        }

        public StringProperty categoryProperty() {
            return new SimpleStringProperty(category);
        }

        public StringProperty dateProperty() {
            return new SimpleStringProperty(date);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}