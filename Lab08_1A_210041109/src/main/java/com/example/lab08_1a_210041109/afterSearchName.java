package com.example.lab08_1a_210041109;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class afterSearchName {

    @FXML
 private ImageView imgv;
    @FXML
    private Button B1;

    private  int id;
    @FXML
    private TextField searchfield;
    public  database_connection db;
    public  String pokeName;
    private String imgNameFinal="/imageFolder/";

    void startName(int id, database_connection db)
    {
        this.id=id;
        this.db=db;
         showName();



    }
    private  void showName()
    {
        pokeName=db.extractNamePokeById(id);
        searchfield.setText(pokeName);
        searchfield.setEditable(false);
        System.out.println(pokeName);
        Image img=new Image(getClass().getResourceAsStream(imgNameFinal+pokeName+".png"));
        imgv.setImage(img);
        B1.setText(pokeName);
        B1.setVisible(true);
    }




    @FXML
    public  void  backToSearch(ActionEvent event)
    {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("hello-view.fxml"));
        try {
            Parent root=fxmlLoader.load();
            HelloController helloController = fxmlLoader.getController();

            // Call the startProcessing() method
            helloController.startProcess();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;

            Scene scene = new Scene(root,947, 690);
            Image icon = new Image(getClass().getResourceAsStream("/imageFolder/splash.png"));


            stage.getIcons().add(icon);
            stage.setTitle("PokeDex!");
            stage.setScene(scene);

            stage.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    public  void pokeInfoPage(ActionEvent event)
    {
        int clickedId=id;
        //as Hello Controller theke oi specific id pass hoise
        //check
        FXMLLoader  loader=new FXMLLoader(getClass().getResource("poke-info.fxml"));
        try {
            Parent root=loader.load();
            pokeInfo controller = loader.getController();
            controller.start(clickedId ,db);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;

            Scene scene = new Scene(root,947, 690);
            Image icon = new Image(getClass().getResourceAsStream("/imageFolder/splash.png"));


            stage.getIcons().add(icon);
            stage.setTitle("PokeDex!");
            stage.setScene(scene);
            stage.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
