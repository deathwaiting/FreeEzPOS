module io.galal {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jfxtras.styles.jmetro;

    opens io.galal to javafx.fxml;
    exports io.galal;
}
