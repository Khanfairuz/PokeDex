package com.example.lab08_1a_210041109;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloController {
    @FXML
    private TextField searchfield;
    @FXML
    private Button buttonsearch;
    @FXML
    private AnchorPane ac1;
    private  String searchbarans;
    public  database_connection db= new database_connection();;
    private int  serachId;
    private  int clickedId;
    private Parent root;
    List<String> typesList = Arrays.asList("grass", "poison", "fire", "flying", "water", "bug", "electric");
    ArrayList<String> arrayList = new ArrayList<>(typesList);

    public  String TypeName;
    private  Boolean flag=false;


     public void startProcess()
     {
         db.connect();
     }

    @FXML
    private  void startSearching(ActionEvent e)
    {
        searchbarans=searchfield.getText();
        System.out.println(searchbarans);
        //db.connect();



            //type er modhe naki
            for(int i=0;i<arrayList.size();i++)
            {
                if(searchbarans.toLowerCase().equals(arrayList.get(i)))
                {
                    //type match hoise
                    System.out.println("Hlww9");
                    serachId=0;
                    showAllTypes(e);
                    flag=true;
                    break;

                }
            }
            if(!flag)
            {
                System.out.println("Hlww");
                serachId=db.serachbarName(searchbarans.toLowerCase());
                System.out.println(serachId);
                //by name hoise
                 if(serachId!=-1)
                 {
                     showByName(e);
                 }


            }



        if(serachId==-1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Name");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Name or Type Searched");
            alert.showAndWait();
        }
        //serach correct-->new page load
        //name wise serach 1 page load one anchor pane
        //type wise search 2 page load multiple image + their button
        //button er sathe showInfoPoke(ActionEvent e) +pokeInfoPage(ActionEvent event) connect kore dibo +back button er code


    }
    public  void showByName(ActionEvent event)
    {
        //page load korbo//id pass
        FXMLLoader  loader=new FXMLLoader(getClass().getResource("name.fxml"));
        try {
            root=loader.load();
            afterSearchName controller = loader.getController();
            controller.startName(serachId,db);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;

            Scene scene = new Scene(root,947, 690);
            stage.setScene(scene);
            Image icon = new Image(getClass().getResourceAsStream("/imageFolder/splash.png"));


            stage.getIcons().add(icon);
            stage.setTitle("PokeDex!");
            stage.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public  void showAllTypes(ActionEvent event)
    {
        FXMLLoader  loader=new FXMLLoader(getClass().getResource("type.fxml"));
        try {
            root=loader.load();
            afterSearch controller = loader.getController();
            controller.startType(searchbarans.toLowerCase(),db);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;

            Scene scene = new Scene(root,947, 690);
            stage.setScene(scene);
            Image icon = new Image(getClass().getResourceAsStream("/imageFolder/splash.png"));


            stage.getIcons().add(icon);
            stage.setTitle("PokeDex!");
            stage.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public  void pokeInfoPage(ActionEvent event)
    {
     FXMLLoader  loader=new FXMLLoader(getClass().getResource("poke-info.fxml"));
        try {
            root=loader.load();
            pokeInfo controller = loader.getController();
            controller.start(clickedId ,db);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;

            Scene scene = new Scene(root,947, 690);
            stage.setScene(scene);
            Image icon = new Image(getClass().getResourceAsStream("/imageFolder/splash.png"));


            stage.getIcons().add(icon);
            stage.setTitle("PokeDex!");
            stage.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    public  void showInfoPoke(ActionEvent e)
    {
       // db.connect();
     Button button=(Button) e.getSource();
     String buttonId=button.getId();
     String newbuttonId=buttonId.substring(1);
     clickedId=Integer.parseInt(newbuttonId);
     //check
        System.out.println(clickedId);
     //new page load hbe +id pass hbe
     pokeInfoPage(e);


    }



}