
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static List<Integer>[] graph;
	static int[] indegree;
	static int V;
	static List<Integer> answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			graph = new ArrayList[V + 1];
			indegree = new int[V + 1];
			answer = new ArrayList<>();
			for (int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < E ; i ++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				//
				graph[start].add(end);
				indegree[end]++;
			}
			Queue<Integer> Q = new ArrayDeque<>();
			for(int i = 1 ; i<= V ; i++) {
				if(indegree[i] == 0 ) {
					Q.offer(i);
				}
			}
			while(!Q.isEmpty()) {
				int cur = Q.poll();
				answer.add(cur);
				
				for(int next : graph[cur]) {
					indegree[next]--;
					
					if(indegree[next] == 0 ) {
						Q.offer(next);
					}
				}
			}
			sb.append("#").append(tc).append(" ");
			for(int i = 0 ; i< answer.size(); i++) {
				sb.append(answer.get(i)).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
