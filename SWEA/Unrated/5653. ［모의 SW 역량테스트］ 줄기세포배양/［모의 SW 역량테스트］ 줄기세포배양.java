import java.util.Scanner;

class Solution {
    static class Node {
        int heart;        // 입력으로 받은 생명력 수치
        int curHeart;        // 현재 생명력 수치
        int status;        // 0: 빈 공간, 1: 비활성상태, 2: 활성상태, 3: 죽은 상태

        Node(int heart, int curHeart, int status) {
            this.heart = heart;
            this.curHeart = curHeart;
            this.status = status;
        }
    }

    static int n;        // 세로
    static int m;        // 가로
    static int k;        // 배양 시간
    static Node[][][] arr;

    final static int MAX_SIZE = 360;
    final static int[] DX = {-1, 1, 0, 0};
    final static int[] DY = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            m = sc.nextInt();
            k = sc.nextInt();
            arr = new Node[2][MAX_SIZE][MAX_SIZE];
            int answer = 0;

            // 초기화
            for(int x=0; x<MAX_SIZE; x++) {
                for(int y=0; y<MAX_SIZE; y++) {
                    arr[0][x][y] = new Node(0, 0, 0);
                    arr[1][x][y] = new Node(0, 0, 0);
                }
            }

            // 줄기 세포의 생명력 입력받기
            for (int i = k/2+1; i < n+k/2+1; i++) {
                for (int j = k/2+1; j < m+k/2+1; j++) {
                    int life = sc.nextInt();        // 생명력 수치
                    if(life == 0) {
                        continue;
                    }
                    arr[0][i][j] = new Node(life, 0,1);
                }
            }

            int curMap = 0;
            for (int i = 0; i < k; i++) {
                for (int x = 0; x < MAX_SIZE; x++) {
                    for (int y = 0; y < MAX_SIZE; y++) {
                        Node node = arr[curMap][x][y];
                        if(node.status == 3) {        // 죽은 줄기세포라면
                            arr[1 - curMap][x][y].status = arr[curMap][x][y].status;
                            continue;
                        }
                        if (node.status == 1) {        // 비활성 상태일때
                            arr[1 - curMap][x][y].curHeart = arr[curMap][x][y].curHeart + 1;
                            arr[1 - curMap][x][y].heart = arr[curMap][x][y].heart;

                            // 비활성 시간이 heart만큼 지났다면
                            if(arr[1 - curMap][x][y].curHeart == arr[1 - curMap][x][y].heart) {
                                // 활성 상태로 변경
                                arr[1 - curMap][x][y].status = 2;
                            } else {
                                arr[1 - curMap][x][y].status = 1;
                            }
                        } else if(node.status == 2) {       // 활성 상태일때
                            // 활성 상태가 된 후, 첫 시간이라면
                            if(arr[curMap][x][y].curHeart == arr[curMap][x][y].heart) {
                                // 상하좌우 탐색
                                for(int d=0; d<4; d++) {
                                    int nx = x + DX[d];
                                    int ny = y + DY[d];

                                    // 줄기 세포가 번식하는 방향이 비어있다면
                                    if(arr[curMap][nx][ny].status == 0) {
                                        // 하나의 줄기 세포가 번식하려고 할때
                                        if(arr[1 - curMap][nx][ny].status == 0) {
                                            arr[1 - curMap][nx][ny].status = 1;
                                            arr[1 - curMap][nx][ny].heart = arr[curMap][x][y].heart;
                                            arr[1- curMap][nx][ny].curHeart = 0;
                                        }
                                        // 두 개 이상의 줄기 세포가 셀에 동시에 번식할 때
                                        // 생명력 heart가 높은 줄기 세포가 셀을 차지
                                        else if(arr[1 - curMap][nx][ny].status == 1 && arr[1 - curMap][nx][ny].heart < arr[curMap][x][y].heart) {
                                            arr[1 - curMap][nx][ny].heart = arr[curMap][x][y].heart;
                                        }
                                    }
                                }
                            }

                            // 활성 시간 증가
                            arr[1 - curMap][x][y].curHeart = arr[curMap][x][y].curHeart - 1;
                            // 활성 상태 시간이 Heart 시간 만큼 지났을 때
                            if(arr[1 - curMap][x][y].curHeart == 0) {
                                // 줄기 세포가 죽음
                                arr[1 - curMap][x][y].status = 3;
                            } else {
                                arr[1 - curMap][x][y].status = 2;
                            }
                        }
                    }
                }
                // 다음 상태를 저장해 놓은 map을 현재 map으로 변경
                curMap = 1 - curMap;
            }

            // k시간 후 살아있는 줄기 세포(비활성 상태 + 활성 상태)의 총 개수 구하기
            for(int x=0; x<MAX_SIZE; x++) {
                for(int y=0; y<MAX_SIZE; y++) {
                    if(arr[curMap][x][y].status == 1 || arr[curMap][x][y].status == 2) {     // 죽은 상태가 아니면
                        answer++;
                    }
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }
}