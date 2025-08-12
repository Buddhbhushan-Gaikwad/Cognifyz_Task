module com.spingwithbushan.imageeditorapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;

    opens com.spingwithbushan.imageeditorapp to javafx.fxml;
    opens com.spingwithbushan.imageeditorapp.controllers to javafx.fxml;

    exports com.spingwithbushan.imageeditorapp;
}
