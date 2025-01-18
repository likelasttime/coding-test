import java.io.*;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());        // 키워드 개수
        int m = Integer.parseInt(st.nextToken());        // 블로그에 쓴 글의 개수
        boolean[] check = new boolean[n];
        Map<String, Integer> map = new HashMap();
        int answer = n;
        // 메모장에 적은 키워드 입력받기
        for(int i=0; i<n; i++) {
            map.put(br.readLine(), i);
        }
        // 글과 관련된 키워드 입력받기
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), ",");
            while(st.hasMoreTokens()) {
                String cur = st.nextToken();
                int idx = map.getOrDefault(cur, -1);
                if(idx == -1) {
                    continue;
                }
                if(!check[idx]) {
                   answer--;
                   check[idx] = true;
                }
            }
            bw.write(answer + "\n");
        }
        bw.flush();
    }
}