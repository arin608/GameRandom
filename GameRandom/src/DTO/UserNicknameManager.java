package DTO;

import java.util.ArrayList;
import java.util.Scanner;
import controller.GameController;

public class UserNicknameManager {
    private ArrayList<String> nicknames;
    private Scanner scanner;
    private GameController gameController;

    public UserNicknameManager() {
        this.nicknames = new ArrayList<>();
        this.scanner = new Scanner(System.in);
//        this.gameController = new GameController();
    }

    public void start() {
        boolean running = true;

        while (running) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNicknameAndStartGame();
                    break;
                case "2":
                    modifyNickname();
                    break;
                case "3":
                    deleteNickname();
                    break;
                case "4":
                    displayAllNicknamesAndScores();
                    break;
                case "5":
                    System.out.println("프로그램을 종료합니다.");
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n1. 닉네임 추가 및 게임 실행");
        System.out.println("2. 닉네임 수정");
        System.out.println("3. 닉네임 삭제");
        System.out.println("4. 저장된 모든 닉네임과 게임 점수 보기");
        System.out.println("5. 종료");
        System.out.print("실행할 번호를 선택하세요: ");
    }

    private void addNicknameAndStartGame() {
        String nickname = promptUsername();
        nicknames.add(nickname);
//        gameController.startProgram(nickname);
    }

    private void modifyNickname() {
        if (nicknames.isEmpty()) {
            System.out.println("저장된 닉네임이 없습니다.");
            return;
        }

        displayAllNicknames();

        System.out.print("수정할 닉네임의 번호를 선택하세요: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        if (index > 0 && index <= nicknames.size()) {
            System.out.print("새로운 닉네임을 입력하세요: ");
            String newNickname = scanner.nextLine();
            nicknames.set(index - 1, newNickname);
            System.out.println("닉네임이 성공적으로 수정되었습니다.");
        } else {
            System.out.println("올바르지 않은 번호입니다.");
        }
    }

    private void deleteNickname() {
        if (nicknames.isEmpty()) {
            System.out.println("저장된 닉네임이 없습니다.");
            return;
        }

        displayAllNicknames();

        System.out.print("삭제할 닉네임의 번호를 선택하세요: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        if (index > 0 && index <= nicknames.size()) {
            nicknames.remove(index - 1);
            System.out.println("닉네임이 성공적으로 삭제되었습니다.");
        } else {
            System.out.println("올바르지 않은 번호입니다.");
        }
    }

    private void displayAllNicknames() {
        System.out.println("\n저장된 모든 닉네임:");
        for (int i = 0; i < nicknames.size(); i++) {
            System.out.println((i + 1) + ". " + nicknames.get(i));
        }
    }

    private void displayAllNicknamesAndScores() {
        displayAllNicknames();

        System.out.println("\n저장된 모든 닉네임과 게임 점수:");
        for (String nickname : nicknames) {
//            int score = gameController.getScore(nickname);
//            System.out.println(nickname + ": " + score + "점");
        }
    }

    private String promptUsername() {
        System.out.print("새로운 닉네임을 입력하세요: ");
        return scanner.nextLine();
    }
}
