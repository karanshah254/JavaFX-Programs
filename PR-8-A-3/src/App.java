import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Rectangle rectanguloid = new Rectangle(200, 100, Color.BLUE);
        root.getChildren().add(rectanguloid);

        rectanguloid.widthProperty().bind(root.widthProperty().divide(2));
        rectanguloid.heightProperty().bind(root.heightProperty().divide(2));

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Practical 8-A-3");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}