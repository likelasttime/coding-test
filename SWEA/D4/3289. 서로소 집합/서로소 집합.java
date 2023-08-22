import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());		// 테스트 케이스 수
		for(int tc=1; tc<=t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());		// 집합의 개수
			int m = Integer.parseInt(st.nextToken());		// 연산의 개수
			
			parents = new int[n+1];
			for(int i=1; i<=n; i++) {
				parents[i] = i;		// 자기 자신이 대표자
			}
			
			bw.write("#" + tc + " ");
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());		// 명령어
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());	
				
				switch(cmd) {
				case 0:		// 합집합
					union(a, b);			// a에 b를 합친다.
					break;
				case 1:		// 포함되어 있나
					if(find(a) == find(b)){
						bw.write(String.valueOf(1)); 		// 같은 집합에 속해있음
					}else {
						bw.write(String.valueOf(0));		// 다른 집합에 속해있음
					}
					break;
				}
			}
			
			bw.write("\n");
		}
		bw.flush();
	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;			// 자기 자신이 대표자인 a를 찾아서 반환
		return parents[a] = find(parents[a]);		// a의 부모를 파라미터로 넣는다.
	}
	
	private static void union(int a, int b) {
		int parentsA = find(a);			// a의 대표자 구하기
		int parentsB = find(b);			// b의 대표자 구하기
        if(parentsA == parentsB) return;
		parents[parentsB] = a;			// b의 대표자를 a로 설정
	}
}