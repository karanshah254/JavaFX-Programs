import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.util.Duration;

public class App extends Application {
	protected TextField tfSpeed = new TextField();
	protected TextField tfPrefix = new TextField();
	protected TextField tfNumberOfImages = new TextField();
	protected TextField tfURL = new TextField();
	protected StackPane paneForImage = new StackPane();
	protected Timeline animation;
	protected int n = 1;

	@Override
	public void start(Stage primaryStage) {
		final int COLUMN_COUNT = 27;
		tfSpeed.setPrefColumnCount(COLUMN_COUNT);
		tfPrefix.setPrefColumnCount(COLUMN_COUNT);
		tfNumberOfImages.setPrefColumnCount(COLUMN_COUNT);
		tfURL.setPrefColumnCount(COLUMN_COUNT);

		Button btStart = new Button("Start Animation");

		GridPane paneForInfo = new GridPane();
		paneForInfo.setAlignment(Pos.CENTER);
		paneForInfo.add(new Label("Enter information for animation"), 0, 0);
		paneForInfo.add(new Label("Animation speed in milliseconds"), 0, 1);
		paneForInfo.add(tfSpeed, 1, 1);
		paneForInfo.add(new Label("Image file prefix"), 0, 2);
		paneForInfo.add(tfPrefix, 1, 2);
		paneForInfo.add(new Label("Number of images"), 0, 3);
		paneForInfo.add(tfNumberOfImages, 1, 3);
		paneForInfo.add(new Label("Audio file URL"), 0, 4);
		paneForInfo.add(tfURL, 1, 4);

		BorderPane pane = new BorderPane();
		pane.setBottom(paneForInfo);
		pane.setCenter(paneForImage);
		pane.setTop(btStart);
		BorderPane.setAlignment(btStart, Pos.TOP_RIGHT);

		animation = new Timeline(
				new KeyFrame(Duration.millis(1000), e -> nextImage()));
		animation.setCycleCount(Timeline.INDEFINITE);

		btStart.setOnAction(e -> {
			if (tfURL.getText().length() > 0) {
				MediaPlayer mediaPlayer = new MediaPlayer(
						new Media(tfURL.getText()));
				mediaPlayer.play();
			}
			if (tfSpeed.getText().length() > 0)
				animation.setRate(Integer.parseInt(tfSpeed.getText()));
			animation.play();
		});

		Scene scene = new Scene(pane, 550, 680);
		primaryStage.setTitle("Practical-8-B-3");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void getImage() {
		paneForImage.getChildren().clear();
		paneForImage.getChildren().add(new ImageView(new Image(
				"images/" +
						tfPrefix.getText() + n + ".gif")));
	}

	private void nextImage() {
		n = n < Integer.parseInt(
				tfNumberOfImages.getText()) ? n += 1 : 1;
		getImage();
	}

	public static void main(String[] args) {
		launch(args);
	}
}