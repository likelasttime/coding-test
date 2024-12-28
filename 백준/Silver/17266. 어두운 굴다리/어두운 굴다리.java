import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[] light;

    public static int binarySearch() {
        int left = 0;
        int right = n;
        int answer = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(!on(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }

    public static boolean on(int mid) {
        int prev = 0;
        for(int i=0; i<m; i++) {
            if(prev >= light[i] - mid) {
                prev = light[i] + mid;
            } else {
                return false;
            }
        }
        return n - prev <= 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());        // 1 <= 굴다리의 길이 <= 100,000
        m = Integer.parseInt(br.readLine());        // 1 <= 가로등의 개수 <= n
        int[] arr = new int[n];
        light = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            light[i] = Integer.parseInt(st.nextToken());
        }
        System.out.print(binarySearch());
    }
}