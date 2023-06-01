module com.example.fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.fx to javafx.fxml;
    exports com.example.fx;
    opens com.example.fx.gameController to javafx.fxml;
}