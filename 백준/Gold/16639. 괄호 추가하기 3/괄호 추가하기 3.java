import java.util.*;
import java.io.*;

public class Main {
    static String expression;  // 수식

    static class Node {
        int minVal;
        int maxVal;
        Node(int minVal, int maxVal) {
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 1 <= 수식의 길이 <= 19
        expression = br.readLine();  // 수식

        int size = n / 2 + 2;  // 피연산자의 갯수(dp에서 인덱스를 1부터 시작할려고 1을 더 더해줌)
        Node[][] dp = new Node[size][size];

        // DP 배열 초기화
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dp[i][j] = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
            }
        }

        // 숫자만 DP 배열에 넣기
        for (int i = 0; i < n; i += 2) {
            char ch = expression.charAt(i);
            int idx = i / 2 + 1;
            dp[idx][idx].minVal = Character.getNumericValue(ch);
            dp[idx][idx].maxVal = Character.getNumericValue(ch);
        }

        // DP를 채워가는 과정
        for (int len = 2; len < size; len++) {  // 부분 수식 길이
            for (int start = 1; start < size - len + 1; start++) {  // 시작점
                int end = start + len - 1;  // 끝점
                int maxTmp = Integer.MIN_VALUE;
                int minTmp = Integer.MAX_VALUE;

                // start와 end 사이의 중간값을 고려하여 계산
                for (int mid = start; mid < end; mid++) {
                    Node node1 = dp[start][mid];  // 왼쪽 부분 수식
                    Node node2 = dp[mid + 1][end];  // 오른쪽 부분 수식
                    char op = expression.charAt(mid * 2 - 1);  // 연산자 위치는 mid * 2 - 1

                    if (op == '+') {
                        minTmp = Math.min(minTmp, node1.minVal + node2.minVal);
                        maxTmp = Math.max(maxTmp, node1.maxVal + node2.maxVal);
                    } else if (op == '-') {
                        maxTmp = Math.max(maxTmp, node1.maxVal - node2.minVal);
                        minTmp = Math.min(minTmp, node1.minVal - node2.maxVal);
                    } else if (op == '*') {
                        maxTmp = Math.max(maxTmp, node1.maxVal * node2.maxVal);
                        maxTmp = Math.max(maxTmp, node1.minVal * node2.minVal);
                        maxTmp = Math.max(maxTmp, node1.maxVal * node2.minVal);
                        maxTmp = Math.max(maxTmp, node1.minVal * node2.maxVal);
                        minTmp = Math.min(minTmp, node1.maxVal * node2.maxVal);
                        minTmp = Math.min(minTmp, node1.minVal * node2.minVal);
                        minTmp = Math.min(minTmp, node1.maxVal * node2.minVal);
                        minTmp = Math.min(minTmp, node1.minVal * node2.maxVal);
                    }
                }
                dp[start][end].minVal = minTmp;
                dp[start][end].maxVal = maxTmp;
            }
        }

        // 최종 결과는 dp[1][size-1]에 저장된 최대값
        System.out.print(dp[1][size - 1].maxVal);
    }
}
