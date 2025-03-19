import java.util.*;

class Solution {
    static char[][] matrix;
    static int rowSize;
    static int colSize;
    static boolean[][] visit;

    /*
        2 * 2 형태로 모양이 같은 블록을 탐색
    */
    public boolean search() {
        boolean isPop = false;
        visit = new boolean[rowSize][colSize];  // 터진 블록을 기록할 배열

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (col + 1 >= colSize || row + 1 >= rowSize) {
                    continue;
                } else if (matrix[row][col] < 'A' || matrix[row][col] > 'Z') {
                    continue;
                }
                if (matrix[row][col] == matrix[row][col + 1] &&
                    matrix[row][col] == matrix[row + 1][col] &&
                    matrix[row][col] == matrix[row + 1][col + 1]) {
                    visit[row][col] = true;
                    visit[row][col + 1] = true;
                    visit[row + 1][col] = true;
                    visit[row + 1][col + 1] = true;
                    isPop = true;
                }
            }
        }

        return isPop;
    }

    public void drop() {
        char[][] copied = new char[rowSize][colSize];
        for (int col = 0; col < colSize; col++) {
            int idx = rowSize - 1;
            for (int row = rowSize - 1; row >= 0; row--) {
                if (!visit[row][col]) {  // 터지지 않은 블록이면
                    copied[idx--][col] = matrix[row][col];
                }
            }
        }
        matrix = copied;
    }

    public int solution(int m, int n, String[] board) {
        int totalPopped = 0;  // 총 터진 블록 수
        matrix = new char[m][n];
        rowSize = m;
        colSize = n;

        // 게임판 복사
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                matrix[row][col] = board[row].charAt(col);
            }
        }

        // 블록이 더 이상 터지지 않으면 종료
        while (true) {
            if (!search()) {
                break;
            }
            drop();
        }
        
        // 터지지 않은 블록 수 세기
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] < 'A' || matrix[row][col] > 'Z') {
                    totalPopped++;
                }
            }
        }

        return totalPopped;
    }
}
