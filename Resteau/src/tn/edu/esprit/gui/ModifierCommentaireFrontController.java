/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import static tn.edu.esprit.gui.AfficherCommentaireFrontController.inde;
import tn.edu.esprit.models.Commentaire;
import tn.edu.esprit.models.Resteau;
import tn.edu.esprit.services.CommentaireResteau;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ModifierCommentaireFrontController implements Initializable {

    @FXML
    private TextField lblcontenucmntr;
    @FXML
    private TextField lblIdR;
    @FXML
    private TextField lbliduser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void modifiercmntrR(MouseEvent event) {
        
                  CommentaireResteau C = new CommentaireResteau();
           Commentaire C1=new Commentaire();
               //C1.setIdR(parseInt(lblIdR.getText())); 
              inde=C1.getIdCommentaireR();

            //   C1.setIdR(Resteau.getIdd());
              // C1.setid_user(parseInt(lbliduser.getText()));
               C1.setContenuCommentaireR(lblcontenucmntr.getText());  
               C.modifierCommentaireR(C1);
      
               Alert alert =new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("succes");
               alert.setHeaderText("modifiéé");
               alert.setContentText("commentaire  modifier avec succés !");
            
              Notifications notificationBuilder = Notifications.create()
                        .title(" commentaire  modifier")
                        .text("Saved in your DATABASE").darkStyle()
             .graphic(null)
   // .graphic(null)
                        
                        .hideAfter(Duration.seconds(15))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               System.out.println("Clicked on notification");
            }
        });
                notificationBuilder.darkStyle();
                notificationBuilder.show();
               alert.showAndWait();
               
    }

    }
    
    
