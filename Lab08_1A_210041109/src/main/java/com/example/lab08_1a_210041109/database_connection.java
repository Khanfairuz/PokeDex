package com.example.lab08_1a_210041109;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;

public class database_connection {
    private String url = "jdbc:postgresql://localhost:5432/java";
    private String username = "postgres";
    private String password = "1234";
    private Connection connection;

    // Method to establish connection
    public void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close connection
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public int serachbarName(String name)
     {
         int id=-1;
         String sql="SELECT id FROM pokeinfo WHERE name=?";
         try(PreparedStatement preparedStatement=connection.prepareStatement(sql))
         {
             preparedStatement.setString(1,name);
             ResultSet rs=preparedStatement.executeQuery();
             if(rs.next())
             {
                 id=rs.getInt("id");
             }


         }
         catch (Exception e)
         {
             e.printStackTrace();
         }

            return  id;
     }
   public  void initialize_poke_info(pokeInfo pk)
   {

       String sql = "SELECT name, height, weight, category, abilities, type, description, weakness, evaluationid, pokeimage FROM pokeinfo WHERE id=?";
       try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setInt(1, pk.id);
           ResultSet rs = preparedStatement.executeQuery();
           if (rs.next()) {
               pk.namePoke = rs.getString("name");
               pk.height = rs.getDouble("height");
               pk.weight = rs.getDouble("weight");
              pk. category = rs.getString("category");

               // Split the abilities and type strings into ArrayLists
              pk. abilities = new ArrayList<>(Arrays.asList(rs.getString("abilities").split(",")));
               pk.type = new ArrayList<>(Arrays.asList(rs.getString("type").split(",")));

               pk.description = rs.getString("description");

               // Split the weakness string into an ArrayList
               pk.weakness = new ArrayList<>(Arrays.asList(rs.getString("weakness").split(",")));

               pk.evaluationId = rs.getInt("evaluationid");
               pk.imgName = rs.getString("pokeimage");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

   }

   public  void intialize_evaluation_info(pokeInfo pk)
   {
       String sql = "SELECT evaluationarray FROM evaluation WHERE evaluationid=?";
       try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setInt(1, pk.evaluationId);
           ResultSet rs = preparedStatement.executeQuery();
           if (rs.next()) {
               // Split the evaluationarray string into an ArrayList
               pk.evaluationArray = new ArrayList<>(Arrays.asList(rs.getString("evaluationarray").split(",")));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }


   }
   public  void initialize_rating(pokeInfo pk)
   {
       String sql = "SELECT fav FROM favpoke WHERE id=?";
       try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setInt(1, pk.id);
           ResultSet rs = preparedStatement.executeQuery();
           if (rs.next()) {
               pk.fav = rs.getBoolean("fav");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }


   }
    public void updateFavPoke(int id, boolean fav) {
        try {
            // Prepare the SQL statement
            String sql = "UPDATE favpoke SET fav = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setBoolean(1, fav);
            preparedStatement.setInt(2, id);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Fav status updated successfully for ID: " + id);
            } else {
                System.out.println("No records were updated for ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* public void serachbarType(afterSearch hc, String name) {
        String sql = "SELECT pokelist FROM poketype WHERE typename=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                // Retrieve the array of integers from the result set
                Array pokelistArray = rs.getArray("pokelist");
                if (pokelistArray != null) {
                    // Convert the array to an integer array
                    Object[] array = (Object[]) pokelistArray.getArray();
                    int[] intArray = new int[array.length];
                    for (int i = 0; i < array.length; i++) {
                        intArray[i] = (int) array[i];
                    }
                    // Assign the integer array to hc.intArray
                    hc.intArray = intArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
   public void serachbarType(afterSearch hc, String name) {
       String sql = "SELECT pokelist FROM poketype WHERE typename=?";
       try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setString(1, name);
           ResultSet rs = preparedStatement.executeQuery();
           if (rs.next()) {
               // Retrieve the array of integers from the result set
               Array pokelistArray = rs.getArray("pokelist");
               if (pokelistArray != null) {
                   // Convert the array to an integer array
                   Object[] array = (Object[]) pokelistArray.getArray();
                   int[] intArray = new int[array.length];
                   for (int i = 0; i < array.length; i++) {
                       intArray[i] = (int) array[i];
                   }
                   // Convert the integer array to an ArrayList<Integer>
                   ArrayList<Integer> arrayList = new ArrayList<>();
                   for (int i : intArray) {
                       arrayList.add(i);
                   }
                   // Assign the ArrayList to hc.intArray
                   hc.intArray = arrayList;
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }


    public  String extractNamePokeById(int id)
    {
        String namePoke = null;
        String sql = "SELECT name  FROM pokeinfo WHERE id=?";
        //image name return
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                namePoke = rs.getString("name");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return   namePoke;

    }
    public  int extractIdByName(String name)
    {
         int id = 0;
        String sql = "SELECT id  FROM pokeinfo WHERE name=?";
        //image name return
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
               id = rs.getInt("id");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return   id;

    }





}