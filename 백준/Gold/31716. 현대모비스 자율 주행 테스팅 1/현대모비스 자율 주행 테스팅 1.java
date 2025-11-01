import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long K;
    static char[][] board;

    static class Node {
        int row, col, startRow;
        Node(int row, int col, int startRow) {
            this.row = row;
            this.col = col;
            this.startRow = startRow;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        board = new char[2][N];
        for (int r = 0; r < 2; r++) {
            String line = br.readLine().trim();
            for (int c = 0; c < N; c++) {
                board[r][c] = line.charAt(c);
            }
        }

        long moveWithinBlock = -1;
        int startRow = -1;
        int endRow = -1;

        Queue<Node> q = new ArrayDeque<>();
        int[][] dist = new int[2][N];  // [row][col], 블록 내 거리
        for (int i = 0; i < 2; i++) Arrays.fill(dist[i], -1);

        // 시작점 등록
        if (board[0][0] == '.') { q.add(new Node(0, 0, 0)); dist[0][0] = 0; }
        if (board[1][0] == '.') { q.add(new Node(1, 0, 1)); dist[1][0] = 0; }

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int r = cur.row, c = cur.col;

            if (c == N - 1) {
                moveWithinBlock = dist[r][c];
                startRow = cur.startRow;
                endRow = r;
                break;
            }

            // 오른쪽 이동
            if (c + 1 < N && board[r][c + 1] == '.' && dist[r][c + 1] == -1) {
                dist[r][c + 1] = dist[r][c] + 1;
                q.add(new Node(r, c + 1, cur.startRow));
            }

            // 반대편 이동
            int nr = (r + 1) % 2;
            if (board[nr][c] == '.' && dist[nr][c] == -1) {
                dist[nr][c] = dist[r][c] + 1;
                q.add(new Node(nr, c, cur.startRow));
            }
        }

        if (moveWithinBlock == -1) {
            System.out.println(-1);
            return;
        }

        long moveToNextBlock = 0;
        if (K > 1) {
            if (startRow == endRow) {
                moveToNextBlock = 1;
            } else {
                if (board[endRow][0] == '.' || board[(endRow + 1) % 2][N - 1] == '.') {
                    moveToNextBlock = 2;
                } else {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(moveWithinBlock * K + moveToNextBlock * (K - 1));
    }
}
