import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    private static final int SCENE_WIDTH = 400;
    private static final int SCENE_HEIGHT = 200;
    private static final int TEXT_SPEED = 2;

    private Text movingText;
    private boolean isPaused = false;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        movingText = new Text("Programming is Fun ....");
        movingText.setFont(Font.font(20));

        root.setOnMousePressed(this::handleMousePressed);
        root.setOnMouseReleased(this::handleMouseReleased);
        root.getChildren().add(movingText);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Practical 8-B-2");
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> moveText()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void handleMousePressed(MouseEvent event) {
        isPaused = true;
    }

    private void handleMouseReleased(MouseEvent event) {
        isPaused = false;
    }

    private void moveText() {
        if (!isPaused) {
            double textX = movingText.getTranslateX();
            textX += TEXT_SPEED;
            if (textX > SCENE_WIDTH) {
                textX = -movingText.getBoundsInLocal().getWidth();
            }
            movingText.setTranslateX(textX);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}