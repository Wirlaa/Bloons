package agh;

import agh.gui.App;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        try {
            Application.launch(App.class, args);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }

    }
}