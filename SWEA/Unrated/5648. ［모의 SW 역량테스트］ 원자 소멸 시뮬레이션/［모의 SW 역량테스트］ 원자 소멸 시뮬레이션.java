import java.io.*;
import java.util.StringTokenizer;

class Solution {
    // 상, 하, 좌, 우
    final static int[] DX = {0, 0, -1, 1};
    final static int[] DY = {1, -1, 0, 0};

    static class Atom {
        int x;      // 가로
        int y;      // 세로
        int d;      // 이동 방향
        int k;      // 보유 에너지
        boolean isDie;

        Atom(int x, int y, int d, int k, boolean isDie) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
            this.isDie = isDie;
        }
    }

    static int n;       // 1 <= 원자들의 수 <= 1,000
    static Atom[] atom;
    static int[][] visit;
    static boolean[][] collied;

    public static int move() {
        int energy = 0;
        int totalN = n;     // 남은 원자 수
        for(int cnt=0; cnt<4002; cnt++) {       // 최대 움직일 수 있는 이동 횟수
            // 1시간에 1씩 살아있는 원자를 움직이기
            for (int i = 0; i < n; i++) {
                if (atom[i].isDie) {        // 죽은 원자는 건너뛰기
                    continue;
                }

                visit[atom[i].x][atom[i].y]--;      // 이동 전 위치에 있는 원자 수 감소

                // 이동 후 원자 위치
                int nx = atom[i].x + DX[atom[i].d];
                int ny = atom[i].y + DY[atom[i].d];

                if (0 > nx || nx > 4000 || 0 > ny || ny > 4000) {      // 범위를 벗어났으면
                    atom[i].isDie = true;       // 죽음
                    totalN--;
                    continue;
                }

                visit[nx][ny]++;
                // 원자의 위치 갱신
                atom[i].x = nx;
                atom[i].y = ny;

                if(visit[nx][ny] >= 2) {
                    collied[nx][ny] = true;     // 충돌 발생 위치로 저장
                }
            }

            // 충돌한 원자 탐색
            for(int i=0; i<n; i++) {
                if(atom[i].isDie) {     // 이미 죽은 원자라면
                    continue;
                }
                if(collied[atom[i].x][atom[i].y]) {     // 충돌이 발생한 위치라면
                    if(visit[atom[i].x][atom[i].y] == 1) {
                        collied[atom[i].x][atom[i].y] = false;
                    }
                    visit[atom[i].x][atom[i].y]--;
                    totalN--;       // 남은 원자 수 감소
                    atom[i].isDie = true;
                    energy += atom[i].k;        // 에너지 방출
                }
            }

            if(totalN == 0) {       // 모든 원자가 죽었으면
                break;
            }
        }
        return energy;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());        // 총 테스트 케이스의 개수

        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            atom = new Atom[n];
            visit = new int[4005][4005];
            collied = new boolean[4005][4005];

            // 원자의 정보 입력 받기
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                atom[i] = new Atom((x + 1000) * 2, (y + 1000) * 2, d, k, false);
                visit[(x + 1000) * 2][(y + 1000) * 2]++;
            }

            System.out.println("#" + tc + " " + move());
        }
    }
}