module com.example.cs487hw1_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cs487hw1_1 to javafx.fxml;
    exports com.example.cs487hw1_1;
}