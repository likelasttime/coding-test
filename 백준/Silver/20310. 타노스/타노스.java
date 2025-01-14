import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        StringBuilder sb = new StringBuilder();
        int n = str.length();
        int zeroCnt = 0;
        int oneCnt = 0;
        boolean[] visit = new boolean[n];
        for(int i=0; i<n; i++) {
            if (str.charAt(i) == '1') {
                oneCnt++;
            } else {
                zeroCnt++;
            }
        }
        // 앞에서부터 1 제거
        int curOne = 0;
        for(int i=0; i<n; i++) {
            if(curOne == oneCnt / 2) {
                break;
            }
            if(str.charAt(i) == '1') {
                curOne++;
                visit[i] = true;
            }
        }
        // 뒤에서부터 0 제거
        int curZero = 0;
        for(int i=n-1; i>=0; i--) {
            if(curZero == zeroCnt / 2) {
                break;
            }
            if(str.charAt(i) == '0') {
                curZero++;
                visit[i] = true;
            }
        }
        // 문자열 만들기
        for(int i=0; i<n; i++) {
            if(!visit[i]) {
                sb.append(str.charAt(i));
            }
        }
        System.out.println(sb.toString());
    }
}