import java.util.*;

class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        Map<Long, Long> map = new HashMap<>();
        
        for(int i = 0; i < room_number.length; i++){
            answer[i] = dfs(room_number[i], map);
        }
    
        return answer;
    }
    
    public long dfs(long number, Map<Long, Long> map) {
        if(!map.containsKey(number)){       // 빈 방이 있으면
            map.put(number, number + 1);
            return number;
        }
        long result = dfs(map.get(number), map);
        map.put(number, result);
        return result;
    }
}