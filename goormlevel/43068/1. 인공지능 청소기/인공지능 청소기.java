import java.io.*;
import java.util.stream.Stream;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());		// 테스트케이스 수
		
		for (int tc=0; tc<t; tc++) {
			int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();	// arr= [X 좌표, Y 좌표, N초]
			int distance = Math.abs(arr[0]) + Math.abs(arr[1]);				// 원점(0,0)에서 (X,Y)까지 거리
			if (arr[2] >= distance && (arr[2] - distance) % 2 == 0) {			// N초는 N만큼 움직이니까 거리가 그 이상이 되어야 하고, N초에 움직이는 거리와 원점에서 (X, Y)까지의 거리의 차가 짝수여야 함
				bw.write("YES\n");
			}
			else {
				bw.write("NO\n");
			}
		}
		
		bw.flush();				// 효율을 위해 한꺼번에 출력
	}
}