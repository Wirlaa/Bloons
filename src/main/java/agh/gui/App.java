package agh.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Objects;


//glowny stage, w sumie tylko startuje okienko aplikacji
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Bloons");
        Image icon = new Image("/icon.png");
        stage.getIcons().add(icon);
        stage.initStyle(StageStyle.TRANSPARENT);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menu.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/menuDark.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            System.out.println("close request");
            Platform.exit();
            System.exit(0);
        });
    }


}
