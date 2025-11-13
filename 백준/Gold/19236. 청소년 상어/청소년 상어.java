import java.util.*;
import java.io.*;

/* * 4 * 4 크기의 공간 
 * * 공간의 각 칸은 (x행, y열) 
 * * 한 칸에는 번호와 방향을 가진 물고기 1마리가 있다. 
 * * 1 <= 번호 <= 16 * 번호는 중복이 없다. 
 * * 8가지 방향(상하좌우, 대각선) 중 하나이다. 
 * * 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0,0)에 들어간다. 
 * * 상어의 방향은 (0,0)에 있던 물고기의 방향과 같다. 
 * * 
 * * [물고기의 이동] 
 * * 번호가 작은 물고기부터 이동한다. 
 * * 빈칸 or 다른 물고기가 있는 칸에 갈 수 있다. 
 * * 상어가 있거나 격자 바깥은 못 간다. 
 * * 이동할 수 있는 칸이 있을때까지 45도 반시계 방향으로 회전한다. 
 * * 이동할 수 있는 칸이 하나도 없으면 이동 x 
 * * 물고기가 다른 물고기가 있는 칸으로 가면 * 서로 위치를 바꾼다. 
 * * 
 * * [상어의 이동] 
 * * 한 번에 여러 개의 칸을 이동할 수 있다. 
 * * 물고기가 있는 칸으로 이동했다면 
 * * 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가진다. 
 * * 이동하는 중에 지나가는 칸에 있는 물고기는 안 먹는다. 
 * * 물고기가 없는 칸으로 이동 불가 
 * * 이동할 수 있는 칸이 없으면 
 * * 집으로 간다. 
 * * 
 * * 상어가 먹을 수 있는 물고기 번호의 합의 최댓값 구하기 */
class Main {
    static class Shark {
        int x, y, dir, eatSum;

        Shark() { }

        Shark(int x, int y, int dir, int eatSum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }

    static class Fish {
        int x, y, id, dir;
        boolean isAlive = true;

        Fish() { }

        Fish(int x, int y, int id, int dir, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }
    
    // 0 부터 7 까지 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[4][4];
        List<Fish> fishes = new ArrayList<>();

        // input
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                Fish f = new Fish();
                f.id = Integer.parseInt(st.nextToken());
                f.dir = Integer.parseInt(st.nextToken()) - 1;
                f.x = i;
                f.y = j;
                
                fishes.add(f);
                arr[i][j] = f.id;
            }
        }

        // 물고기는 작은 순서부터 이동해야 하기 때문에 미리 정렬해둠
        Collections.sort(fishes, new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return o1.id - o2.id;
            }
        });

        // 상어는 (0, 0) 물고기를 먹고 시작하며 위치는 -1 로 표현
        Fish f = fishes.get(arr[0][0] - 1);
        Shark shark = new Shark(0, 0, f.dir, f.id);
        f.isAlive = false;
        arr[0][0] = -1;
        
        // solution
        dfs(arr, shark, fishes);
        System.out.println(maxSum);
    }

    // 재귀로 상어가 이동할 수 없을 때까지 반복한다.
    static void dfs(int[][] arr, Shark shark, List<Fish> fishes) {
        // 잡아먹은 양의 최대값 구하기
        if (maxSum < shark.eatSum) {
            maxSum = shark.eatSum;
        }

        // 모든 물고기 순서대로 이동
        fishes.forEach(e -> moveFish(e, arr, fishes));
        
        for (int dist = 1; dist < 4; dist++) {
            int nx = shark.x + dx[shark.dir] * dist;
            int ny = shark.y + dy[shark.dir] * dist;
    
            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > 0) {
                // 물고기 잡아먹고 dfs
                int[][] arrCopies = copyArr(arr);
                List<Fish> fishCopies = copyFishes(fishes);
                
                arrCopies[shark.x][shark.y] = 0;
                Fish f = fishCopies.get(arr[nx][ny] - 1);
                Shark newShark = new Shark(f.x, f.y, f.dir, shark.eatSum + f.id);
                f.isAlive = false;
                arrCopies[f.x][f.y] = -1;
                
                dfs(arrCopies, newShark, fishCopies);
            }
        }
    }
    
    // 이동가능한 칸은 빈칸, 다른 물고기가 있는 칸
    // 45도 반시계 방향으로 회전하면서 스캔
    static void moveFish(Fish fish, int[][] arr, List<Fish> fishes) {
        if (fish.isAlive == false) return;

        for (int i = 0; i < 8; i++) {
            int nextDir = (fish.dir + i) % 8;
            int nx = fish.x + dx[nextDir];
            int ny = fish.y + dy[nextDir];

            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > -1) {
                arr[fish.x][fish.y] = 0;
                
                if (arr[nx][ny] == 0) {
                    fish.x = nx;
                    fish.y = ny;
                } else {
                    Fish temp = fishes.get(arr[nx][ny] - 1);
                    temp.x = fish.x;
                    temp.y = fish.y;
                    arr[fish.x][fish.y] = temp.id;

                    fish.x = nx;
                    fish.y = ny;
                }

                arr[nx][ny] = fish.id;
                fish.dir = nextDir;
                return;
            }
        }
    }

    static int[][] copyArr(int[][] arr) {
        int[][] temp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        return temp;
    }

    static List<Fish> copyFishes(List<Fish> fishes) {
        List<Fish> temp = new ArrayList<>();
        fishes.forEach(e -> temp.add(new Fish(e.x, e.y, e.id, e.dir, e.isAlive)));
        return temp;
    }
}
