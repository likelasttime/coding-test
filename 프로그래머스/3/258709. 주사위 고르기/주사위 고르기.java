import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    
    static List<List<Integer>> lst = new ArrayList();       // A가 고른 주사위 조합
    static int[] diceIdxArr;
    
    public int[] solution(int[][] dice) {
        int[] answer = new int[dice.length / 2];    // A의 승률이 가장 높은 주사위 조합
        int answerIdx = 0;       // A가 고른 주사위 조합 중 승률이 가장 높은 주사위 인덱스
        int maxScore = 0;       // A의 최대 승률
        diceIdxArr = new int[dice.length];
        
        dfs(0, 0, dice.length);     // A가 고를 수 있는 주사위의 조합 생성
        
        for(int i=0; i<lst.size(); i++) {
            List<Integer> aTotalArr = new ArrayList();      // A 주사위를 던졌을 때 합의 모든 경우의 수
            List<Integer> bTotalArr = new ArrayList();      // B 주사위를 던졌을 때 합의 모든 경우의 수
            
            int[][] aDice = new int[dice.length / 2][6];
            int[][] bDice = new int[dice.length / 2][6];
            int a = 0;
            int b = 0;
            
            /*
                A와 B가 고른 주사위를 생성한다.
            */
            for(int j=0; j<dice.length; j++) {
                if(lst.get(i).contains(j)) {
                    aDice[a++] = dice[j];
                } else {
                    bDice[b++] = dice[j];       // A가 고르지 않은 주사위는 B가 고른 주사위
                }
            }
            
            throwDice(dice.length/2, aDice, 0, aTotalArr, 0);        // A 주사위 던지기
            throwDice(dice.length/2, bDice, 0, bTotalArr, 0);        // B 주사위 던지기
        
            int resultAScore = binarySearch(aTotalArr, bTotalArr);      // 이진탐색으로 A가 고른 조합의 승률 계산
            if(maxScore < resultAScore) {           // 승률이 더 높다면
                maxScore = resultAScore;            // 승률을 갱신
                answerIdx = i;                      // 해당 승률의 조합 인덱스 저장
            }
        }
        
        /*
            승률이 높은 주사위 조합 출력을 위한 작업
        */
        for(int i=0; i<dice.length/2; i++) {
            answer[i] = lst.get(answerIdx).get(i) + 1;
        }
        
        return answer;
    }
    
    /*
        A가 n/2개의 주사위를 고르는 조합 생성
    */
    public void dfs(int start, int cnt, int n) {
        if(cnt == n/2) {    
            List<Integer> tmp = new ArrayList();
            for(int i=0; i<n; i++) {
                if(diceIdxArr[i] == 1) {
                    tmp.add(i);
                }
            }
            lst.add(tmp);
            return;
        } else {
            for (int i=start; i<n; i++) {
                diceIdxArr[i] = 1;
                dfs(i+1, cnt+1, n);
                diceIdxArr[i] = 0;
            }
        }
    }
    
    /*
        A 또는 B가 주사위를 던져서 나올 수 있는 모든 합을 구한다.
    */
    public void throwDice(int r, int[][] dice, int cnt, List<Integer> totalArr, int total) {
        if(cnt == r) {
            totalArr.add(total);
            return;
        } else {
            for(int i=0; i<6; i++) {
                throwDice(r, dice, cnt + 1, totalArr, total + dice[cnt][i]);
            }
        }
    }
    
    /*
        A의 승률 계산
    */
    public int binarySearch(List<Integer> aTotalArr, List<Integer> bTotalArr) {
        Collections.sort(bTotalArr);        // B가 던진 주사위의 합이 담김 배열을 오름차순 정렬
        int result = 0;
        for(int i=0; i<aTotalArr.size(); i++) {
            int aValue = aTotalArr.get(i);
            int left = 0;
            int right = aTotalArr.size() - 1;       // 인덱스는 0부터 시작하니까 1을 빼줘야 함
            int idx = Integer.MIN_VALUE;
            while(left <= right) {
                int mid = (left + right) / 2;
                if(aValue > bTotalArr.get(mid)) {        // A의 승률이 더 큼
                    left = mid + 1;
                    idx = Math.max(idx, mid);
                } else {
                    right = mid - 1;
                }
            }
            if(idx != Integer.MIN_VALUE) {
                result += idx + 1;       // 인덱스는 0부터 시작이니까 1을 더해줌
            }
        }
        return result;
    }
} 