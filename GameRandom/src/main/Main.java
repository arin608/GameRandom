package main;

import DTO.GameScore;
import View.ConsoleView;
import controller.GameController;

public class Main {

    public static void main(String[] args) {
        GameScore gameScore = new GameScore();
        ConsoleView consoleView = new ConsoleView();
        GameController gameController = new GameController(gameScore, consoleView);

        gameController.startProgram();
    }
}
