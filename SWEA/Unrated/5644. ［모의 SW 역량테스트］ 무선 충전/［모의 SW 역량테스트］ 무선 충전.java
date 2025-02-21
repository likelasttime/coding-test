import java.io.*;
import java.util.StringTokenizer;

class Solution {
    final static int MAX_MOVE = 100;        // 최대 이동 시간
    final static int PEOPLE_CNT = 2;
    final static int PIdx = 3;      // 처리량 인덱스
    // 이동하지 않음, 상, 우, 하, 좌
    final static int[] DX = {0, 0, 1, 0, -1};
    final static int[] DY = {0, -1, 0, 1, 0};

    static int[] aMoveArr;
    static int[] bMoveArr;
    static int[][] bc;
    static Position a;
    static Position b;
    static int[] choice;        // A와 B 각각이 선택한 충전기 번호 저장
    static int answer;      // 모든 사용자의 충전량 합의 최대값
    static int maxCharge;   // 현재 시각에서 최대 충전량
    static int bcCount;     // 1 <= 충전기 갯수 <= 8

    static class Position {
        int x;      // 가로
        int y;      // 세로
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /*
        사람이 해당 충전기의 영역 안에 속하는지
        bcNum: 충전기 번호
     */
    public static boolean isNear(int distance, int bcNum) {
        if(distance <= bc[bcNum][2]) {
            return true;
        }
        return false;
    }

    public static void dfs(int depth) {
        if(depth == PEOPLE_CNT) {      // 모든 사람이 충전기를 선택했다면
            int aDistance = getDistance(a, new Position(bc[choice[0]][1], bc[choice[0]][0]));       // A와 충전기 사이의 거리
            int bDistance = getDistance(b, new Position(bc[choice[1]][1], bc[choice[1]][0]));       // B와 충전기 사이의 거리
            int aCharge = 0;
            int bCharge = 0;
            if(isNear(aDistance, choice[0])) {      // A가 충전기 영역에 있으면
                aCharge = bc[choice[0]][PIdx];
            }
            if(isNear(bDistance, choice[1])) {      // B가 충전기 영역에 있으면
                bCharge = bc[choice[1]][PIdx];
            }
            if(choice[0] == choice[1] && aCharge != 0 && bCharge != 0) {      // A와 B가 같은 충전기를 사용하면
                maxCharge = Math.max(maxCharge, bc[choice[0]][PIdx]);
            } else {
                maxCharge = Math.max(maxCharge, aCharge + bCharge);
            }
            return;
        }
        for(int bcNum=1; bcNum<=bcCount; bcNum++) {
            choice[depth] = bcNum;
            dfs(depth + 1);
        }
    }

    public static int getDistance(Position person, Position bcPosition) {
        return Math.abs(person.x - bcPosition.x) + Math.abs(person.y - bcPosition.y);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());        // 총 테스트 케이스의 개수

        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            int moveTime = Integer.parseInt(st.nextToken());    // 20 <= 이동 시간 <= 100
            maxCharge = 0;
            bcCount = Integer.parseInt(st.nextToken());     // 1 <= BC의 개수 <= 8
            answer = 0;
            choice = new int[PEOPLE_CNT];
            aMoveArr = new int[MAX_MOVE + 1];
            bMoveArr = new int[MAX_MOVE + 1];
            bc = new int[bcCount + 1][4];
            a = new Position(1, 1);
            b = new Position(10, 10);

            // A의 이동 방향 입력 받기
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=moveTime; i++) {
                aMoveArr[i] = Integer.parseInt(st.nextToken());
            }

            // B의 이동 방향 입력 받기
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=moveTime; i++) {
                bMoveArr[i] = Integer.parseInt(st.nextToken());
            }

            // BC의 정보 입력 받기
            for(int i=1; i<=bcCount; i++) {
                st = new StringTokenizer(br.readLine());
                bc[i][1] = Integer.parseInt(st.nextToken());        // x 좌표(가로)
                bc[i][0] = Integer.parseInt(st.nextToken());        // y 좌표(세로)
                bc[i][2] = Integer.parseInt(st.nextToken());        // 충전 범위
                bc[i][PIdx] = Integer.parseInt(st.nextToken());        // 처리량
            }

            // 초기 위치에서 충전할 수 있는지
            dfs(0);
            answer += maxCharge;

            for(int time=1; time<=moveTime; time++) {
                maxCharge = 0;
                a = new Position(DX[aMoveArr[time]] + a.x, DY[aMoveArr[time]] + a.y);
                b = new Position(DX[bMoveArr[time]] + b.x, DY[bMoveArr[time]] + b.y);
                dfs(0);
                answer += maxCharge;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}