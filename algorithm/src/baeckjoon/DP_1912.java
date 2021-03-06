package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_1912 {
	public static int N;
	public static long dp[];
	public static int numAry[];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		dp=new long[N+1];
		numAry=new int[N+1];
		//dp[0]=Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i ++){
			numAry[i]=Integer.parseInt(st.nextToken());
		}
		
		dp[1]=numAry[1];
		for(int i = 2 ; i <= N ; i ++){
			if(dp[i-1]+numAry[i]<0)
				dp[i]=0;
			else
				dp[i]=dp[i-1]+numAry[i];
		}

		Arrays.sort(dp);
		if(dp[N]==0){
			numAry[0]=numAry[1];
			Arrays.sort(numAry);
			System.out.println(numAry[N]);
		}else
		System.out.println(dp[N]);
		
		
		
	}

}
