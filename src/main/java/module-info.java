module com.everton.cashflow.cashflow {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires AnimateFX;

    opens com.everton.cashflow to javafx.fxml;
    exports com.everton.cashflow;
    exports com.everton.cashflow.controller;
    opens com.everton.cashflow.controller to javafx.fxml;
}