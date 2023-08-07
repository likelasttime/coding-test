import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


class Main
{	
	static List<Integer> lst;
	static int k;
	static int n;
	static StringBuilder sb;
	
	public static void main(String args[]) throws Exception
	{	
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.parseInt(st.nextToken());		// 사람 수
		k = Integer.parseInt(st.nextToken());		// 양의 정수
		lst = new LinkedList<>();
		
		for(int i=1; i<=n; i++) {
			lst.add(i);
		}
		
		sb = new StringBuilder();
		sb.append("<");
		
		int idx = 0;		// 이전에 삭제한 인덱스 
		while(n > 1) {
			idx = play(idx);
			n--;
		}
		
		sb.append(lst.get(0));	
		sb.append(">");
		System.out.print(sb);
		
	}	
	
	public static int play(int idx) {
		int num = lst.remove(((k+idx-1) % n));	
		sb.append(num).append(", ");
		return (k+idx-1) % n;
	}
}
