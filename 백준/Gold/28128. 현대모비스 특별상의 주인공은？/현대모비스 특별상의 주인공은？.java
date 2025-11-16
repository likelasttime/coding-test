import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력 받기
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        String[][] board = new String[n][m];
        
        // 보드 초기화
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<m; j++) {
        		board[i][j] = st.nextToken();
        	}
        }
        
        Set<String> tmp = new HashSet<>();
        
        // 이동 방향 설정 (우, 좌, 하, 상)
        int[][] move = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        
        // 첫 번째 경우 (인접한 곳에 같은 값이 있을 때)
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (!tmp.contains(board[x][y])) {
                    for (int i = 0; i < 4; i++) {
                        int nx = x + move[i][0];
                        int ny = y + move[i][1];
                        
                        if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                            if (board[nx][ny].equals(board[x][y])) {
                                tmp.add(board[x][y]);
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        // 두 번째 경우 (세로로 같은 값이 3칸 이상 있을 때)
        if (n >= 3) {
        	for(int x=0; x<n-2; x++) {
        		for (int y = 0; y < m; y++) {
                    if (!tmp.contains(board[x][y])) {
                        if (board[x][y].equals(board[x + 2][y])) {
                            tmp.add(board[x][y]);
                        }
                    }
                }
            }
        }
        
        // 세 번째 경우 (가로로 같은 값이 3칸 이상 있을 때)
        if (m >= 3) {
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m - 2; y++) {
                    if (!tmp.contains(board[x][y])) {
                        if (board[x][y].equals(board[x][y + 2])) {
                            tmp.add(board[x][y]);
                        }
                    }
                }
            }
        }
        
        // 결과 출력
        List<String> res = new ArrayList<>(tmp);
        Collections.sort(res);
        
        if (res.isEmpty()) {
            System.out.println("MANIPULATED");
        } else {
        	StringBuilder sb = new StringBuilder();
            for (String name : res) {
                sb.append(name + "\n");
            }
            System.out.print(sb.toString());
        }
    }
}
