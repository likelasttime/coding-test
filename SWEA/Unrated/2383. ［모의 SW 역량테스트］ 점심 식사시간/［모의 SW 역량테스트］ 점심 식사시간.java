import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Solution {
    final static int MAX_STAIR_CNT = 2;
    final static int MAX_TIME = 11 * 11 + 11 * 2;
    final static int MAX_N = 11;

    static int[][] room;
    static int[] person;        // 사람별로 이동한 계단 저장
    static List<Position> personPosition;
    static List<Position> stairs;
    static int answer;
    static int peopleCnt;       // 총 사람 수

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int getDistance(Position stair, Position people) {
        return Math.abs(stair.x - people.x) + Math.abs(stair.y - people.y);
    }

    /*
        계단을 내려가는 데 걸리는 시간을 반환
     */
    public static int getStairTime(int stairIdx) {
        Position position = stairs.get(stairIdx);
        return room[position.x][position.y];
    }

    public static void upAndDownStair() {
        int totalMinTime = 0;       // 모든 사람이 계단을 다 내려간 후 최소 시간
        for(int stairIdx=0; stairIdx<2; stairIdx++) {
            int[] waitingStair = new int[MAX_N * 2];      // 계단 앞에 대기하는 사람
            int[] currentStair = new int[MAX_TIME];       // 계단 위에 있는 사람
            for(int personIdx=0; personIdx<peopleCnt; personIdx++) {     // 사람들을 계단 위에 올리기
                if(person[personIdx] == stairIdx) {
                    waitingStair[getDistance(stairs.get(stairIdx), personPosition.get(personIdx)) + 1]++;
                }
            }
            int curMinTime = 0;
            for(int time=1; time<=20; time++) {
                while(waitingStair[time] > 0) {     // 계단 위에 사람이 있을 때
                    waitingStair[time]--;
                    int remainTime = getStairTime(stairIdx);       // 현재 계단을 내려가는 데 걸리는 시간
                    for(int t=time; t<MAX_TIME; t++) {
                        if (currentStair[t] < 3) {      // 계단 위에 사람이 아직 꽉 차지 않았다면
                            currentStair[t]++;
                            remainTime--;
                            if(remainTime == 0) {       // 계단을 내려왔다면
                                curMinTime = Math.max(curMinTime, t + 1);
                                break;
                            }
                        }
                    }
                }
            }
            totalMinTime = Math.max(totalMinTime, curMinTime);
        }
        answer = Math.min(answer, totalMinTime);
    }

    public static void dfs(int cnt) {
        if(cnt == peopleCnt) {      // 모든 사람이 올라갈 계단을 선택했다면
            upAndDownStair();
            return;
        }
        for(int stairIdx=0; stairIdx<MAX_STAIR_CNT; stairIdx++) {
            person[cnt] = stairIdx;
            dfs(cnt + 1);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();       // 총 테스트 케이스 개수
        for(int tc=1; tc<=t; tc++) {
            int n = sc.nextInt();       // 4 <= 방의 한 변의 길이 <= 10
            room = new int[n][n];
            stairs = new ArrayList();
            answer = Integer.MAX_VALUE;
            personPosition = new ArrayList();
            // 지도의 정보 입력 받기
            for(int x=0; x<n; x++) {
                for(int y=0; y<n; y++) {
                    room[x][y] = sc.nextInt();
                    if(room[x][y] >= 2 && room[x][y] <= 10) {   // 계단 입구라면
                        stairs.add(new Position(x, y));
                    }
                }
            }
            int personIdx = 0;
            for(int x=0; x<n; x++) {
                for(int y=0; y<n; y++) {
                    if(room[x][y] != 1) {       // 사람이 아니라면
                       continue;
                    }
                    personPosition.add(new Position(x, y));
                    personIdx++;
                }
            }
            peopleCnt = personIdx;
            person = new int[peopleCnt];
            dfs(0);
            System.out.println("#" + tc + " " + answer);
        }
    }
}