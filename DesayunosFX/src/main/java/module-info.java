module com.mycompany.desayunosfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.desayunosfx to javafx.fxml;
    exports com.mycompany.desayunosfx;
    requires java.persistence;
    requires java.base;
}
