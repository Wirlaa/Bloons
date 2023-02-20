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
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
    private Label roundNumberUI;
    @FXML
    private Label lifeCountLabel;
    @FXML
    private Label moneyNumberUI;
    @FXML
    private ImageView towerShop1;
    @FXML
    private ImageView towerShop2;
    @FXML
    private ImageView towerShop3;
    @FXML
    private HBox shop;
    private Map map;
    private Engine engine;
    private boolean mode;
    private TowerType towerTypeToBuy = null;

    //daloby sie uzyc initializable zamiast tego?
    public void initGame(Map map, Player player, boolean mode, Image image){
        lifeCountLabel.setText(Integer.toString(player.getLifeCount()));
        playerNameLabel.setText(player.getName());
        moneyNumberUI.setText(Integer.toString(player.getMoney()));
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
        towerShop1.setOpacity(0.5);
        towerShop2.setOpacity(0.5);
        towerShop3.setOpacity(0.5);

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
    public void unlockTower1() {
        System.out.println("unlock tower 1");
        if(engine.unlockTower(TowerType.BASIC)){
            towerShop1.setOpacity(1);
        }
    }
    @FXML
    public void unlockTower2(){
        System.out.println("unlock tower 2");
        if(engine.unlockTower(TowerType.MEDIUM)){
            towerShop2.setOpacity(1);
        }
    }

    @FXML
    public void unlockTower3(){
        if(engine.unlockTower(TowerType.ADVANCED)){
            towerShop3.setOpacity(1);
        }
    }
    @FXML
    public void buyTower1(MouseEvent event){
        //System.out.println("buy tower 1");
        if(engine.isUnlocked(TowerType.BASIC)) {
            towerTypeToBuy = TowerType.BASIC;
            ((Node) event.getSource()).getScene().setCursor(Cursor.CLOSED_HAND);
        }
    }
    @FXML
    public void buyTower2(MouseEvent event){
        System.out.println("buy tower 2");
        if(engine.isUnlocked(TowerType.MEDIUM)) {
            System.out.println("here");
            towerTypeToBuy = TowerType.MEDIUM;
            ((Node) event.getSource()).getScene().setCursor(Cursor.CLOSED_HAND);
        }
    }

    @FXML
    public void buyTower3(MouseEvent event){
        if(engine.isUnlocked(TowerType.ADVANCED)) {
            towerTypeToBuy = TowerType.ADVANCED;
            ((Node) event.getSource()).getScene().setCursor(Cursor.CLOSED_HAND);
        }
    }

    @FXML
    public void placeTower(MouseEvent event){
        System.out.println("tower placed");
        System.out.println(towerTypeToBuy);
        if(towerTypeToBuy != null) {
            Tower tower = new Tower(towerTypeToBuy, new Point(event.getX()/5, event.getY()/5));
            if (engine.buyTower(tower)) {
                map.placeTower(tower);
                Color color = switch(tower.getType()) {
                    case BASIC -> Color.BROWN;
                    case MEDIUM -> Color.ORANGE;
                    case ADVANCED -> Color.RED;
                };
                Platform.runLater(() -> {
                    Rectangle square = new Rectangle(20, 20, color);
                    square.setRotate(45);
                    square.setX(tower.getPosition().x()*5);
                    square.setY(tower.getPosition().y()*5);
                    pane.getChildren().add(square);
                });
            }
            ((Node) event.getSource()).getScene().setCursor(Cursor.DEFAULT);
            towerTypeToBuy = null;
        }

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
            pane.getChildren().removeIf(o -> !Objects.equals(o.getId(), shop.getId()) && o.getRotate() == 0);
            for(Balloon balloon: map.getBalloons()){
                Color color = switch(balloon.getColor()) {
                    case RED -> Color.RED;
                    case BLUE -> Color.BLUE;
                    case GREEN -> Color.GREEN;
                };
                Circle circle = new Circle(10, color);
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

    @Override
    public void newRound() {
        Platform.runLater(() -> {
            roundNumberUI.setText(Integer.toString(Integer.parseInt(roundNumberUI.getText()) + 1));
        });
    }

    @Override
    public void moneyChanged(int money) {
        Platform.runLater(() -> {
            moneyNumberUI.setText(Integer.toString(money));
        });
    }

}
