class Solution {
    public int solution(int[] a) {
        int N = a.length;
        if (N <= 2) return N;

        int answer = 0;

        // 오른쪽 최솟값 미리 저장
        int[] rightMin = new int[N];
        rightMin[N - 1] = a[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            rightMin[i] = Math.min(a[i], rightMin[i + 1]);
        }

        // 왼쪽 최솟값은 변수 하나로 관리
        int leftMinNum = a[0];

        for (int i = 0; i < N; i++) {

            // 왼쪽 또는 오른쪽에서 최솟값이면 생존 가능
            if (a[i] <= leftMinNum || a[i] <= rightMin[i]) {
                answer++;
            }

            leftMinNum = Math.min(leftMinNum, a[i]);
        }

        return answer;
    }
}