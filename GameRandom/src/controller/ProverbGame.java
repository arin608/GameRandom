package controller;

import DTO.GameScore;
import DTO.User;
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

        boolean correct = playGameAndGetResult(username, answer); // username을 전달하여 호출
        updateScore(username, correct);
    }

    private void initializeProverbMap() {
        // 사자성어 게임을 위한 사자성어와 뒷 부분 매핑 초기화
        proverbMap.put("호랑이 담 넘어가듯 한다", "담 넘어가듯 한다");
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

    private boolean playGameAndGetResult(String username, String answer) {
        int attempts = 5;

        while (attempts > 0) {
            String userAnswer = consoleView.promptAnswer();
            // 사용자의 답변과 정답의 뒷부분을 대소문자 구분 없이 비교
            String actualAnswer = answer.substring(answer.lastIndexOf(' ') + 1); // 정답의 뒷부분만 추출

            if (userAnswer.equalsIgnoreCase(answer)) {
                consoleView.displayMessage("정답입니다! 300점 획득!");
                updateScore(username, true); // 정답인 경우 점수 업데이트
                return true;
            } else {
                consoleView.displayMessage("틀렸습니다. 다시 시도하세요. 남은 횟수: " + (attempts - 1));
                attempts--;
            }
        }

        consoleView.displayMessage("시도 횟수를 모두 소진했습니다. 정답은 '" + answer + "'입니다.");
        updateScore(username, false); // 정답을 못 맞춘 경우 점수 업데이트
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