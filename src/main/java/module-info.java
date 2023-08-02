module com.citi.move_move_ {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.citi.move_move_ to javafx.fxml;
    exports com.citi.move_move_;
}