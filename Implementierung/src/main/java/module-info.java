module gui.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.sql;
    requires org.junit.jupiter.api;


    opens kings.gui to javafx.fxml;
    exports kings.gui;
    exports kings.server.chat;
    exports kings.server.room;
    exports kings.client;
    exports kings.server.administration;
}