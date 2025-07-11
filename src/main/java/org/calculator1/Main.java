package org.calculator1;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 무한 루프를 사용하여 "exit"이 입력될 때까지 계산을 반복합니다.
        while (true) {
            System.out.println("계산기입니다. 'exit'을 입력하면 종료됩니다.");
            System.out.print("첫 번째 숫자를 입력하세요 (종료하려면 'exit' 입력): ");
            String input = scanner.nextLine().trim();

            // "exit"이 입력되면 프로그램 종료
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("계산기를 종료합니다.");
                return;
            }

            int firstNum;
            int secondNum;
            char operation;

            try {
                // 첫 번째 입력으로 받은 문자열을 정수로 변환 시도
                firstNum = Integer.parseInt(input);

                // 두 번째 숫자 입력 받기
                System.out.print("두 번째 숫자를 입력하세요: ");
                secondNum = Integer.parseInt(scanner.nextLine().trim());

                // 사칙연산 기호 입력 받기
                System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
                String opInput = scanner.nextLine().trim();

                // 연산 기호가 한 글자인지 확인
                if (opInput.length() != 1) {
                    System.out.println("오류: 올바른 사칙연산 기호(+, -, *, /)를 입력해주세요.");
                    continue; // 다시 반복 시작
                }
                operation = opInput.charAt(0); // 첫 번째 문자를 연산 기호로 사용

            } catch (NumberFormatException e) {
                // 숫자가 아닌 입력을 받았을 때 처리
                System.out.println("오류: 잘못된 숫자 입력입니다. 다시 시도해주세요.");
                continue; // 다시 반복 시작
            }

            double result = 0; // 결과를 저장할 변수 (나눗셈을 위해 double 타입 사용)

            // switch 문을 사용하여 연산 기호에 따른 계산 수행
            switch (operation) {
                case '+':
                    result = firstNum + secondNum;
                    System.out.println("결과: " + (int) result); // 정수 결과 출력
                    break;
                case '-':
                    result = firstNum - secondNum;
                    System.out.println("결과: " + (int) result); // 정수 결과 출력
                    break;
                case '*':
                    result = firstNum * secondNum;
                    System.out.println("결과: " + (int) result); // 정수 결과 출력
                    break;
                case '/':
                    // 나눗셈 연산 시 분모가 0인지 확인
                    if (secondNum == 0) {
                        System.out.println("오류: 나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                    } else {
                        result = (double) firstNum / secondNum; // 실수 나눗셈 수행
                        System.out.println("결과: " + result); // 실수 결과 출력
                    }
                    break;
                default:
                    // 올바르지 않은 연산 기호가 입력된 경우
                    System.out.println("오류: 지원하지 않는 사칙연산 기호입니다. (+, -, *, / 중 하나를 입력해주세요)");
                    break;
            }
            System.out.println(); // 다음 계산을 위해 줄 바꿈
        }
    }
}