package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BackTracking_15649 {
	public static int N;
	public static int M;
	public static void main (String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] flag = new int[N];
		int[] ret= new int[M];
		dfs(0, flag, ret);
	}
	
	public static void dfs(int nowStep,int[] flag, int ret[]){
		if(nowStep>=M){
			for(int i=0;i < M ; i++){
				System.out.print(ret[i]+1+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 0 ; i < N ; i ++){
			
			if(flag[i]==0){
				flag[i]=1;
				ret[nowStep]=i;
				dfs(nowStep+1,flag,ret);
				flag[i]=0;
			}
			
		}
	}
}
