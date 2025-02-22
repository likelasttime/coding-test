import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Solution {
    // 원위치, 상, 하, 좌, 우
    final static int[] DX = {0, 0, 0, -1, 1};
    final static int[] DY = {0, -1, 1, 0, 0};

    static int answer;      // 모든 사용자의 충전량 합의 최대값
    static int n;           // 5 <= 구역의 한 변에 있는 셀의 개수 <= 100
    static int m;           // 1 <= 격리 시간 <= 1,000
    static int k;           // 1 <= 미생물 군집의 개수 <= 10,000
    static List<Microorganism> lst;
    static boolean[] die;       // 죽은 미생물 표시
    static List<Integer>[][] matrix;

    static class Microorganism {
        int x;      // 가로
        int y;      // 세로
        int cnt;
        int d;

        public Microorganism(int x, int y, int cnt, int d) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.d = d;
        }
    }

    /*
        미생물이 약품이 칠해진 셀에 도착했다면
        idx: 미생물 인덱스
     */
    public static boolean isArrive(int x, int y) {
        if(x == 0 || y == 0 || x == n - 1 || y == n - 1) {
            return true;
        }
        return false;
    }

    public static int getReverseDirection(int d) {
        if(d == 1 || d == 3) {
            return d + 1;
        }
        return d - 1;
    }

    public static boolean isDie(Microorganism microorganism) {
        if(microorganism.cnt == 1) {
            return true;
        }
        return false;
    }

    /*
        미생물의 이동 방향, 미생물의 수를 바꾸기
     */
    public static void change(Microorganism microorganism) {
        microorganism.d = getReverseDirection(microorganism.d);      // 이동 방향이 반대로 변한다
        microorganism.cnt = microorganism.cnt / 2;
    }

    public static void generateLst() {
        List<Microorganism> tmp = new ArrayList();
        for (int i = 0; i < lst.size(); i++) {
            if(die.length > i && die[i]) {
                continue;
            }
            tmp.add(lst.get(i));
        }
        lst = tmp;
    }

    /*
        두 개 이상의 군집이 한 셀에 모였을 때 군집들이 합쳐진다.
     */
    public static void gather() {
        for(int y=0; y<n; y++) {
            for(int x=0; x<n; x++) {
                if(matrix[y][x].size() >= 2) {
                    int sumCnt = 0;     // 합쳐진 군집의 미생물 수
                    int d = 0;          // 미생물 수가 가장 많은 군집의 이동방향
                    int maxCnt = 0;     // 최대 미생물 수
                    for(int i=0; i<matrix[y][x].size(); i++) {
                        Microorganism microorganism = lst.get(matrix[y][x].get(i));
                        if(microorganism.cnt > maxCnt) {
                            d = microorganism.d;
                            maxCnt = microorganism.cnt;
                        }
                        sumCnt += microorganism.cnt;
                        die[matrix[y][x].get(i)] = true;
                    }
                    // 합쳐진 군집 추가
                    lst.add(new Microorganism(x, y, sumCnt, d));
                }
            }
        }
    }

    public static void move() {
        for(int i=0; i<lst.size(); i++) {
            Microorganism microorganism = lst.get(i);
            microorganism.x += DX[microorganism.d];
            microorganism.y += DY[microorganism.d];
            // 이동 후 약품이 칠해진 셀에 도착하면
            if(isArrive(microorganism.x, microorganism.y)) {
                if(isDie(microorganism)) {
                    die[i] = true;
                    continue;
                } else {
                    // 이동 방향, 미생물의 수가 바뀐다.
                    change(microorganism);
                    lst.set(i, microorganism);
                }
            }
            matrix[microorganism.y][microorganism.x].add(i);
        }
    }

    public static int getFinalCnt() {
        int cnt = 0;
        for(int i=0; i<lst.size(); i++) {
            cnt += lst.get(i).cnt;
        }
        return cnt;
    }

    public static void initMatrix() {
        for(int y=0; y<n; y++) {
            for(int x=0; x<n; x++) {
                matrix[y][x] = new ArrayList();
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());        // 총 테스트 케이스의 개수

        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            // 미생물 군집의 정보 입력 받기
            lst = new ArrayList();
            for(int i=0; i<k; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());       // 세로 위치
                int x = Integer.parseInt(st.nextToken());       // 가로 위치
                int cnt = Integer.parseInt(st.nextToken());     // 미생물 수
                int d = Integer.parseInt(st.nextToken());        // 이동 방향
                lst.add(new Microorganism(x, y, cnt, d));
            }

            for(int time=1; time<=m; time++) {
                die = new boolean[lst.size()];
                matrix = new ArrayList[n][n];
                initMatrix();
                move();
                gather();
                generateLst();
            }

            System.out.println("#" + tc + " " + getFinalCnt());
        }
    }
}