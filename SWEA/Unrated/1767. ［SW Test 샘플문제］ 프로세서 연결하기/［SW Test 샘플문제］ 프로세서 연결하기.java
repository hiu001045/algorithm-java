
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static List<int[]> cores;
	static int maxCore;
	static int minWire;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();

			maxCore = 0;
			minWire = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (i != 0 && i != N - 1 && j != 0 && j != N - 1) {
							cores.add(new int[] { i, j });
						}
					}
				}
			}

			dfs(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(minWire).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int index, int connected, int wireLength) {
		if (index == cores.size()) {
			if (connected > maxCore) {
				maxCore = connected;
				minWire = wireLength;
			} else if (connected == maxCore) {
				minWire = Math.min(minWire, wireLength);
			}

			return;

		}

		int[] core = cores.get(index);
		int r = core[0];
		int c = core[1];

		for (int dir = 0; dir < 4; dir++) {
			if (canConnect(r, c, dir)) {
				int length = setWire(r, c, dir, 2);

				dfs(index + 1, connected + 1, wireLength + length);

				setWire(r, c, dir, 0);
			}
		}

		dfs(index + 1, connected, wireLength);
	}

	static int setWire(int r, int c, int dir, int value) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];

		int length = 0;
		while (nr >= 0 && nr < N && nc >= 0 && nc < N) {

			map[nr][nc] = value;

			length++;

			nr += dr[dir];
			nc += dc[dir];

		}
		return length;
	}

	static boolean canConnect(int r, int c, int dir) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];

		while (nr >= 0 && nr < N && nc >= 0 && nc < N) {

			if (map[nr][nc] != 0) {
				return false;
			}

			nr += dr[dir];
			nc += dc[dir];
		}
		return true;
	}
}
