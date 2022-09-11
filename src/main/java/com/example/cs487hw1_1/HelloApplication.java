package com.example.cs487hw1_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 386, 229);
        stage.setTitle("Admin Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {


        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunlab_management","root","Sunanda$0822");

            PreparedStatement statement = connection.prepareStatement("select * from sunlab_entry_records");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("psu_id"));
                System.out.println(resultSet.getInt("user_status"));
                System.out.println(resultSet.getString("user_role"));
                System.out.println(resultSet.getTimestamp("entry_time"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        launch();

    }
}