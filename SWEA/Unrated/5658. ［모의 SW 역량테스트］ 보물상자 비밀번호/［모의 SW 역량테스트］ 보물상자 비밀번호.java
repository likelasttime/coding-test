import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
* 회전을 4번만 하는 게 아니라 n/4만큼 해야한다.
* n의 길이가 입력마다 다르고, 회전했을 때 다시 원점으로 돌아와야 모든 경우를 다 따지기 때문이다.
* 회전할 때 규칙이 있다.
* 회전 회차가 늘어날수록 시작 인덱스는 0, n-1, n-2, n-3,.. 이런식으로 왼쪽으로 간다.
* 회전 회차마다 시작 인덱스를 구한 후 +1씩 증가하고, 나머지 연산자를 이용해서 인덱스 초과하지 않도록 한다.
* 한 회전 회차마다 4개씩 끊어서 담는다.
* 중복이 없어야 하고 순서도 있어야 해서 TreeSet을 사용했다.
* HashSet은 순서를 보장할 수 없다.
*/
public class Solution {
	
	static int n;		// 16진수 0~F 숫자를 입력으로 주는 갯수
	static String s;		// 입력으로 받는 문자열
	static char[] ch;		// 회전시킨 문자를 담는 문자 배열
	static TreeSet<Password> treeSet;
	
	static class Password implements Comparable<Password> {

		String str;

		public Password (String str) {
			this.str = str;
		}
		
		public int getStr() {
			return Integer.parseInt(str, 16);			// 16진수를 10진수로 변환
		}

		@Override
		public int compareTo(Password o) {
			return o.getStr() - this.getStr();		// 내림차순 정렬
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());	// 테스트 케이스 수
		for (int t=1; t<=tc; t++) {
			treeSet = new TreeSet<Password>();
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());	// 16진수 0~F 숫자를 입력으로 주는 갯수
			int k = Integer.parseInt(st.nextToken());	// k번째
			s = br.readLine();
			ch = new char[n/4];
			
			for (int i=0; i<n/4; i++) {		// 회전 회차
				rotate((n-i) % n);		// 회전 회차가 늘어날수록 시작점은 왼쪽 방향으로 간다.
			}
			
			Iterator<Password> iter = treeSet.iterator();
			while(--k > 0) {
				iter.next();
			}
			
			bw.write("#" + t + " " + iter.next().getStr() + "\n");	// k번째로 큰 수를 10진수로 만든 수
		}
		
		bw.flush();

	}
	
	public static void rotate(int start) {
		for (int i=0; i<4; i++) {		// 회전 횟수
			int cnt = 0;		// 문자 배열에 담은 문자 인덱스
			while (cnt < n/4) {			// 4개씩 끊기
				ch[cnt] = s.charAt(start%n);
				start++;
				cnt++;
			}
	
			String str = String.valueOf(ch);		// 문자열로 변환
			treeSet.add(new Password(str));
		}
	}

}