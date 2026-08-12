
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
	//bfs로 한칸씩 탐색, 1인곳만 갈 수 있게 설정
	//탐색한 곳은 배열 0으로 변경.
	//한칸씩 갈때마다 count ++;
	//최단 count 반환?
	//전체 탐색 완료했는데 상대팀 진영에 1이 남아 있다면 -1 반환.

	int[][] maps;
	// 아래부터 시계방향
	int[] dx = {1,0,-1,0};
	int[] dy = {0,-1,0,1};
	int[][] dis;
	Deque<int[]> Q; 
	public int solution(int[][] maps) {
		this.maps = maps;
		dis = new int[maps.length][maps[0].length];
		int answer = 0;
		
		Q = new ArrayDeque<>();
		bfs(0,0);
		
		answer = dis[maps.length-1][maps[0].length-1];
		
		if(maps[maps.length-1][maps[0].length-1] == 1) {
			answer = -1;
		}
		
	
		return answer;
	}
	
	public void bfs(int x, int y) {
		Q.offer(new int[] {x,y});
		maps[x][y] = 0;
        dis[x][y] = 1;
		while(!Q.isEmpty()) {
			int[] tmp = Q.poll();
			for(int i=0; i<4; i++) {
				int nx = tmp[0] + dx[i];
				int ny = tmp[1] + dy[i];
				if(nx>=0 && nx<= maps.length-1 && ny>=0 && ny<= maps[0].length -1 && maps[nx][ny] == 1) {
					maps[nx][ny] = 0;
					Q.offer(new int[] {nx,ny});
					dis[nx][ny] = dis[tmp[0]][tmp[1]] +1;
				}
			}
		}
		
	}
}
