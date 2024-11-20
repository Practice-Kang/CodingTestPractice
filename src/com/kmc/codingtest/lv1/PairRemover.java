package com.kmc.codingtest.lv1;

import java.util.Stack;

/*
문제 설명
짝지어 제거하기는, 알파벳 소문자로 이루어진 문자열을 가지고 시작합니다. 먼저 문자열에서 같은 알파벳이 2개 붙어 있는 짝을 찾습니다.
그다음, 그 둘을 제거한 뒤, 앞뒤로 문자열을 이어 붙입니다. 이 과정을 반복해서 문자열을 모두 제거한다면 짝지어 제거하기가 종료됩니다.
문자열 S가 주어졌을 때, 짝지어 제거하기를 성공적으로 수행할 수 있는지 반환하는 함수를 완성해 주세요. 성공적으로 수행할 수 있으면 1을, 아닐 경우 0을 리턴해주면 됩니다.

예를 들어, 문자열 S = baabaa 라면

b aa baa → bb aa → aa →

의 순서로 문자열을 모두 제거할 수 있으므로 1을 반환합니다.

제한사항
문자열의 길이 : 1,000,000이하의 자연수
문자열은 모두 소문자로 이루어져 있습니다.

 */
public class PairRemover {
    public int solution(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<str.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == str.charAt(i))
                stack.pop();
            else
                stack.push(str.charAt(i));
        }

        return stack.isEmpty() ? 1 : 0;
    }
}

/* 테스트 케이스
public static void main(String[] args) {
        PairRemover remover = new PairRemover();

        // 테스트 케이스 1: 짝지어 제거 가능
        System.out.println(remover.solution("baabaa")); // 예상 출력: 1

        // 테스트 케이스 2: 짝지어 제거 불가능
        System.out.println(remover.solution("cdcd")); // 예상 출력: 0

        // 테스트 케이스 3: 모든 문자가 제거되는 경우
        System.out.println(remover.solution("aabbcc")); // 예상 출력: 1

        // 테스트 케이스 4: 한 문자만 있는 경우
        System.out.println(remover.solution("a")); // 예상 출력: 0

        // 테스트 케이스 5: 빈 문자열
        System.out.println(remover.solution("")); // 예상 출력: 1

        // 테스트 케이스 6: 복잡한 문자열
        System.out.println(remover.solution("abccba")); // 예상 출력: 1

        // 테스트 케이스 7: 연속되지 않는 짝
        System.out.println(remover.solution("abcabc")); // 예상 출력: 0

        // 테스트 케이스 8: 긴 문자열
        System.out.println(remover.solution("a".repeat(500000) + "b".repeat(500000))); // 예상 출력: 0

        // 테스트 케이스 9: 혼합된 짝
        System.out.println(remover.solution("abccbaabccba")); // 예상 출력: 1
    }
 */