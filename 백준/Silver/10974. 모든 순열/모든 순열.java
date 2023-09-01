import java.io.*;

public class Main{
    static int N;
    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb=new StringBuilder();
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        arr=new int[N+1];
        visited=new boolean[N+1];
        dfs(0);
        bw.write(sb.toString());
        bw.flush();
    }

    public static void dfs(int depth) throws IOException{
        if(depth == N){
            for(int i=0; i<N; i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
        }

        for(int i=1; i<=N; i++){
            if(!visited[i]){
                visited[i]=true;
                arr[depth]=i;
                dfs(depth+1);
                visited[i]=false;
            }
        }
    }
}