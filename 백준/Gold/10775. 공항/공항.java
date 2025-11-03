import java.io.*;
/*
 * [union-find 알고리즘 사용]
 * G개의 게이트(1 ~ G)
 * 공항에는 P개의 비행기가 순서대로 도착
 * i번째 비행기를 1 ~ G 게이트중 하나에 영구적으로 도킹
 * 비행기가 어느 게이트에도 도킹을 못하면
 * 	공항 폐쇄, 어떤 비행기도 도착 불가
 * 가장 많은 비행기를 공항에 도킹시키기 => 최대 몇 대 도킹?
 */
public class Main {
    static int G;
    static int P;
    static int[] parent;

    // 경로 압축을 포함한 find: x 이하의 가장 큰 비어있는 게이트(루트)를 반환
    static int find(int x) {
        if (x == 0) return 0;
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // a를 b의 루트로 연결 (여기서는 a가 채워진 게이트, b = a-1의 루트)
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine().trim());
            int root = find(g);        // g 이하에서 가장 큰 빈 게이트
            if (root == 0) {
                // 더 이상 도킹 불가 -> 공항 폐쇄
                break;
            } else {
                // 도킹 성공
                answer++;
                // 이제 root은 채워졌으니 root를 root-1의 집합과 합친다.
                union(root, root - 1);
            }
        }

        System.out.println(answer);
    }
}
