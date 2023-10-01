import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static class Shark implements Comparable<Shark> {
		int x;
		int y;
		int nearFriends; // 주변에 있는 좋아하는 친구 수
		int emptyCnt; // 주변 빈칸 수

		public Shark(int x, int y, int nearFriends, int emptyCnt) {
			this.x = x;
			this.y = y;
			this.nearFriends = nearFriends;
			this.emptyCnt = emptyCnt;
		}

		@Override
		public int compareTo(Shark shark) {
			if (nearFriends != shark.nearFriends) {
				return (nearFriends - shark.nearFriends) * -1;
			}

			if (emptyCnt != shark.emptyCnt) {
				return (emptyCnt - shark.emptyCnt) * -1;
			}

			if (x != shark.x) {
				return x - shark.x;
			}

			return y - shark.y;
		}
	}

	static int n; // 교실의 크기
	static int[][] sharkClass;		// 상어들에게 자리를 배정하는 배열(값=상어 번호)
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Map<Integer, List<Integer>> hashMap;		// 상어 번호 : 상어와 친한 친구 4명의 번호 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine()); // 교실의 크기 3 <= n <= 20
		sharkClass = new int[n][n]; // 자리배치
		hashMap = new HashMap<Integer, List<Integer>>(); // 내 번호 : 좋아하는 친구 4명 번호 배열
		int answer = 0; // 학생의 만족도의 총 합
		int[] orderStudents = new int[n * n];		// 자리를 배정받는 상어 번호 순서

		/* 학생의 번호와 그 학생이 좋아하는 학생 4명의 번호가 주어짐 */
		for (int i = 0; i < n * n; i++) {
			List<Integer> students = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			int myNum = Integer.parseInt(st.nextToken()); // 당사자 번호
			for (int j = 0; j < 4; j++) {
				students.add(Integer.parseInt(st.nextToken()));
			}
			hashMap.put(myNum, students);
			orderStudents[i] = myNum; // 입력으로 주어진 학생의 번호 순서(자리를 배정받는 순서)
		}

		for (int i = 0; i < n * n; i++) {
			Shark resultShark = getPos(orderStudents[i]);		// 상어를 앉힐 자리 구하기
			sharkClass[resultShark.x][resultShark.y] = orderStudents[i];		// 상어에게 자리 배정
		}

		/* 학생의 만족도의 총 합 구하기 */
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int total = getNearLikeTotal(i, j, sharkClass[i][j]);
				switch (total) {
				case 1:
					answer += 1;
					break;

				case 2:
					answer += 10;
					break;

				case 3:
					answer += 100;
					break;

				case 4:
					answer += 1000;
					break;
				}
			}
		}

		System.out.println(answer); // 학생의 만족도의 총 합

	}

	public static boolean isValid(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n; // 인덱스가 범위 내에 있음
	}

	/*
	 * num번 상어의 자리 찾기
	 */
	public static Shark getPos(int num) {
		Shark shark = null;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (sharkClass[i][j] > 0) { // 다른 상어가 이미 자리 잡아서 이 자리는 선택 불가
					continue;
				}
				Shark tempShark = new Shark(i, j, getNearLikeCnt(i, j, num), getNearEmptyCnt(i, j));
				if (shark == null) {
					shark = tempShark;
					continue;
				}
				if (shark.compareTo(tempShark) > 0) {
					shark = tempShark;
				}
			}
		}
		return shark;
	}

	public static int getNearLikeCnt(int x, int y, int num) {
		/* 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다. */
		int likeCnt = 0;
		for (int d = 0; d < 4; d++) { 
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!isValid(nx, ny)) { // 인덱스 유효성 검사
				continue;
			}
			if (hashMap.get(num).contains(sharkClass[nx][ny])) {		// 앉아있는 상어가 num 상어와 친하다.
				likeCnt++;
			}
		}
		return likeCnt;
	}

	/*
	 * 인근에 빈 칸의 갯수 세기
	 */
	public static int getNearEmptyCnt(int i, int j) {
		int nearCnt = 0;

		for (int d = 0; d < 4; d++) {
			int nx = i + dx[d];
			int ny = j + dy[d];
			if (!isValid(nx, ny)) { // 인덱스 유효성 검사
				continue;
			}
			if (sharkClass[nx][ny] != 0) {
				continue; // 비어있는 칸이 아니다.
			}
			nearCnt++;
		}
		return nearCnt;
	}

	/*
	 * 인근에 좋아하는 친구의 갯수를 센다.
	 */
	public static int getNearLikeTotal(int i, int j, int num) {
		int cnt = 0;

		for (int d = 0; d < 4; d++) {
			int nx = i + dx[d];
			int ny = j + dy[d];

			if (!isValid(nx, ny)) {
				continue;
			}

			if (hashMap.get(num).contains(sharkClass[nx][ny])) {	// 앉아있는 sharkClass[nx][ny]는  num 상어가 좋아하는 상어다.
				cnt++;
			}
		}

		return cnt;
	}

}