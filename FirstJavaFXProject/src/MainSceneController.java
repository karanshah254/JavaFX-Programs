
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainSceneController {

    @FXML
    private TextField tlTitle;

    @FXML
    void btnOKClicked(ActionEvent event) {
        Stage mainWindow = (Stage) tlTitle.getScene().getWindow();
        String title = tlTitle.getText();
        mainWindow.setTitle(title);
    }
}