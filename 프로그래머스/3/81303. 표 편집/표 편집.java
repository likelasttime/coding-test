import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] deleted = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        // 초기 연결 설정
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1; // 마지막 행은 next가 없음

        int cur = k; // 현재 선택된 행 인덱스

        for (String c : cmd) {
            char type = c.charAt(0);

            if (type == 'U') { // 위로 이동
                int x = Integer.parseInt(c.substring(2));
                for (int i = 0; i < x; i++) cur = prev[cur];
            } 
            else if (type == 'D') { // 아래로 이동
                int x = Integer.parseInt(c.substring(2));
                for (int i = 0; i < x; i++) cur = next[cur];
            } 
            else if (type == 'C') { // 삭제
                stack.push(cur);
                deleted[cur] = true;

                if (prev[cur] != -1) next[prev[cur]] = next[cur];
                if (next[cur] != -1) prev[next[cur]] = prev[cur];

                cur = (next[cur] != -1) ? next[cur] : prev[cur];
            } 
            else if (type == 'Z') { // 복구
                int restore = stack.pop();
                deleted[restore] = false;

                if (prev[restore] != -1) next[prev[restore]] = restore;
                if (next[restore] != -1) prev[next[restore]] = restore;
            }
        }

        // 결과 문자열 생성
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(deleted[i] ? 'X' : 'O');
        }

        return sb.toString();
    }
}
