package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2579 {

	public static int stair;
	public static int stairScore[];
	public static int[][] dp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		stair = Integer.parseInt(st.nextToken());
		stairScore = new int[stair + 1];
		dp = new int[300 * 10][2]; // 몇번쨰계단에 몇칸쩜프로왔는가
		for (int i = 0; i < stair; i++) {
			st = new StringTokenizer(br.readLine());
			stairScore[i + 1] = Integer.parseInt(st.nextToken());
		}
		// System.out.println(dfs(0,0,0));
		System.out.println(dfs(0, 0, 0));

	}

	public static int dfs(int nowStep, int nowStair, int tp) {
		if (nowStair == stair) {
			return stairScore[nowStair];
		}
		if(nowStair>stair)
			return  Integer.MIN_VALUE;

		
		int num1 = Integer.MIN_VALUE, num2 = Integer.MIN_VALUE;
		int temp = 1;
		
		if (nowStep == 0)
			temp = 0;
		if (tp == 0) { // 이전에 두칸올라와서 한칸만 올라가기가 가능한경우

			if (dp[nowStair + 1][temp] != 0) {
				num2 = dp[nowStair + 1][temp];
			} else {
				num2 = dfs(nowStep + 1, nowStair + 1, temp);
			}
		}
		
		
	

		if (dp[nowStair + 2][0] != 0) {// 두계단을 올라감
			num1 = dp[nowStair + 2][0];
		} else {
			num1 = dfs(nowStep + 1, nowStair + 2, 0);
		}
		

		return dp[nowStair][tp] = Math.max(num1, num2) + stairScore[nowStair];

	}

}
