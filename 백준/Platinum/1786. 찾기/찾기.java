import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * KMP 알고리즘
 * 1. 패턴 문자열의 부분 일치 테이블 배열을 생성한다.
 * 패턴 문자열의 문자 길이가 2이상일 때부터 고려한다.
 * pi[i] = 0에서 인덱스 i까지 접두사와 접미사가 같은 최대 길이
 * 2. 패턴 문자열 찾기
 * 2-1) 두 문자가 다르면 이전 패턴 문자의 접두사와 접미사가 같은 최장 길이를 사용한다.
 * 2-2) 두 문자가 같을 때
 * 2-2-1) 패턴 문자열을 다 찾았으면, 패턴 문자열의 마지막 인덱스의 접두사와 접미사가 같은 최장 길이를 사용한다.
 * 2-2-2) 아직 패턴 문자열을 찾는 중이라면, 다음 패턴 문자를 찾을 수 있게 +1을 한다.
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int answer1 = 0;	// T 중간에 P가 몇 번 나타나는지를 나타내는 음이 아닌 정수
		List<Integer> answer2 = new ArrayList<Integer>();	// P가 나타나는 위치를 차례대로 공백으로 구분해서 출력한다.
		// t와 p는 1이상 100만 이하, 알파벳 대소문자와 공백으로만 이루어짐
		String t = br.readLine();
		String p = br.readLine();
		
		int n = t.length();			// 패턴 문자열을 찾을 대상이 되는 문자열의 길이
		int m = p.length();			// 패턴 문자열의 길이
		
		// 패턴 문자열 p의 부분일치 테이블 배열 만들기
		int[] pi = new int[m];
		int x = 0;		// 최장 길이
		for (int i=1; i<m; i++) {		// i=접두사와 접미사가 같은지 볼 범위, 길이 2이상부터 따질거라서 1부터 시작
			while (x > 0 && p.charAt(i) != p.charAt(x)) {		// 패턴 문자열의 길이가 2 이상이어야 한다.
				x = pi[x-1];		// 이전 최장 길이를 가져옴
			}
			if (p.charAt(i) == p.charAt(x)) {		
				pi[i] = ++x;		// 최장 길이 증가
			}
		}
		
		/*
		 * KMP 알고리즘
		 */
		int j = 0;		// 패턴 문자열의 인덱스
		for (int i=0; i<n; i++) {		// 패턴 문자열을 찾을 대상이 되는 문자열의 인덱스
			while (j > 0 && t.charAt(i) != p.charAt(j)) {	// 패턴 문자열의 길이가 2이상이 되어야 한다.
				j = pi[j-1];		// 이전까지의 접두사=접미사인 최대 길이
			}
			if (t.charAt(i) == p.charAt(j)) {		// 문자가 같으면
				if (j == m-1) {		// 패턴 문자열을 찾음
					answer1++;		// 찾은 패턴 문자열의 개수
					answer2.add(i-j+1);		// 패턴 문자열이 끝나는 인덱스 위치 - 패턴 문자열의 길이 + 1이 패턴 문자열이 시작되는 위치가 됨
					j = pi[j];				// j까지의 접두사와 접미사가 같은 최장 길이
				} else {		// 패턴 문자열에서 다음 문자 인덱스로 넘어가기
					j++;		// 패턴 문자열의 인덱스 증가
				}
			}
		}
		
		System.out.println(answer1);
		for (int ans : answer2) {
			bw.write(ans + " ");
		}
		bw.flush();
	}

}