import java.util.Scanner;
import java.util.LinkedList;

class Main {
    static class Box {
        int power;      // 내구도
        boolean flag;   // 로봇이 있는지

        Box(int power) {
            this.power = power;
            this.flag = false;  // 기본적으로 로봇은 없다
        }

        public void put() {
            this.power--;
            this.flag = true;
        }

        public void remove() {
            this.flag = false;
        }
    }

    static LinkedList<Box> belt;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer = 0;     // 단계
        n = sc.nextInt();       // 컨베이어 벨트의 길이
        int k = sc.nextInt();   // 종료 기준
        int curK = 0;       // 내구도가 0인 칸의 갯수
        belt = new LinkedList<>();

        // 벨트 입력 받기
        for (int i = 0; i < n * 2; i++) {
            belt.add(new Box(sc.nextInt()));  // 내구도만 입력 받음
        }

        while (curK < k) {
            answer++;

            // 1. 벨트 회전
            belt.add(0, belt.removeLast());  // 가장 마지막 박스를 맨 앞에 추가
            if (belt.get(n - 1).flag) {  // 마지막 칸에 로봇이 있으면 내리기
                belt.get(n - 1).remove();
            }

            // 2. 로봇 이동
            for (int i = n - 2; i >= 0; i--) {  // 마지막 칸부터 두 번째 칸까지
                if (belt.get(i).flag) {  // 로봇이 있다면
                    if (!belt.get(i + 1).flag && belt.get(i + 1).power > 0) {  // 이동할 칸이 비어있고 내구도가 0보다 크면
                        belt.get(i).remove();
                        belt.get(i + 1).put();
                        if (belt.get(i + 1).power <= 0) {  // 내구도가 0이면 카운트 증가
                            curK++;
                        }

                        // 마지막 칸에 로봇을 내린다
                        if (i + 1 == n - 1) {
                            belt.get(i + 1).remove();
                        }
                    }
                }
            }

            // 3. 로봇 올리기
            if (belt.get(0).power > 0) {  // 내구도가 0이 아니면 로봇을 올릴 수 있다
                belt.get(0).put();
                if (belt.get(0).power <= 0) {
                    curK++;
                }
            }
        }

        System.out.println(answer);
    }
}