package gui;
import javafx.scene.control.ScrollPane;
import services.PersonalityController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuTestController implements Initializable {
    @FXML
    private Button menuButton;
    @FXML
    private Button toBackOffice;
    @FXML
    private VBox pane = new VBox();

    PersonalityController personalityController = new PersonalityController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    public void init(){
        ScrollPane scrollPane= new ScrollPane();
        scrollPane.setContent(pane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        PersonalityController personalityController=new PersonalityController();
        //System.out.println(personalityController.getAllPersonality().toString());
    }

    public void onButtonClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/Question1.fxml"));
            VBox yourNewView = fxmlLoader.load();
            pane.getChildren().setAll(yourNewView);
            Question1Controller addUserPersonalityController = fxmlLoader.getController();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public void onAdminClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/Dashboard.fxml"));
            VBox yourNewView = fxmlLoader.load();
            pane.getChildren().setAll(yourNewView);
            DashboardController dashboardController = fxmlLoader.getController();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
