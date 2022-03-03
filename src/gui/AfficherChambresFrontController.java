/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import static gui.AfficherHotelFrontController.index;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Chambre;
import services.ServiceChambre;
import services.ServiceHotel;

/**
 * FXML Controller class
 *
 * @author dorsaf
 */
public class AfficherChambresFrontController implements Initializable {

    @FXML
    private ListView<Pane> listViewChambres;
    public static int index_ch = 0;
    @FXML
    private Label nomHotel;
    ServiceHotel sh = new ServiceHotel();
    @FXML
    private Pane imageHotel;
    @FXML
    private FontAwesomeIcon back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listChambres();
        String c = sh.getNomByIDHotel(index);
        nomHotel.setText("Hotel \n" +c); 
        ImageView img = null;
        Image new_image = new Image("file:" + sh.getImageByid(index));   
        img = new ImageView();
        img.setImage(new_image); 
        img.setFitWidth(200);
        img.setFitHeight(200);
        imageHotel.getChildren().add(img);
    }

    public void listChambres(){
        ObservableList<Pane> refresh = FXCollections.observableArrayList();
        listViewChambres.setItems(refresh);
        
        ServiceChambre sch = new ServiceChambre();
        ObservableList<Pane> Panes = FXCollections.observableArrayList();
        
        for (Chambre p3 : sch.getListchambresByID(index) ) {
            FileInputStream F1 = null;                    
            try {            
              //  pane.setStyle(" -fx-background-color: white");
                Pane pane2 = new Pane();
                pane2.setLayoutX(400);
                pane2.setLayoutY(400);
                pane2.setPrefWidth(pane2.getWidth() + 750);
                pane2.setPrefHeight(pane2.getHeight() + 500);
          //      pane2.setStyle("-fx-background-radius: 50;");
           //     pane2.setStyle("-fx-background-color: #8fbc8f;");
                pane2.setStyle(" -fx-border-radius: 10 10 10 10;-fx-border-color: #B2DFDB; -fx-border-width: 2px; -fx-background-radius: 30;");
                final Button btnreserver = new Button("Réserver");     
                btnreserver.setStyle("-fx-font: 18px Serif;");
                btnreserver.setStyle("-fx-padding: 20;");
                btnreserver.setStyle("-fx-alignment:right;");
                btnreserver.setLayoutX(500);
                btnreserver.setLayoutY(50);
                btnreserver.setStyle("-fx-background-color: #80CBC4;");
                
                btnreserver.setOnAction(event -> {
                    index_ch  = p3.getId_chambre();
                    String nom = sch.getNomByID(index_ch);                    
                    FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ReserverChambresFront.fxml"));
                    try {
                        Parent root = LOADER.load();
                        Scene sc = new Scene(root);
                        ReserverChambresFrontController cntr = LOADER.getController();
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(sc);
                        window.show();
                    } catch (Exception ex) {

                    }
                });   
                ImageView image = p3.getImg(); 
                image.setFitWidth(400);
                image.setFitHeight(300);
                image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0);");
                image.setLayoutX(350);
                image.setLayoutY(300);
                pane2.getChildren().add(image);
                
                Text numt = new Text("Numéro de chambre ");
                Label num = new Label(String.valueOf(p3.getNum_chambre()));
                numt.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-fill : #26A69A");
                num.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-fill : #80CBC4");;
                numt.setLayoutX(70);
                numt.setLayoutY(30);
                num.setLayoutX(300);
                num.setLayoutY(5);
              
                Text typet = new Text("Chambre ");
                Label type = new Label(p3.getType_chambre());
                typet.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-fill : #26A69A");
                type.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-fill : #80CBC4");;
                typet.setLayoutX(70);
                typet.setLayoutY(70);
                type.setLayoutX(200);
                type.setLayoutY(45);              
                
                Text etaget = new Text("Se situe au " + p3.getEtage()+ " ème étage ");
                Label etage = new Label(String.valueOf(p3.getEtage()));
                etaget.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-fill : #26A69A");
                etage.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-fill : #80CBC4");;
                etaget.setLayoutX(70);
                etaget.setLayoutY(110);
                etage.setLayoutX(300);
                etage.setLayoutY(85);
                
                Text prixt = new Text("PRIX:  ");
                Label prix = new Label(String.valueOf(p3.getPrix())+ " DT");            
                prixt.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-fill : #26A69A");
                prix.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-fill : #80CBC4");;
                prixt.setLayoutX(70);
                prixt.setLayoutY(150);
                prix.setLayoutX(300);
                prix.setLayoutY(125);              
              
                pane2.getChildren().addAll(numt, typet, etaget, prixt, num, type, prix);
                Panes.add(pane2);
                pane2.getChildren().add(btnreserver);
            } catch (Exception ex) {
            } finally {
                try {
                    F1.close();
                } catch (Exception ex) {
                }
            }
            listViewChambres.setPrefHeight(listViewChambres.getHeight() + 800);
            listViewChambres.setPrefHeight(listViewChambres.getWidth() + 800);
            listViewChambres.setItems(Panes);

        }
    }

    @FXML
    private void getBack(MouseEvent event) {
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("AfficherHotelFront.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            AfficherHotelFrontController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
        } catch (Exception ex) {

        }
    }

}