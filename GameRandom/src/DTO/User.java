package DTO;

public class User {
    private String username;
    private int score;

    public User(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    // 점수를 증가시키는 메서드
    public void increaseScore(int points) {
        this.score += points;
    }

    // 점수를 감소시키는 메서드
    public void decreaseScore(int points) {
        this.score -= points;
        if (this.score < 0) {
            this.score = 0; // 음수로 내려가지 않도록 보정
        }
    }
}
