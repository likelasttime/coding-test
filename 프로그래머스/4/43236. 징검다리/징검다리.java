/*
    출발지점부터 distance만큼 떨어진 곳에 도착지점이 있다.
    그 사이에는 바위들이 놓여있다.
    바위 n개를 제거한 뒤 각 지점 사이의 거리의 최솟값 중에 가장 큰 값을 반환
*/
import java.util.*;
class Solution {
    public int binarySearch(int distance, int[] rocks, int n) {
        int left = 0;
        int right = distance; 
        int answer = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;        // 건너뛴 바위의 갯수
            int prev = 0;
            for(int i=0; i<rocks.length; i++) {
                if(rocks[i] - prev < mid) {
                    // 건너뛰기
                    cnt++;
                } else {
                    prev = rocks[i];
                }
            }
            // 도착점과의 거리 계산
            if(distance - prev < mid) {
                cnt++;
            }
            if(cnt > n) {       // 주어진 제거해야하는 바위 갯수 n보다 더 많이 제거했다면
                right = mid - 1;
            } else {
                left = mid + 1;
                answer = mid;
            }
        }
        return answer;
    }
    
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);     // 오름차순 정렬
        return binarySearch(distance, rocks, n);
    }
}