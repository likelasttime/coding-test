import java.io.*;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static int n;
    static int k;
    static char[] arr;
    static char[] line;
    static int lineSize;
    static int pointer;
    static Set<Integer> set;
    static Map<Character, Integer> hexadecimal;

    public static void init() {
        hexadecimal = new HashMap();
        hexadecimal.put('0', 0);
        hexadecimal.put('1', 1);
        hexadecimal.put('2', 2);
        hexadecimal.put('3', 3);
        hexadecimal.put('4', 4);
        hexadecimal.put('5', 5);
        hexadecimal.put('6', 6);
        hexadecimal.put('7', 7);
        hexadecimal.put('8', 8);
        hexadecimal.put('9', 9);
        hexadecimal.put('A', 10);
        hexadecimal.put('B', 11);
        hexadecimal.put('C', 12);
        hexadecimal.put('D', 13);
        hexadecimal.put('E', 14);
        hexadecimal.put('F', 15);
    }

    public static int getMaxKthNum() {
        List<Integer> lst = new ArrayList();
        for(int num : set) {
            lst.add(num);
        }
        Collections.sort(lst, Collections.reverseOrder());
        return lst.get(k - 1);
    }

    /*
        16진수를 10진수로 변환
     */
    public static int transNum() {
        int result = 0;
        for(int i=0; i<lineSize; i++) {
            int cur = hexadecimal.get(line[i]);
            result += Math.pow(16, lineSize - 1 - i) * cur;
        }
        return result;
    }

    public static void rotate() {
        for(int i=0; i<4; i++) {
            split(pointer + lineSize * i);
        }
    }

    public static void split(int start) {
        int idx = 0;
        for(int i=start; i<start+lineSize; i++) {
            line[idx++] = arr[i % n];
        }
        set.add(transNum());     // 16진수 -> 10진수
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());        // 테스트 케이스 수
        StringTokenizer st;

        init();

        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new char[n];
            lineSize = n / 4;
            pointer = 0;
            set = new HashSet();
            line = new char[lineSize];
            String str = br.readLine();
            for(int i=0; i<n; i++) {
                arr[i] = str.charAt(i);
            }
            
            rotate();
            pointer = n - 1;
            for(int i=1; i<lineSize; i++) {
                // 한번 회전할 때마다 4개의 변을 자르기
                rotate();
                pointer--;
            }

            System.out.println("#" + tc + " " + getMaxKthNum());
        }
    }
}