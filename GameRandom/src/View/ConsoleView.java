package View;

import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public String promptUsername() {
        System.out.print("닉네임을 입력하세요: ");
        return scanner.nextLine(); // 입력 버퍼를 비우기 위해 nextLine() 호출
    }

    public int showMenuAndGetSelection() {
        System.out.println("=== 게임 선택 ===");
        System.out.println("1. 사자성어 맞추기");
        System.out.println("2. 속담 맞추기");
        System.out.println("3. 초성 게임");
        System.out.println("4. 종료");
        System.out.print("선택: ");
        return scanner.nextInt();
    }

    public String promptAnswer() {
        System.out.print("답을 입력하세요: ");
        return scanner.next();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}