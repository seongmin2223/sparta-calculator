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
                sc.nextLine(); // nextInt() 후 남은 개행 문자 소비

                System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
                operator = sc.next().charAt(0);
                sc.nextLine(); // next().charAt(0) 후 남은 개행 문자 소비

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
            System.out.print("가장 오래된 연산 결과를 삭제하시겠습니까?" +
                    " all 을 입력하면 전체삭제됩니다." +
                    " (y/n/all): ");

            String removeChoice = sc.next();

            if (removeChoice.equalsIgnoreCase("y")) {
                calculator.removeFirstResult();
                System.out.println("삭제 후 현재까지의 연산 결과들: " + calculator.getResults());
            } else if(removeChoice.equalsIgnoreCase("all")) {
                calculator.clearResults();
            }

//            // --- 특정 요소 삭제 기능 추가 ---
//            if (!allResults.isEmpty()) {
//                System.out.println("\n--- 연산 결과 삭제 ---");
//                System.out.println("현재 연산 결과 목록:");
//                for (int i = 0; i < allResults.size(); i++) {
//                    System.out.println((i + 1) + ". " + allResults.get(i)); // 사용자에게 1부터 시작하는 번호로 보여줌
//                }
//
//                System.out.print("삭제할 항목을 선택하세요 (번호 입력, 'v [값]'로 값 삭제, 'y'로 첫 번째, 'last'로 마지막, 'all'로 전체, 'n'으로 취소): ");
//                String removeChoice = sc.nextLine().trim(); // 한 줄 전체를 읽어서 "v 10.5" 같은 입력 처리
//
//                if (removeChoice.equalsIgnoreCase("n")) {
//                    System.out.println("삭제를 취소합니다.");
//                } else if (removeChoice.equalsIgnoreCase("y")) {
//                    calculator.removeFirstResult();
//                    System.out.println("삭제 후 현재까지의 연산 결과들: " + calculator.getResults());
//                } else if (removeChoice.equalsIgnoreCase("last")) {
//                    if (!allResults.isEmpty()) {
//                        calculator.removeResultAtIndex(allResults.size() - 1);
//                        System.out.println("가장 최근의 연산 결과가 삭제되었습니다.");
//                    } else {
//                        System.out.println("삭제할 연산 결과가 없습니다.");
//                    }
//                } else if (removeChoice.equalsIgnoreCase("all")) {
//                    calculator.clearResults();
//                    System.out.println("모든 연산 결과가 삭제되었습니다.");
//                } else if (removeChoice.toLowerCase().startsWith("v ")) { // 'v '로 시작하면 값으로 삭제
//                    try {
//                        String valueString = removeChoice.substring(2).trim(); // 'v ' 뒤의 문자열 추출
//                        double valueToDelete = Double.parseDouble(valueString); // 숫자로 변환
//
//                        if (calculator.removeResultByValue(valueToDelete)) {
//                            System.out.println("값 '" + valueToDelete + "'을(를) 가진 연산 결과가 삭제되었습니다.");
//                        } else {
//                            System.out.println("오류: 값 '" + valueToDelete + "'을(를) 가진 연산 결과를 찾을 수 없습니다.");
//                        }
//                    } catch (NumberFormatException e) {
//                        System.out.println("오류: 'v [값]' 형식으로 올바른 숫자를 입력해주세요.");
//                    }
//                } else { // 그 외의 경우, 번호(인덱스)로 삭제 시도
//                    try {
//                        int indexToDelete = Integer.parseInt(removeChoice) - 1; // 사용자 입력은 1부터 시작하므로 1 빼기
//
//                        if (indexToDelete >= 0 && indexToDelete < allResults.size()) {
//                            calculator.removeResultAtIndex(indexToDelete);
//                            System.out.println("선택된 연산 결과가 삭제되었습니다.");
//                        } else {
//                            System.out.println("오류: 유효하지 않은 번호입니다. 다시 시도해주세요.");
//                        }
//                    } catch (NumberFormatException e) {
//                        System.out.println("오류: 잘못된 입력입니다. 번호, 'v [값]', 'y', 'last', 'all', 또는 'n'을 입력해주세요.");
//                    }
//                }
//            } else {
//                System.out.println("\n삭제할 연산 결과가 없습니다.");
//            }
//            // --- 특정 요소 삭제 기능 추가 끝 ---

            System.out.println("\n더 계산하시겠습니까? (exit 입력 시 종료)");
            // 이 부분은 이미 위에서 'exit' 처리를 하고 있으므로 중복될 수 있습니다.
            // 사용자 경험을 위해 다음 입력 대기 전에 줄바꿈만 추가합니다.
        }
        // 반복문 종료 후 Scanner 닫기
        sc.close();
    }
}
