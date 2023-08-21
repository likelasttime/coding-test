import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static Queue<Integer> queue = new LinkedList<>();
	static int n; // 학생 수
	static ArrayList<ArrayList<Integer>> arrayList; // 인접 리스트(유향)
	static int[] cntmap;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 학생 수
		int m = Integer.parseInt(st.nextToken()); // 키를 비교한 횟수

		arrayList = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<=n; i++) {
			arrayList.add(new ArrayList<Integer>());
		}
		
		cntmap = new int[n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arrayList.get(a).add(b); // a가 b 앞에 있다.
			if(cntmap[b] == 0) {
				cntmap[b] = 0;
			}
			cntmap[b]++; // 선수 과목 수 증가
		}

		/*
		 * 진입 차수가 0인 노드를 큐에 모두 넣기
		 */
		for (int i = 1; i <= n; i++) {
			if (cntmap[i] == 0) { // 선수 과목이 없다.
				queue.offer(i);
			}
		}

		bfs();
		bw.flush();
	}

	public static void bfs() throws IOException {
		while (!queue.isEmpty()) { 			// 큐가 안 비었으면
			int num = queue.poll();
			bw.write(num + " ");
			for (Integer b : arrayList.get(num)) {		// num의 뒤에 있는 사람을 가져오기
					cntmap[b]--;				// b의 앞에 선 사람의 수 감소
					if (cntmap[b] == 0) {		// 0이 된 노드는 큐에 넣기
						queue.offer(b);
					}
				}
			}
	}
}
