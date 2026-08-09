
import java.util.ArrayDeque;
import java.util.Queue;
class Solution {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int sec = 0;

		// 트럭은 1초에 한대씩 올라갈 수 있음.
		// bridge_length와 weight를 사용해서 올라갈 수 있는지 없는지 판단.
		// 다리를 큐로 생각해서, 다리 기준으로 길이/ 무게/ 조건 넣어서 넣고 제거하고 하는 식으로 조건 짜봐야겠다 생각.

		Queue<int[]> bridge = new ArrayDeque<>();
		int brWeight = 0;
		int brCount = 0;
		int i = 0;
		// 종료조건을 어떻게?
		while (i < truck_weights.length || !bridge.isEmpty()) {
			// NullPointException 방지
			if (!bridge.isEmpty() && sec >= bridge.peek()[1] + bridge_length) {
				// 시간이 지났다면 다리에서 트럭 제거, 무게 및 대수 낮추기
				brWeight -= bridge.poll()[0];
				brCount--;
			}

			if (i < truck_weights.length && weight >= brWeight + truck_weights[i] && bridge_length > brCount) {
				// 다리무게, 다리길이 제한 통과하는지 확인 후 다리에 트럭 배치
				// 한대를 넣는 기준이기 떄문에 bridge_length> brCount
				bridge.offer(new int[] { truck_weights[i], sec });
				brWeight += truck_weights[i];

				i++;
				brCount++;
				sec++;

			} else {
				sec++;
			}

//			if (sec > bridge.peek()[1] + bridge_length) {
//				// 시간이 지났다면 다리에서 트럭 제거, 무게 및 대수 낮추기
//				brWeight -= bridge.poll()[0];
//				brCount--;
//			}
//순서가 다리에 있는 트럭을 빼고 -> 다음 트럭을 넣어야하는데, 이게 밑으로 갔을때 순서대로 안되서 옮김.

		}

		return sec;
	}
}
