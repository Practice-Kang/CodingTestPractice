package com.kmc.codingtest.lv2;

import java.util.HashSet;
import java.util.Set;

public class UniquePath {
    public int solution(String dirs) {
        // 현재 위치
        int x = 0, y = 0;
        // 방문한 길을 저장
        Set<String> visited = new HashSet<>();

        // 이동 방향 정의
        int[][] directions = {
                {0, 1},  // U
                {0, -1}, // D
                {1, 0},  // R
                {-1, 0}  // L
        };

        // 각 명령어에 대한 매핑
        String command = "UDRL";

        // 명령어 처리
        for (char dir : dirs.toCharArray()) {
            int index = command.indexOf(dir);
            int nx = x + directions[index][0];
            int ny = y + directions[index][1];

            // 경계 체크
            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                continue; // 경계를 벗어나면 무시
            }

            // 현재 길과 반대 방향 길 기록
            String path1 = x + "," + y + "->" + nx + "," + ny;
            String path2 = nx + "," + ny + "->" + x + "," + y;

            // 방문 처리
            visited.add(path1);
            visited.add(path2);

            // 현재 위치 업데이트
            x = nx;
            y = ny;
        }

        // 방문한 길의 개수 반환
        return visited.size() / 2;
    }

    /**
     * 실행 테스트 케이스
     * public static void main(String[] args) {
     *         UniquePath solution = new UniquePath();
     *
     *         System.out.println(solution.solution("ULURRDLLU"));
     *         System.out.println(solution.solution("LULLLLLLU"));
     *     }
     */
}
