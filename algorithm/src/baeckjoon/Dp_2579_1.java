package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dp_2579_1 {
	public static int stair;
	public static int stairScore[];
	public static int[][] dp;
	public static int ret = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		stair = Integer.parseInt(st.nextToken());
		stairScore = new int[stair + 1];
		dp = new int[300 * 10][2]; // 몇번쨰계단에 몇칸쩜프로왔는가
		for (int i = 0; i < stair; i++) {
			st = new StringTokenizer(br.readLine());
			stairScore[i + 1] = Integer.parseInt(st.nextToken());
		}
		// System.out.println(dfs(0,0,0));
		int order[]=new int [stair+1];
		dfs(0, 0, order,0);
		System.out.println(ret);
	}
	
	public static void dfs(int nowStep, int nowStair, int[] order, int tp){
		
		if(nowStair==stair){
			check(order,nowStep,tp);
			return;
		}else if(nowStair>stair){
			
			return;
		}
		int temp=1;
		if(nowStep==0)
			temp=0;
		
		order[nowStep]=nowStair;
		if(dp[nowStair][tp]==0){
			dfs(nowStep+1,nowStair+1,order,temp);
			dfs(nowStep+1,nowStair+2,order,0);
			
			return;
		}else{
			check(order,nowStep,tp);
		}
		
		
		
	}
	
	public static void check(int [] order,int nowStep,int tp){
		int num = 0;
		for(int i = nowStep ; i >=0 ; i --){
			num+=stairScore[order[i]];
			
		}
		ret=Math.max(ret, num);
	}
}
