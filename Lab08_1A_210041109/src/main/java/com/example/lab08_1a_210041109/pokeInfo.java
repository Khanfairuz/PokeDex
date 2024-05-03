package com.example.lab08_1a_210041109;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class pokeInfo {

    public   int id;
    public String imgName;
    public  String namePoke;
    public  double height ,weight;
    public  String  category;
    public ArrayList<String> abilities= new ArrayList<>();
    public ArrayList<String> type= new ArrayList<>();
    public  String description;
    public ArrayList<String>weakness=new ArrayList<>();
    public  int evaluationId;
    public String imgNameFinal="/imageFolder/";
    public ArrayList<String> evaluationArray=new ArrayList<>();
    public boolean fav;
     @FXML
     private ImageView imgViewMain,evo1,evo2,evo3 , com1 , com2 , rating;
     @FXML
     private TextField name,heightText ,weightText,categoryText,abilityText,type1,type2,
             weak1,weak2,weak3,weak4,weak5 , evaName1,evaName2,evaName3;
     @FXML
     private TextArea descrip;
     @FXML
     private Button favB;
     private  database_connection db;
    //private
    public  void start(int  id , database_connection db)
    {
        this.id=id;
        this. db=db;

        db.initialize_poke_info(this);
        db.intialize_evaluation_info(this);
        db.initialize_rating(this);
        setRating();
        setFixed();
        setType();
        setWeakness();
        setEvolution();

    }
    public String convertHeightToString(double height) {
        int feet = (int) height;
        int inches = (int) ((height - feet));
        return feet + "'" + inches + "''";
    }
    public  void setFixed()
    {
        name.setText(namePoke);
        Image im=new Image(getClass().getResourceAsStream(imgNameFinal+imgName));
        imgViewMain.setImage(im);
        descrip.setText(description);
        heightText.setText(convertHeightToString(height));
        weightText.setText(Double.toString(weight)+" lbs");
        categoryText.setText(category);
        abilityText.setText(abilities.get(0).substring(1,abilities.get(0).length()-1));
    }

    public void setType() {
        int size=type.size();
        //extra bracket ashe
        type.set(0,type.get(0).substring(1));
        type.set(size-1 ,type.get(size-1).substring(0,type.get(size-1).length()-1));
        if(size>=1)
        {
            type1.setText(type.get(0));
            type1.setVisible(true);
        }
         if(size==2)
         {
             type2.setText(type.get(1));
             type2.setVisible(true);
         }
    }
   public void setWeakness()
   {
       int size=weakness.size();
       System.out.println(weakness.get(0));
       weakness.set(0,weakness.get(0).substring(1));
       System.out.println(weakness.get(0));
       weakness.set(size-1 ,weakness.get(size-1).substring(0,weakness.get(size-1).length()-1));
       if(size>=1)
       {
           weak1.setText(weakness.get(0));
           weak1.setVisible(true);
       }
       if(size>=2)
       {
           weak2.setText(weakness.get(1));
           weak2.setVisible(true);
       }
       if(size>=3)
       {
           weak3.setText(weakness.get(2));
           weak3.setVisible(true);
       }
       if(size>=4)
       {
           weak4.setText(weakness.get(3));
           weak4.setVisible(true);
       }
       if(size>=5)
       {
           weak5.setText(weakness.get(4));
           weak5.setVisible(true);
       }

   }
   public void setEvolution()
   {
       int indexOfDot;

      int size= evaluationArray.size();
       evaluationArray.set(0,evaluationArray.get(0).substring(1));
       evaluationArray.set(size-1 ,evaluationArray.get(size-1).substring(0,evaluationArray.get(size-1).length()-1));


      if(size>=1)
      {

          Image im1=new Image(getClass().getResourceAsStream(imgNameFinal+evaluationArray.get(0)));
          evo1.setImage(im1);
          evaName1.setVisible(true);
          indexOfDot=evaluationArray.get(0).indexOf('.');
          evaName1.setText(evaluationArray.get(0).substring(0,indexOfDot));

      }
       if(size>=2)
       {

           Image im2=new Image(getClass().getResourceAsStream(imgNameFinal+evaluationArray.get(1)));
           evo2.setImage(im2);
           com1.setVisible(true);

           evaName2.setVisible(true);
           indexOfDot=evaluationArray.get(1).indexOf('.');
           evaName2.setText(evaluationArray.get(1).substring(0,indexOfDot));

       }
       if(size==3)
       {

           Image im3=new Image(getClass().getResourceAsStream(imgNameFinal+evaluationArray.get(2)));
           evo3.setImage(im3);
           com2.setVisible(true);

           evaName3.setVisible(true);
           indexOfDot=evaluationArray.get(2).indexOf('.');
           evaName3.setText(evaluationArray.get(2).substring(0,indexOfDot));
       }


   }

    public  void setRating()
    {
     if(fav)
     {
         Image im=new Image(getClass().getResourceAsStream("/imageFolder/starF.png")) ;
         rating.setImage(im);
         favB.setText("Reomve from Favourites");
         //fav theke shorano
     }
     else
     {
         Image im1=new Image(getClass().getResourceAsStream("/imageFolder/star.png")) ;
         rating.setImage(im1);
         favB.setText("Add to Favorites");
     }


    }
    @FXML
    public  void clickFav(ActionEvent e)
    {
        String compare=favB.getText();
        if(compare.equals("Add to Favorites"))
        {
            //add
            fav=true;
            setRating();
            //database change

        }
        else if(compare.equals("Reomve from Favourites"))
        {
            //remove
            fav=false;
            setRating();


        }
        db.updateFavPoke(id,fav);

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

}
