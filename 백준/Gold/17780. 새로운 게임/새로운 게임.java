import java.io.*;
import java.util.*;

public class Main {
    // 우좌상하 (방향: 1: 오른쪽, 2: 왼쪽, 3: 위, 4: 아래)
    final static int[] DX = {0, 0, 0, -1, 1};
    final static int[] DY = {0, 1, -1, 0, 0};

    static int n;       // 4 <= 체스판의 크기 <= 12
    static int k;       // 4 <= 말의 개수 <= 10
    static List<List<List<Integer>>> matrix; // 말들이 쌓일 칸 정보
    static int[][] arr; // 체스판의 색 (0: 흰색, 1: 빨간색, 2: 파란색)
    static Horse[] horse; // 말 정보

    // 말 정보를 저장하는 클래스
    static class Horse {
        int x;      // 행
        int y;      // 열
        int d;      // 이동방향
        Horse(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    // (x, y) 위치에 말이 4개 이상 쌓이면 게임 종료
    public static boolean isEnd(int x, int y) {
        return matrix.get(x).get(y).size() >= 4;
    }

    // 빨간 칸일 때, 말들이 쌓여있는 순서를 거꾸로 바꾸기
    public static void redMove(int x, int y, int nx, int ny) {
        List<Integer> horseLst = new ArrayList<>(matrix.get(x).get(y));
        matrix.get(x).get(y).clear();     // 움직이기 전 위치에 있는 말을 제거
        // 빨간 칸에서는 역순으로 쌓기
        for (int i = horseLst.size() - 1; i >= 0; i--) {
            int num = horseLst.get(i);
            horse[num].x = nx;
            horse[num].y = ny;
            matrix.get(nx).get(ny).add(num);  // 새 위치에 말 추가
        }
    }

    // 흰 칸일 때, 말들을 그대로 쌓기
    public static void whiteMove(int x, int y, int nx, int ny) {
        List<Integer> horseLst = new ArrayList<>(matrix.get(x).get(y));
        matrix.get(x).get(y).clear();     // 움직이기 전 위치에 있는 말을 제거
        for (int i = 0; i < horseLst.size(); i++) {
            int num = horseLst.get(i);
            matrix.get(nx).get(ny).add(num);  // 새 위치에 말 추가
            horse[num].x = nx;
            horse[num].y = ny;
        }
    }

    // 좌표가 유효한지 확인하는 함수
    public static boolean isValid(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    // 말의 방향을 반대로 바꾸는 함수
    public static void changeDirection(int num) {
        switch (horse[num].d) {
            case 1:  // 우
                horse[num].d = 2;  // 좌로 변환
                break;
            case 2:  // 좌
                horse[num].d = 1;  // 우로 변환
                break;
            case 3:  // 상
                horse[num].d = 4;  // 하로 변환
                break;
            case 4:  // 하
                horse[num].d = 3;  // 상으로 변환
                break;
        }
    }

    // 말의 위치를 바꾸는 함수
    public static void changePosition(int x, int y, int nx, int ny) {
        List<Integer> nums = matrix.get(x).get(y);  // (x, y)에 있는 말 번호들
        for (int i : nums) {
            horse[i].x = nx;
            horse[i].y = ny;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 체스판의 크기
        k = Integer.parseInt(st.nextToken()); // 말의 개수
        matrix = new ArrayList<>();
        arr = new int[n + 1][n + 1];
        matrix = new ArrayList<>();
        int answer = 1;     // 게임 종료 턴 번호
        // matrix 초기화: 체스판 크기만큼 말들을 저장할 리스트 생성
        for (int i = 0; i <= n; i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j <= n; j++) {
                matrix.get(i).add(new ArrayList<>());
            }
        }

        // 체스판의 색 정보 입력 받기 (0:흰색, 1:빨간색, 2:파란색)
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 말의 위치와 방향 정보를 입력 받기
        horse = new Horse[k + 1];
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            horse[i] = new Horse(x, y, d);
            matrix.get(x).get(y).add(i);  // (x, y) 위치에 말을 추가
        }

        // 1부터 1000턴까지 게임 진행
        for (int turn = 1; turn <= 1001; turn++) {
            answer = turn;
            boolean isEndGame = false;
            // 1번 말부터 순서대로 움직이기
            for (int i = 1; i <= k; i++) {
                int x = horse[i].x;
                int y = horse[i].y;
                int nx = x + DX[horse[i].d];
                int ny = y + DY[horse[i].d];
                List<Integer> horseLst = matrix.get(x).get(y);

                // 가장 아래에 있는 말만 움직이기
                if (!horseLst.isEmpty() && horseLst.get(0) != i) {
                    continue;
                }

                // 좌표를 벗어나거나 파란 칸을 밟으면
                if (!isValid(nx, ny) || arr[nx][ny] == 2) {
                    changeDirection(i);  // 방향 바꾸기
                    nx = x + DX[horse[i].d];
                    ny = y + DY[horse[i].d];

                    // 또 파란 칸을 밟았다면 방향만 바꾸기
                    if (!isValid(nx, ny) || arr[nx][ny] == 2) {
                        continue;
                    }
                }

                // 칸의 색에 따른 처리
                if (arr[nx][ny] == 0) {  // 흰색 칸을 밟았을 때
                    whiteMove(x, y, nx, ny);
                } else if (arr[nx][ny] == 1) {  // 빨간 칸을 밟았을 때
                    redMove(x, y, nx, ny);
                }

                // 말의 위치 갱신
                changePosition(x, y, nx, ny);

                // 4개 이상의 말이 쌓였다면 게임 종료
                if (isEnd(nx, ny)) {
                    isEndGame = true;
                    break;
                }
            }
            if (isEndGame) {
                break;
            }
        }

        if (answer == 1001) {
            answer = -1;
        }

        // 게임 종료 턴 번호 출력 (게임이 종료되지 않으면 -1)
        System.out.print(answer);
    }
}
