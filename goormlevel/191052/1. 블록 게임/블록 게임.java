import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.Stack;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());		// 블록을 올려놓은 횟수
		String d = br.readLine();										// 블록을 놓은 방향을 의미하는 길이 n의 문자열
		int[] s = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();		// 블럭의 점수들	
		
		// 왼쪽, 오른쪽, 위쪽, 아래쪽
		int[] dx = {0, 0, 1, -1};
		int[] dy = {-1, 1, 0, 0};
        
		int answer = 1;		// 초기 점수
		int x = 0;				// 초기 x 좌표
		int y = 0;				// 초기 y 좌표
		Map<String, Integer> blockMap = new HashMap();		// 블럭이 쌓인 위치인지 O(1)만에 판단하기 위한 맵 생성
		blockMap.put("0 0", 1);								// 시작점 추가
		Stack<String> stack = new Stack();		// 블록을 쌓은 순서를 기록하기 위한 스택
		stack.push("0 0");										// 시작점 추가

		// 값은 dx, dy 배열에서의 인덱스
		Map<Character, Integer> cmdMap = new HashMap();
		cmdMap.put('L', 0);
		cmdMap.put('R', 1);
    cmdMap.put('U', 2);
    cmdMap.put('D', 3);

		for (int i=0; i<n; i++) {
			char cmd = d.charAt(i);					// 움직일 방향 문자
			int posIdx = cmdMap.get(cmd);		// 움직여야 하는 방향의 인덱스	
			int nx = x + dx[posIdx];				// 새로운 x 좌표
			int ny = y + dy[posIdx];				// 새로운 y 좌표
			String strNewPos = String.valueOf(nx) + " " + String.valueOf(ny);		// 문자열로 위치를 저장

			if (blockMap.containsKey(strNewPos)) {					// 블록이 쌓인 위치라면
				while (!stack.peek().equals(strNewPos)) {			// 없애야 할 블럭이 나오기 전까지 스택에서 pop하기
					String popStack = stack.pop();						// 스택에서 가장 위에 있는 값을 제거
					answer -= blockMap.get(popStack);					// 점수 차감
					blockMap.remove(popStack);								// key 제거
				}
				String popStack = stack.pop();							// 없애야 하는 블럭을 스택에서 제거
				answer -= blockMap.get(popStack);						// 점수 차감
				blockMap.remove(popStack);									// key 제거
			}

			// 블럭 쌓기
			stack.push(strNewPos);
			blockMap.put(strNewPos, s[i]);
		
			answer += s[i];			// 점수 획득
		
			// 새로운 위치로 변경
			x = nx;
			y = ny;
		}

		System.out.print(answer);				// 평면 위에 남아있는 블록들의 점수 합 출력
	}
}