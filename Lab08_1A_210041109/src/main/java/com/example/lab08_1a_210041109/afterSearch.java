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
import java.util.ArrayList;

public class afterSearch {

     private  String typeName;
     public   database_connection db;
    public ArrayList<Integer> intArray = new ArrayList<>();
    private String imgNameFinal="/imageFolder/";
       @FXML
    private ImageView  imgv1,imgv2,imgv3,imgv4,imgv5,imgv6 ,logo;

    @FXML
    private TextField searchfield;
    @FXML
    private Button buttonsearch ,B1,B2,B3,B4,B5,B6;
    private  String searchbarans;


     void startType(String typeName, database_connection db)
    {
        this.typeName=typeName;
        this.db=db;
       db. serachbarType(this,typeName);
        searchfield.setText(typeName);
        searchfield.setEditable(false);
       //array pailo then image show korbe
        logoSet();
        showAllInImageView();


    }
    private  void logoSet()
    {
       // "grass", "poison", "fire", "flying", "water", "bug", "electric"
        Image img = null;
        if(typeName.equals("grass"))
        {
            img=new Image(getClass().getResourceAsStream(imgNameFinal+typeName+".png"));

        }
        else  if(typeName.equals("poison"))
        {
            img=new Image(getClass().getResourceAsStream(imgNameFinal+typeName+".png"));

        }
        else if(typeName.equals("fire"))
        {
            img=new Image(getClass().getResourceAsStream(imgNameFinal+typeName+".png"));

        }
       else  if(typeName.equals("flying"))
        {
            img=new Image(getClass().getResourceAsStream(imgNameFinal+typeName+".png"));

        }
       else  if(typeName.equals("water"))
        {
            img=new Image(getClass().getResourceAsStream(imgNameFinal+typeName+".png"));

        }
       else  if(typeName.equals("bug"))
        {
            img=new Image(getClass().getResourceAsStream(imgNameFinal+typeName+".png"));

        }
       else  if(typeName.equals("electric"))
        {
            img=new Image(getClass().getResourceAsStream(imgNameFinal+typeName+".png"));

        }



        logo.setImage(img);



    }

    public  void showAllInImageView()
    {
        int size= intArray.size();
        Image img;
        String namePoke="";
        if(size>=1)
        {
           namePoke= db.extractNamePokeById(intArray.get(0).intValue());
            img=new Image(getClass().getResourceAsStream(imgNameFinal+namePoke+".png"));
            imgv1.setImage(img);
            B1.setVisible(true);
            B1.setText(namePoke);

        }
        if(size>=2)
        {
            namePoke= db.extractNamePokeById(intArray.get(1).intValue());
            img=new Image(getClass().getResourceAsStream(imgNameFinal+namePoke+".png"));
            imgv2.setImage(img);
            B2.setVisible(true);
            B2.setText(namePoke);

        }
        if(size>=3)
        {
            namePoke= db.extractNamePokeById(intArray.get(2).intValue());
            img=new Image(getClass().getResourceAsStream(imgNameFinal+namePoke+".png"));
            imgv3.setImage(img);
            B3.setVisible(true);
            B3.setText(namePoke);

        }
        if(size>=4)
        {
            namePoke= db.extractNamePokeById(intArray.get(3).intValue());
            img=new Image(getClass().getResourceAsStream(imgNameFinal+namePoke+".png"));
            imgv4.setImage(img);
            B4.setVisible(true);
            B4.setText(namePoke);

        }
        if(size>=5)
        {
            namePoke= db.extractNamePokeById(intArray.get(4).intValue());
            img=new Image(getClass().getResourceAsStream(imgNameFinal+namePoke+".png"));
            imgv5.setImage(img);
            B5.setVisible(true);
            B5.setText(namePoke);

        }
        if(size>=6)
        {
            namePoke= db.extractNamePokeById(intArray.get(5).intValue());
            img=new Image(getClass().getResourceAsStream(imgNameFinal+namePoke+".png"));
            imgv6.setImage(img);
            B6.setVisible(true);
            B6.setText(namePoke);

        }





    }

    @FXML
    private  void startSearching(ActionEvent e)
    {

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
        //text
        Button button=(Button) event.getSource();
        String buttonText = button.getText();


        int  clickedId=db.extractIdByName(buttonText);
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
