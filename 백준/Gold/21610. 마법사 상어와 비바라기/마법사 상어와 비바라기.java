import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Cmd {
		int d; // 방향
		int s; // 거리

		public Cmd(int d, int s) {
			this.d = d;
			this.s = s;
		}
	}

	static class Cloud {
		int x;
		int y;

		public Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int n; // 격자 크기
	static int[][] a; // 격자
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static Queue<Cloud> cloud = new LinkedList<Cloud>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 2 <= n <= 50, 격자 크기
		int m = Integer.parseInt(st.nextToken()); // 1 <= m <= 100, 이동 명령 갯수

		a = new int[n][n];
		for (int x = 0; x < n; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < n; y++) {
				a[x][y] = Integer.parseInt(st.nextToken());
			}
		}

		// 이동 정보 입력 받기, d=방향, s=거리
		List<Cmd> cmdList = new ArrayList<Cmd>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()); // 방향
			int s = Integer.parseInt(st.nextToken()); // 거리
			cmdList.add(new Cmd(d - 1, s));
		}

		// 멸령 실행
		cloud.add(new Cloud(n - 1, 0));
		cloud.add(new Cloud(n - 1, 1));
		cloud.add(new Cloud(n - 2, 0));
		cloud.add(new Cloud(n - 2, 1));
		for (Cmd cmd : cmdList) {
			boolean[][] visit = move(cmd.d, cmd.s);
			increaseWater(visit);
			nextCloud(visit);
		}

		System.out.println(total());
	}

	/*
	 * 구름 이동 d = 방향 s = 거리
	 */
	public static boolean[][] move(int d, int s) {
		boolean[][] visit = new boolean[n][n];
		while (!cloud.isEmpty()) {
			Cloud curCloud = cloud.poll(); // 맨앞에 있는 구름 뽑기
			int nx = (curCloud.x + (dx[d] * s)) % n;
			int ny = (curCloud.y + (dy[d] * s)) % n;

			// 좌표가 음수라면
			if (nx < 0) {
				nx = n - Math.abs(nx % n);
			}
			if (ny < 0) {
				ny = n - Math.abs(ny % n);
			}

			a[nx][ny] += 1; // 구름이 있는 칸에 저장된 물의 양 증가
			visit[nx][ny] = true; // 물이 증가한 칸
		}
		return visit;
	}

	/*
	 * 물이 증가한 칸 (r,c)에서 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니 수만큼 (r,c)에 있는 바구니의 물의 양 증가
	 */
	public static void increaseWater(boolean[][] visit) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int cnt = 0;
				if (!visit[i][j])
					continue;
				for (int d = 1; d < 8; d += 2) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (!isValid(nx, ny)) { // 범위를 벗어나면 건너띄기
						continue;
					}
					if (a[nx][ny] > 0)
						cnt++;
				}
				a[i][j] += cnt; // 물이 들어있는 바구니의 갯수만큼 증가
			}
		}
	}

	/*
	 * 다음에 생성될 구름
	 */
	public static void nextCloud(boolean[][] visit) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j])
					continue; // 구름이 있었던 곳
				if (a[i][j] >= 2) {
					cloud.add(new Cloud(i, j));
					a[i][j] -= 2;
				}
			}
		}
	}

	public static boolean isValid(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}

	/*
	 * 바구니에 들어있는 물의 양의 합 구하기
	 */
	public static int total() {
		int total = 0;
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				total += a[x][y];
			}
		}
		return total;
	}
}