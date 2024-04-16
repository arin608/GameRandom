package DTO;

import View.ConsoleView;

import java.util.HashMap;
import java.util.Map;

public class GameScore {
    private Map<String, User> users;

    public GameScore() {
        this.users = new HashMap<>();
    }

    public Map<String, User> getUsers() {
		return users;
	}

	public void addUser(String username) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, 0));
            System.out.println(username + "님의 정보가 추가되었습니다.");
        } else {
            System.out.println("이미 존재하는 닉네임입니다.");
        }
    }

    public void updateUser(String username, String newUsername) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            users.remove(username);
            user.setUsername(newUsername);
            users.put(newUsername, user);
            System.out.println(username + "님의 닉네임이 " + newUsername + "(으)로 수정되었습니다.");
        } else {
            System.out.println("해당 유저를 찾을 수 없습니다.");
        }
    }

    public void updateUserScore(String username, int points) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            user.increaseScore(points);
            System.out.println(username + "님의 점수가 " + points + "점 증가하였습니다. 현재 점수: " + user.getScore());
        } else {
            System.out.println("해당 유저를 찾을 수 없습니다.");
        }
    }

    public void removeUser(String username) {
        if (users.containsKey(username)) {
            users.remove(username);
            System.out.println(username + "님의 정보가 삭제되었습니다.");
        } else {
            System.out.println("해당 유저를 찾을 수 없습니다.");
        }
    }

    public void displayAllUsers(ConsoleView consoleView) {
        consoleView.displayMessage("=== 전체 유저 목록 ===");
        for (User user : users.values()) {
            consoleView.displayMessage(user.getUsername() + ": " + user.getScore() + "점");
        }
    }

    public boolean hasUser(String username) {
        return users.containsKey(username);
    }


}

