package agh.gui;


import agh.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameController implements IChangeObserver {
    @FXML
    private AnchorPane pane;
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label lifeCountLabel;
    private Map map;
    private Engine engine;

    //daloby sie uzyc initializable zamiast tego?
    public void initGame(Map map, Player player){
        playerNameLabel.setText(player.getName());
        lifeCountLabel.setText(Integer.toString(player.getLifeCount()));
        System.out.println(player.getName());
        this.map = map;
        engine = new Engine(player, map, 0);
        engine.addObserver(this);
        Thread engineThread = new Thread(engine);
        engineThread.start();

    }

    @FXML
    public void nextRound(ActionEvent event){
        engine.resume();
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
            pane.getChildren().clear();
            for(Balloon balloon: map.getBalloons()){
                Circle circle = new Circle(5, Color.RED);
                circle.setCenterX(balloon.getPosition().x()*5);
                circle.setCenterY(balloon.getPosition().y()*5);

                pane.getChildren().add(circle);
            }
        });
    }

}
