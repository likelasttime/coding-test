import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

class Solution
{
    static Map<String, Integer> map = new HashMap();

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();      // 테스트 케이스의 수
        map.put("0001101", 0);
        map.put("0011001", 1);
        map.put("0010011", 2);
        map.put("0111101", 3);
        map.put("0100011", 4);
        map.put("0110001", 5);
        map.put("0101111", 6);
        map.put("0111011", 7);
        map.put("0110111", 8);
        map.put("0001011", 9);

        for(int t=1; t<=tc; t++) {
            int n = sc.nextInt();   // 1 <= 세로 크기 <= 50
            int m = sc.nextInt();   // 56 <= 가로 크기 <= 100
            String[] arr = new String[n];
            int[] num = new int[8];
            int idx = 0;
            int answer = 0;
            boolean flag = false;

            for(int i=0; i<n; i++) {
                arr[i] = sc.next();
            }

            // 암호 코드 정보 추출
            for(int i=0; i<n; i++) {
                for(int j=m-1; j>56; j--) {       // 끝에서부터 1을 찾기
                    if(arr[i].charAt(j) == '1') {
                        int start = j - 55;
                        for (int k = 0; k < 8; k++) {
                            String subStr = arr[i].substring(start + (7 * k), start + (k * 7) + 7);
                            if (map.containsKey(subStr)) {
                                int value = map.get(subStr);
                                num[idx++] = value;
                                answer += value;
                            }
                        }
                        flag = true;
                        break;
                    }
                }
                if(flag) {
                    break;
                }
            }

            if((((num[0] + num[2] + num[4] + num[6]) * 3) + (num[1] + num[3] + num[5] + num[7])) % 10 != 0) {
                answer = 0;
            }
            System.out.println("#" + t + " " + answer);
        }
    }
}