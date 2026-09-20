import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine());
            long count = 0;

            while (N != 2) {

                // N의 제곱근에서 소수점 버림
                long sqrt = (long) Math.sqrt(N);

                // N이 완전제곱수라면
                if (sqrt * sqrt == N) {
                    N = sqrt;
                    count++;
                }

                // 완전제곱수가 아니라면
                else {
                    // N보다 큰 가장 가까운 완전제곱수
                    long next = (sqrt + 1) * (sqrt + 1);

                    // +1을 몇 번 해야 하는지 한 번에 계산
                    count += next - N;

                    // 다음 완전제곱수로 이동
                    N = next;
                }
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(count)
              .append("\n");
        }

        System.out.print(sb);
    }
}