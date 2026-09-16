
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] desserts;
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, -1, 1 };

	static int answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			desserts = new int[N][N];
			answer = -1;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					desserts[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 사각형을 만들 수 있는 모든 시작점 탐색
			// row -> 아래로 최소 2칸 필요
			// col -> 좌우로 최소 한 칸씩 필요
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					makeRectangle(i, j);
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	static void makeRectangle(int row, int col) {

		int len1, len2;

		// 두 변의 길이를 정해 만들 수 있는 모든 사각형 탐색
		for (len1 = 1; len1 <= N - 2; len1++) {
			for (len2 = 1; len2 <= N - 2; len2++) {

				// 사각형의 꼭짓점이 배열 범위를 벗어나면 제외
				if (col + len1 >= N || row + len1 + len2 >= N || col - len2 < 0)
					continue;

				// 중복되는 디저트가 없는 경우 최대 방문 수 갱신
				if (duplicate(row, col, len1, len2)) {
					answer = Math.max(answer, (len1 + len2) * 2);
				}
			}
		}
	}

	static boolean duplicate(int row, int col, int len1, int len2) {

		Set<Integer> set = new HashSet<>();

		// 방향별 이동 횟수
		int[] len = { len1, len2, len1, len2 };

		// 사각형의 네 변을 순서대로 이동하며 디저트 저장
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < len[i]; j++) {

				set.add(desserts[row][col]);

				row += dr[i];
				col += dc[i];
			}
		}

		// 방문한 카페 수와 Set의 크기가 같으면 중복 없음
		if (set.size() == (len1 + len2) * 2) {
			return true;
		}

		return false;
	}
}