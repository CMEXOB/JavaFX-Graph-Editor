module com.applic {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;

    opens com.applic to javafx.fxml;
    exports com.applic;
    exports com.applic.entity;
    opens com.applic.entity to javafx.fxml;
}