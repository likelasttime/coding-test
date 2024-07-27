class Solution {
    static final int[] dx = new int[]{1, 0, 0, -1};
    static final int[] dy = new int[]{0, -1, 1, 0};
    static final String[] pos = new String[]{"d", "l", "r", "u"};       // 아래, 왼쪽, 오른쪽, 위
    static int endX;        // r
    static int endY;        // c
    static int col;         // 미로의 세로 길이
    static int row;         // 미로의 가로 길이
    static int moveCnt;     // 이동 가능 횟수 k
    static String answer;   // 탈출한 경로 문자열

    class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "impossible";
        endX = r;
        endY = c;
        moveCnt = k;
        col = m;
        row = n;
        int minDistance = Math.abs(x - r) + Math.abs(y - c);
        if(minDistance % 2 != k % 2) {  // 최단 거리와 k의 홀짝성이 다르면 impossible
            return "impossible";
        }
        backtracking("", k, new Position(x, y));
        return answer;
    }

    /*
        path: 현재까지 이동한 경로
        depth: 남은 이동 가능 횟수
        position: 현재 위치를 나타내는 객체
     */
    public void backtracking(String path, int depth, Position position) {
        if(!answer.equals("impossible")) {
            return;
        } else if(depth == 0) {        // k만큼 움직임
            if(position.x == endX && position.y == endY) {      // 도착
                answer = path;      // 사전순으로 가장 빠른 경로로 갱신
            }
            return;
        } else if(Math.abs(position.x - endX) + Math.abs(position.y - endY) > depth) {  // 남은 이동 가능 횟수 depth로는 도착할 수 없음
            return;
        }
        for(int i = 0; i < 4; i++) {        // 아래, 왼쪽, 오른쪽, 위 순서로 탐색
            int nx = position.x + dx[i];
            int ny = position.y + dy[i];
            if(nx < 1 || nx > row || ny < 1 || ny > col) {      // 미로 밖으로 나가면 안 된다.
                continue;
            }
            backtracking(path + pos[i], depth - 1, new Position(nx, ny));
        }
    }
}