import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10; // 테스트 케이스 수

        for (int test_case = 1; test_case <= T; test_case++) {
            int test_num = sc.nextInt();  // 테스트 케이스 번호
            String keyword = sc.next();    // 찾을 문자열
            String sentence = sc.next();    // 검색할 문장

            int answer = 0;                // 빈도 수
            int len = keyword.length();     // 찾을 문자열 길이
            Map<Character, Integer> skip = new HashMap<>();

            // skip 만들기
            for (int i = 0; i < len; i++) {
                skip.put(keyword.charAt(i), len - i - 1);
            }

            int start = len - 1;  // 검색할 문장 인덱스
            while (start <= sentence.length() - 1) {
                if (sentence.charAt(start) != keyword.charAt(len - 1)) { // 마지막 문자가 일치하지 않는다면
                    // 이 문자가 skip에 있으면
                    if (skip.containsKey(sentence.charAt(start))) {
                        start += skip.get(sentence.charAt(start));
                    } else { // skip에 없으면
                        start += len; // 찾으려는 문자의 길이만큼 건너뛰기
                    }
                } else { // 문자가 일치하면 그 다음 문자 비교
                    boolean flag = false;
                    for (int i = len - 2; i >= 0; i--) {
                        if (sentence.charAt(start - (len - 1 - i)) != keyword.charAt(i)) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        answer++;
                    }

                    start += len; // 다음 위치로 이동
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }
}