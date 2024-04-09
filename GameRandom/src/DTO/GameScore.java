package DTO;

import View.ConsoleView;

import java.util.HashMap;
import java.util.Map;

public class GameScore {
    private Map<String, User> users;

    public GameScore() {
        this.users = new HashMap<>();
    }

    public void addUser(String username) {
        users.put(username, new User(username, 0));
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void updateUser(String username, int newScore) {
        users.get(username).setScore(newScore);
    }

    public void displayAllUsers(ConsoleView consoleView) {
        consoleView.displayMessage("=== 전체 유저 점수 ===");
        for (User user : users.values()) {
            consoleView.displayMessage(user.getUsername() + ": " + user.getScore() + "점");
        }
    }
    
    public boolean hasUser(String username) {
        return users.containsKey(username);
    }

    public void removeUser(String username) {
        if (hasUser(username)) {
            users.remove(username);
            System.out.println(username + "님의 정보가 삭제되었습니다.");
        } else {
            System.out.println("해당 유저를 찾을 수 없습니다.");
        }
    }
}
