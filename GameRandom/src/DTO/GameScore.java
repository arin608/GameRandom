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
}
