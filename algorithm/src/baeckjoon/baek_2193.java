package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek_2193 {
	static long dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		dp = new long[n + 1][2];
		
		dp[1][1] = 1;
		if(n>=2)
		dp[2][0] = 1;
		for (int i = 3; i <= n; i++){
			dp[i][0] = dp[i-1][0]+dp[i-1][1];
			dp[i][1] = dp[i-1][0];
		}

		System.out.println(dp[n][0]+dp[n][1]);
	}

}
