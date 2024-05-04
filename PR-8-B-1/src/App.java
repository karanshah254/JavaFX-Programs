import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {

    private Label textLabel;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        textLabel = new Label("Programming IS Fun ....");
        textLabel.setFont(Font.font("Arial", 50));

        ComboBox<String> fontComboBox = new ComboBox<>(FXCollections.observableArrayList(Font.getFamilies()));
        fontComboBox.setValue("Arial");
        ComboBox<Integer> fontSizeComboBox = new ComboBox<>(FXCollections.observableArrayList(generateFontSizeList()));
        fontSizeComboBox.setValue(50);

        fontComboBox.setOnAction(e -> changeFont());
        fontSizeComboBox.setOnAction(e -> changeFont());
        root.getChildren().addAll(textLabel, fontComboBox, fontSizeComboBox);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Practical 8-B-1");
        primaryStage.show();
    }

    private void changeFont() {
        boolean isBold = textLabel.getFont().getStyle().contains("Bold");
        boolean isItalic = textLabel.getFont().getStyle().contains("Italic");

        String newFont = ((ComboBox<String>) ((ComboBox<?>) textLabel.getParent().getChildrenUnmodifiable().get(1)))
                .getValue();
        int newFontSize = ((ComboBox<Integer>) ((ComboBox<?>) textLabel.getParent().getChildrenUnmodifiable().get(2)))
                .getValue();

        textLabel.setFont(Font.font(newFont, isBold ? FontWeight.BOLD : FontWeight.NORMAL,
                isItalic ? FontPosture.ITALIC : FontPosture.REGULAR, newFontSize));
    }

    private Integer[] generateFontSizeList() {
        Integer[] fontSizeList = new Integer[100];
        for (int i = 0; i < 100; i++) {
            fontSizeList[i] = i + 1;
        }
        return fontSizeList;
    }

    public static void main(String[] args) {
        launch(args);
    }
}