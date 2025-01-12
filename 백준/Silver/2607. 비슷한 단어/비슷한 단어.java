import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static String standard;
    static int res = 0;
    static int[] alphabet = new int[26];
    static int[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 기준값 알파벳 분석 : 알파벳 개수를 기록
        standard = br.readLine();
        for (int i = 0; i < standard.length(); i++) {
            int idx = standard.charAt(i) - 'A';
            alphabet[idx]++;
        }

        // 나머지 값 분석
        for (int i = 0; i < N - 1; i++) {
            check = alphabet.clone();  // 기준 알파벳 개수 복사
            String curr = br.readLine();

            // 길이 차이가 1을 넘을 경우, 같은 단어로 만들 수 없음
            if (Math.abs(curr.length() - standard.length()) > 1) continue;

            // cnt는 기준값과 현재 단어에서 일치하는 알파벳 개수
            int cnt = 0;

            // 현재 단어의 알파벳과 기준 단어의 알파벳 비교
            for (int j = 0; j < curr.length(); j++) {
                int idx = curr.charAt(j) - 'A';

                // 기준 알파벳 배열에 해당 알파벳이 있을 경우
                if (check[idx] > 0) {
                    cnt++;
                    check[idx]--;  // 해당 알파벳 사용
                }
            }

            // 길이 차이가 1인 경우
            if (Math.abs(standard.length() - curr.length()) == 1) {
                if (cnt == Math.min(standard.length(), curr.length())) {
                    res++;
                }
            }
            // 길이가 동일한 경우
            else if (standard.length() == curr.length()) {
                // 두 단어의 알파벳이 완전히 동일하거나, 하나만 다른 경우
                if (cnt == standard.length() || cnt == standard.length() - 1) {
                    res++;
                }
            }
        }

        System.out.println(res);
    }
}