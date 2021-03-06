package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_14501 {

	public static int graph[][];
	public static int ret = 0 ;
	public static int N;
	
	public static int memo[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		 N = Integer.parseInt(st.nextToken());
		graph= new int[N][2];
		memo= new int[N][6]; // N번까지 확인했을때 쿨타임당 price의 최대값
		for(int i = 0 ; i < N ; i++){
			 st = new StringTokenizer(br.readLine());
			 for(int j = 0 ; j < 2 ; j++){
				 graph[i][j]=Integer.parseInt(st.nextToken());
			 }
		}
		
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < 6 ; j ++){
				memo[i][j]=-1;
			}
		}
		
	int t=	dfs(0,0);
		System.out.println(t);
		
	}
	
	public static int dfs(int now, int coolTime){

		if(now>=N){
			return 0;
		}
		
		if(memo[now][coolTime]!=-1)
			return memo[now][coolTime];
		
		if(coolTime==0){
			if(graph[now][0]-1>N-now-1){
				memo[now][coolTime]=dfs(now+1,coolTime);
			}else{
			memo[now][coolTime]=Math.max(dfs(now+1,coolTime),dfs(now+1,coolTime+graph[now][0]-1)+graph[now][1]);
			}
		}else{
			memo[now][coolTime]=dfs(now+1,coolTime-1);
		}
		
		return memo[now][coolTime];
	}
		
		
}
