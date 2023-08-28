import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Solution {

	static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static List<Node> cores;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int n;		// 멕시노스의 크기
	static int[][] arr;
	static int answer;
	static int maxCnt;		// 코어의 최대 개수
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());		// 테스트 케이스 개수
		
		for(int t=1; t<=tc; t++) {
			answer = 0;
			n = Integer.parseInt(br.readLine());	// 멕시노스의 크기
			
			/*
			 * 멕시노스의 초기 상태를 입력받기
			 * 0: 빈 cell
			 * 1: core
			 */
			cores = new ArrayList<Node>();		// 코어를 담은 리스트
			arr = new int[n][n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 1 && i != 0 && j != 0) {	// 바깥에 있는 코어를 제외하고 리스트에 담기
						cores.add(new Node(i, j));
					}
				}
			}
			maxCnt = 0;
			dfs(0, 0, 0);
			
			bw.append("#").append(String.valueOf(t)).append(" ").append(String.valueOf(answer)).append("\n");
		}
		bw.flush();		// 모든 테스트 케이스 정답 출력
	}
	
	/* 
	 * cnt = 최대 코어 개수
	 * length = 전선의 길이의 합
	 * idx = 코어 인덱스
	 */
	public static void dfs(int cnt, int length, int idx) {
		if(cnt > maxCnt) {
			maxCnt = cnt;		// 최대 코어의 개수 갱신
			answer = length;	// 최소 전선의 길이 합 갱신 
		}else if(cnt == maxCnt) {
			answer = Math.min(answer, length);
		}
		
		if(idx == cores.size()) {		// 코어 리스트의 사이즈를 넘지 않도록 한다.
			return;
		}
		
		for(int i=0; i<4; i++) {
			int distance = getDistance(cores.get(idx).x, cores.get(idx).y, i);		// 전선의 길이를 구한다
			if(distance == -1) {		// 다른 코어와 전선이 겹침	
				continue;
			}
			
			dfs(cnt + 1, length + distance, idx + 1);
			rollBack(cores.get(idx), i);			// 전선을 다시 걷기
		}
		
		dfs(cnt, length, idx + 1);
	}
	
	/*
	 * 전선의 길이 구하기
	 * d: 방향
	 */
	public static int getDistance(int x, int y, int d) {
		int result = 0;
		int nx = x;
		int ny = y;
		
		/* 전선을 연결할 수 있는지 여부 */
		while(true) {
			nx += dx[d];
			ny += dy[d];
			
			if(!isValid(nx, ny)) {		// 범위를 넘었다.
				break;
			}
			
			if(arr[nx][ny] != 0) {		// 다른 코어와 전선이 겹침 또는 다른 코어 위치에 있음
				return -1;
			}
		}
		
		nx = x;
		ny = y;
		
		while(true) {
			nx += dx[d];
			ny += dy[d];
			
			if(!isValid(nx, ny)) {		// 범위를 넘었다.
				break;
			}
			
			arr[nx][ny] = 2;		// 전선을 까는 작업
			result++;		
		}
		return result;		// 전선의 길이 반환
	}
	
	/*
	 * 전선을 걷어서 원상복구
	 * pos = 코어 위치
	 * d = 방향
	 */
	public static void rollBack(Node pos, int d) {
		int nx = pos.x;
		int ny = pos.y;
		while(true) {
			nx += dx[d];
			ny += dy[d];
			
			if (!isValid(nx, ny)) {
				break;
			}
			
			arr[nx][ny] = 0;		// 전선을 걷는 작업
			
		}
	}
	
	public static boolean isValid(int nx, int ny) {
		return 0 <= nx && nx < n && 0 <= ny && ny < n;		// 유효성 검사 성공
	}
}
