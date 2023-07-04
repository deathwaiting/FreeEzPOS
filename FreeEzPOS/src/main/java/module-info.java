module io.galal {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.galal to javafx.fxml;
    exports io.galal;
}
