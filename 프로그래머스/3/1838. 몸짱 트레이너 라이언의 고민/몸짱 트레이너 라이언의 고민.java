import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    class Position {
        int x;
        int y;
        
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    /*
        n: 0 < 정사각형 한 변의 길이 <= 10
        m: 0 <= 손님 수 <= 1,000
        timetable: 각 손님의 예약된 입실 또는 퇴실 시간
    */
    public int solution(int n, int m, int[][] timetable) {
        List<int[]> timeLst = new ArrayList();
        int cnt = 0;        // 순간의 겹치는 사람 수
        int timeMax = 0;        // 겹치는 시간대 최대 갯수
        int maxDistance = 2 * (n - 1);        // 락커들 간의 최대 거리
        List<Position> room = new ArrayList();      // 방에서 배정받은 사람들의 위치
        
        // 겹치는 시간대를 구하기 위해 리스트 초기화
        for(int i=0; i<m; i++) {
            timeLst.add(new int[]{timetable[i][0], 1});        // 입실
            timeLst.add(new int[]{timetable[i][1], -1});       // 퇴실
        }
        
        // 시간이 같으면 퇴실 시간이 빠른 순으로 정렬
        Collections.sort(timeLst, (x, y) -> x[0] == y[0] ? y[1] - x[1] : x[0] - y[0]);
        
        // +1 또는 -1 되면서 겹치는 사람의 최대를 구할 수 있다.
        for(int i=0; i<timeLst.size(); i++) {
            cnt += timeLst.get(i)[1];       // 현재 시점에서 겹치는 사람의 수
            timeMax = Math.max(timeMax, cnt);       // 겹치는 사람의 수 갱신
        }
        
        if(timeMax <= 1) {      // 겹치는 사람이 없다면
            return 0;       // 최소 거리 0
        }
        
        // 빠른 탐색을 위해 최대 거리부터 역순으로 탐색하기
        for(int i=maxDistance; i>=1; i--) {
            for(int sc=0; sc<n; sc++) {     // 첫 번재 행에서 열의 시작점
                room.clear();       // 방 초기화
                // 겹치는 사람 수만큼 i만큼의 거리를 두고 배치해야 함
                for(int row=0; row<n; row++) {      // 행
                    for(int col=0; col<n; col++) {     // 열
                        if(row == 0 && sc > col) {      // 첫 번째 행(0)에서 시작 열을 바꿔가면서 탐색
                            continue;
                        }

                        // 거리 비교
                        boolean flag = false;       // 거리 조건을 만족하는지
                        for(Position pos : room) {      // 방에 있는 사람 위치
                            if(Math.abs(pos.x - row) + Math.abs(pos.y - col) < i) {     // 거리가 i보다 작다면
                                flag = true;    
                                break;
                            }
                        }

                        if(flag) {      // 거리 조건이 안 맞았다면
                            continue;
                        }

                        // 모든 좌표들과 i만큼의 거리를 유지할 수 있다면
                        room.add(new Position(row, col));       // 현재 좌표 추가

                        if(room.size() == timeMax) {       // 모든 사람을 다 배정했다면
                            return i;       // 할당된 락커들 간 거리 중 최소거리 
                        }
                    }
                }
            }
        }      
        return 0;
    }
}