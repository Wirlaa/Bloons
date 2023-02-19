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
import javafx.scene.image.Image;
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
        map = new Map(160,90);
        Point[] points = {new Point(35,0),
                new Point(31,12),
                new Point(60,40),
                new Point(50,50),
                new Point(30,50),
                new Point(24,35),
                new Point(47,18),
                new Point(60,17),
                new Point(80,25),
                new Point(110,49),
                new Point(130,54),
                new Point(140,53),
                new Point(160,40),
                new Point(155,30),
                new Point(130,23),
                new Point(110,23),
                new Point(95,33),
                new Point(80,50),
                new Point(80,60),
                new Point(110,65),
                new Point(120,77)
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
        map = new Map(160,90);
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
                new Point(107,77)
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
        map = new Map(160,90);
        Point[] points1 = {new Point(80, 0),
                new Point(80, 12),
                new Point(40, 20),
                new Point(66, 30),
                new Point(37, 50),
                new Point(70, 57),
                new Point(55, 77)
        };
        Path path1 = new Path(points1);
        path1.addObserver(map);
        map.addPath(path1);
        Point[] points2 = {new Point(80, 0),
                new Point(80, 12),
                new Point(132, 20),
                new Point(108, 34),
                new Point(145, 42),
                new Point(110, 60),
                new Point(144, 76)
        };
        Path path2 = new Path(points2);
        path2.addObserver(map);
        map.addPath(path2);
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
        Image image = switch(mapid){
            case 1 -> new Image("/map1.png");
            case 2 -> new Image("/map2.png");
            case 3 -> new Image("/map3.png");
            default -> null;
        };
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/game.fxml"));
        root = loader.load();
        scene = new Scene(root);
        GameController gameController = loader.getController();

        if (darkMode.isSelected()) {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/gameDark.css")).toExternalForm());
        } else {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/gameLight.css")).toExternalForm());
        }
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(scene);

        String name = textField.getText();
        if (name.isEmpty()) name = "Player";

        gameController.initGame(map, new Player(name, 100), darkMode.isSelected(), image);
    }


}
