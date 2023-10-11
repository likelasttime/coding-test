import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/*
 * 페르마 소정리 이용
 * a^(p-2) = 1/a(mod p)
 */
public class Solution {
	
	static long[] factorialArr = new long[1000001];
	static int n;		// 1 <= n <= 1000000
	static final int MOD = 1234567891;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());		// 테스트 케이스 수
		
		for (int t=1; t<=tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int answer = 0;			// N combination R의 값을 1234567891로 나눈 나머지
			
			init();
			
			long a = factorialArr[n]; 			// 분자
			long b = (factorialArr[n - r] * factorialArr[r]) % MOD;		// 분모
			long c = calculate(b, MOD - 2);
			
			bw.write("#" + t + " " + ((a * c) % MOD) + "\n");
		}
		
		bw.flush();
	}
	
	private static long calculate(long n, long k) {
		if (k == 1) {
			return n;
		}
		
		long x = calculate(n, k/2) % MOD;
		
		if (k % 2 == 0) {		// 짝
			return x * x % MOD;
		} else {
			return (((x * x) % MOD) * n) % MOD;
		}
	}
	
	/*
	 * 각 값마다 팩토리얼 구하기
	 */
	public static void init() {
		factorialArr[0] = 1;
		for (int i=1; i<=n; i++) {
			factorialArr[i] = (factorialArr[i-1] * i) % MOD;
		}
	}

}
