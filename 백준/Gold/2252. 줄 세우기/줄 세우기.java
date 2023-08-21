import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input=br.readLine().split(" ");
        int N=Integer.parseInt(input[0]);       // 사람 수
        int M=Integer.parseInt(input[1]);       // 키를 비교한 횟수
        ArrayList<ArrayList<Integer>> arr=new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=N; i++){
            arr.add(new ArrayList<Integer>());
        }
        Integer[] cnt=new Integer[N+1];
        Queue que=new LinkedList();
        for(int i=1; i<=N; i++){
            cnt[i]=0;
        }

        for(int m=0; m<M; m++){
            String[] ab=br.readLine().split(" ");
            int a=Integer.parseInt(ab[0]);
            int b=Integer.parseInt(ab[1]);
            arr.get(a).add(b);
            cnt[b]++;
        }

        for(int i=1; i<=N; i++){
            if(cnt[i] == 0){
                que.add(i);
            }
        }

        while(!que.isEmpty()){
            int num=(int)que.poll();
            bw.write(num+" ");
            for(Integer b : arr.get(num)){
                cnt[b]--;
                if(cnt[b] == 0 ){
                    que.add(b);
                }
            }
        }
        bw.flush();
    }
}