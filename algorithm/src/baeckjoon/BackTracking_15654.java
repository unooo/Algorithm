package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BackTracking_15654 {
	public static int N;
	public static int M;
	public static StringBuffer sb;
	public static int[] numAry;
	public static HashSet<String> hset;

	public static void main(String[] args) throws IOException {
		sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		numAry = new int[N];
		for (int i = 0; i < N; i++) {
			numAry[i] = Integer.parseInt(st.nextToken());
		}
		int[] order = new int[M];
		
		int[] flag = new int[N];
		Arrays.sort(numAry);
		dfs(0, order, flag);
		System.out.println(sb);

	}

	public static void dfs(int now, int[] order, int[] flag) {
		if (now >= M) {

			for (int i = 0; i < M; i++) {
				sb.append(numAry[order[i]]).append(" ");
			}

			sb.append("\n");
			return;
		}
//if(now+1!=M&&numAry[order[now]]==numAry[i]){
		for (int i = 0; i < N; i++) {
			if (flag[i] == 0) {
			
				flag[i] = 1;
				order[now] = i;
				dfs(now + 1, order, flag);
				flag[i] = 0;

			}
		}
		order[now] = 0;

	}
}
