import java.io.*;
import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstInput = br.readLine().split(" ");
        int n = Integer.parseInt(firstInput[0]);
        Set<String> set = new HashSet();
        int needCnt;
        if(firstInput[1].equals("Y")) {
            needCnt = 1;
        } else if(firstInput[1].equals("F")) {
            needCnt = 2;
        } else {
            needCnt = 3;
        }

        for(int i=0; i<n; i++) {
            set.add(br.readLine());
        }

        System.out.println(set.size() / needCnt);
    }
}