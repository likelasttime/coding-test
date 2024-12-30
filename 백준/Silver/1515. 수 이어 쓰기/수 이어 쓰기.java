import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        int cnt = 1;
        int idx = 0;
        int len = n.length();
        while(cnt <= 30000) {
            String tmp = String.valueOf(cnt);
            for(int i=0; i<tmp.length(); i++) {
                if (n.charAt(idx) == tmp.charAt(i)) {
                    idx++;
                }
                if(idx == len) {
                    System.out.print(cnt);
                    return;
                }
            }
            cnt++;
        }
    }
}