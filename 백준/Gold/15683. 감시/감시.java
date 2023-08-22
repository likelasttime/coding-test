// 실패
import java.io.*;
import java.util.*;

class Pos{
    int x;
    int y;
    Pos(int x, int y){
        this.x=x;
        this.y=y;
    }
}

public class Main{
    static int N;   // 세로
    static int M;   // 가로
    static int[][] arr;
    static int[][] copy;
    static ArrayList<Pos> cctv5=new ArrayList<>();
    static ArrayList<Pos> cctv=new ArrayList<>();
    static ArrayList<Integer> tmp=new ArrayList<>();
    static int total;
    static int ans;
    static int c;
    /* 좌 상 우 하
    static int[] dx={0, -1, 0, 1};
    static int[] dy={-1, 0, 1, 0};*/

    static int[] dx={1, 0, -1, 0};
    static int[] dy={0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        arr=new int[N][M];
        copy=new int[N][M];
        total=N*M;          // 넓이
        int tmp=0;
        // 배열에 값 입력
        for(int i=0; i<N; i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                tmp=Integer.parseInt(st.nextToken());
                if(tmp == 5){
                    cctv5.add(new Pos(i, j));
                    total--;
                }else if(0 < tmp && tmp < 5){       // cctv
                    cctv.add(new Pos(i, j));
                    total--;
                }else if(tmp == 6){     // 벽
                    total--;
                }
                arr[i][j]=tmp;
            }
        }
        // cctv 번호5부터 처리
        for(Pos p : cctv5){
            int x=p.x;
            int y=p.y;
            for(int i=0; i<4; i++){
                int nx=x;
                int ny=y;
                while(true){
                    nx+=dx[i];
                    ny+=dy[i];
                    if((0 > nx || nx >= N) || (0 > ny || ny >= M) || arr[nx][ny] == 6){     // 범위를 넘거나 벽이면 break
                        break;
                    }else if((0 < arr[nx][ny] && arr[nx][ny] <= 5) || arr[nx][ny] == -1){      // cctv 번호(1~5)라면 통과(건너띄기)
                        continue;
                    }
                    arr[nx][ny]=-1;
                    total--;
                }
            }
        }
        ans=total;
        dfs(0);
        System.out.println(ans);
    }

    public static int move(int x, int y, int d){
        int c=0;
        int nx=x;
        int ny=y;
        while(true){
            nx=x+dx[d];
            ny=y+dy[d];
            if((0 > nx || nx >= N) || (0 > ny || ny >= M) || copy[nx][ny] == 6){
                return c;
            }else if((0 < copy[nx][ny] && copy[nx][ny] <= 5 ) || copy[nx][ny] == -1){
                x=nx;
                y=ny;
                continue;
            }
            copy[nx][ny]=-1;
            c++;
            x=nx;
            y=ny;
        }

    }

    public static void dfs(int cnt){
        if(cnt == cctv.size()){
            // 깊은 복사
            for(int i=0; i<N; i++){
                System.arraycopy(arr[i], 0, copy[i], 0, M);
            }
            c=0;
            for(int j=0; j<cctv.size(); j++){
                int x=cctv.get(j).x;
                int y=cctv.get(j).y;
                if(copy[x][y] == 1){
                    c+=move(x, y, tmp.get(j));
                }
                else if(copy[x][y] == 2){
                    c+=move(x, y, tmp.get(j));
                    c+=move(x, y, (tmp.get(j)+2)%4);
                }
                else if(copy[x][y] == 3){
                    c+=move(x, y, tmp.get(j));
                    c+=move(x, y, (tmp.get(j)+1)%4);
                }
                else{
                    c+=move(x, y, tmp.get(j));
                    c+=move(x, y, (tmp.get(j)+1)%4);
                    c+=move(x, y, (tmp.get(j)+2)%4);
                }
            }
            ans=Math.min(ans, total-c);
            return;
        }

        for(int i=0; i<4; i++){
            tmp.add(i);
            dfs(cnt+1);
            tmp.remove(tmp.size()-1);
        }
    }
    //ans=total;
}