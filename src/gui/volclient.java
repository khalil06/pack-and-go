package gui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.modelBillet;
import models.modelVol;
import services.JavaMailUtil;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;


public class volclient implements Initializable {
    @FXML
    private TextField valrecherche;
    @FXML
    private TableView<modelVol> tablevol;

    @FXML
    private TableColumn<modelVol, String> cln_compagnie;

    @FXML
    private TableColumn<modelVol, String> cln_depart;

    @FXML
    private TableColumn<modelVol, Date> cln_destination;

    @FXML
    private TableColumn<modelVol, Date> cln_date_depart;

    @FXML
    private TableColumn<modelVol, Date> cln_date_arrive;

    @FXML
    private TableColumn<modelVol, String> cln_type;

    @FXML
    private TableColumn<modelVol, String> cln_duree;

    @FXML
    private TableColumn<modelVol, Integer> cln_prix;

    @FXML
    private TableColumn cln_action;
    String erreur;
    interVol crudData = new implVol();
    interBillet crudDatabillet = new implBillet();
    ObservableList<modelVol> listData;
    private String statusCode,statusClick;
    //ObservableList<modelVol> listDelete;
    public void initialize(URL url, ResourceBundle rb) {

        /*cln_id.setCellValueFactory((TableColumn.CellDataFeatures<modelVol, Integer> cellData) ->
                cellData.getValue().idProperty().asObject());*/
        cln_compagnie.setCellValueFactory((TableColumn.CellDataFeatures<modelVol, String> cellData) ->
                cellData.getValue().compagnie_aerienProperty());
        cln_depart.setCellValueFactory((TableColumn.CellDataFeatures<modelVol, String> cellData) ->
                cellData.getValue().departProperty());
        cln_date_depart.setCellValueFactory((TableColumn.CellDataFeatures<modelVol, Date> cellData) ->
                cellData.getValue().date_departProperty());
        cln_date_arrive.setCellValueFactory((TableColumn.CellDataFeatures<modelVol, Date> cellData) ->
                cellData.getValue().date_arriveProperty());
       // cln_destination.setCellValueFactory((TableColumn.CellDataFeatures<modelVol, String> cellData) ->
                //cellData.getValue().destinationProperty());
        cln_type.setCellValueFactory((TableColumn.CellDataFeatures<modelVol, String> cellData) ->
                cellData.getValue().type_avionProperty());
        cln_duree.setCellValueFactory((TableColumn.CellDataFeatures<modelVol, String> cellData) ->
                cellData.getValue().dureeProperty());
        /*cln_image.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Image>,
                ObservableValue<Image>>() {
            @Override
            public ObservableValue<Image> call(TableColumn.CellDataFeatures<Object, Image> p) {
                return (ObservableValue<Image>) new Image("src/public/Image/\"+item+\".jpg");
            }
        });
        cln_image.setCellFactory = new Callback<TableColumn<modelVol, String>, TableCell<modelVol, String>>() {
            @Override
            public TableCell<modelVol, String> call(TableColumn<modelVol, String> param) {
                HBox box= new HBox();
                ImageView imageview = new ImageView();
                return new TableCell<modelVol, String> () {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        //  super.updateItem(item, empty);
                        if (item != null) {
                            box.setSpacing(10) ;
                            box.setPadding(new Insets(10, 10, 10, 10));
                            Image img = null;
                            Image image = new Image(new Image("src/public/Image/"+item+".jpg");

                            imageview.setImage(image);
                            imageview.setFitHeight(320.0);
                            imageview.setFitWidth(200.0);

                        }
                        if(!box.getChildren().contains(imageview)) {
                            box.getChildren().add(imageview);
                            setGraphic(box);
                        }
                    }
                };
            }
        };*/
        //cln_prix.setCellValueFactory((TableColumn.CellDataFeatures<modelVol, String> cellData) ->
        //  cellData.getValue().prixProperty());



       /* cln_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>,
                ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });*/

        cln_action.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
            @Override
            public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                return new ButtonCell(tablevol);
            }
        });
       /* cln_action.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
            @Override
            public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                return new volclient.ButtonCell(tablevol);
            }
        }); */


        listData = FXCollections.observableArrayList();
        //  AwesomeDude.setIcon(boutton_ajouter, AwesomeIcon.CHECK_SQUARE, "15px");
        //AwesomeDude.setIcon(Refresh, AwesomeIcon.CHAIN_BROKEN, "15px");

        //date_depart.setValue(LocalDate.of(1990, 01, 01));
        //date_arrive.setValue(LocalDate.of(1990, 01, 01));

        statusCode = "0";
        statusClick = "0";
        showData();

        tablevol.getSelectionModel().clearSelection();

    }

    private void showData(){
        interVol crudData = new implVol();
        ObservableList<modelVol> listData;
        listData = crudData.getAll();
        tablevol.setItems(listData);


        FilteredList<modelVol> filteredData = new FilteredList<>(listData, b -> true);

        valrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Vol -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;

                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();



                if (Vol.get_depart().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                }else if (Vol.get_compagnie_aerien().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;

                }else if (Vol.get_destination().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;

                }

                else if (Vol.get_duree().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                }else if (Vol.get_type_avion().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                }
                else
                    return false;
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<modelVol> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablevol.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablevol.setItems(sortedData);
        tablevol.setRowFactory(tv -> new TableRow<modelVol>());
    }

    @FXML
    private void restauFront(MouseEvent event) {
    }

    @FXML
    private void activiteFront(MouseEvent event) {
         FXMLLoader LOADER = new FXMLLoader(getClass().getResource("Activite.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            ActiviteController ac = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    
    }

    @FXML
    private void toPersonalityTestMenu(MouseEvent event) {
         FXMLLoader LOADER = new FXMLLoader(getClass().getResource("MenuTest.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            MenuTestController menuTestController = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }

    @FXML
    private void HotelsFront(MouseEvent event) {
         FXMLLoader LOADER = new FXMLLoader(getClass().getResource("AfficherHotelFront.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            AfficherHotelFrontController menuTestController = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }

    class ButtonCell extends TableCell<Object, Boolean> {
        final Hyperlink cellButtonReserver = new Hyperlink("Reserver");
        final HBox hb = new HBox(cellButtonReserver);


        ButtonCell(final TableView tableVol) {
            hb.setSpacing(4);

            //cell delete
            cellButtonReserver.setOnAction((ActionEvent t)  -> {
               try{
                   int row = getTableRow().getIndex();
                   tableVol.getSelectionModel().select(row);
                modelBillet m = new modelBillet();
                m.set_nom("testamir");
                modelVol mv= (modelVol) tableVol.getSelectionModel().getSelectedItem();
                m.setVolid(mv.getId());
                m.set_classe("type1");
                   crudDatabillet.insert(m);
                dialog(Alert.AlertType.INFORMATION, "Data has saved \n email sent to : amir.daghsen@esprit.tn");
                JavaMailUtil.sendMail("amir.daghsen@esprit.tn","Billet reservé avec success","Votre reservation est confirmé");
                } catch (MessagingException e) {
                    System.err.println(String.format("Error: %s", e.getMessage()));
                }});}
        private void dialog(Alert.AlertType alertType,String s){
            Alert alert = new Alert(alertType,s);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Info");
            alert.showAndWait();
        }
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(hb);
            }else{
                setGraphic(null);
            }
        }
    }
}
