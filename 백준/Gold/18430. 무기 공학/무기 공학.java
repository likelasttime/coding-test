import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    final static int[][] dir = {{0, -1, 1, 0},
            {-1, 0, 0, -1},
            {-1, 0, 0, 1},
            {1, 0, 0, 1}};

    static int n;       // 1 <= 세로 크기 <= 5
    static int m;       // 1 <= 가로 크기 <= 5
    static int[][] arr;
    static List<Bumaerang> lst;
    static int answer;
    static boolean[][] visit;

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Bumaerang {
        int sum;        // 강도
        Position p1;
        Position p2;
        Position p3;

        Bumaerang(int sum, Position p1, Position p2, Position p3) {
            this.sum = sum;
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void dfs(int sum, int start) {
        answer = Math.max(answer, sum);

        for(int i=start; i<lst.size(); i++) {
            Bumaerang bumaerang = lst.get(i);
            if(visit[bumaerang.p1.x][bumaerang.p1.y] || visit[bumaerang.p2.x][bumaerang.p2.y]
                    || visit[bumaerang.p3.x][bumaerang.p3.y]) {
                continue;
            }
            visit[bumaerang.p1.x][bumaerang.p1.y] = true;
            visit[bumaerang.p2.x][bumaerang.p2.y] = true;
            visit[bumaerang.p3.x][bumaerang.p3.y] = true;
            dfs(sum + bumaerang.sum, i + 1);
            visit[bumaerang.p1.x][bumaerang.p1.y] = false;
            visit[bumaerang.p2.x][bumaerang.p2.y] = false;
            visit[bumaerang.p3.x][bumaerang.p3.y] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        lst = new ArrayList();
        visit = new boolean[n][m];

        if(n < 2 || m < 2) {
            System.out.print(0);
            return;
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        // 부메랑 만들기
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                for(int d=0; d<4; d++) {
                    int x1 = i + dir[d][0];
                    int y1 = j + dir[d][1];
                    int x2 = i + dir[d][2];
                    int y2 = j + dir[d][3];
                    if(isValid(x1, y1) && isValid(x2, y2)) {
                        lst.add(new Bumaerang(arr[i][j] * 2 + arr[x1][y1] + arr[x2][y2],
                                new Position(x1, y1),
                                new Position(x2, y2),
                                new Position(i, j)));
                    }
                }
            }
        }

        dfs(0, 0);

        System.out.print(answer);
    }
}