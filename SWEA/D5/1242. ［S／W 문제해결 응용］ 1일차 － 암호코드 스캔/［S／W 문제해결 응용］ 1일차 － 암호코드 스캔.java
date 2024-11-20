import java.io.*;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb;
        int[][][] rate = new int[5][5][5];
        rate[2][1][1] = 0;
        rate[2][2][1] = 1;
        rate[1][2][2] = 2;
        rate[4][1][1] = 3;
        rate[1][3][2] = 4;
        rate[2][3][1] = 5;
        rate[1][1][4] = 6;
        rate[3][1][2] = 7;
        rate[2][1][3] = 8;
        rate[1][1][2] = 9;
        int[][] toBinary = {{0, 0, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}, {0, 0, 1, 1}, {0, 1, 0, 0}, {0, 1, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1},
                {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 0, 1, 0}, {1, 0, 1, 1}, {1, 1, 0, 0}, {1, 1, 0, 1}, {1, 1, 1, 0}, {1, 1, 1, 1}};

        int tc = Integer.parseInt(br.readLine());       // 테스트케이스의 수
        for(int t=1; t<=tc; t++) {
            st = new StringTokenizer(br.readLine());
            sb = new StringBuilder();
            int n = Integer.parseInt(st.nextToken());     // 배열의 세로 크기
            int m = Integer.parseInt(st.nextToken());       // 가로 크기
            String[] arr = new String[n];
            int[][] matrix = new int[n][m * 4];
            int[] cryptogram = new int[8];
            int answer = 0;

            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine();
            }

            // 2진수로 변환하기
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    int num = Character.digit(arr[i].charAt(j), 16);
                    for(int k=0; k<4; k++) {
                        matrix[i][j * 4 + k] = toBinary[num][k];
                    }
                }
            }

            for(int i=1; i<n; i++) {
                int idx = 7;
                for(int j=m*4-1; j>0; j--) {
                    if(matrix[i-1][j] == 0 && matrix[i][j] == 1) {
                        int x = 0;
                        int y = 0;
                        int z = 0;
                        while(matrix[i][j] == 1) {
                            z++;
                            j--;
                        }
                        while(matrix[i][j] == 0) {
                            y++;
                            j--;
                        }
                        while(matrix[i][j] == 1) {
                            x++;
                            j--;
                        }
                        j++;

                        int min = Math.min(Math.min(x, y), z);
                        x /= min;
                        y /= min;
                        z /= min;
                        cryptogram[idx--] = rate[x][y][z];

                        if(idx == -1) {
                            int oddSum = cryptogram[0] + cryptogram[2] + cryptogram[4] + cryptogram[6];
                            int evenSum = cryptogram[1] + cryptogram[3] + cryptogram[5];
                            if((oddSum * 3 + evenSum + cryptogram[7]) % 10 == 0) {
                                for(int a=0; a<8; a++) {
                                    answer += cryptogram[a];
                                }
                            }
                            idx = 7;
                        }
                    }
                }
            }
            System.out.println("#" + t + " " + answer);
        }
    }
}