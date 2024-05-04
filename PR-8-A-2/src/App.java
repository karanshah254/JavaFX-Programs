import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Data for the chart
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.getData().add(new XYChart.Data<>("Projects", 20));
        dataSeries.getData().add(new XYChart.Data<>("Quizzes", 10));
        dataSeries.getData().add(new XYChart.Data<>("Midterm Exams", 30));
        dataSeries.getData().add(new XYChart.Data<>("Final Exam", 40));

        // Create the X and Y axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Category");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Percentage");

        // Create the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Grade Distribution");
        barChart.getData().add(dataSeries);

        // Customize colors for each data point
        dataSeries.getData().get(0).getNode().setStyle("-fx-bar-fill: red;");
        dataSeries.getData().get(1).getNode().setStyle("-fx-bar-fill: blue;");
        dataSeries.getData().get(2).getNode().setStyle("-fx-bar-fill: green;");
        dataSeries.getData().get(3).getNode().setStyle("-fx-bar-fill: orange;");

        // Create a VBox to hold the chart
        VBox root = new VBox(barChart);

        // Create the scene and set it to the stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Practical 8-A-2");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}