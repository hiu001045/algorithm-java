import java.util.*;

class Solution {
    static List<int[]>[] graph;
    static int N;
    static int[] Gates;
    static int[] Summits;
    static int[] answer;
    static int[] intensity;
    static boolean[] isGate;
    static boolean[] isSummit;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        N = n;
        Gates = gates;
        Summits = summits;

        graph = new ArrayList[N + 1];
        intensity = new int[N + 1];
        isGate = new boolean[N + 1];
        isSummit = new boolean[N + 1];
        answer = new int[]{0, Integer.MAX_VALUE};

        // 1. 인접 리스트 초기화
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 2. 양방향 그래프 생성
        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int weight = path[2];

            graph[from].add(new int[]{to, weight});
            graph[to].add(new int[]{from, weight});
        }

        // 3. 출입구, 산봉우리 구분
        for (int gate : Gates) {
            isGate[gate] = true;
        }

        for (int summit : Summits) {
            isSummit[summit] = true;
        }

        // 4. 다익스트라 실행
        dijkstra();

        // 5. 산봉우리 번호 오름차순 정렬
        Arrays.sort(Summits);

        // 6. intensity가 가장 작은 산봉우리 선택
        for (int summit : Summits) {
            if (answer[1] > intensity[summit]) {
                answer[0] = summit;
                answer[1] = intensity[summit];
                
            }
        }

        return answer;
    }

    static void dijkstra() {
        Arrays.fill(intensity, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        // 모든 출입구를 PQ에 삽입
        for (int gate : Gates) {
            intensity[gate] = 0;
            pq.offer(new int[]{gate, 0});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int now = cur[0];
            int cost = cur[1];

            // 이미 더 좋은 경로가 존재하면 skip
            if (intensity[now] < cost) continue;

            // 산봉우리에 도착하면 추가 탐색 금지
            if (isSummit[now]) continue;

            for (int[] edge : graph[now]) {
                int next = edge[0];
                int weight = edge[1];

                // 다른 출입구 방문 금지
                if (isGate[next]) continue;

                // 기존 intensity와 새로운 간선 가중치 중 최댓값
                int nextIntensity = Math.max(cost, weight);

                // 더 작은 intensity로 도달할 수 있다면 갱신
                if (intensity[next] > nextIntensity) {
                    intensity[next] = nextIntensity;

                    pq.offer(new int[]{next, nextIntensity});
                }
            }
        }
    }
}