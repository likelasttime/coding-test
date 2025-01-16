import java.util.Scanner;

class Solution {
    static int[][] arr;
    static int n;
    static int x;
    static int[] height;
    static int answer;

    public static boolean isFlatten(int start, int end) {
        // 인덱스 범위를 넘어가면
        if(start < 0 || end >= n) {
            return false;
        }

        for(int i=start; i<end; i++) {
            if(height[i] != height[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPossible() {
        boolean[] leftSlope = new boolean[n];
        boolean[] rightSlope = new boolean[n];
        // 한 줄 또는 한 행에 높이 차이가 2이상이 나는 구간이 있으면 안 된다.
        for(int i=0; i<n-1; i++) {
            if(Math.abs(height[i] - height[i + 1]) >= 2) {
                return false;
            }
        }

        for(int i=0; i<n-1; i++) {
            if(height[i] - 1 == height[i + 1]) {        // 왼쪽이 더 높을 때
                int start = i + 1;
                int end = i + x;
                if(isFlatten(start, end)) {
                    for(int j=start; j<=end; j++) {
                        rightSlope[j] = true;
                    }
                } else {        // 오른쪽으로 경사로를 설치할 수 없다면
                    return false;
                }
            } else if(height[i] == height[i + 1] - 1) {     // 오른쪽이 더 높을 때
                int start = i + 1 - x;
                int end = i;
                if(isFlatten(start, end)) {
                    for(int j=start; j<=end; j++) {
                        leftSlope[j] = true;
                    }
                } else {        // 왼쪽으로 경사로를 설치할 수 없다면
                    return false;
                }
            }
        }

        // 겹치는 슬로프가 있는지 탐색
        for(int i=0; i<n; i++) {
            if(leftSlope[i] && rightSlope[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();       // 테스트 케이스 개수
        for(int tc=1; tc<=t; tc++) {
            n = sc.nextInt();       // 지도 한 변의 크기
            x = sc.nextInt();       // 경사로의 길이
            arr = new int[n][n];
            height = new int[n];
            answer = 0;
            // 지형 정보 입력 받기
            for(int row=0; row<n; row++) {
                for(int col=0; col<n; col++) {
                    arr[row][col] = sc.nextInt();
                }
            }
            // 가로 탐색
            for(int row=0; row<n; row++) {
                for(int col=0; col<n; col++) {
                    height[col] = arr[row][col];
                }
                if(isPossible()) {
                    answer++;
                }
            }
            // 세로 탐색
            for(int col=0; col<n; col++) {
                for(int row=0; row<n; row++) {
                    height[row] = arr[row][col];
                }
                if(isPossible()) {
                    answer++;
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}