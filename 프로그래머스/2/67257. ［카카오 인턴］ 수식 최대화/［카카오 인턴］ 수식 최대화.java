import java.util.*;

class Solution {

    // 연산자 순열 만들기(인덱스, 모든 연산자, 방문기록, 연산자 순열의 한 행, 연산자 순열)
    public ArrayList<ArrayList<String>> dfs(int idx, String[] operator, boolean[] visited, ArrayList<String> temp, ArrayList<ArrayList<String>> ans){
        if(idx == 3) {
            ArrayList<String> copy=new ArrayList<>(temp);
            ans.add(copy);
            return ans;
        }
        for(int i=0; i<3; i++){
            if(!visited[i]){
                visited[i]=true;
                temp.add(operator[i]);
                dfs(idx+1, operator, visited, temp, ans);
                visited[i]=false;
                temp.remove(temp.size()-1);		// 맨 끝값 제거
            }
        }
        return ans;
    }

    // 계산
    public long cal(long a, long b, String op){
        if(op.equals("*")) return a*b;
        else if(op.equals("+")) return a+b;
        else return a-b;
    }

    public long solution(String expression) {
        long answer = 0;
        ArrayList<String> num=new ArrayList<>(Arrays.asList(expression.split("[^0-9]+")));   // 숫자 추출
        ArrayList<String> op=new ArrayList<>(Arrays.asList(expression.split("[0-9]+")));    // 연산자 추출
        op.remove(0);	// "" 제거
        String[] operator={"*", "+", "-"};
        boolean[] visited=new boolean[3];
        ArrayList<ArrayList<String>> lst=dfs(0, operator, visited, new ArrayList<String>(), new ArrayList<ArrayList<String>>());
            for(ArrayList op_lst : lst){    // 연산자 순위
                ArrayList<String> copy_num=new ArrayList<>();
                ArrayList<String> copy_op=new ArrayList<>();
                copy_num.addAll(num);
                copy_op.addAll(op);
                for(int i=0; i<3; i++){     // 연산자 우선순위
                        for(int j=0; j<copy_op.size(); j++){     // 추출한 연산자
                            if(op_lst.get(i).equals(copy_op.get(j))){    
                            long a=Long.parseLong(copy_num.get(j));
                            long b=Long.parseLong(copy_num.get(j+1));
                            copy_num.set(j, String.valueOf(cal(a, b, copy_op.get(j))));
                            copy_num.remove(j+1);
                            copy_op.remove(j);  
                            j--;
                    }
                }
            }
                long total=Long.parseLong(copy_num.get(0));
                answer=Math.max(answer, Math.abs(total));
        }

    return answer;
    }
}