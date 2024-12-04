package com.kmc.codingtest.lv2;

import java.util.ArrayList;
import java.util.List;

/*
프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.

또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.

제한 사항
작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
작업 진도는 100 미만의 자연수입니다.
작업 속도는 100 이하의 자연수입니다.
배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
 */
public class IntegrationDeploy {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] surplusDays = new int[progresses.length];
        List<Integer> answerList = new ArrayList<>();

        // 1. 남은 작업일 계산
        for (int i = 0; i < progresses.length; i++) {
            surplusDays[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }

        // 2. 배포 그룹 계산
        int deployDay = surplusDays[0]; // 첫 번째 작업의 남은 작업일로 초기화
        int deployCount = 1; // 첫 배포에 포함된 작업 수

        for (int i = 1; i < surplusDays.length; i++) {
            if (surplusDays[i] <= deployDay) {
                // 현재 작업이 현재 배포 그룹에 포함
                deployCount++;
            } else {
                // 새로운 배포 그룹 시작
                answerList.add(deployCount); // 이전 배포 그룹 저장
                deployDay = surplusDays[i]; // 새로운 기준일 설정
                deployCount = 1; // 새로운 그룹의 첫 작업
            }
        }
        // 마지막 배포 그룹 추가
        answerList.add(deployCount);

        // 3. 결과를 배열로 변환
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}

/* 테스트 케이스
public static void main(String[] args) {
    Solution sol = new Solution();
    int[] progresses = {93, 30, 55};
    int[] speeds = {1, 30, 5};
    int[] result = sol.solution(progresses, speeds);
    System.out.println(Arrays.toString(result)); // 예상 출력: [7, 3, 9]
}

 */