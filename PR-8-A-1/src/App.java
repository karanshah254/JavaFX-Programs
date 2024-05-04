import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        HBox root = new HBox();

        Text[] texts = new Text[5];
        for (int i = 0; i < 5; i++) {
            texts[i] = new Text("      Java");
            texts[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
            texts[i].setFill(randomColor());
            texts[i].setOpacity(randomOpacity());
            texts[i].setRotate(90);
            root.getChildren().add(texts[i]);
        }

        Scene scene = new Scene(root, 600, 200);
        scene.setFill(Color.DARKGRAY);

        primaryStage.setTitle("Practical 8-A-1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Color randomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private double randomOpacity() {
        Random random = new Random();
        return 0.5 + random.nextDouble() * 0.5;
    }

    public static void main(String[] args) {
        launch(args);
    }
}