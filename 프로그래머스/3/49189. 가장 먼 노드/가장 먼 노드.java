/*
    N개의 노드가 있는 그래프(1번부터 N번까지 번호가 있다.)
    1번 노드에서 가장 멀리 떨어진 노드의 갯수 구하기
    최단 거리로 이동했을 때 간선의 개수가 가장 많은 노드
    간선은 양방향 연결
*/
import java.util.*;

class Solution {
    public int bfs(int n, List<Integer>[] connected) {
        Queue<Integer> que = new LinkedList();
        boolean[] visit = new boolean[n + 1];
        int answer = 0;
        que.offer(1);
        visit[1] = true;
        while(!que.isEmpty()) {
            int size = que.size();
            int nodeCnt = 0;    
            for(int cnt=0; cnt<size; cnt++) {
                Integer cur = que.poll();
                // cur과 연결된 i 노드
                for(int i : connected[cur]) {
                    if(visit[i]) continue;      // 방문했으면
                    visit[i] = true;
                    que.offer(i);
                    nodeCnt++;
                }
            }
            if(nodeCnt == 0) continue;
            answer = nodeCnt;
        }
        return answer;
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<Integer>[] connected = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) {
            connected[i] = new ArrayList();
        }
        for(int[] e : edge) {
            connected[e[0]].add(e[1]);
            connected[e[1]].add(e[0]);
        }
        
        return bfs(n, connected);
    }
}