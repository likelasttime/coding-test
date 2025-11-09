import java.io.*;
import java.util.*;

public class Main {
    final static int GAME_CNT = 5;
    final static int[] DX = {-1, 1, 0, 0};  // 상, 하, 좌, 우
    final static int[] DY = {0, 0, -1, 1};  // 상, 하, 좌, 우
    
    static int N; // 보드 크기
    static int[][] board;
    static int answer = 0;

    public static void generatePermutations(int[] result, int index) {
        if (index == GAME_CNT) {  // 5개 다 뽑았으면 시뮬레이션 시작
            simulate(result);
            return;
        }
        
        // 4가지 방향에 대해 중복 순열 생성
        for (int i = 0; i < 4; i++) { // 상, 하, 좌, 우
            result[index] = i;
            generatePermutations(result, index + 1);
        }
    }

    public static int[][] copy(int[][] origin) {
        int[][] copied = new int[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                copied[x][y] = origin[x][y];
            }
        }
        return copied;
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static void goUp(int[][] before) {
        for (int y = 0; y < N; y++) {  // 열
            boolean[] isMerged = new boolean[N]; // 해당 위치가 합쳐졌는지 확인하는 배열
            int idx = 0; // 합칠 위치
            for (int x = 0; x < N; x++) {  // 행
                if (before[x][y] != 0) {  // 블록이 있으면
                    int currValue = before[x][y];
                    int target = idx - 1;

                    // 블록 합치기
                    if (target >= 0 && before[target][y] == currValue && !isMerged[target]) {
                        before[target][y] *= 2;
                        before[x][y] = 0;
                        isMerged[target] = true;
                    } else {
                        before[idx][y] = currValue;
                        if (idx != x) {
                            before[x][y] = 0;
                        }
                        idx++;
                    }
                }
            }
        }
    }

    public static void goDown(int[][] before) {
        for (int y = 0; y < N; y++) {  // 열
            boolean[] isMerged = new boolean[N]; // 해당 위치가 합쳐졌는지 확인하는 배열
            int idx = N - 1; // 합칠 위치
            for (int x = N - 1; x >= 0; x--) {  // 행
                if (before[x][y] != 0) {  // 블록이 있으면
                    int currValue = before[x][y];
                    int target = idx + 1;

                    // 블록 합치기
                    if (target < N && before[target][y] == currValue && !isMerged[target]) {
                        before[target][y] *= 2;
                        before[x][y] = 0;
                        isMerged[target] = true;
                    } else {
                        before[idx][y] = currValue;
                        if (idx != x) {
                            before[x][y] = 0;
                        }
                        idx--;
                    }
                }
            }
        }
    }

    public static void goLeft(int[][] before) {
        for (int x = 0; x < N; x++) {  // 행
            boolean[] isMerged = new boolean[N]; // 해당 위치가 합쳐졌는지 확인하는 배열
            int idx = 0; // 합칠 위치
            for (int y = 0; y < N; y++) {  // 열
                if (before[x][y] != 0) {  // 블록이 있으면
                    int currValue = before[x][y];
                    int target = idx - 1;

                    // 블록 합치기
                    if (target >= 0 && before[x][target] == currValue && !isMerged[target]) {
                        before[x][target] *= 2;
                        before[x][y] = 0;
                        isMerged[target] = true;
                    } else {
                        before[x][idx] = currValue;
                        if (idx != y) {
                            before[x][y] = 0;
                        }
                        idx++;
                    }
                }
            }
        }
    }

    public static void goRight(int[][] before) {
        for (int x = 0; x < N; x++) {  // 행
            boolean[] isMerged = new boolean[N]; // 해당 위치가 합쳐졌는지 확인하는 배열
            int idx = N - 1; // 합칠 위치
            for (int y = N - 1; y >= 0; y--) {  // 열
                if (before[x][y] != 0) {  // 블록이 있으면
                    int currValue = before[x][y];
                    int target = idx + 1;

                    // 블록 합치기
                    if (target < N && before[x][target] == currValue && !isMerged[target]) {
                        before[x][target] *= 2;
                        before[x][y] = 0;
                        isMerged[target] = true;
                    } else {
                        before[x][idx] = currValue;
                        if (idx != y) {
                            before[x][y] = 0;
                        }
                        idx--;
                    }
                }
            }
        }
    }

    public static void simulate(int[] result) {
        int[][] before = copy(board); // 입력으로 받은 게임판을 깊은 복사
        for (int i = 0; i < GAME_CNT; i++) { // 총 5번의 이동
            if (result[i] == 0) {
                goUp(before); // 상
            } else if (result[i] == 1) {
                goDown(before); // 하
            } else if (result[i] == 2) {
                goLeft(before); // 좌
            } else if (result[i] == 3) {
                goRight(before); // 우
            }
        }
        findMax(before);
    }

    public static void findMax(int[][] arr) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                answer = Math.max(answer, arr[x][y]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        
        // 게임판의 초기 상태 입력받기
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                board[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        // 재귀를 통해 중복 순열 구하기
        generatePermutations(new int[GAME_CNT], 0);
        
        System.out.println(answer);
    }
}
