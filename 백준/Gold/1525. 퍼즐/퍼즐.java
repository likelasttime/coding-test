import java.util.*;

public class Main {
    static final int SIZE = 3;
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    static final String GOAL = "123456780";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE * SIZE; i++) {
            sb.append(sc.nextInt());
        }

        System.out.println(bfs(sb.toString()));
    }

    static int bfs(String start) {
        Queue<String> q = new ArrayDeque<>();
        Map<String, Integer> dist = new HashMap<>();

        q.offer(start);
        dist.put(start, 0);

        while (!q.isEmpty()) {
            String cur = q.poll();
            int cnt = dist.get(cur);

            if (cur.equals(GOAL)) return cnt;

            int zero = cur.indexOf('0');
            int x = zero / 3;
            int y = zero % 3;

            for (int d = 0; d < 4; d++) {
                int nx = x + DX[d];
                int ny = y + DY[d];

                if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) continue;

                int nPos = nx * 3 + ny;

                char[] next = cur.toCharArray();
                next[zero] = next[nPos];
                next[nPos] = '0';

                String nextState = new String(next);
                if (!dist.containsKey(nextState)) {
                    dist.put(nextState, cnt + 1);
                    q.offer(nextState);
                }
            }
        }
        return -1;
    }
}
