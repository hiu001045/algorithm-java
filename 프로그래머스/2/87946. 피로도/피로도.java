public class Solution {
	boolean[] visited;
	int max = Integer.MIN_VALUE;
	public int solution(int k, int[][] dungeons) {
	
		
		visited = new boolean[dungeons.length];
		//dfs -> 남은 피로도 -> 던전 돌 수 있는지 체크 -> 돌기
		dfs(0, k, dungeons);
		
		return max;
	}
	
	public void dfs(int count, int k, int[][] dungeons) {
		max = Math.max(count, max);
		
		for(int i=0;i<dungeons.length;i++) {
			if(!visited[i] && k>= dungeons[i][0]) {
				visited[i] = true;
				k = k - dungeons[i][1];
				dfs(count+1,k,dungeons);
				visited[i] = false;
				k = k + dungeons[i][1];
			}
		}
	}
}
