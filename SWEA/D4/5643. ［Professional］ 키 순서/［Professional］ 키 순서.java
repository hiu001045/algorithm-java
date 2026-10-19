
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Solution {
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer>[] reverse;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			int answer = 0;
			adj = new ArrayList[N + 1];
			reverse = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) {
				adj[i] = new ArrayList<>();
				reverse[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int small = Integer.parseInt(st.nextToken());
				int big = Integer.parseInt(st.nextToken());
				adj[small].add(big);
				reverse[big].add(small);
			}

			for (int i = 1; i <= N; i++) {
				visited = new boolean[N + 1];
				dfs(i, adj);
				int biggerCount = 0;
				for (int j = 1; j <= N; j++) {
					if (j == i)
						continue;
					if (visited[j])
						biggerCount++;
				}
				visited = new boolean[N + 1];
				dfs(i, reverse);
				int smallerCount = 0;
				for (int j = 1; j <= N; j++) {
					if (j == i)
						continue;
					if (visited[j])
						smallerCount++;
				}

				if (biggerCount + smallerCount == N - 1) {
					answer++;
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");

		}
		System.out.println(sb);
	}

	static void dfs(int start, ArrayList<Integer>[] graph) {
		visited[start] = true;

		for (int next : graph[start]) {
			if (visited[next]) {
				continue;
			}
			dfs(next, graph);
		}

	}

}
