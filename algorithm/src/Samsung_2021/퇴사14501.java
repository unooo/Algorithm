package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사14501 {

	static int N;
	static int graph[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		graph = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
		}
		path = new int[N];
		dfs(0);
		System.out.println(ret);
	}

	// 중복순열
	public static int ret = Integer.MIN_VALUE;
	public static int[] path;

	public static void dfs(int next) {
		if (next >= N) {
		 int piv=0;
		 int sum=0;
		 
		 for(int i = 0 ; i < N ;i++){
			 if(path[i]!=0){
				 if(i<piv||i+graph[i][0]>N){
					 return;
				 }else{
					sum+=graph[i][1]; 
					piv=i+graph[i][0];
				 }
			 }
		 }
		 
		 ret=Math.max(ret, sum);
		 return;
		}
		for (int i = 0; i < 2; i++) {
			path[next] = i;
			dfs(next + 1);
		}
	}

}
