import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Solution {
    static Integer[] arr;
    static int n;
    static int m;

    public static int bfs() {
        int answer = 1;
        List<int[]> lst = new ArrayList();
        lst.add(new int[]{m, m});
        for(int i=0; i<n; i++) {        
            int cut = arr[i];
            boolean flag = false;
            for(int j=0; j<lst.size(); j++) {
                int w = lst.get(j)[0];
                int h = lst.get(j)[1];
                if(cut <= w && cut <= h) {
                    flag = true;
                    lst.remove(j);      // 자르면 기존 타일 사라짐
                    lst.add(new int[]{w - cut, cut});
                    lst.add(new int[]{w, h - cut});
                    break;
                }
            }
            if(!flag) {     // 하나도 못 잘랐다면, 새 타일을 추가하고 자르기
                lst.add(new int[]{m - cut, cut});
                lst.add(new int[]{m, m - cut});
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++) {
            n = sc.nextInt();
            m = sc.nextInt();
            arr = new Integer[n];
            for(int i=0; i<n; i++) {
                arr[i] = (int)Math.pow(2, sc.nextInt());
            }
            Arrays.sort(arr, Collections.reverseOrder());
            System.out.println("#" + tc + " " + bfs());
        }
    }
}