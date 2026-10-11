import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    // dfs
    // 맨해튼 거리 계산
    // depth -> N일때, 현재 거리 sum + 집 가는 거리 계산해서 최솟값 구하기.
    // 거리가 이미 최솟값을 넘었다면 return
    // 함수 -> 거리계산, dfs

    static int[][] locations;
    static boolean[] visited;
    static int N;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 회사(0), 집(1), 고객(2 ~ N+1)
            locations = new int[N + 2][2];
            visited = new boolean[N + 2];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < N + 2; i++) {
                locations[i][0] = Integer.parseInt(st.nextToken());
                locations[i][1] = Integer.parseInt(st.nextToken());
            }

            // 회사에서 출발, 방문 고객 0명, 이동거리 0
            dfs(0, locations[0], 0);

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int depth, int[] cur, int sum) {

        // 현재 거리가 이미 최솟값 이상이면 가지치기
        if (sum >= min) {
            return;
        }

        // 고객을 전부 방문했다면 집까지의 거리 계산
        if (depth == N) {
            int total = sum + distance(cur, locations[1]);
            min = Math.min(min, total);
            return;
        }

        // 회사와 집을 제외한 고객들만 탐색
        for (int i = 2; i < N + 2; i++) {
            if (visited[i]) {
                continue;
            }
            int nextDistance = distance(cur, locations[i]);
            
            visited[i] = true;
            dfs(depth + 1, locations[i], sum + nextDistance);
            visited[i] = false; // 백트래킹
        }
    }

    // 맨해튼 거리 계산
    static int distance(int[] start, int[] end) {
        return Math.abs(start[0] - end[0])
             + Math.abs(start[1] - end[1]);
    }
}