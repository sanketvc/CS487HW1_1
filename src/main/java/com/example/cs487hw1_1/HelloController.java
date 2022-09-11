package com.example.cs487hw1_1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class HelloController {


        @FXML
        private Button btnAdd;
        @FXML
        private Button btnSearch;


        @FXML
        private TableView<RecordModel> table;
        @FXML
        private TableColumn<RecordModel, String> psuIdColumn;
        @FXML
        private TableColumn<RecordModel, Integer> statusColumn;
        @FXML
        private TableColumn<RecordModel, String> roleColumn;
        @FXML
        private TableColumn<RecordModel, Timestamp> timeOfEntryColumn;


        @FXML
        private TextField txtRole;
        @FXML
        private TextField txtPsuId;
        @FXML
        private TextField txtStatus;


        @FXML
        private TextField txtSearchByDateTimeRangeEnd;
        @FXML
        private TextField txtSearchByDateTimeRangeStart;
        @FXML
        private TextField txtSearchByPsuId;


        public void initialize(){
                updateTable();
        }


        @FXML
        void Add(ActionEvent event) {
                String psuid = txtPsuId.getText();
                int status = Integer.parseInt(txtStatus.getText());
                String role = txtRole.getText();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                try{
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunlab_management","root","Sunanda$0822");
                        PreparedStatement statement = connection.prepareStatement("insert into sunlab_entry_records (psu_id, user_status, user_role, entry_time) values (?,?,?,?)");

                        statement.setString(1, psuid);
                        statement.setInt(2,status);
                        statement.setString(3,role);
                        statement.setTimestamp(4, timestamp);
                        statement.executeUpdate();

                }catch (Exception e){
                        e.printStackTrace();
                }
                updateTable();
        }

        void updateTable(){
                try{
                        ArrayList<RecordModel> listOfRecordModels = new ArrayList<RecordModel>();

                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunlab_management","root","Sunanda$0822");
                        PreparedStatement statement = connection.prepareStatement("select * from sunlab_entry_records");

                        ResultSet resultSet = statement.executeQuery();
                        while (resultSet.next()){

                                String psuid = resultSet.getString("psu_id");
                                int userStatus = resultSet.getInt("user_status");
                                String userRole = resultSet.getString("user_role");
                                Timestamp entryTime = resultSet.getTimestamp("entry_time");

                                listOfRecordModels.add(new RecordModel(psuid,userStatus,userRole,entryTime));
                        }
                        psuIdColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, String>("psu_id"));
                        statusColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, Integer>("status"));
                        roleColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, String>("role"));
                        timeOfEntryColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, Timestamp>("entry_time"));

                        ObservableList<RecordModel> recordModels = FXCollections.observableList(listOfRecordModels);
                        table.setItems(recordModels);

                }catch (Exception e){
                        e.printStackTrace();
                }

        }


        @FXML
        void Search(ActionEvent event) {

                String psuIdSearch = txtSearchByPsuId.getText();
                String dateTimeStart = txtSearchByDateTimeRangeStart.getText();
                String dateTimeEnd = txtSearchByDateTimeRangeEnd.getText();

                if (psuIdSearch.isEmpty()){
                        try{
                                ArrayList<RecordModel> listOfRecordModels = new ArrayList<RecordModel>();
                                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunlab_management","root","Sunanda$0822");
                                PreparedStatement statement = connection.prepareStatement("select * from sunlab_entry_records where psu_id > ? and entry_time between ? and ?");


                                statement.setString(1, psuIdSearch);
                                statement.setString(2,dateTimeStart);
                                statement.setString(3,dateTimeEnd);

                                ResultSet resultSetOfSearch = statement.executeQuery();
                                while (resultSetOfSearch.next()){

                                        String psuid = resultSetOfSearch.getString("psu_id");
                                        int userStatus = resultSetOfSearch.getInt("user_status");
                                        String userRole = resultSetOfSearch.getString("user_role");
                                        Timestamp entryTime = resultSetOfSearch.getTimestamp("entry_time");

                                        listOfRecordModels.add(new RecordModel(psuid,userStatus,userRole,entryTime));
                                }
                                psuIdColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, String>("psu_id"));
                                statusColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, Integer>("status"));
                                roleColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, String>("role"));
                                timeOfEntryColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, Timestamp>("entry_time"));

                                ObservableList<RecordModel> recordModels = FXCollections.observableList(listOfRecordModels);
                                table.setItems(recordModels);


                        }catch (Exception e){
                                e.printStackTrace();
                        }
                }
                else{
                        if (dateTimeStart.isEmpty()){
                                dateTimeStart="1970-01-01 00:00:01";
                        }
                        if (dateTimeEnd.isEmpty()){
                                dateTimeEnd="2037-01-19 03:14:07";
                        }

                        try{
                                ArrayList<RecordModel> listOfRecordModels = new ArrayList<RecordModel>();
                                Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunlab_management","root","Sunanda$0822");
                                PreparedStatement statement = connection2.prepareStatement("select * from sunlab_entry_records where psu_id = ? and entry_time between ? and ?");


                                statement.setString(1, psuIdSearch);
                                statement.setString(2,dateTimeStart);
                                statement.setString(3,dateTimeEnd);

                                ResultSet resultSetOfSearch = statement.executeQuery();
                                while (resultSetOfSearch.next()){

                                        String psuid = resultSetOfSearch.getString("psu_id");
                                        int userStatus = resultSetOfSearch.getInt("user_status");
                                        String userRole = resultSetOfSearch.getString("user_role");
                                        Timestamp entryTime = resultSetOfSearch.getTimestamp("entry_time");

                                        listOfRecordModels.add(new RecordModel(psuid,userStatus,userRole,entryTime));
                                }
                                psuIdColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, String>("psu_id"));
                                statusColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, Integer>("status"));
                                roleColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, String>("role"));
                                timeOfEntryColumn.setCellValueFactory(new PropertyValueFactory<RecordModel, Timestamp>("entry_time"));

                                ObservableList<RecordModel> recordModels = FXCollections.observableList(listOfRecordModels);
                                table.setItems(recordModels);


                        }catch (Exception e){
                                e.printStackTrace();
                        }
                }

        }
}




