/*
    모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고싶다.
    1 <= 입국을 기다리는 사람의 수 n <= 1,000,000,000
    1 <= 심사관의 수 <= 100,000
    1 <= 각 심사관이 한 명을 심사하는데 걸리는 시간 times[i] <= 1,000,000,000
*/
import java.util.*;

class Solution {
    final static long MAX_EXAMINER = 100000;
    final static long MAX_TIME = 1000000000;
    
    public long binarySearch(int n, int[] times) {
        long left = 1;
        long right = MAX_EXAMINER * MAX_TIME;
        long answer = 0;
        
        Arrays.sort(times);     // 심사 시간이 작은 순으로 정렬
        
        while(left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;       // 각 심사관이 시간 mid 동안에 처리할 수 있는 사람의 수의 총합
            for(long time : times) {
                cnt += (mid / time);        // 현재 심사관이 처리할 수 있는 사람의 수
                if(cnt > n) {       // n명만 보면 되니까 그 이상으로 더 탐색하는 것은 의미가 없다
                    break;
                }
            }
            if(cnt < n) {       // 모든 사람 n명을 처리할 수 없다
                left = mid + 1;     // 시간을 더 늘리기
            } else {        // 모든 사람 n명을 처리했다
                right = mid - 1;        // 시간 줄이기
                answer = mid;
            }
        }
        return answer;
    }
    
    public long solution(int n, int[] times) {
        long answer = 0;
        return binarySearch(n, times);
    }
}