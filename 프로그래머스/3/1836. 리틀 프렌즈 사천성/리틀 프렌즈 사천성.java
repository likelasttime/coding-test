import java.util.TreeSet;
import java.util.Set;
import java.util.Arrays;

class Solution {
    static char[][] arr;
    static int[][] posX;
    static int[][] posY;

    public String solution(int m, int n, String[] board) {
        Set<Character> treeSet = new TreeSet();
        StringBuilder sb = new StringBuilder();
        arr = new char[m][n];
        posX = new int[26][2];     // 각 문자별로 x 좌표 저장
        posY = new int[26][2];     // 각 문자별로 y 좌표 저장
        
        // String -> char[]
        for(int i=0; i<m; i++) {        // 행
            arr[i] = board[i].toCharArray();
        }
        
        // 초기화
        for(int i=0; i<26; i++) {
            Arrays.fill(posX[i], -1);
            Arrays.fill(posY[i], -1);
        }
        
        // 문자별 위치 할당
        for(int x=0; x<m; x++) {        // 행
            for(int y=0; y<n; y++) {    // 열
                if(Character.isUpperCase(arr[x][y])) {      // 대문자가 맞다면
                    treeSet.add(arr[x][y]);     // 대문자 저장
                    int c = arr[x][y] - 'A';
                    if(posX[c][0] == -1) {     // 첫 번째로 등장한 문자라면
                        posX[c][0] = x;        // 첫 번째로 발견한 문자의 행 저장
                    } else {        // 같은 문자를 발견했을 때
                        posX[c][1] = x;        // 첫 번째로 발견한 문자의 행 저장
                    }
                    
                    if(posY[c][0] == -1) {     // 첫 번째로 등장한 문자라면
                        posY[c][0] = y;     // 첫 번째로 발견한 문자의 열 저장
                    } else {        // 같은 문자를 발견했을 때
                        posY[c][1] = y;        // 열 저장
                    }
                }
            }
        }
        
        while(!treeSet.isEmpty()) {     // 대문자를 모두 제거할 때까지
            boolean flag = false;
            int size = treeSet.size();

            for(char c : treeSet) {     // 오름차순 정렬된 순서대로 꺼내기
                if(check(c)) {
                    flag = true;
                    sb.append(c);       // 제거 후 문자 추가
                    treeSet.remove(c);  // 다음 번에 탐색하지 않아도 되니까 제거
                    break;
                }
            }

            if(!flag) {     // 제거 작업이 하나도 일어나지 않았다면
                break;
            }
        }
        return treeSet.size() > 0 ? "IMPOSSIBLE" : sb.toString();
    }
    
    public boolean check(char curChar) {
        boolean result = false;
        if(Character.isUpperCase(curChar)) {      // 대문자라면
            int curIdx = curChar - 'A';
            int x1 = posX[curIdx][0];
            int x2 = posX[curIdx][1];
            int y1 = posY[curIdx][0];
            int y2 = posY[curIdx][1];
                    
            // 조건을 충족하면 제거
            if(x1 != x2 && y1 != y2) {      // 꺾어야 할때
                if(checkX(x1, x2, y2, curChar) && checkY(y1, y2, x1, curChar)) {
                    arr[x1][y1] = '.';
                    arr[x2][y2] = '.';
                    result = true;
                }
                        
                if(checkX(x1, x2, y1, curChar) && checkY(y1, y2, x2, curChar)) {
                    arr[x1][y1] = '.';
                    arr[x2][y2] = '.';
                    result = true;
                }
            } else if(x1 == x2 && y1 != y2) {       // x좌표가 동일할 때
                if(checkY(y1, y2, x1, curChar)) {
                    arr[x1][y1] = '.';
                    arr[x2][y2] = '.';
                    result = true;
                }
            } else if(x1 != x2 && y1 == y2) {        // y좌표가 동일할 때
                if(checkX(x1, x2, y1, curChar)) {
                    arr[x1][y1] = '.';
                    arr[x2][y2] = '.';
                    result = true;
                }
            } 
        }
        return result;
    }
    
    public boolean checkX(int x1, int x2, int y, char c) {
        int start = Math.min(x1, x2);
        int end = Math.max(x1, x2);
        
        for(int i=start; i<=end; i++) {
            if(arr[i][y] != '.' && arr[i][y] != c) {        // 빈칸이 아니고, 같은 문자도 아니라면
                return false;        // 같은 문자가 아님
            }
        }
        
        return true;   // 같은 문자를 찾음
    }
    
    public boolean checkY(int y1, int y2, int x, char c) {
        int start = Math.min(y1, y2);
        int end = Math.max(y1, y2);
        
        for(int i=start; i<=end; i++) {
            if(arr[x][i] != '.' && arr[x][i] != c) {    // 빈칸이 아니고, 같은 문자도 아니라면
                return false;        // 같은 문자가 아님
            }
        }
        
        return true;
    }
}