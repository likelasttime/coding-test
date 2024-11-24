import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Solution {
    static int[] combiArr;
    static List<int[]> lst;
    static int[] arr;
    static char[] origin;
    static int answer;
    
    /*
        6개 중에서 2개를 뽑는 조합
     */
    public static void combination(int depth, int start, int n) {
        if(depth == 2) {
            lst.add(combiArr.clone());
            return;
        }
        for(int i=start; i<n; i++) {
            combiArr[depth] = i;
            combination(depth + 1, i + 1, n);
        }
    }

    public static void replicationCombination(int depth, int start, int cnt) {
        if(depth == cnt) {
            char[] tmp = origin.clone();
            for(int i=0; i<cnt; i++) {
                int[] info = lst.get(arr[i]);
                char tmpCh = tmp[info[0]];
                tmp[info[0]] = tmp[info[1]];
                tmp[info[1]] = tmpCh;
            }
            int tmpStr = Integer.parseInt(new String(tmp));
            if(answer < tmpStr) {
                answer = tmpStr;
            }
            return;
        }
        for(int i=start; i<lst.size(); i++) {
            arr[depth] = i;
            replicationCombination(depth + 1, i, cnt);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();      // 테스트 케이스의 수

        for(int t=1; t<=tc; t++) {
            origin = sc.next().toCharArray();    // 숫자판의 정보
            int cnt = sc.nextInt();     // 교환 횟수
            arr = new int[cnt];
            combiArr = new int[2];
            lst = new ArrayList();
            answer = 0;
            
            combination(0, 0, origin.length);
            replicationCombination(0, 0, cnt);

            System.out.println("#" + t + " " + answer);
        }
    }
}