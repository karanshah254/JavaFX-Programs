import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {
    @FXML
    private Label myLabel;
    @FXML
    private CheckBox myCheckBox;
    @FXML
    private ImageView myImageView;

    Image myImage1 = new Image(getClass().getResourceAsStream("image1.jpg"));
    Image myImage2 = new Image(getClass().getResourceAsStream("image2.jpg"));

    public void change(ActionEvent event) {
        if (myCheckBox.isSelected()) {
            myLabel.setText("ON");
            myImageView.setImage(myImage2);
        } else {
            myLabel.setText("OFF");
            myImageView.setImage(myImage1);
        }
    }
}
