import java.util.*;

class Solution {

    static int weakCount;
    static int[] gap;
    static boolean[] visited;
    static int answer;

    public int solution(int n, int[] weak, int[] dist) {

        weakCount = weak.length;
        answer = Integer.MAX_VALUE;

        // 1. 취약 지점 사이 거리 계산
        gap = new int[weakCount];

        for (int i = 0; i < weakCount - 1; i++) {
            gap[i] = weak[i + 1] - weak[i];
        }

        // 마지막 취약 지점에서 첫 번째 취약 지점까지 거리
        gap[weakCount - 1] = n - weak[weakCount - 1] + weak[0];

        // 2. 친구 이동 거리 정렬
        Arrays.sort(dist);

        visited = new boolean[dist.length];

        // 3. 모든 취약 지점을 시작점으로 선택
        for (int i = 0; i < weakCount; i++) {
            dfs(i, 0, 0, dist);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    // idx : 다음에 점검할 취약 지점
    // count : 사용한 친구 수
    // checked : 지금까지 점검한 취약 지점 수
    void dfs(int idx, int count, int checked, int[] dist) {

        // 1. 모든 취약 지점을 점검한 경우
        if (checked >= weakCount) {
            answer = Math.min(answer, count);
            return;
        }

        // 2. 이동 거리가 큰 친구부터 선택
        for (int i = dist.length - 1; i >= 0; i--) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;

            int move = 0;       // 현재 친구가 이동한 거리
            int covered = 1;    // 현재 친구가 점검한 취약 지점 수
            int current = idx;  // 현재 친구의 위치

            // 3. 현재 친구가 점검할 수 있는 취약 지점 계산
            while (checked + covered < weakCount) {

                // 다음 취약 지점까지 이동할 수 없는 경우
                if (move + gap[current] > dist[i]) {
                    break;
                }
                move += gap[current];

                // 다음 취약 지점으로 이동
                current = (current + 1) % weakCount;
                covered++;
            }

            int nextIdx = (current + 1) % weakCount;

            dfs(nextIdx, count + 1, checked + covered, dist);

            visited[i] = false;
        }
    }
}