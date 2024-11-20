package com.kmc.codingtest.lv1;

import java.util.Stack;

/*
문제 설명
다음 규칙을 지키는 문자열을 올바른 괄호 문자열이라고 정의합니다.

(), [], {} 는 모두 올바른 괄호 문자열입니다.
만약 A가 올바른 괄호 문자열이라면, (A), [A], {A} 도 올바른 괄호 문자열입니다. 예를 들어, [] 가 올바른 괄호 문자열이므로, ([]) 도 올바른 괄호 문자열입니다.
만약 A, B가 올바른 괄호 문자열이라면, AB 도 올바른 괄호 문자열입니다. 예를 들어, {} 와 ([]) 가 올바른 괄호 문자열이므로, {}([]) 도 올바른 괄호 문자열입니다.
대괄호, 중괄호, 그리고 소괄호로 이루어진 문자열 s가 매개변수로 주어집니다.
이 s를 왼쪽으로 x (0 ≤ x < (s의 길이)) 칸만큼 회전시켰을 때 s가 올바른 괄호 문자열이 되게 하는 x의 개수를 return 하도록 solution 함수를 완성해주세요.

제한사항
s의 길이는 1 이상 1,000 이하입니다.
 */
public class RotateParentheses {
    public int solution(String s) {
        int count = 0;

        for (int i=0; i<s.length(); i++) {
            if (isValid(s))
                count++;

            s = rotateLeft(s);
        }

        return count;
    }

    private boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<str.length(); i++) {
            switch (str.charAt(i)) {
                case '(' :
                case '[' :
                case '{' :
                    stack.push(str.charAt(i));
                    break;

                case ')' :
                    if (stack.isEmpty() || '(' != stack.pop())
                        return false;
                    break;

                case '}' :
                    if (stack.isEmpty() || '{' != stack.pop())
                        return false;
                    break;

                case ']' :
                    if (stack.isEmpty() || '[' != stack.pop())
                        return false;
                    break;
            }
        }

        return stack.isEmpty();
    }

    private String rotateLeft(String str) {
        return str.substring(1) + str.charAt(0);
    }
}

/* 테스트 케이스
public static void main(String[] args) {
        RotateParentheses rp = new RotateParentheses();

        // 테스트 케이스 1
        String s1 = "[](){}";
        System.out.println("Test 1: " + rp.solution(s1)); // 예상 출력: 3

        // 테스트 케이스 2
        String s2 = "}]()[{";
        System.out.println("Test 2: " + rp.solution(s2)); // 예상 출력: 2

        // 테스트 케이스 3
        String s3 = "[)(]";
        System.out.println("Test 3: " + rp.solution(s3)); // 예상 출력: 0

        // 테스트 케이스 4
        String s4 = "}}}";
        System.out.println("Test 4: " + rp.solution(s4)); // 예상 출력: 0

        // 테스트 케이스 5
        String s5 = "({[]})";
        System.out.println("Test 5: " + rp.solution(s5)); // 예상 출력: 6

        // 테스트 케이스 6
        String s6 = "{[()]}";
        System.out.println("Test 6: " + rp.solution(s6)); // 예상 출력: 6

        // 테스트 케이스 7
        String s7 = "()";
        System.out.println("Test 7: " + rp.solution(s7)); // 예상 출력: 2

        // 테스트 케이스 8
        String s8 = "";
        System.out.println("Test 8: " + rp.solution(s8)); // 예상 출력: 0 (빈 문자열)

        // 테스트 케이스 9
        String s9 = "{[(])}";
        System.out.println("Test 9: " + rp.solution(s9)); // 예상 출력: 0
    }
 */