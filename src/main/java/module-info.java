module org.isen.projet {
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.web;
    requires org.json;

    opens org.isen.projet to javafx.fxml;
    exports org.isen.projet;
}

