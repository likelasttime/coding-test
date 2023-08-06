import java.io.*;

public class Main{
    static int N;
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception{
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(bf.readLine());
        dfs("", 0);
        bw.flush();

    }

    public static void dfs(String s, int depth) throws IOException{
        if(depth == N){
            bw.write(s+"\n");
            return;
        }

        for(int i=1; i<=9; i++){
            if(check(Integer.parseInt(s+Integer.toString(i)))) dfs(s+Integer.toString(i), depth+1);
        }
    }

    public static boolean check(int num){
        if(num == 1) return false;
        int end=(int)Math.sqrt(num);
        for(int i=2; i<=end; i++){
            if(num%i == 0) return false;
        }
        return true;
    }
}