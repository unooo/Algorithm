package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BackTracking_15651 {

	public static int N;
	public static int M;
	public static StringBuffer sb;
	public static void main(String[] args) throws IOException{
		 sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		int ret[]=new int[M];
		dfs(0,ret);
		System.out.println(sb.toString());
		br.close();
		
	}
	
	
	public static void dfs(int now, int ret[]){
		if(now>=M){
			
			for(int i = 0 ; i < M ; i++){
			sb.append(ret[i]+" ");
			}
			sb.append("\n");
			return;			
		}
		
		for(int i = 1 ; i <= N ; i ++){
			ret[now]=i;
			dfs(now+1,ret);
		}
	}
}
