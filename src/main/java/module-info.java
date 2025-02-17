module com.esprit.microservice.sponsor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.esprit.microservice.sponsor to javafx.fxml;
    opens controllers to javafx.fxml;

    exports com.esprit.microservice.sponsor;
    exports controllers;
}