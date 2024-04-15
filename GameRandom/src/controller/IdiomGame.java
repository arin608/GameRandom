package controller;

import DTO.GameScore;
import DTO.User;
import View.ConsoleView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class IdiomGame implements Game {
    private GameScore gameScore;
    private ConsoleView consoleView;
    private Map<String, String> idiomMap; // 속담과 매핑된 뒷 부분을 위한 맵

    public IdiomGame(GameScore gameScore, ConsoleView consoleView) {
        this.gameScore = gameScore;
        this.consoleView = consoleView;
        this.idiomMap = new HashMap<>();
        initializeIdiomMap();
    }

    @Override
    public void play(String username) {
        consoleView.displayMessage("=== 사자성어 게임을 시작합니다 ===");

        String idiom = getRandomIdiom();
        String answer = idiomMap.get(idiom);

        String firstPart = idiom.substring(0, 2); // 앞의 2글자만 추출
        String hiddenPart = hideCharacters(answer.substring(2)); // 앞의 2글자를 제외한 부분을 숨김 처리
        
//        int spaceIndex = idiom.indexOf(' ');
//        String firstPart = spaceIndex != -1 ? idiom.substring(0, spaceIndex) : idiom;
//        String hiddenPart = hideCharacters(answer);

        consoleView.displayMessage("사자성어 앞 2글자 : " + firstPart);
        consoleView.displayMessage("이어질 부분을 맞춰보세요: " + hiddenPart);

        boolean correct = playGameAndGetResult(answer);
        updateScore(username, correct);
    }

    private void initializeIdiomMap() {
        // 속담 게임을 위한 속담과 뒷 부분 매핑 초기화
        idiomMap.put("일석이조", "이조");
        idiomMap.put("마이동풍", "동풍");
        idiomMap.put("십시일반", "일반");
        idiomMap.put("오비이락", "이락");
        idiomMap.put("시시비비", "비비");
    }

    private String getRandomIdiom() {
        // 매핑된 속담 중 랜덤으로 선택
        String[] idioms = idiomMap.keySet().toArray(new String[0]);
        int randomIndex = new Random().nextInt(idioms.length);
        return idioms[randomIndex];
    }

    private String hideCharacters(String text) {
        // 문자열을 숨김 처리하여 반환
        StringBuilder hidden = new StringBuilder();
        for (int i = 0; i < text.length() - 1; i++) {
            hidden.append("*");
        }
        return hidden.toString();
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
    	User user = gameScore.getUser(username);

        if (user != null) { // 사용자 정보가 null이 아닌 경우에만 점수를 업데이트
            int currentScore = user.getScore();

            if (correct) {
                gameScore.updateUser(username, currentScore + 300);
            } else {
                gameScore.updateUser(username, currentScore - 50);
            }
        } else {
            // 사용자 정보가 null인 경우에 대한 처리
            System.out.println("사용자 정보를 찾을 수 없습니다: " + username);
        }
    }
}