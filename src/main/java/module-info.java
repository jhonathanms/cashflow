module com.everton.cashflow.cashflow {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires AnimateFX;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires okhttp3;
    requires lombok;

    exports com.everton.cashflow.controller;
    opens com.everton.cashflow.controller to javafx.fxml;
    exports com.everton.cashflow.main;
    opens com.everton.cashflow.main to javafx.fxml;
}