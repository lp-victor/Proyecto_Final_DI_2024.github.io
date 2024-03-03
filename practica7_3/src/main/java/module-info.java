module victor.practica7_3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens victor.practica7_3 to javafx.fxml;
    exports victor.practica7_3;
    exports victor.practica7_3.controlador;
    opens victor.practica7_3.controlador to javafx.fxml;
}