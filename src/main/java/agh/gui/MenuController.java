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
    private final double coeffx = 0.80;
    private final double coeffy = 1;

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
        Point[] points = {new Point(35*coeffx,0*coeffy),
                new Point(31*coeffx,12*coeffy),
                new Point(60*coeffx,40*coeffy),
                new Point(50*coeffx,50*coeffy),
                new Point(30*coeffx,50*coeffy),
                new Point(24*coeffx,35*coeffy),
                new Point(47*coeffx,18*coeffy),
                new Point(60*coeffx,17*coeffy),
                new Point(80*coeffx,25*coeffy),
                new Point(110*coeffx,49*coeffy),
                new Point(130*coeffx,54*coeffy),
                new Point(140*coeffx,53*coeffy),
                new Point(160*coeffx,40*coeffy),
                new Point(155*coeffx,30*coeffy),
                new Point(130*coeffx,23*coeffy),
                new Point(110*coeffx,23*coeffy),
                new Point(95*coeffx,33*coeffy),
                new Point(80*coeffx,50*coeffy),
                new Point(80*coeffx,60*coeffy),
                new Point(110*coeffx,65*coeffy),
                new Point(120*coeffx,76*coeffy)
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
        Point[] points = {new Point(0*coeffx,17*coeffy),
                new Point(24*coeffx,17*coeffy),
                new Point(24*coeffx,36*coeffy),
                new Point(71*coeffx,36*coeffy),
                new Point(71*coeffx,62*coeffy),
                new Point(42*coeffx,62*coeffy),
                new Point(42*coeffx,22*coeffy),
                new Point(84*coeffx,22*coeffy),
                new Point(84*coeffx,10*coeffy),
                new Point(145*coeffx,10*coeffy),
                new Point(145*coeffx,44*coeffy),
                new Point(107*coeffx,44*coeffy),
                new Point(107*coeffx,76*coeffy)
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
        Point[] points1 = {new Point(80*coeffx, 0*coeffy),
                new Point(80*coeffx, 12*coeffy),
                new Point(40*coeffx, 20*coeffy),
                new Point(66*coeffx, 30*coeffy),
                new Point(37*coeffx, 50*coeffy),
                new Point(70*coeffx, 57*coeffy),
                new Point(55*coeffx, 76*coeffy)
        };
        Path path1 = new Path(points1);
        path1.addObserver(map);
        map.addPath(path1);
        Point[] points2 = {new Point(80*coeffx, 0*coeffy),
                new Point(80*coeffx, 12*coeffy),
                new Point(132*coeffx, 20*coeffy),
                new Point(108*coeffx, 34*coeffy),
                new Point(145*coeffx, 42*coeffy),
                new Point(110*coeffx, 60*coeffy),
                new Point(144*coeffx, 76*coeffy)
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
