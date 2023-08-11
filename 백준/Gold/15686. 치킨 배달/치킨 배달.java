import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Main
{
	static StringBuilder sb = new StringBuilder();
	static int n; // 배열의 크기
	static int m; // 치킨집의 최대 개수
	static int[][] arr;
	static int answer = 50000;
	static List<int[]> city = new LinkedList(); // 도시 인덱스
	static List<int[]> house = new LinkedList(); // 집 인덱스
	static int[] chicken; // 치킨이 있는 인덱스

	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());		// 배열의 크기
		m = Integer.parseInt(st.nextToken());		// 치킨집의 최대 개수

		/**
		 * 도시의 정보를 입력 받기
		 * 0: 빈칸
		 * 1: 집
		 * 2: 치킨집
		 */
		arr = new int[n][n];
		chicken = new int[m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) { // 치킨집을 찾았다면
					city.add(new int[] { i, j });
				} else if (arr[i][j] == 1) { // 집을 찾았다면
					house.add(new int[] { i, j });
				}
			}
		}
	
		combi(0, 0);

		sb.append(answer);
		System.out.println(sb); // 폐업시키지 않을 치킨집을 최대 M개를 골랐을 때 치킨 거리의 최솟값을 출력

	}

	/**
	 * 치킨을 m개를 고른다. 치킨을 많이 고를수록 최소 거리를 얻을 수 있다!
	 * @param start 값을 뽑을 때 시작할 인덱스 위치
	 * @param cnt 고른 치킨의 갯수
	 */
	public static void combi(int start, int cnt) {
		if (cnt == m) {		// 치킨을 m개 고름
			getDistance(); // 모든 집과 치킨 거리의 합 계산
			return;
		}

		for (int i = start; i < city.size(); i++) {
			chicken[cnt] = i;
			combi(i + 1, cnt + 1);
			chicken[cnt] = 0;
		}
	}

	/**
	 * 집과 치킨의 거리 계산
	 * 집에서 가장 가까운 치킨과의 거리를 구한 후 합을 구한다.
	 */
	public static void getDistance() {
		int result = 0;		

		for (int i = 0; i < house.size(); i++) { // 집 인덱스
			int total = 5000;	// 집에서 가장 가까운 치킨까지의 거리
			int tmp = 5000;		// 치킨과 집의 거리
			for (int j = 0; j < m; j++) { // 치킨 인덱스
				tmp = Math.abs(house.get(i)[0] - city.get(chicken[j])[0])
						+ Math.abs(house.get(i)[1] - city.get(chicken[j])[1]);
				total = Math.min(total, tmp);	// 집에서 가장 가까운 치킨까지의 거리
			}
			result += total;		// 모든 집과 치킨까지의 거리의 합
		}

		answer = Math.min(answer, result);	// 모든 집과 치킨까지의 거리의 합을 최소값으로 갱신
	}

}