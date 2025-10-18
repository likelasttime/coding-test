import java.util.*;

class Solution {
    /*
        올바른 괄호 문자열이 맞으면, true 반환
    */
    public boolean check(String s) {
        Stack<Character> stack = new Stack();
        int n = s.length();
        for(int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                stack.push(ch);
            } else {
                if(stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        if(!stack.isEmpty()) {
            return false;
        }
        return true;
    }
    
    public String dfs(String w) {
        if(w.equals("")) {
            return w;
        }
        // 분리하기
        StringBuilder sb = new StringBuilder();
        int openCnt = 0;
        int closeCnt = 0;
        int idx = 0;
        for(int i=0; i<w.length(); i++) {
            sb.append(w.charAt(i));
            if(w.charAt(i) == '(') {
                openCnt++;
            } else {
                closeCnt++;
            }
            idx++;
            if(openCnt == closeCnt) {       // 균형잡힌 괄호 문자열을 만들었다면
                break;
            }
        }
        String u = sb.toString();
        sb = new StringBuilder();
        for(int i=idx; i<w.length(); i++) {
            sb.append(w.charAt(i));
        }
        String v = sb.toString();
        
        if(check(u)) {      // 올바른 괄호 문자열이면
            return u + dfs(v);
        } else {
            sb = new StringBuilder();
            sb.append("(");
            sb.append(dfs(v));
            sb.append(")");
            // 첫 번째와 마지막 문자를 제거하고
            for(int i=1; i<u.length()-1; i++) {
                // 나머지 문자열의 괄호 방향을 뒤집어 뒤에 붙이기
                if(u.charAt(i) == '(') {
                    sb.append(')');
                } else {
                    sb.append('(');
                }
            }
            return sb.toString();
        }
    }
    
    public String solution(String p) {
        String answer = "";
        if(check(p)) {
        	return p;
        }
        return dfs(p);
    }
}