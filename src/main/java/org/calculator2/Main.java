package org.calculator2;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Calculator 인스턴스 생성
        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);

        // 반복문 시작
        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요 (종료하려면 'exit' 입력): ");
            String input1 = sc.next();

            // 'exit' 입력 시 프로그램 종료
            if (input1.equalsIgnoreCase("exit")) {
                System.out.println("계산기를 종료합니다.");
                break;
            }

            int num1;
            int num2;
            char operator;

            try {
                num1 = Integer.parseInt(input1);

                System.out.print("두 번째 숫자를 입력하세요: ");
                num2 = sc.nextInt();

                System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
                operator = sc.next().charAt(0);

            } catch (NumberFormatException e) {
                System.out.println("오류: 잘못된 숫자 입력입니다. 다시 시도해주세요.");
                sc.nextLine(); // 잘못된 입력 버퍼 비우기
                continue;
            } catch (Exception e) {
                System.out.println("오류가 발생했습니다: " + e.getMessage());
                sc.nextLine(); // 잘못된 입력 버퍼 비우기
                continue;
            }

            // Calculator 클래스에 연산 수행 역할 위임
            double result = calculator.calculate(num1, num2, operator);

            // 유효한 결과인 경우에만 출력
            if (!Double.isNaN(result)) {
                // 정수형으로 표현 가능한 경우 정수로 출력, 아닌 경우 실수로 출력
                if (result == (long) result) {
                    System.out.println("결과: " + (long) result);
                } else {
                    System.out.println("결과: " + result);
                }
            }

            // 연산 결과를 저장하고 있는 컬렉션 필드에 간접 접근하여 가져오기 (Getter 활용)
            List<Double> allResults = calculator.getResults();
            System.out.println("현재까지의 연산 결과들: " + allResults);

            // 가장 오래된 연산 결과 삭제 기능 활용
            System.out.print("가장 오래된 연산 결과를 삭제하시겠습니까? (y/n): ");
            String removeChoice = sc.next();
            if (removeChoice.equalsIgnoreCase("y")) {
                calculator.removeFirstResult();
                System.out.println("삭제 후 현재까지의 연산 결과들: " + calculator.getResults());
            }

            System.out.println("\n더 계산하시겠습니까? (exit 입력 시 종료)");
            // 이 부분은 이미 위에서 'exit' 처리를 하고 있으므로 중복될 수 있습니다.
            // 사용자 경험을 위해 다음 입력 대기 전에 줄바꿈만 추가합니다.
        }
        // 반복문 종료 후 Scanner 닫기
        sc.close();
    }
}