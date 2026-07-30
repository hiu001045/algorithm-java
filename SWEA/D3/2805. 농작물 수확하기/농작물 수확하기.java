
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] array = new int[N][N];

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					//br.read()했다가 안되서 gpt 도움. 공백없을때 기준
					array[i][j] = line.charAt(j)-'0';
				}
			}
			int result = sum(array, N);
			sb.append("#").append(tc).append(" ").append(result).append("\n");

		}
		System.out.println(sb);

	}

	static int sum(int[][] array, int N) {
		// 방향은 오른쪽아래, 왼쪽아래만 필요!
//		int[] dr = {1,1};
//		int[] dc = {1,-1};
		int sum = 0;
		int r = 0;
		int c = N / 2;
		// 시작지점 -> [0,N/2]
		for (int i = 0; i < N / 2 + 1; i++) {
			for (int j = 0; j < N / 2 + 1; j++) {
				sum += array[r + j][c - j];
			}
			r++;
			c++;
		}
		r = 1;
		c = N / 2;

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				sum += array[r + j][c - j];
			}
			r++;
			c++;
		}

		return sum;
	}
}
