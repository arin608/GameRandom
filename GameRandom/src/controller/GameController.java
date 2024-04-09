package controller;

import DTO.GameScore;
import View.ConsoleView;

import java.util.Random;

public class GameController {
    private GameScore gameScore;
    private ConsoleView consoleView;
    private Random random;

    public GameController(GameScore gameScore, ConsoleView consoleView) {
        this.gameScore = gameScore;
        this.consoleView = consoleView;
        this.random = new Random();
    }

    public void startProgram() {
        boolean running = true;

        while (running) {
            int choice = consoleView.showMenuAndGetSelection();

            switch (choice) {
                case 1:
                    playGame("사자성어 맞추기");
                    break;
                case 2:
                    playGame("속담 맞추기");
                    break;
                case 3:
                    playGame("초성 게임");
                    break;
                case 4:
                    gameScore.displayAllUsers(consoleView);
                    break;
                case 5:
                    consoleView.displayMessage("프로그램을 종료합니다.");
                    running = false;
                    break;
                default:
                    consoleView.displayMessage("잘못된 선택입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }

    private void playGame(String gameName) {
        consoleView.displayMessage("=== " + gameName + "을 시작합니다. ===");

        Game game;
        switch (gameName) {
            case "사자성어 맞추기":
                game = new IdiomGame(gameScore, consoleView);
                break;
            case "속담 맞추기":
                game = new ProverbGame(gameScore, consoleView);
                break;
            case "초성 게임":
                game = new InitialGame(gameScore, consoleView);
                break;
            default:
                consoleView.displayMessage("알 수 없는 게임입니다.");
                return;
        }

        String username = consoleView.promptUsername();
        game.play(username);
    }
}