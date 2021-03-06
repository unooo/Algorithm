package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BackTracking_15664 {
	public static int N;
	public static int M;
	public static StringBuffer sb;
	public static int[] numAry;
	public static HashSet<String> hset = new HashSet<String>();

	public static void main(String[] args) throws IOException {
		sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		 st = new StringTokenizer(br.readLine());
		 numAry=new int[N];
		 
		for (int i = 0; i < N; i++) {
			numAry[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numAry);
		int[] orderAry=new int[M];
		dfs(0,0,orderAry);
		System.out.println(sb.toString());
	}

	public static void dfs(int now, int idx, int[] orderAry) {

		if (now >= M) {
			String str = new String();
			for(int i = 0 ; i < orderAry.length;i++){
				str+=numAry[orderAry[i]]+" ";
			}
			
			if(!hset.contains(str)){
				hset.add(str);
				sb.append(str.toString());
				sb.append("\n");
			}
			return;
		}

		for (int i = idx; i < N; i++) {
			orderAry[now] = i;
			dfs(now + 1, i , orderAry);
		}

	}

}
