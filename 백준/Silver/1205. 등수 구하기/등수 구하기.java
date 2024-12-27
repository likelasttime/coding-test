import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 기존 랭킹에 올라있는 사람 수
        int taesu = sc.nextInt();   // 태수의 새로운 점수
        int p = sc.nextInt();       // 랭킹 리스트에 올라갈 수 있는 최대 사람 수
        Integer[] arr = new Integer[n]; // 기존 점수 배열

        // 기존 랭킹에 올라갈 수 있는 자리가 없을 경우
        if (n == 0) {
            System.out.println(1);  // 랭킹에 태수가 들어갈 첫 번째 자리
            return;
        }

        // 기존 점수 입력
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 기존 점수 배열 정렬 (내림차순)
        Arrays.sort(arr, Comparator.reverseOrder());

        // 점수가 랭킹 리스트의 제약을 초과한 경우
        if (n >= p && arr[n - 1] >= taesu) {
            System.out.println(-1);
            return;
        }

        // 태수가 랭킹에 들어갈 위치를 계산
        int rank = 1;
        for (int i = 0; i < n; i++) {
            // 같은 점수가 여러 번 나타날 수 있기 때문에, 순위를 계산할 때 주의
            if (arr[i] > taesu) {
                rank++;
            } else if (arr[i] == taesu) {
                // 같은 점수가 있으면 순위는 그 점수의 첫 번째 자리에 해당
                rank = i + 1;
                break;
            } else {
                break;  // 태수가 더 낮은 점수라면 바로 그 자리에 들어감
            }
        }

        // 랭킹을 출력
        if (rank <= p) {
            System.out.println(rank);
        } else {
            System.out.println(-1);
        }
    }
}