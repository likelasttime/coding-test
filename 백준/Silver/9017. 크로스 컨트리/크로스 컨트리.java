import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    final static int TEAM_CNT = 201;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());       // 태스트 케이스 수
        for(int t=0; t<tc; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 탈락 팀 구하기
            boolean[] isFail = new boolean[TEAM_CNT];
            boolean[] isVisit = new boolean[TEAM_CNT];
            Arrays.fill(isFail, true);
            for(int i=0; i<n-5; i++) {
                if(isVisit[arr[i]]) {
                    continue;
                }
                isVisit[arr[i]] = true;
                int cnt = 1;
                for(int j=i+1; j<n; j++) {
                    if(arr[i] == arr[j]) {
                        cnt++;
                    }
                    if(cnt == 6) {
                        isFail[arr[i]] = false;
                        break;
                    }
                }
            }

            // 각 팀별로 점수 구하기
            List<List<Integer>> score = new ArrayList();
            for(int i=0; i<TEAM_CNT; i++) {
                score.add(new ArrayList());
            }
            int curScore = 1;
            for(int i=0; i<n; i++) {
                if(isFail[arr[i]]) {
                    continue;
                }
                score.get(arr[i]).add(curScore++);
            }

            // 우승 팀 구하기
            int answer = 0;     // 우승 팀 번호
            int minScore = Integer.MAX_VALUE;
            int fiveRank = Integer.MAX_VALUE;       // 다섯 번째 사람 점수
            for(int i=1; i<TEAM_CNT; i++) {
                if(isFail[i]) {
                    continue;
                }
                int curSum = 0;
                for(int j=0; j<4; j++) {     // 상위 네 명의 주자의 점수 합 계산
                    curSum += score.get(i).get(j);
                }
                if(curSum < minScore) {
                    answer = i;
                    minScore = curSum;
                    fiveRank = score.get(i).get(4);
                } else if(minScore == curSum && fiveRank > score.get(i).get(4)) {       // 동점일때
                    answer = i;
                    fiveRank = score.get(i).get(4);
                }
            }

            System.out.println(answer);
        }
    }
}