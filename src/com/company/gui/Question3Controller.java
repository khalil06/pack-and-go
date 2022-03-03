package com.company.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Question3Controller implements Initializable {
    @FXML
    private Button nextButton;
    @FXML
    private Button previousButton;
    @FXML
    private VBox pane = new VBox();
    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;
    private String answer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onRadioButton1Clicked(ActionEvent actionEvent) {
        nextButton.setDisable(false);
        answer = "T";
    }

    public void onRadioButton2Clicked(ActionEvent actionEvent) {
        nextButton.setDisable(false);
        answer = "F";
    }

    public void onButtonClicked(javafx.event.ActionEvent actionEvent) {
        try {
            if (radioButton1.isSelected() || radioButton2.isSelected()) {
                nextButton.setDisable(false);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/Question4.fxml"));
                VBox yourNewView = fxmlLoader.load();
                pane.getChildren().setAll(yourNewView);
                Question4Controller question4Controller = fxmlLoader.getController();
                ResultController.setTest(answer);
            } else {
                nextButton.setDisable(true);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public void onPreviousClicked(javafx.event.ActionEvent actionEvent) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/Question2.fxml"));
            VBox yourNewView = fxmlLoader.load();
            pane.getChildren().setAll(yourNewView);
            Question2Controller question2Controller = fxmlLoader.getController();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }


}
