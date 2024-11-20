package com.kmc.codingtest.lv2;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/64061
// board 는 2차원의 게임판 / board[i][j] = i행 j열의 인형번호
// moves 는 index번의 턴에 moves[index] 의 값에 해당하는 열에 크레인이 위치해있음을 나타냄.
// 단 moves[index] 의 값은 1~5 사이의 열을 나타내기 때문에 1-based 배열
public class DollGrabber {
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();
        int removedCount = 0; // 터진 인형의 개수

        for (int move : moves) {
            int column = move - 1; // moves 배열은 1-based, 배열 인덱스는 0-based

            for (int row = 0; row < board.length; row++) {
                if (board[row][column] != 0) { // 인형을 발견한 경우
                    int doll = board[row][column]; // 인형 가져오기
                    board[row][column] = 0; // 해당 위치를 빈칸으로 업데이트

                    if (!stack.isEmpty() && stack.peek() == doll) {
                        stack.pop(); // 같은 인형 제거
                        removedCount += 2; // 터진 인형의 개수 증가
                    } else {
                        stack.push(doll); // 바구니에 인형 추가
                    }
                    break; // 한 번 집었으면 다음 move로 이동
                }
            }
        }

        return removedCount; // 최종적으로 터진 인형의 개수를 반환
    }
}

/* 테스트 케이스
public static void main(String[] args) {
    DollGrabber dg = new DollGrabber();

    int[][] board = {
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 3},
        {0, 2, 5, 0, 1},
        {4, 2, 4, 4, 2},
        {3, 5, 1, 3, 1}
    };

    int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
    System.out.println(dg.solution(board, moves)); // 예상 출력: 4
}

 */