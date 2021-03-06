package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BackTracking_15655 {
	public static int N;
	public static int M;
	public static StringBuffer sb;
	public static int[] numAry;

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
		int[] order = new int[N];
		
		Arrays.sort(numAry);
		dfs(0, order,0);
		System.out.println(sb);

	}

	public static void dfs(int now, int[] order,int idx) {
		if (now >= M) {
			if(order[M-1]!=0&&numAry[order[M-1]]==numAry[order[M-1]-1]){
				return;
			}

			for (int i = 0; i < M; i++) {
				sb.append(numAry[order[i]]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = idx; i < N; i++) {
			
			
				order[now] = i;
				dfs(now + 1, order, i+1);
				
			
		}

	}
}
