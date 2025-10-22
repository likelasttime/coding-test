import java.util.*;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        // 기본적으로 각 유형에 1명씩 배정
        int[] mentors = new int[k];
        Arrays.fill(mentors, 1);
        int remaining = n - k;

        // 요청 시간 순 정렬
        Arrays.sort(reqs, (a, b) -> a[0] - b[0]);

        // 남은 멘토들을 순차적으로 배정
        while (remaining-- > 0) {
            int bestType = -1;
            int maxDiff = -1;

            // 현재 전체 대기 시간 (한 번만 계산)
            int currentWait = calcWait(reqs, mentors);

            // 각 유형별로 멘토를 1명 더 추가했을 때의 대기시간 감소량 계산
            for (int i = 0; i < k; i++) {
                mentors[i]++;
                int newWait = calcWait(reqs, mentors);
                mentors[i]--; // 원복

                int diff = currentWait - newWait; // 감소량
                if (diff > maxDiff) {            // 감소량이 큰 것을 선택
                    maxDiff = diff;
                    bestType = i;
                }
            }

            // 만약 더 이상 감소가 없으면(모두 diff == 0) 멈출 수 있음
            if (maxDiff <= 0) break;

            // 대기시간이 가장 많이 줄어드는 유형에 멘토 1명 추가
            mentors[bestType]++;
        }

        // 최종 총 대기 시간 계산
        return calcWait(reqs, mentors);
    }

    private int calcWait(int[][] reqs, int[] mentors) {
        int k = mentors.length;
        int[] waitSum = new int[k];

        @SuppressWarnings("unchecked")
        PriorityQueue<Integer>[] pq = new PriorityQueue[k];
        for (int i = 0; i < k; i++) {
            pq[i] = new PriorityQueue<>();
            for (int j = 0; j < mentors[i]; j++) pq[i].offer(0);
        }

        for (int[] r : reqs) {
            int start = r[0], duration = r[1], type = r[2] - 1;
            int nextFree = pq[type].poll();
            if (start < nextFree) waitSum[type] += nextFree - start;
            pq[type].offer(Math.max(start, nextFree) + duration);
        }

        int total = 0;
        for (int t : waitSum) total += t;
        return total;
    }
}
