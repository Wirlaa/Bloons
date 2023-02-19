package agh.gui;


import agh.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Objects;

import static javafx.scene.layout.BackgroundSize.AUTO;

public class GameController implements IChangeObserver {
    @FXML
    private AnchorPane pane;
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label lifeCountLabel;
    @FXML
    private HBox shop;
    private Map map;
    private Engine engine;
    private boolean mode;

    //daloby sie uzyc initializable zamiast tego?
    public void initGame(Map map, Player player, boolean mode, Image image){
        lifeCountLabel.setText(Integer.toString(player.getLifeCount()));
        playerNameLabel.setText(player.getName());
        this.map = map;
        engine = new Engine(player, map, 0);
        engine.addObserver(this);
        Thread engineThread = new Thread(engine);
        engineThread.start();
        engine.pause();
        this.mode = mode;
        BackgroundImage bImg = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(AUTO,AUTO,false,false,true,true));
        Background bGround = new Background(bImg);
        pane.setBackground(bGround);
    }

    @FXML
    public void nextRound(ActionEvent event){
        engine.resume();
    }

    @FXML
    public void switchShop(ActionEvent event) {
        shop.setVisible(!shop.isVisible());
        shop.setDisable(!shop.isDisable());
    }

    @FXML
    public void switchMode(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        if (!mode) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/gameDark.css")).toExternalForm());
        } else {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/gameLight.css")).toExternalForm());
        }
        mode = !mode;
    }

    @FXML
    public void unlockTower1(MouseEvent event) {
        System.out.println("unlock tower 2");
        ((Node) event.getSource()).getScene().setCursor(Cursor.CLOSED_HAND);
        //engine.buyTower();
    }
    @FXML
    public void unlockTower2(){
        System.out.println("unlock tower 2");
        //engine.buyTower();
    }

    @FXML
    public void unlockTower3(){
        System.out.println("unlock tower 3");
        //engine.buyTower();
    }

    @FXML
    public void buyTower1(){

        System.out.println("buy tower 1");
        //((Node) event.getSource()).getScene().setCursor(Cursor.CLOSED_HAND);
        //engine.buyTower();
    }
    @FXML
    public void buyTower2(){
        System.out.println("buy tower 2");
        //((Node) event.getSource()).getScene().setCursor(Cursor.CLOSED_HAND);
        //engine.buyTower();
    }

    @FXML
    public void buyTower3(){
        System.out.println("buy tower 3");
        //((Node) event.getSource()).getScene().setCursor(Cursor.CLOSED_HAND);
        //engine.buyTower();
    }

    @FXML
    public void saveAndExit(ActionEvent event){
        // zapisuje postep i zamyka aplikajce (mozna tez podlaczyc x pod to)
        System.out.println("exit game (without save for now)");
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @Override
    public void mapChanged() {
        Platform.runLater(() -> {
            pane.getChildren().removeIf(o -> !Objects.equals(o.getId(), shop.getId()));
            for(Balloon balloon: map.getBalloons()){
                Circle circle = new Circle(5, Color.RED);
                circle.setCenterX(balloon.getPosition().x()*5);
                circle.setCenterY(balloon.getPosition().y()*5);

                pane.getChildren().add(circle);
            }
        });
    }

    @Override
    public void lifeLost() {
        Platform.runLater(() -> {
            lifeCountLabel.setText(Integer.toString(Integer.parseInt(lifeCountLabel.getText()) - 1));
        });
    }

}
