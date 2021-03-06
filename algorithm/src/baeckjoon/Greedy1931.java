package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Greedy1931 {

	public static int N;
	public static int ary[][];
	static int ret = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		ary=new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] order = new int[N];
		dfs(order,0);
		System.out.println(ret);

	}

	public static void dfs(int[] order, int now) {

		if (now >= N) {
			int num = check(order);
			if(num!=-1)
				ret=Math.max(ret, num);
			return;
		}

		order[now] = 1;
		dfs(order, now + 1);
		order[now] = 0;
		dfs(order, now + 1);

	}

	public static int check(int[] order) {
		int ret=0;
		int startTime=0;
		int endTime=0;
		for (int i = 0; i < order.length; i++) {
			
			if(order[i]==1){
				if(ary[i][0]>endTime){
					startTime=ary[i][0];
					endTime=ary[i][1];
					ret++;
				}else{
					ret=-1;
					break;
				}
			}
			
		}
		return ret;

	}

}
