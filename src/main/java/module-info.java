module tasks {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.controlsfx.controls;
    requires log4j;


    opens tasks.model to javafx.base;
    exports tasks.model;
    opens tasks.view to javafx.fxml;
    exports tasks.view;
    opens tasks.controller to javafx.fxml;
    exports tasks.controller;
}