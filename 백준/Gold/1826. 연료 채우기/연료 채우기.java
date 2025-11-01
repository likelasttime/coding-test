import java.io.*;
import java.util.*;

public class Main {
    // 주유소 클래스: 거리(a)와 연료(b)
    static class GasStation implements Comparable<GasStation> {
        int distance, fuel;

        public GasStation(int distance, int fuel) {
            this.distance = distance;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(GasStation o) {
            return this.distance - o.distance; // 거리 기준 오름차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 주유소 개수
        PriorityQueue<GasStation> stations = new PriorityQueue<>();

        // 주유소 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int distance = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());
            stations.add(new GasStation(distance, fuel));
        }

        // 초기 연료 L, 목표 거리 P
        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        // 현재 위치에서 갈 수 있는 주유소 연료를 저장하는 최대 힙
        PriorityQueue<Integer> fuels = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0; // 주유 횟수

        // 목표 도착할 때까지 반복
        while (P < L) {
            // 현재 위치 P까지 도달 가능한 주유소들을 PQ에 넣음
            while (!stations.isEmpty() && stations.peek().distance <= P) {
                fuels.add(stations.poll().fuel);
            }

            // 갈 수 있는 주유소가 없으면 도착 불가
            if (fuels.isEmpty()) {
                System.out.println(-1);
                return;
            }

            // 최대 연료 주유
            P += fuels.poll();
            answer++;
        }

        // 목표 도착 가능 → 최소 주유 횟수 출력
        System.out.println(answer);
    }
}
