import java.util.*;

/*
 * 귀신 구멍으로 들어가면
 * 	묘지의 다른 장소로 다시 나온다.
 * 	특정 시간이 지난 후에 평행 우주의 다른 구멍에서 나온다.
 * 		시간은 양수 or 음수 or 0
 * 묘지 = W * H
 * 묘지의 입구 = (0, 0)
 * 묘지의 출구 = (W - 1, H - 1)
 * 각 칸은 잔디 or 묘비 or 귀신 구멍
 * 묘비가 있는 칸은 이동 불가
 * 
*/
public class Main {
    static int W, H, G, E;
    static int[][] map;
    static long[][] cost;
    static List<Edge> edges;
    static List<String> result;

    static final int GRASS = 0;
    static final int GRAVE = 1;
    static final int HOLE = 2;
    static final int EXIT = 3;

    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        result = new ArrayList<>();

        while (true) {
            W = sc.nextInt();
            H = sc.nextInt();
            if (W == 0 && H == 0) break;

            map = new int[H][W];
            map[H - 1][W - 1] = EXIT; // 출구 표시

            G = sc.nextInt();
            for (int i = 0; i < G; i++) {
                int col = sc.nextInt();
                int row = sc.nextInt();
                map[row][col] = GRAVE;
            }

            E = sc.nextInt();
            edges = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                int col1 = sc.nextInt();
                int row1 = sc.nextInt();
                int col2 = sc.nextInt();
                int row2 = sc.nextInt();
                int t = sc.nextInt();
                map[row1][col1] = HOLE;
                edges.add(new Edge(new Point(row1, col1), new Point(row2, col2), t));
            }

            add_edges();
            get_cost_bf();
        }

        for (String s : result) System.out.println(s);
    }

    static void get_cost_bf() {
        cost = new long[H][W];
        for (int i = 0; i < H; i++)
            Arrays.fill(cost[i], INF);

        cost[0][0] = 0;
        int V = W * H; // 전체 칸 수로 반복
        boolean negativeCycle = false;

        for (int i = 0; i < V; i++) {
            boolean updated = false;
            for (Edge e : edges) {
                Point start = e.start;
                Point end = e.end;
                if (cost[start.row][start.col] == INF) continue;
                if (cost[end.row][end.col] > cost[start.row][start.col] + e.cost) {
                    cost[end.row][end.col] = cost[start.row][start.col] + e.cost;
                    updated = true;
                    if (i == V - 1) negativeCycle = true;
                }
            }
            if (!updated) break;
        }

        if (negativeCycle) result.add("Never");
        else if (cost[H - 1][W - 1] == INF) result.add("Impossible");
        else result.add(Long.toString(cost[H - 1][W - 1]));
    }

    static void add_edges() {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                if (map[r][c] != GRASS) continue;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (can_move_to(nr, nc))
                        edges.add(new Edge(new Point(r, c), new Point(nr, nc), 1));
                }
            }
        }
    }

    static boolean can_move_to(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W && map[r][c] != GRAVE;
    }
}

class Point {
    int row, col;
    Point(int r, int c) { row = r; col = c; }
}

class Edge {
    Point start, end;
    int cost;
    Edge(Point s, Point e, int c) { start = s; end = e; cost = c; }
}
