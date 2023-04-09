module com.example.exam {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;


    opens com.example.exam to javafx.fxml;
    exports com.example.exam;

    opens com.example.exam.Models to javafx.fxml;
    exports com.example.exam.Models;
}