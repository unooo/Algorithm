package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_11052 {
	
static int dp[][];
	
	
	public static void main(String[] args) throws  IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		dp=new int[n+1][n+1];
		int worthAry[] = new int[n+1];
		for(int i = 1 ; i <=n ; i ++)
			worthAry[i]=Integer.parseInt(st.nextToken());
		
		for(int i = 1 ; i <=n ; i ++){
			for(int j = 1 ; j <=n ; j ++){
				int max=Integer.MIN_VALUE;
				for(int k = 1 ; k <=n ; k ++){
					int worth=worthAry[k];
					int temp=-1;
					if(j-k<0)
						continue;
					else
						temp=Math.max(dp[i-1][j], dp[i-1][j-k]+worth);
					max=Math.min(max, temp);
				}
				dp[i][j]=max;
				
			}
		}
		System.out.println(dp[n][n]);
	}

}
