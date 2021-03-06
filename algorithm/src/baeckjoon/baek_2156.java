package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_2156 {
	static int N;
	static long dp[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		dp = new long[N + 1][3];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			long case1 = dp[i-1][0]+num;
			long case2 = dp[i-1][0];
			long case3=dp[i-1][1]+num;
			long case4=dp[i-1][1];
			long case5=dp[i-1][2];
			
			dp[i][1]=case1;
			dp[i][2]=case3;
			dp[i][0]=Math.max(case5, Math.max(case2,case4));

		}

		System.out.println(Arrays.stream(dp[N]).max().getAsLong());

	}
}
