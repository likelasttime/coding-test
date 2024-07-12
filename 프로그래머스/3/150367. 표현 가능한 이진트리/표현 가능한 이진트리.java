class Solution {
    static int flag;        // 이진트리로 해당 수를 표현할 수 있는지 여부
    static String binaryNum;    // 이진수
    static boolean[] isRealNode;    // 포화 이진 트리의 길이만큼 생성된 배열로서 더미 노드면, false
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            binaryNum = Long.toBinaryString(numbers[i]);     // 이진수로 변환
            flag = 1;       // 초기값(이진트리로 해당 수를 표현 가능)
            
            /*
                포화 이진 트리의 노드 수 구하기
            */
            int fullTreeCnt = 1;
            int x = 1;
            while(fullTreeCnt < binaryNum.length()) {
                fullTreeCnt = (int)Math.pow(2, x++) - 1;
            }
            
            isRealNode = new boolean[fullTreeCnt];
            int idx = fullTreeCnt - binaryNum.length();     // 더미 노드를 제외하고 시작하는 인덱스
            
            for(int j=0; j<binaryNum.length(); j++) {
                isRealNode[idx++] = binaryNum.charAt(j) == '1';      // 더미 노드가 아니면, true
            }      
            
            dfs(0, fullTreeCnt - 1, false);            
            answer[i] = flag;
        }
        return answer;
    }
    
    /*
        시작점, 도착점, 원래 존재하는 노드(단말 노드 아님)면 true
    */
    public void dfs(int start, int end, boolean check) {
        int mid = (start + end) / 2;
        if(isRealNode[mid] && check) {      // 루트가 단말 노드라면
            flag = 0;       // 이진트리로 해당 수를 표현 불가능
            return;
        }
        if(start != end) {              // 시작 인덱스와 마지막 인덱스가 같지 않아야 함
            dfs(start, mid - 1, !isRealNode[mid]);        // 왼쪽 탐색
            dfs(mid + 1, end, !isRealNode[mid]);          // 오른쪽 탐색
        }
    }
}