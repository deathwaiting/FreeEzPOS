module io.galal.fre3zpos {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jfxtras.styles.jmetro;

    opens io.galal.fre3zpos to javafx.fxml;
    exports io.galal.fre3zpos;
}
