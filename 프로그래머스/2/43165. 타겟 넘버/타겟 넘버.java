class Solution {

	int target;
	int count;
	int[] numbers;

	public int solution(int[] numbers, int target) {
		// dfs로 depth가 5인거 찾음
		this.numbers = numbers;
		this.target = target;
		count = 0;
		dfs(0, 0, '+');
		dfs(0, 0, '-');

		return count;
	}

	public void dfs(int start, int depth, char plma) {

		if (plma == '+') {
			start += numbers[depth];
		}
		if (plma == '-') {
			start -= numbers[depth];
		}

		if (depth == numbers.length - 1) {
			if (start == target)
				count++;
			return;
		}

		dfs(start, depth + 1, '+');
		dfs(start, depth + 1, '-');

	}
}
