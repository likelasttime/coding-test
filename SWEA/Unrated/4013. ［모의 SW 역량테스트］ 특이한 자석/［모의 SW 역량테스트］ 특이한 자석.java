import java.util.Scanner;

public class Solution {
	
	static final int numOfmagnet = 4;
	static final int numOfteeth = 8;
	static int magnet[][] = new int[5][8];		// 자석의 정보를 초기화 (자석이 4개지만 문제에서 자석의 번호는 1로 시작해서 편의상 5개로 만들었다)
	static int turn[][] = new int[21][2];		// 자석의 회전 정보를 저장함. 최대 20번 돌아갈 수 있다.
	static int magnetScorePointer[] = new int[5];		// 각 자석마다 빨간 화살표가 가리키는 날을 저장함
	static int turningMagnets[] = new int[numOfmagnet + 1];		// 각 회전 수행 시 같이 돌아가는 자석의 방향을 저장

	/*
	 * 회전될 자석 회전 작업(실제로는 빨간 화살표 위치를 바꿈)
	 */
	static void turnMagnet() {
		for (int magnetNum = 1; magnetNum <= numOfmagnet; magnetNum++) {
			int turningDirection = turningMagnets[magnetNum];
			if (turningDirection == 1) {		// 시계방향 회전 <-
				magnetScorePointer[magnetNum] = magnetScorePointer[magnetNum] == 0 ? numOfteeth - 1 : magnetScorePointer[magnetNum] - 1;
			} else if (turningDirection == -1) {	// 반시계방향 회전 ->
				magnetScorePointer[magnetNum] = magnetScorePointer[magnetNum] == numOfteeth - 1 ? 0 : magnetScorePointer[magnetNum] + 1;
			}
		}
	}
	
	/*
	 * 회전할 자석들 확인
	 */
	static void checkSide(int pivotMagnetNum, int rotateDirection, int side) {
		int pivotMagneticPole, sideMagneticPole, pivotScorePointer, sideScorePointer, sideMagnetNum;
		sideMagneticPole = 0;
		pivotMagneticPole = 0;
		
		// 옆 자석을 연속해서 확인하기 위한 while loop
		while (true) {
			if (side == 1 && pivotMagnetNum == numOfmagnet) {	// 오른쪽 자석을 확인하는데 없으면 중지
				break;
			} else if (side == -1 && pivotMagnetNum == 1) {		// 왼쪽 자석을 확인하는데 없으면 중지	
				break;
			}
			sideMagnetNum = pivotMagnetNum + side;		// 옆 자석
			pivotScorePointer = magnetScorePointer[pivotMagnetNum];		// 중심 자석 빨간 화살표 위치
			sideScorePointer = magnetScorePointer[sideMagnetNum];		// 사이드 자석 빨간 화살표 위치
			
			if (side == -1) {		// 왼쪽 자석 처리
				pivotMagneticPole = pivotScorePointer < 2 ? magnet[pivotMagnetNum][numOfteeth + pivotScorePointer - 2] : magnet[pivotMagnetNum][pivotScorePointer - 2];
				sideMagneticPole = sideScorePointer >= numOfteeth - 2 ? magnet[sideMagnetNum][sideScorePointer - numOfteeth + 2] : magnet[sideMagnetNum][sideScorePointer + 2];
			} else if (side == 1) {		// 오른쪽 자석 처리
				pivotMagneticPole = pivotScorePointer >= numOfteeth - 2 ? magnet[pivotMagnetNum][pivotScorePointer - numOfteeth + 2] : magnet[pivotMagnetNum][pivotScorePointer + 2];
				sideMagneticPole = sideScorePointer < 2 ? magnet[sideMagnetNum][numOfteeth + sideScorePointer - 2] : magnet[sideMagnetNum][sideScorePointer - 2];
			}
			
			if (sideMagneticPole != pivotMagneticPole) {		// 자성이 다르면 회전
				turningMagnets[sideMagnetNum] = -rotateDirection;
				rotateDirection = -rotateDirection;
			} else {	// 자성이 다르면 더 이상 확인하지 않음
				break;
			}
			pivotMagnetNum += side;		// 다음 옆 자석 확인
		}
	}
	
	/*
	 * 자석의 점수를 구하기 위한 함수
	 */
	static int pow (int x, int n) {
		int result = 1;
		for (int i = 1; i < n; i++) {
			result *= x;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T, k, score, pivotMagnetNum, rotateDirection;
		T = sc.nextInt();
		// 테스트 케이스 입력 받음
		
		for (int tc = 1; tc<=T; tc++) {
			// 점수를 0으로 초기화함
			score = 0;
			// 회전의 수를 저장함
			k = sc.nextInt();
			
			// 각 자석의 정보를 초기화함
			for (int i=1; i<=numOfmagnet; i++) {
				for (int j=0; j<numOfteeth; j++) {
					magnet[i][j] = sc.nextInt();
				}
			}
			
			// 회전할 자석과 방향의 정보를 초기화함
			for (int i=1; i<=k; i++) {
				turn[i][0] = sc.nextInt(); 			// magnet number
				turn[i][1] = sc.nextInt(); 			// rotate direction
			}
			
			// 빨간 화살표를 초기화함
			for (int i=0; i<numOfmagnet + 1; i++) {
				magnetScorePointer[i] = 0;
			}
			
			// 자석 회전 실행
			for (int i=1; i<=k; i++) {
				// 같이 돌아가게 될 자석 초기화
				for (int j=0; j<numOfmagnet + 1; j++) {
					turningMagnets[j] = 0;
				}
				
				pivotMagnetNum = turn[i][0];		// input으로 돌릴 자석
				rotateDirection = turn[i][1];		// input으로 돌릴 자석의 방향
				
				// input으로 돌릴 자석 방향 저장
				turningMagnets[pivotMagnetNum] = rotateDirection;
				
				// input으로 돌릴 자석의 오른쪽에 있는 자석 중 같이 돌아갈 자석 저장
				if (pivotMagnetNum != numOfmagnet) {
					checkSide(pivotMagnetNum, rotateDirection, 1);
				}
				
				// input으로 돌릴 자석의 왼쪽에 있는 자석 중 같이 돌아갈 자석 저장
				if (pivotMagnetNum != -1) {
					checkSide(pivotMagnetNum, rotateDirection, -1);
				}
				
				// 회전할 자석 회전
				turnMagnet();
			}
			
			// 점수 계산
			for (int i=1; i<=numOfmagnet; i++) {
				if (magnet[i][magnetScorePointer[i]] == 1) {
					score += pow(2, i);
				}
			}
			
			// score 출력
			System.out.println("#" + tc + " " + score);
		}
		sc.close();
	}

}
