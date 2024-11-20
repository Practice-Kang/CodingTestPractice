package com.kmc.codingtest.lv1;

/* https://school.programmers.co.kr/learn/courses/30/lessons/340213
문제 설명
당신은 동영상 재생기를 만들고 있습니다. 당신의 동영상 재생기는 10초 전으로 이동, 10초 후로 이동, 오프닝 건너뛰기 3가지 기능을 지원합니다. 각 기능이 수행하는 작업은 다음과 같습니다.

10초 전으로 이동: 사용자가 "prev" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 전으로 이동합니다. 현재 위치가 10초 미만인 경우 영상의 처음 위치로 이동합니다.
영상의 처음 위치는 0분 0초입니다.
10초 후로 이동: 사용자가 "next" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 후로 이동합니다. 동영상의 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동합니다.
영상의 마지막 위치는 동영상의 길이와 같습니다.
오프닝 건너뛰기: 현재 재생 위치가 오프닝 구간(op_start ≤ 현재 재생 위치 ≤ op_end)인 경우 자동으로 오프닝이 끝나는 위치로 이동합니다.
동영상의 길이를 나타내는 문자열 video_len, 기능이 수행되기 직전의 재생위치를 나타내는 문자열 pos, 오프닝 시작 시각을 나타내는 문자열 op_start,
오프닝이 끝나는 시각을 나타내는 문자열 op_end, 사용자의 입력을 나타내는 1차원 문자열 배열 commands가 매개변수로 주어집니다.
이때 사용자의 입력이 모두 끝난 후 동영상의 위치를 "mm:ss" 형식으로 return 하도록 solution 함수를 완성해 주세요.

제한사항
video_len의 길이 = pos의 길이 = op_start의 길이 = op_end의 길이 = 5
video_len, pos, op_start, op_end는 "mm:ss" 형식으로 mm분 ss초를 나타냅니다.
0 ≤ mm ≤ 59
0 ≤ ss ≤ 59
분, 초가 한 자리일 경우 0을 붙여 두 자리로 나타냅니다.
비디오의 현재 위치 혹은 오프닝이 끝나는 시각이 동영상의 범위 밖인 경우는 주어지지 않습니다.
오프닝이 시작하는 시각은 항상 오프닝이 끝나는 시각보다 전입니다.
1 ≤ commands의 길이 ≤ 100
commands의 원소는 "prev" 혹은 "next"입니다.
"prev"는 10초 전으로 이동하는 명령입니다.
"next"는 10초 후로 이동하는 명령입니다.
 */

// video_len = 동영상 길이
// pos = 기능 수행직전 재생위치
// op_start = 오프닝 영상 시작 시각
// op_end = 오프닝 영상 종료 시각
// commands = 사용자의 입력 배열

/*
입출력 예 #2
시작 위치 0분 5초에서 10초 전으로 이동합니다. 현재 위치가 10초 미만이기 때문에 0분 0초로 이동합니다.
0분 0초에서 10초 후로 이동하면 0분 10초입니다.
0분 10초에서 10초 후로 이동하면 0분 20초입니다. 0분 20초는 오프닝 구간이기 때문에 오프닝이 끝나는 위치인 6분 55초로 이동합니다. 따라서 "06:55"를 return 하면 됩니다.

입출력 예 #3
시작 위치 4분 5초는 오프닝 구간이기 때문에 오프닝이 끝나는 위치인 4분 7초로 이동합니다. 4분 7초에서 10초 후로 이동하면 4분 17초입니다. 따라서 "04:17"을 return 하면 됩니다.
 */
public class VideoPlayer {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 파라미터를 정수로 변환
        int posToInt = timeToSecond(pos);
        int videoLenToInt = timeToSecond(video_len);
        int location = posToInt;
        int opStartToInt = timeToSecond(op_start);
        int opEndToInt = timeToSecond(op_end);

        if (opStartToInt < location && location < opEndToInt)
            location = opEndToInt;

        for (String command : commands) {
            switch (command) {
                case "prev":
                    location = Math.max(0, location - 10);
                    break;
                case "next":
                    location = Math.min(videoLenToInt, location + 10);
                    break;
            }

            if (opStartToInt <= location && location <= opEndToInt) {
                location = opEndToInt;
            }
        }

        return secondToTime(location);
    }

    private int timeToSecond(String mmss) {
        String[] splitTime = mmss.split(":");
        int minute = Integer.parseInt(splitTime[0]);
        int second = Integer.parseInt(splitTime[1]);

        return minute * 60 + second;
    }

    private String secondToTime(int inputSecond) {
        int minutes = inputSecond / 60;
        int seconds = inputSecond % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}

/* 테스트 케이스
public static void main(String[] args) {
    VideoPlayer vp = new VideoPlayer();

    // 테스트케이스 1: 기본 테스트
    System.out.println("Test 1: " + vp.solution("34:33", "13:00", "00:55", "02:55", new String[]{"next", "prev"})); 
    // 예상 출력: "13:00"

    // 테스트케이스 2: 오프닝 구간 강제 이동
    System.out.println("Test 2: " + vp.solution("10:55", "00:05", "00:15", "06:55", new String[]{"prev", "next", "next"})); 
    // 예상 출력: "06:55"

    // 테스트케이스 3: 오프닝 종료 직후 next
    System.out.println("Test 3: " + vp.solution("07:22", "04:05", "00:15", "04:07", new String[]{"next"})); 
    // 예상 출력: "04:17"

    // 테스트케이스 4: 동영상 끝부분 테스트
    System.out.println("Test 4: " + vp.solution("02:30", "02:20", "00:10", "00:20", new String[]{"next"})); 
    // 예상 출력: "02:30"

    // 테스트케이스 5: 동영상 시작 부분 테스트
    System.out.println("Test 5: " + vp.solution("05:00", "00:05", "01:00", "02:00", new String[]{"prev"})); 
    // 예상 출력: "00:00"

    // 테스트케이스 6: 여러 명령 실행
    System.out.println("Test 6: " + vp.solution("15:00", "05:00", "03:00", "04:00", new String[]{"next", "next", "prev", "prev"})); 
    // 예상 출력: "05:00"

    // 테스트케이스 7: 복잡한 오프닝 처리
    System.out.println("Test 7: " + vp.solution("20:00", "02:50", "02:30", "03:00", new String[]{"prev", "next", "next", "prev"})); 
    // 예상 출력: "03:10"

    // 테스트케이스 8: 긴 동영상
    System.out.println("Test 8: " + vp.solution("120:00", "100:00", "10:00", "15:00", new String[]{"prev", "prev", "prev"})); 
    // 예상 출력: "99:30"

    // 테스트케이스 9: 명령 없이 초기값 유지
    System.out.println("Test 9: " + vp.solution("30:00", "10:00", "05:00", "06:00", new String[]{})); 
    // 예상 출력: "10:00"

    // 테스트케이스 10: 오프닝 구간에서 시작
    System.out.println("Test 10: " + vp.solution("10:00", "01:45", "01:30", "03:00", new String[]{"next"})); 
    // 예상 출력: "03:10"
}

 */