package controller;

import DTO.GameScore;
import DTO.User;
import View.ConsoleView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InitialGame implements Game {
    private GameScore gameScore;
    private ConsoleView consoleView;
    private Map<String, String> animalMap; // 동물 이름과 초성 매핑을 위한 맵

    public InitialGame(GameScore gameScore, ConsoleView consoleView) {
        this.gameScore = gameScore;
        this.consoleView = consoleView;
        this.animalMap = new HashMap<>();
        initializeAnimalMap();
    }

    @Override
    public void play(String username) {
        consoleView.displayMessage("=== 초성 게임을 시작합니다 ===");

        String animal = getRandomAnimal();
        String initial = animalMap.get(animal);

        consoleView.displayMessage("다음 동물의 초성은? " + initial);

        boolean correct = playGameAndGetResult(animal);
        updateScore(username, correct);
    }

    private void initializeAnimalMap() {
        // 초성 게임을 위한 동물 이름과 초성 매핑 초기화
        animalMap.put("사자", "ㅅㅈ");
        animalMap.put("토끼", "ㅌㄲ");
        animalMap.put("병아리", "ㅂㅇㄹ");
        animalMap.put("코끼리", "ㅋㄲㄹ");
        animalMap.put("고래", "ㄱㄹ");
        animalMap.put("코뿔소", "ㅋㅃㅅ");
    }

    private String getRandomAnimal() {
        // 매핑된 동물 중 랜덤으로 선택
        String[] animals = animalMap.keySet().toArray(new String[0]);
        int randomIndex = new Random().nextInt(animals.length);
        return animals[randomIndex];
    }

    private boolean playGameAndGetResult(String answer) {
        int attempts = 5;

        while (attempts > 0) {
            String userAnswer = consoleView.promptAnswer();

            if (userAnswer.equalsIgnoreCase(answer)) {
                consoleView.displayMessage("정답입니다! 300점 획득!");
                return true;
            } else {
                consoleView.displayMessage("틀렸습니다. 다시 시도하세요.");
                attempts--;
            }
        }

        consoleView.displayMessage("정답은 '" + answer + "' 입니다.");
        return false;
    }

    private void updateScore(String username, boolean correct) {
        Map<String, User> users = gameScore.getUsers();

        if (users.containsKey(username)) {
            User user = users.get(username);
            int currentScore = user.getScore();

            if (correct) {
                gameScore.updateUserScore(username, currentScore + 300);
                consoleView.displayMessage(username + "님의 점수가 300점 증가하였습니다. 현재 점수: " + (currentScore + 300));
            } else {
                gameScore.updateUserScore(username, currentScore - 50);
                consoleView.displayMessage(username + "님의 점수가 50점 감소하였습니다. 현재 점수: " + (currentScore - 50));
            }
        } else {
            consoleView.displayMessage("사용자 정보를 찾을 수 없습니다: " + username);
        }
    }
}
