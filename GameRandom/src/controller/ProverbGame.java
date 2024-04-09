package controller;

import DTO.GameScore;
import View.ConsoleView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ProverbGame implements Game {
    private GameScore gameScore;
    private ConsoleView consoleView;
    private Map<String, String> proverbMap; // 사자성어와 매핑된 뒷 부분을 위한 맵

    public ProverbGame(GameScore gameScore, ConsoleView consoleView) {
        this.gameScore = gameScore;
        this.consoleView = consoleView;
        this.proverbMap = new HashMap<>();
        initializeProverbMap();
    }

    @Override
    public void play(String username) {
        consoleView.displayMessage("=== 속담 맞추기 게임을 시작합니다 ===");

        String proverb = getRandomProverb();
        String answer = proverbMap.get(proverb);

        String firstPart = proverb.substring(0, proverb.indexOf(' '));
        String hiddenPart = hideCharacters(answer);

        consoleView.displayMessage("다음 속담의 앞 부분은? " + firstPart);
        consoleView.displayMessage("속담의 뒷 부분을 맞춰보세요: " + hiddenPart);

        boolean correct = playGameAndGetResult(answer);
        updateScore(username, correct);
    }

    private void initializeProverbMap() {
        // 사자성어 게임을 위한 사자성어와 뒷 부분 매핑 초기화
        proverbMap.put("호랑이 담 넘어가듯 한다", "넘어가듯 한다");
        proverbMap.put("백지장도 맞들면 낫다", "맞들면 낫다");
        proverbMap.put("배보다 배꼽이 더 크다", "배꼽이 더 크다");
        proverbMap.put("남의 재주 따라하지 말고 자기 재주를 내라", "자기 재주를 내라");
        proverbMap.put("물에 빠지면 지푸라기라도 잡는다", "지푸라기라도 잡는다");
    }

    private String getRandomProverb() {
        // 매핑된 사자성어 중 랜덤으로 선택
        String[] proverbs = proverbMap.keySet().toArray(new String[0]);
        int randomIndex = new Random().nextInt(proverbs.length);
        return proverbs[randomIndex];
    }

    private String hideCharacters(String text) {
        // 문자열을 숨김 처리하여 반환 (마지막 글자만 공개)
        StringBuilder hidden = new StringBuilder();
        for (int i = 0; i < text.length() - 1; i++) {
            hidden.append("*");
        }
        hidden.append(text.charAt(text.length() - 1)); // 마지막 글자 공개
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
        int currentScore = gameScore.getUser(username).getScore();
        if (correct) {
            gameScore.updateUser(username, currentScore + 300);
        } else {
            gameScore.updateUser(username, currentScore - 50);
        }
    }
}