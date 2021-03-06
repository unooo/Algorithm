package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_10844 {
	
	static long dp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		dp=new long[n+1][10];
		
		Arrays.fill(dp[1], 1);
		dp[1][0]=0;
			
		
		for(int i = 2 ; i <=n ; i ++){
			for(int j = 0 ; j <= 9 ; j ++){
				if(j==0){
					dp[i][j]=dp[i-1][j+1]%1000000000;
				}else if(j==9){
					dp[i][j]=dp[i-1][j-1]%1000000000;
				}else{
					dp[i][j]=dp[i-1][j-1]%1000000000+dp[i-1][j+1]%1000000000;
				}
			}
			
		}
		long res=0;
		for(int i = 0 ; i <=9 ; i ++){
			res= (res+ dp[n][i])%1000000000;
		}
		System.out.println(res);
		
	}

}
