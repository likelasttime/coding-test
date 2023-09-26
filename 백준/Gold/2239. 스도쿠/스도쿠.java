import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	static List<Pos> zeroList;
	static int[][] arr;

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
		arr = new int[9][9];
		zeroList = new ArrayList<Pos>();

		for (int i = 0; i < 9; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				arr[i][j] = str.charAt(j) - '0'; // int형으로 변환해서 저장
				if (arr[i][j] == 0) { // 빈칸
					zeroList.add(new Pos(i, j)); // 위치 추가
				}
			}
		}

		dfs(0);

	}

	public static boolean[] checkRow(boolean[] visit, int col) {
		for (int i = 0; i < 9; i++) {
			visit[arr[i][col]] = true;
		}
		return visit;
	}

	public static boolean[] checkCol(boolean[] visit, int row) {
		for (int i = 0; i < 9; i++) {
			visit[arr[row][i]] = true;
		}
		return visit;
	}

	public static boolean[] checkMatrix(boolean[] visit, int x, int y) {
		int startX = (x / 3) * 3;
		int startY = (y / 3) * 3;

		for (int i = startX; i < startX + 3; i++) {
			for (int j = startY; j < startY + 3; j++) {
				visit[arr[i][j]] = true;
			}
		}
		return visit;
	}

	public static void dfs(int depth) {
		if (depth == zeroList.size()) { // 모두 채움
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}

		boolean[] visit = new boolean[10];

		visit = checkRow(visit, zeroList.get(depth).y);
		visit = checkCol(visit, zeroList.get(depth).x);
		visit = checkMatrix(visit, zeroList.get(depth).x, zeroList.get(depth).y);

		for (int i = 1; i <= 9; i++) {
			if (!visit[i]) {
				arr[zeroList.get(depth).x][zeroList.get(depth).y] = i;
				dfs(depth + 1);
				arr[zeroList.get(depth).x][zeroList.get(depth).y] = 0;
			}
		}
	}
}