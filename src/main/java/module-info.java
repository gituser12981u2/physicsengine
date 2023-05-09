module com.physicsengine {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.physicsengine to javafx.fxml;

    exports com.physicsengine;
}
