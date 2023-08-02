import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Solution
{    
    static int[][] arr;
    static int[][] visit;
    static int x, y;
    /** 좌우상 */
    static int[] dx = {0, 0, -1};
    static int[] dy = {-1, 1, 0};
    
    public static void main(String args[]) throws Exception
    {    
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int num;                // 테스트 케이스 번호
        arr = new int[100][100];
        visit = new int[100][100];            // 방문 처리 배열
        
        for(int t=0; t<10; t++) {        // 10개의 테스트 케이스
            num = Integer.parseInt(bf.readLine());        // 테스트 케이스 번호
            
            /** 100*100 배열에 값 입력받기 */
            for(int i=0; i<100; i++) {
                st = new StringTokenizer(bf.readLine());    // 한 줄씩 입력받기
                for(int j=0; j<100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] == 2) {        // 도착지점의 좌표 찾기
                        x = i;
                        y = j;
                    }
                }
            }
            
            visit = new int[100][100];
            
            System.out.printf("#%d ", t+1);
            bfs();
        }
    }
    
    public static boolean bfs() {
        Deque<int[]> que = new LinkedList();
        int[] pos = {x, y};        // x, y 좌표를 담는 배열
        que.add(pos);            // 시작 좌표를  추가
        int nx, ny;
        int leftY, rightY;        // 좌우
        boolean flag;
        
        while(!que.isEmpty()) {        // 큐에 원소가 있을 동안 반복
            flag = false;            // 좌 또는 우에 사다리 경로가 있는지
            pos = que.pollFirst();        // 맨 앞 원소를 뽑기
            
            visit[pos[0]][pos[1]] = 1;        // 방문 처리
            
            if(pos[0] == 0) {        // 도착했다면
                System.out.printf("%d\n", pos[1]);
                return true;
            }
            
            leftY = pos[1] - 1;
            rightY = pos[1] + 1;
            
            if(!validLeft(leftY) && arr[pos[0]][leftY] == 1 && visit[pos[0]][leftY] != 1) {        // 인덱스 범위 내에 있고, 좌로 사다리 경로가 있음
                pos[1] = leftY;
                que.add(pos);
                flag = true;
            }
            
            if(!validRight(rightY) && arr[pos[0]][rightY] == 1 && visit[pos[0]][rightY] != 1) {        // 인덱스 범위 내에 있고, 우로 사다리 경로가 있음
                pos[1] = rightY;
                que.add(pos);
                flag = true;
            }
            
            if(!validLeft(pos[0]-1) && !flag){
                pos[0] -= 1;
                que.add(pos);        // 직진
            }            
        }
        return false;
    }
    
    /*
     * 왼쪽 검증
     */
    public static boolean validLeft(int y) {
        if(y < 0) {
            return true;
        }
        return false;
    }
    
    /*
     * 오른쪽 검증
     */
    public static boolean validRight(int y) {
        if(y >= 100) {
            return true;
        }
        return false;
    }
    
}
