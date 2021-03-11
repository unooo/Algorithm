package Programmers;

import java.util.Arrays;

public class Programmers_dpN으로표현 {
	public static void main(String[] args){
		int N =5;
		int number=12;
		solution(N, number);
	}
	static int dp[] = new int[1000000000];
	public static int solution(int N, int number) {
		int answer = 0;
		return dfs(0, N, dp, N, number);
	}

	public static int dfs(int next, int nowVal,int[]dp, int N, int number) {
//		if (next >= 8) {
//			// todo
//			return;
//		}

		if (nowVal == number) {
			// todo
			return next;
		}
		
		int ret = 0;
		for(int i = 0 ; i < 5 ; i ++){
			switch(i){
			case 0:
				nowVal=nowVal*N;
				break;
			case 1: 
				nowVal+=N;
				break;
			case 2:
				nowVal-=N;
				break;
			case 3:
				nowVal/=N;
				break;
			case 4:
				nowVal=Integer.parseInt(nowVal+""+N);
				break;
			}
			if(dp[nowVal]!=0){
				ret=Math.min(ret, dp[nowVal]);
			}else{
				ret=Math.min(ret,dfs(next + 1,nowVal,dp , N, number));
			}
		} 
		return ret;
	}
}
