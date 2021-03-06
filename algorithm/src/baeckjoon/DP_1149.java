package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1149 {

	public static int N;
	public static int[][] score;
	public static int dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		score = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[N + 1][3];
		/*
		 * int num1=dfs(0,0); num1=Math.min(num1, dfs(0,1)); num1=Math.min(num1,
		 * dfs(0,2)); System.out.println(num1);
		 */
		calc();
	}

	public static void calc() {
		dp[1][0] = score[1][0];
		dp[1][1] = score[1][1];
		dp[1][2] = score[1][2];
		int color = 0; // 이전단계에 선택된게 컬러야
		for (int i = 2; i < N + 1; i++) {
			for (int j = 0; j < 3; j++) {
				int num1 = 0, num2 = 0;
				color=j;
				if (color == 0) {
					num1 = 1;
					num2 = 2;
				} else if (color == 1) {
					num1 = 0;
					num2 = 2;
				} else if (color == 2) {
					num1 = 0;
					num2 = 1;
				}
				
				int val=0;
				if (dp[i-1][num1] > dp[i-1][num2]) {
					val=dp[i-1][num2];
				} else if (dp[i-1][num1] < dp[i-1][num2]) {
					val=dp[i-1][num1];
				} else {
					val=dp[i-1][num2];
				}
				dp[i][color] = score[i][color] + val;
				
			}
		}
		int ret =Integer.MAX_VALUE;
		for(int i = 0 ; i < 3 ; i ++){
			ret=Math.min(ret, dp[N][i]);
		}
		System.out.println(ret);

	}

	public static int dfs(int now, int color) {
		if (now >= N) {
			return 0;
		}
		if (dp[now][color] != 0)
			return dp[now][color];

		int num = Integer.MAX_VALUE, temp1 = 0, temp2 = 0;
		int num1 = 0, num2 = 0;
		if (color == 0) {
			num1 = 1;
			num2 = 2;
		} else if (color == 1) {
			num1 = 0;
			num2 = 2;
		} else if (color == 2) {
			num1 = 0;
			num2 = 1;
		}

		if (dp[now + 1][num1] != 0)
			temp1 = dp[now + 1][num1];
		else
			temp1 = dfs(now + 1, num1);

		if (dp[now + 1][num2] != 0)
			temp2 = dp[now + 1][num2];
		else
			temp2 = dfs(now + 1, num2);

		num = Math.min(temp1, temp2);

		return dp[now][color] = num + score[now + 1][color];

	}

}
