import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int n; // 1 <= 땅의 크기 <= 50
	static int l; // 1 <= ㅣ <= 100
	static int r; // 1 <= r <= 100
	static int[][] arr; // n*n 크기의 땅
	static boolean[][] visit;

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int answer = 0; // 인구 이동이 발생한 일수
		n = Integer.parseInt(st.nextToken()); // 땅의 크기
		l = Integer.parseInt(st.nextToken()); // 두 나라의 인구 차이의 최소값
		r = Integer.parseInt(st.nextToken()); // 두 나라의 인구 차이의 최대값
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			boolean flag = false;
			visit = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visit[i][j]) {
						continue;
					}
					if (bfs(i, j)) { // 연합한 칸이 있어야 인구 이동 발생 일수가 올라감
						flag = true;
					}
				}
			}
			if (!flag) {		// 인구 이동이 발생하지 않았다.
				break;
			}
			answer++;
		}

		System.out.println(answer);
	}

	public static boolean bfs(int startX, int startY) {
		Deque<Pos> deque = new ArrayDeque<Pos>();
		List<Pos> list = new ArrayList<Pos>(); // 연합한 좌표 리스트
		visit[startX][startY] = true;
		list.add(new Pos(startX, startY));
		deque.offer(new Pos(startX, startY));
		int total = arr[startX][startY]; // 연합의 인구 수 총합
		int cnt = 1; // 연합한 칸의 수

		while (!deque.isEmpty()) {
			Pos pos = deque.pollFirst(); // 첫 번째 원소를 꺼내기

			int x = pos.x;
			int y = pos.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (!isValid(nx, ny) || visit[nx][ny]) { // 인덱스 범위가 유효하지 않거나 방문한 곳이면 건너띄기
					continue;
				}

				int diff = Math.abs(arr[nx][ny] - arr[x][y]);
				if (l <= diff && diff <= r) { 
					deque.offer(new Pos(nx, ny));
					cnt++; // 연합한 칸의 수 증가
					list.add(new Pos(nx, ny));
					visit[nx][ny] = true; // 방문 처리
					total += arr[nx][ny]; // 연합의 인구 수 추가
				}

			}
		}

		if (cnt == 1) { // 연합을 맺지 못함
			return false;
		}

		move(list, total / cnt); // 연합을 이룬 칸들에 (연합의 인구수)/(연합을 이루고 있는 칸의 개수)를 넣기

		return true;
	}

	/*
	 * 인구 이동 
     * list = 연합을 이룬 칸들의 좌표를 담은 리스트 
     * value = 연합을 이루고 있는 각 칸의 인구수
	 */
	public static void move(List<Pos> list, int value) {
		for (Pos pos : list) {
			arr[pos.x][pos.y] = value;
		}
	}

	public static boolean isValid(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n; // (x, y)가 유효한 좌표
	}

}