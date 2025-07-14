package org.calculator2;

import java.util.ArrayList;
import java.util.List; // List 인터페이스 임포트

public class Calculator {
    // 연산 결과를 저장하는 컬렉션 타입 필드 선언 및 생성
    // 외부에서 직접 접근하지 못하도록 private으로 선언하여 캡슐화를 적용합니다.
    private List<Double> results; // 나눗셈을 위해 Double 타입 사용

    // Calculator 클래스의 생성자
    public Calculator() {
        this.results = new ArrayList<>(); // ArrayList로 초기화
    }

    /**
     * 두 개의 양의 정수와 연산 기호를 받아 사칙연산을 수행하고 결과를 반환합니다.
     * 연산 결과는 내부 컬렉션 필드에 저장됩니다.
     *
     * @param num1 첫 번째 숫자 (양의 정수 또는 0)
     * @param num2 두 번째 숫자 (양의 정수 또는 0)
     * @param operator 사칙연산 기호 (+, -, *, /)
     * @return 연산 결과 (나눗셈의 경우 실수, 그 외는 정수)
     */
    public double calculate(int num1, int num2, char operator) {
        double result = 0; // 결과를 저장할 변수

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                // 나눗셈 연산 시 분모가 0인지 확인
                if (num2 == 0) {
                    System.out.println("오류: 나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                    // 0으로 나눌 경우 특별한 값(예: Double.NaN 또는 Double.POSITIVE_INFINITY)을 반환하거나,
                    // 예외를 던지는 것이 일반적이지만, 여기서는 오류 메시지 출력 후 0을 반환합니다.
                    return Double.NaN; // Not a Number 반환
                }
                result = (double) num1 / num2; // 실수 나눗셈 수행
                break;
            default:
                System.out.println("오류: 지원하지 않는 사칙연산 기호입니다. (+, -, *, / 중 하나를 입력해주세요)");
                return Double.NaN; // 유효하지 않은 연산자일 경우 Not a Number 반환
        }

        // 연산 결과를 컬렉션 필드에 저장
        results.add(result);
        return result;
    }

    /**
     * 연산 결과를 저장하고 있는 컬렉션 필드의 값을 반환하는 Getter 메서드입니다.
     *
     * @return 연산 결과 리스트
     */
    public List<Double> getResults() {
        return new ArrayList<>(this.results); // 외부에서 원본 리스트를 수정하지 못하도록 복사본 반환
    }

    /**
     * Calculator 클래스에 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 메서드입니다.
     */
    public void removeFirstResult() {
        if (!results.isEmpty()) {
            results.remove(0); // 첫 번째 요소 삭제
            System.out.println("가장 오래된 연산 결과가 삭제되었습니다.");
        } else {
            System.out.println("삭제할 연산 결과가 없습니다.");
        }
    }

    // /**
    //  *
    //  * 특정 인덱스를 삭제하는 메소드
    //  */
    // public void removeResultAtIndex(int indexToDelete) {
    //     if (indexToDelete >= 0 && indexToDelete < results.size()) {
    //         results.remove(indexToDelete);
    //     }
    // }

    // /**
    //  * 특정 값을 가진 첫 번째 연산 결과를 리스트에서 삭제합니다.
    //  *
    //  * @param valueToRemove 삭제할 값
    //  * @return 해당 값을 찾아 삭제했으면 true, 찾지 못했으면 false
    //  */
    // public boolean removeResultByValue(double valueToRemove) {
    //     return results.remove(valueToRemove);
    // }
    /**
     * Calculator 클래스에 저장된 모든 연산 결과를 삭제하는 메서드입니다.
     */
    public void clearResults() {
        results.clear();
        System.out.println("모든 연산 결과가 삭제되었습니다.");
    }
}
