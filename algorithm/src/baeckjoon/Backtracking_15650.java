package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backtracking_15650 {

	public static int N;
	public static int M;
	public static StringBuffer sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		int ret[]=new int[M];
		sb=new StringBuffer();
		dfs(0,1,ret);
		System.out.println(sb);
	}
	
	
	public static void dfs(int now, int idx, int ret[]){
		if(now>=M){
			for(int i = 0 ; i < M ; i++){
				sb.append(ret[i]+" ");
			}
			sb.append("\n");
			return;			
		}
		
		for(int i = idx ; i <= N ; i ++){
			ret[now]=i;
			dfs(now+1,i,ret);
		}
	}
}
