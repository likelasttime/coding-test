import java.util.Scanner;

class Main {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 식탁의 길이
        int k = sc.nextInt();       // 햄버거를 선택할 수 있는 거리
        String str = sc.next();
        int answer = 0;     // 햄버거를 먹을 수 있는 사람의 최대 수
        boolean[] visit = new boolean[n];
        for(int i=0; i<n; i++) {
            char ch = str.charAt(i);
            if(ch == 'P') {
                // 왼쪽 탐색
                boolean isLeft = false;
                for(int left=Math.max(0, i-k); left<i; left++) {
                    if(!visit[left] && str.charAt(left) == 'H') {   // 아직 아무도 안 먹은 햄버거라면
                        visit[left] = true;
                        isLeft = true;
                        answer++;
                        break;
                    }
                }
                if(isLeft) {
                    continue;
                }
                // 오른쪽 탐색
                for(int right=Math.min(n-1, i+1); right<=Math.min(n-1, i+k); right++) {
                    if(!visit[right] && str.charAt(right) == 'H') {   // 아직 아무도 안 먹은 햄버거라면
                        visit[right] = true;
                        answer++;
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}