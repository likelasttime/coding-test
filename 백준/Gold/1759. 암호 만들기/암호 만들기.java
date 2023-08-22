import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int l; // 알파벳의 갯수
	static int c; // 문자의 종류
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean visit[]; // 문자열을 선택했는지 방문 체크 배열
	static int[] alphabet; // 입력으로 주어지는 알파벳을 담는 배열
	static String[] tmp; // 조합을 담을 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		l = Integer.parseInt(st.nextToken()); // 알파벳의 갯수
		c = Integer.parseInt(st.nextToken()); // 문자의 종류

		/*
		 * c개의 후보 문자를 입력받기
		 */
		alphabet = new int[c];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			alphabet[i] = st.nextToken().toCharArray()[0] - 'a'; // 아스키코드 차이 값을 배열에 저장
		}

		Arrays.sort(alphabet); // 사전순으로 출력해야하므로 정렬
		visit = new boolean[26];
		tmp = new String[l];
		dfs(0, 0);

		bw.flush();
	}

	/*
	 * 조합 구현 cnt = 지금까지 생성한 문자열의 길이 start = 시작할 문자열의 위치 인덱스
	 */
	public static void dfs(int cnt, int start) throws IOException {
		if (cnt == l) { // 길이 l만큼 문자열을 완성
			int vowels = 0; // 모음 갯수
			int consonant = 0; // 자음 갯수
			for (int i = 0; i < l; i++) {
				if (tmp[i].equals("a") || tmp[i].equals("e") || tmp[i].equals("i") || tmp[i].equals("o")
						|| tmp[i].equals("u")) {
					vowels++; // 모음 갯수 증가
				} else {
					consonant++; // 자음 갯수 증가
				}
			}
			if (vowels > 0 && consonant > 1) { // 모음이 1개 이상이고 자음이 2개 이상
				for (int i = 0; i < l; i++) {
					bw.append(tmp[i]);
				}
				bw.append("\n");
			}
			return;
		}

		for (int i = start; i < c; i++) {
			if (!visit[alphabet[i]]) { // 아직 고르지 않은 문자열이면
				tmp[cnt] = String.valueOf((char) (alphabet[i] + 97)); // 소문자로 변환해서 저장
				visit[alphabet[i]] = true; // 선택함
				dfs(cnt + 1, i + 1);
				visit[alphabet[i]] = false; // 선택 해제
			}

		}
	}

}
