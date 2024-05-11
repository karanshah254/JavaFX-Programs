import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 800, Color.SKYBLUE);
        Stage stage = new Stage();

        Text text = new Text();
        text.setText("Hello!!");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Verdana", 40));
        text.setFill(Color.DARKGREEN);

        Line line = new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(200);
        line.setStrokeWidth(5);
        line.setStroke(Color.RED);
        line.setRotate(45);
        line.setOpacity(0.5);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setHeight(100);
        rectangle.setWidth(100);
        rectangle.setFill(Color.ORCHID);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.BLACK);

        Polygon traingle = new Polygon();
        traingle.getPoints().setAll(
                200.0, 200.0,
                300.0, 300.0,
                200.0, 300.0);
        traingle.setFill(Color.BROWN);

        Circle circle = new Circle();
        circle.setCenterX(350);
        circle.setCenterY(350);
        circle.setRadius(50);
        circle.setFill(Color.ORANGE);

        Image image = new Image("image.jpg");
        ImageView view = new ImageView(image);
        view.setX(400);
        view.setY(400);

        root.getChildren().add(rectangle);
        root.getChildren().add(text);
        root.getChildren().add(line);
        root.getChildren().add(traingle);
        root.getChildren().add(circle);
        root.getChildren().add(view);

        primaryStage.setTitle("Drawing some of shapes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}