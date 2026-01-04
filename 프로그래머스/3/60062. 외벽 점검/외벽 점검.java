import java.util.*;
import java.io.*;
/*
    외벽의 총 둘레는 n미터
    레스토랑의 정북 방향 지점을 0으로 나타낸다.
    취약 지점의 위치는 정북 방향 지점으로부터 시계 방향으로 떨어진 거리로 나타난다.
    친구들은 출발 지점부터 시계, 반시계 방향으로 외벽을 따라서만 이동

    취약 지점을 점검하기 위해 보내야 하는 친구의 수의 최소값을 구하기
    친구들을 모두 투입해도 취약 지점을 전부 점검할 수 없는 경우에 -1
*/
class Solution {
    List<List<Integer>> permutationLst = new ArrayList();
    
    public void permutation(int cnt, int[] dist, boolean[] visit, ArrayList<Integer> lst) {
        if(cnt == dist.length) {
            permutationLst.add((List)lst.clone());
            return;
        }    
        for(int i=0; i<dist.length; i++) {
            if(visit[i]) {
                continue;
            }
            lst.add(dist[i]);
            visit[i] = true;
            permutation(cnt + 1, dist, visit, lst);
            visit[i] = false;
            lst.remove(cnt);
        }
    }
    
    public int solution(int n, int[] weak, int[] dist) {
        int lenWeak = weak.length;
        int lenDist = dist.length;
        int answer = lenDist + 1;
        int[] doubleWeak = new int[lenWeak * 2];
        for(int i=0; i<lenWeak; i++) {
            doubleWeak[i] = weak[i];
        }
        for(int i=0; i<lenWeak; i++) {
            doubleWeak[i+lenWeak] = weak[i] + n;
        }
        
        permutation(0, dist, new boolean[lenDist], new ArrayList());
        for(int i=0; i<lenWeak; i++) {      // 시작점
            for(List<Integer> p : permutationLst) {
            	int friendCnt = 1;
            	int idx = 0;
            	int distance = p.get(idx) + weak[i];
            	for(int k=i; k<i+lenWeak; k++) {
            		if(distance < doubleWeak[k]) {
            			friendCnt++;
            			idx++;
            			if(friendCnt > lenDist) {
            				break;
            			}
            			distance = doubleWeak[k] + p.get(idx);
            		}
            	}
            	answer = Math.min(answer, friendCnt);
            }
        }
        return answer <= lenDist ? answer : -1;
    }
}