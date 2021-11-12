module com.mycompany.desayunosfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    

    opens com.mycompany.desayunosfx to javafx.fxml;
    opens models;
    exports com.mycompany.desayunosfx;
    
    
}
