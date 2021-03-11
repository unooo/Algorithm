package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek_2133 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		N=N<4?4:N;
		int dp[] = new int[N+1];
		dp[0]=0;
		dp[1]=0;
		dp[2]=3;
		dp[3]=0;
		dp[4]=11;
		
		for(int i = 5 ; i <= N ; i ++){
			dp[i]=dp[i-2]*3+dp[i-4]*2;
		}
		System.out.println(dp[N]);
		
	}
}
