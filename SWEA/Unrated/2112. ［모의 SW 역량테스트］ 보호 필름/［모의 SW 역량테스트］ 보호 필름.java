import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution {
    static int d;       // 3 <= 보호필름의 두께 <= 13
    static int w;       // 1 <= 가로크기 <= 20
    static int k;       // 1 <= 합격기준 <= d
    static int[][] film;        // 입력받은 보호필름 배열 원본
    static boolean isEnd;       // 성능 검사를 아직 통과하지 못 했다면 false

    /*
        세로방향에 대해 동일한 특성의 셀들이 k개 이상 연속일 경우 true 반환
        col: 보호필름의 열
        tmpFilm: 입력받은 film에서 약품을 투여해 변화시킨 배열
     */
    public static boolean slidingWindow(int col, int[][] tmpFilm) {
        int start = 0;
        int end = k;
        while(end <= d) {
            // 연속된 k개를 탐색
            boolean isContinue = true;
            for(int i=start+1; i<end; i++) {
                if(tmpFilm[i][col] != tmpFilm[start][col]) {
                    isContinue = false;
                    start = i;
                    end = start + k;
                    break;
                }
            }
            if(isContinue) {
                return true;
            }
        }
        return false;
    }

    /*
        약품을 투입할 행을 선택하는 DFS
        depth: 현재 선택한 행의 갯수
        cnt: 선택해야할 행의 갯수
        arr: 선택한 행의 인덱스를 저장하는 배열
        start: 다음 인덱스
     */
    public static void choiceRowDFS(int cnt, int depth, int[] arr, int start) {
        if(cnt == depth) {
            // 행을 다 선택한 후에는 어떤 약품을 투입할지를 결정
            choiceValueDFS(arr, 0, new int[cnt], cnt);
            return;
        }
        for(int i=start; i<d; i++) {
            arr[depth] = i;
            choiceRowDFS(cnt, depth + 1, arr, i + 1);
        }
    }

    /*
        A와 B(0, 1) 약품을 투입하는 완전 탐색
        arr: 약품을 투입할 행을 저장하는 배열
        valueArr: 어떤 약품을 투입할지 저장하는 배열
        depth: 선택한 값 갯수
        cnt: 선택해야 할 값의 갯수
     */
    public static void choiceValueDFS(int[] arr, int depth, int[] valueArr, int cnt) {
        if(cnt == depth) {
            // 원본 배열 복사
            int[][] copiedArr = copied();
           // 약품을 투입하기
            for(int i=0; i<cnt; i++) {      // 약품 투입 횟수만큼
                Arrays.fill(copiedArr[arr[i]], valueArr[i]);
            }
            // 성능 검사
            boolean isSuccess = true;
            for(int col=0; col<w; col++) {
                if(!slidingWindow(col, copiedArr)) {
                    isSuccess = false;
                    break;
                }
            }
            if(isSuccess) {
                isEnd = true;
            }
            return;
        }
        for(int value=0; value<2; value++) {
            valueArr[depth] = value;
            choiceValueDFS(arr, depth + 1, valueArr, cnt);
        }
    }

    /*
        입력 받은 원본 배열을 복사
     */
    public static int[][] copied() {
        int[][] copiedFilm = new int[d][w];
        for(int row=0; row<d; row++) {
            for(int col=0; col<w; col++) {
                copiedFilm[row][col] = film[row][col];
            }
        }
        return copiedFilm;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());        // 총 테스트 케이스의 개수

        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            // 보호필름의 단면 정보 입력 받기(0: A, B: 1)
            film = new int[d][w];
            for(int row=0; row<d; row++) {
                st = new StringTokenizer(br.readLine());
                for(int col=0; col<w; col++) {
                    film[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            // 초기 보호필름의 단면 성능 검사
            int answer = 0;
            boolean isNotNeedMedicine = true;
            for(int col=0; col<w; col++) {
                if(!slidingWindow(col, film)) {
                    isNotNeedMedicine = false;
                    break;
                }
            }

            if(!isNotNeedMedicine) {
                isEnd = false;
                for (int cnt = 1; cnt <= d; cnt++) {
                    choiceRowDFS(cnt, 0, new int[cnt], 0);
                    if (isEnd) {
                        answer = cnt;
                        break;
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}