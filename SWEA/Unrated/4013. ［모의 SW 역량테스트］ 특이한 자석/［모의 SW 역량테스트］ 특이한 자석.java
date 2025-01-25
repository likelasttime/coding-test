import java.util.Scanner;

class Solution {
    final static int MAGNETIC_CNT = 4;
    final static int NAL_CNT = 8;

    static int[][] magnetic;
    static int[][] pos;     // 각 자석의 빨간색 화살표 위치 저장
    static int answer;      // 총 점수
    static int[] rotateDirection;       // 각 자석별 회전 방향 저장

    /*
        반시계 방향 회전
        num: 자석 번호
     */
    public static void rotateLeft(int num) {
        pos[num][0] = (pos[num][0] + 1) % NAL_CNT;
    }

    /*
        시계 방향 회전
        num: 자석 번호
     */
    public static void rotateRight(int num) {
        if(pos[num][0] == 0) {
            pos[num][0] = NAL_CNT - 1;
        } else {
            pos[num][0]--;
        }
    }

    /*
        현재 자석 num에서 왼쪽으로 탐색하면서 같이 회전할 자석을 찾기
     */
    public static int getLeftRange(int num, int d) {
        int leftRange = num;
        int curD = d;
        for(int i=num; i>=1; i--) {      // 자석 번호
            int left = magnetic[i - 1][(pos[i - 1][0] + 2) % NAL_CNT];
            int right = magnetic[i][(pos[i][0] + 6) % NAL_CNT];
            if(left == right) {     // 자성이 같으면
                return i;
            }
            curD = getDirection(curD);
            rotateDirection[i - 1] = curD;
            leftRange = i - 1;
        }
        return leftRange;
    }

    /*
        현재 자석 num에서 오른쪽으로 탐색하면서 같이 회전할 자석을 찾기
     */
    public static int getRightRange(int num, int d) {
        int rightRange = num;
        int curD = d;
        for(int i=num+1; i<MAGNETIC_CNT; i++) {      // 자석 번호
            int right = magnetic[i][(pos[i][0] + 6) % NAL_CNT];
            int left = magnetic[i - 1][(pos[i - 1][0] + 2) % NAL_CNT];
            if(left == right) {     // 자성이 같으면
                return i - 1;
            }
            curD = getDirection(curD);
            rotateDirection[i] = curD;
            rightRange = i;
        }
        return rightRange;
    }

    public static int getDirection(int d) {
        if(d == 1) {
            return -1;
        }
        return 1;
    }

    public static void rotate(int num, int d) {
        int start = getLeftRange(num, d);
        int end = getRightRange(num, d);
        rotateDirection[num] = d;
        for(int i=start; i<=end; i++) {
            if(rotateDirection[i] == 1) {       // 시계 방향
                rotateRight(i);
            } else {        // 반시계 방향
                rotateLeft(i);
            }
        }
    }

    public static void getAnswer() {
        for(int i=0; i<MAGNETIC_CNT; i++) {
            if(magnetic[i][pos[i][0]] == 1) {       // S극일때
                answer += Math.pow(2, i);
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();       // 테스트 케이스 수
        for(int tc=1; tc<=t; tc++) {
            magnetic = new int[MAGNETIC_CNT][NAL_CNT];
            pos = new int[MAGNETIC_CNT][1];
            rotateDirection = new int[MAGNETIC_CNT];
            answer = 0;
            int k = sc.nextInt();       // 자석을 회전시키는 횟수
            // 1번 자석부터 4번 자석까지 각각 8개 날의 자성정보 입력받기
            for(int i=0; i<4; i++) {
                for(int j=0; j<8; j++) {
                    magnetic[i][j] = sc.nextInt();
                }
            }
            // 자석을 1칸씩 회전시키는 회전 정보 입력받기
            for(int i=0; i<k; i++) {
                int num = sc.nextInt();     // 자석의 번호
                int d = sc.nextInt();       // 회전 방향
                rotate(num - 1, d);
            }
            getAnswer();
            System.out.println("#" + tc + " " + answer);
        }
    }
}