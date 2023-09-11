import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

/*
* <pre>
* [ 아디이어]
* HashSet을 사용해서 첫 번째 집합의 문자열들을 담는다.
* 두 번째 집합의 문자열들을 HashSet에 담을 때는 이미 있는 문자열인지 확인하고 있다면, 카운트가 증가한다.
* </pre>
*/

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int answer = 0; // 두 집합에 모두 속하는 문자열 원소의 개수

			// 두 집합의 원소의 갯수를 나타내는 두 자연수
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			HashSet<String> hashSet = new HashSet(); // 중복을 허용하지 않음

			// 첫 번째 집합 입력받기
			for (int i = 0; i < n; i++) {
				String s = st.nextToken();
				hashSet.add(s);
			}

			// 두 번째 집합 입력받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				String s = st.nextToken();
				if (hashSet.contains(s)) { // 이미 있는 문자열이면
					answer++;
				}
			}

			bw.append("#").append(String.valueOf(test_case)).append(" ").append(String.valueOf(answer)).append("\n");
		}
		bw.flush();
	}
}
