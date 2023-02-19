package agh.gui;

import agh.Map;
import agh.Path;
import agh.Player;
import agh.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

public class MenuController {
    private Map map;
    private int mapid;
    @FXML
    private BorderPane pane;
    @FXML
    private HBox maps;
    @FXML
    private Button buttonStart;
    @FXML
    private TextField textField;
    @FXML
    private CheckBox darkMode;
    @FXML
    private Label helpLabel;
    private Scene scene;
    private Parent root;

    @FXML
    public void createNew(ActionEvent event){
        pane.setCenter(maps);
        maps.setVisible(!maps.isVisible());
        if(buttonStart.isVisible()) hideStart();
        // pokazuje mapy to wyboru
        System.out.println("create new save");
    }

    @FXML
    public void loadSave(ActionEvent event){
        // pokazuje save'y to wczytania
        System.out.println("load save");
    }

    @FXML
    public void showOptions(ActionEvent event){
        // pokazuje opcje
        System.out.println("show options");
        switchOptions();
    }

    @FXML
    public void showAbout(ActionEvent event){
        // pokazuje opcje
        System.out.println("show about");
        helpLabel.setVisible(!helpLabel.isVisible());
    }


    @FXML
    public void saveAndExit(ActionEvent event){
        // zapisuje postep i zamyka aplikajce (mozna tez podlaczyc x pod to)
        System.out.println("exit (without save for now)");
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    public void switchDarkMode(ActionEvent event){
        Scene scene = ((Node) event.getSource()).getScene();
        if (darkMode.isSelected()) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/menuDark.css")).toExternalForm());
        } else {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/menuLight.css")).toExternalForm());
        }
    }

    @FXML
    public void selectMap1(){
        System.out.println("map1");
        mapid = 1;
        map = new Map(150,150);
        Point[] points = {new Point(35,0),
                new Point(31,12),
                new Point(60,40),
                new Point(50,50),
                new Point(30,50),
                new Point(24,35),
                new Point(24,35),
                new Point(65,30),
                new Point(70,30)
        };
        Path path = new Path(points);
        path.addObserver(map);
        map.addPath(path);
        showStart();
    }

    @FXML
    public void selectMap2(){
        System.out.println("map2");
        mapid = 2;
        map = new Map(50,50);
        Point[] points = {new Point(0,17),
                new Point(24,17),
                new Point(24,36),
                new Point(71,36),
                new Point(71,62),
                new Point(42,62),
                new Point(42,22),
                new Point(84,22),
                new Point(84,10),
                new Point(145,10),
                new Point(145,44),
                new Point(107,44),
                new Point(107,80)
        };
        Path path = new Path(points);
        path.addObserver(map);
        map.addPath(path);
        showStart();
    }

    @FXML
    public void selectMap3(){
        System.out.println("map3");
        mapid = 3;
        map = new Map(50,50);
        Point[] points = {new Point(0,0), new Point(20,0), new Point(20,20), new Point(0,20)};
        Path path = new Path(points);
        path.addObserver(map);
        map.addPath(path);
        showStart();
    }

    private void showStart(){
        textField.setDisable(false);
        textField.setVisible(true);
        buttonStart.setDisable(false);
        buttonStart.setVisible(true);
    }

    private void hideStart(){
        textField.setDisable(true);
        textField.setVisible(false);
        buttonStart.setDisable(true);
        buttonStart.setVisible(false);
    }

    private void switchOptions(){
        boolean state = darkMode.isVisible();
        darkMode.setDisable(state);
        darkMode.setVisible(!state);

    }

    @FXML
    public void start(ActionEvent event) throws IOException {
        FXMLLoader loader = switch(mapid){
            case 1 -> new FXMLLoader(getClass().getResource("/game1.fxml"));
            case 2 -> new FXMLLoader(getClass().getResource("/game2.fxml"));
            case 3 -> new FXMLLoader(getClass().getResource("/game3.fxml"));
            default -> null;
        };
        root = loader.load();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/game.css")).toExternalForm());
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(scene);

        GameController gameController = loader.getController();
        String name = textField.getText();
        if (name.isEmpty()) name = "Player";
        gameController.initGame(map, new Player(name, 100));
    }


}
