package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Greedy1931_2 {

	public static int N;
	public static LinkedList<Pair> ary;
	static int ret = Integer.MIN_VALUE;
	public static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		ary = new LinkedList<Pair>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) + 1;
			int end = Integer.parseInt(st.nextToken()) + 1;
			ary.add(new Pair(start, end));

		}

		Collections.sort(ary);
		dp = new int[N][ary.getLast().startTime + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < ary.getLast().startTime + 1; j++) {
				dp[i][j] = -10;
			}
		}
		dfs(0, 0, 0);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < ary.getLast().startTime + 1; j++) {
				ret = Math.max(ret, dp[i][j]);
			}
		}
		System.out.println(ret);

	}

	public static int dfs(int now, int startTime, int endTime) {

		if (now >= N) {

			return 0;
		}
		int newStartTime = ary.get(now).startTime;
		int newEndTime = ary.get(now).endTime;
		if (startTime == 9 && endTime == 12 && newStartTime == 13 && newEndTime == 15) {
		//if (startTime == 13 && endTime == 15 ) {
			System.out.println();
		}
		if (dp[now][startTime] != -10) {
			return dp[now][startTime];
		} else {
			if (endTime >= newStartTime) {
				return dp[now][startTime] = dfs(now + 1, startTime, endTime);
			} else {
				return dp[now][startTime] = Math.max(dfs(now + 1, startTime, endTime),
						dfs(now + 1, newStartTime, newEndTime) + 1);
			}
		}

	}

	static class Pair implements Comparable<Pair> {
		int startTime;
		int endTime;

		public Pair(int startTime, int endTime) {
			super();
			this.startTime = startTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.startTime > o.startTime) {
				return 1;
			} else if (this.startTime < o.startTime) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
