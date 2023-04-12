class Solution {
    
    public int solution(int[] cookie) {
        int answer = 0;
        
        for(int i=0; i < cookie.length-1; i++){
            int left = i;
            int right = i + 1;
            int left_total = cookie[i];
            int right_total = cookie[i + 1];
            
            while (true) {
                if (left_total == right_total) {
                    answer = Math.max(answer, left_total);
                }
                if (left_total >= right_total && right < cookie.length - 1) {
                    right += 1;
                    right_total += cookie[right];
                }else if(left_total < right_total && left > 0) {
                    left -= 1;
                    left_total += cookie[left];
                }else{
                    break;
                }
            }
        }
        
        return answer;
    }
}