module vallegrande.edu.pe.projectfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;

    exports vallegrande.edu.pe.projectfinal;
    exports vallegrande.edu.pe.projectfinal.view;
    exports vallegrande.edu.pe.projectfinal.controller;
    exports vallegrande.edu.pe.projectfinal.model;

    opens vallegrande.edu.pe.projectfinal.view to javafx.fxml;
}