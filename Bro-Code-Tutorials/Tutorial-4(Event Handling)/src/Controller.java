import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Controller {
    @FXML
    private Circle myCircle;
    private double x;
    private double y;

    public void up(ActionEvent e) {
        myCircle.setCenterY(y = y - 2);
    }

    public void downn(ActionEvent e) {
        myCircle.setCenterY(y = y + 2);
    }

    public void left(ActionEvent e) {
        myCircle.setCenterX(x = x - 2);
    }

    public void right(ActionEvent e) {
        myCircle.setCenterX(x = x + 2);
    }
}
