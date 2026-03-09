/*
    항상 "ICN" 공항에서 출발한다.
    주어진 항공권을 모두 이용하여 여행경로를 짜야한다.
    가능한 경로가 2개 이상이면
        알파벳 순서가 앞서는 경로를 구한다.
        
    [풀이]
    "ICN"에서 출발하는 DFS 탐색
    경로를 오름차순 정렬 후 배열로 변환하여 반환
*/
import java.util.*;

class Solution {
    boolean[] visited;
    List<String> answers;
    public String[] solution(String[][] tickets) {
        answers=new LinkedList<>();
        visited=new boolean[tickets.length];
        dfs(tickets, "ICN", "ICN", 0); // 티켓 배열, 출발, 경로, 갯수
        Collections.sort(answers);
        String[] ans = answers.get(0).split(" ");       // 공백 제거
        return ans;
    }
    
    /*
        tickets: 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다.
        start: 출발지 
        str: 경로 
        cnt: 사용한 티켓 수
    */
    public void dfs(String[][] tickets, String start, String str, int cnt){
        if(cnt == tickets.length){      // 모든 티켓을 다 사용했다면
            answers.add(str);
            return;    
        }
        
        for(int i=0; i<tickets.length; i++){
            if(visited[i]) continue;    // 사용한 티켓이라면
            if(start.equals(tickets[i][0])){
                visited[i] = true;
                dfs(tickets, tickets[i][1], str + " " + tickets[i][1], cnt + 1);
                visited[i] = false;    
            }       
        }  
    }
}