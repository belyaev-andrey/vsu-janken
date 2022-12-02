module ru.vsu.csf.janken.client.jankenclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires ru.vsu.csf.janken.sdk.jankensdk;


    opens ru.vsu.csf.janken.client to javafx.fxml;
    exports ru.vsu.csf.janken.client;
}