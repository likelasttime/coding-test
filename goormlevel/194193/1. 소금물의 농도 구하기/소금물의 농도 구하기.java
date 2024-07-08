import java.io.*;
import java.util.stream.Stream;
import java.math.BigDecimal;
import java.math.RoundingMode;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();			// 소금물, 추가한 물의 양
		
		// 1. 소금 양을 구하기
		double salt = 0.07 * input[0];
		
		// 2. Mg의 물을 추가한 후 소금물의 농도 구하기
		BigDecimal answer = new BigDecimal((salt / (input[0] + input[1])) * 100);
		System.out.println(answer.setScale(2, RoundingMode.DOWN));
	}
}