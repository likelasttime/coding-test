import java.io.*;
import java.util.*;

class Pos{
    int x;
    int y;
    public Pos(int x, int y){
        this.x=x;
        this.y=y;
    }
}

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String nm=br.readLine();
        StringTokenizer st=new StringTokenizer(nm, " ");
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        int[][] visit=new int[n][m];
        String[][] lst=new String[n][m];
        Deque<Pos> que=new ArrayDeque<>();
        int[] d=new int[2];     // 도착 지점 행, 열
        int[] dx={1, -1, 0, 0};
        int[] dy={0, 0, 1, -1};
        boolean flag=false;

        // 지도 입력
        for (int i=0; i<n; i++){
            String tmp=br.readLine();

            for(int j=0; j<m; j++){
                String s=tmp.substring(j, j+1);
                //int[] xy={i, j};
                if(s.equals("S")){
                    que.addFirst(new Pos(i, j));     // 시작 추가
                }
                else if(s.equals("*")){
                    que.add(new Pos(i, j));     // 물 추가
                }
                else if(s.equals("D")){
                    d[0]=i;
                    d[1]=j;  // 도착 추가
                }
                lst[i][j]=s;
            }
        }


        while (!que.isEmpty()){
            if(flag){
               break;
            }
            Pos xy=que.pollFirst();

            for(int i=0; i<4; i++){
                int nx=xy.x+dx[i];
                int ny=xy.y+dy[i];
                if(0>nx || nx>=n || ny<0 || ny>=m) continue;
                if(lst[xy.x][xy.y].equals("*")){      // 물
                    if(lst[nx][ny].equals("S") || lst[nx][ny].equals(".")){
                        lst[nx][ny]="*";
                        que.add(new Pos(nx,ny));
                    }
                }else if(lst[xy.x][xy.y].equals("S")){     // 고슴도치 위치
                    if(lst[nx][ny].equals(".")){
                        lst[nx][ny]="S";
                        que.add(new Pos(nx, ny));
                        visit[nx][ny]=visit[xy.x][xy.y]+1;
                    }else if(lst[nx][ny].equals("D")) {
                        flag=true;
                        visit[nx][ny]=visit[xy.x][xy.y]+1;
                        break;
                    }
                }
            }
        }

        if(visit[d[0]][d[1]] != 0){
            System.out.println(visit[d[0]][d[1]]);
        }else{
            System.out.println("KAKTUS");
        }
    }
}