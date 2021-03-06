package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baek_11727 {
	
	static int dp[] = new int[1001];
	
	
	public static void main(String[] args) throws  IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp[1]=1;
		dp[2]=3;
		for(int i = 3 ; i <=n ; i ++ ){
			dp[i]=dp[i-1]%10007+2*dp[i-2]%10007;
		}
		System.out.println(dp[n]%10007);
	}

}
