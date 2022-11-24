// 문자열 S의 모든 접미사를 사전순으로 정렬한 다음 출력하는 문제
// 0부터 시작해서 끝까지 인덱스를 증가시키면서 모든 접미사를 구한다
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input=br.readLine();
        int input_size = input.length();
        LinkedList<String> lst = new LinkedList<String>();

        for(int i=0; i<input_size; i++){
            String sub_input = input.substring(i, input_size);
            lst.add(sub_input);
        }

        Collections.sort(lst);      // 정렬

        for(int i=0; i<lst.size(); i++){
            System.out.println(lst.get(i));
        }
    }
}