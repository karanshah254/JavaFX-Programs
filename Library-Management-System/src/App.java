import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    private ObservableList<String> items = FXCollections.observableArrayList(
            "Book", "Journal", "DVD");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Top - Header
        Label headerLabel = new Label("Library Management System");
        headerLabel.setStyle("-fx-font-size: 20px;");
        root.setTop(headerLabel);

        // Center - Content
        VBox centerVBox = new VBox(10);
        centerVBox.setPadding(new Insets(10));
        ComboBox<String> itemTypeComboBox = new ComboBox<>(items);
        itemTypeComboBox.setValue("Book"); // Default to Book
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        Button searchButton = new Button("Search");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefHeight(200);
        resultArea.setWrapText(true);

        centerVBox.getChildren().addAll(itemTypeComboBox, searchField, searchButton, resultArea);

        // Left - Sidebar
        VBox sidebarVBox = new VBox(10);
        sidebarVBox.setPadding(new Insets(10));
        Button borrowButton = new Button("Borrow Item");
        Button returnButton = new Button("Return Item");
        sidebarVBox.getChildren().addAll(borrowButton, returnButton);

        // Right - Book Details
        VBox detailsVBox = new VBox(10);
        detailsVBox.setPadding(new Insets(10));
        Label detailsLabel = new Label("Item Details");
        TextArea detailsArea = new TextArea();
        detailsArea.setEditable(false);
        detailsArea.setPrefHeight(200);
        detailsArea.setWrapText(true);
        detailsVBox.getChildren().addAll(detailsLabel, detailsArea);

        // Layout setup
        root.setCenter(centerVBox);
        root.setLeft(sidebarVBox);
        root.setRight(detailsVBox);

        // Search button action
        searchButton.setOnAction(event -> {
            String searchQuery = searchField.getText().trim();
            String itemType = itemTypeComboBox.getValue();
            // Perform search operation (e.g., query database)
            // Dummy data for demonstration
            String result = "Search Results for '" + searchQuery + "' in " + itemType + ":\n";
            result += "Book 1\nBook 2\nBook 3"; // Dummy results
            resultArea.setText(result);
        });

        // Borrow button action
        borrowButton.setOnAction(event -> {
            // Perform borrow operation
            // Dummy data for demonstration
            detailsArea.setText("Borrowed: Book 1\nDue Date: 2024-05-31"); // Dummy result
        });

        // Return button action
        returnButton.setOnAction(event -> {
            // Perform return operation
            // Dummy data for demonstration
            detailsArea.setText("Returned: Book 1"); // Dummy result
        });

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
