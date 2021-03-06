package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek2225 {
	
	static int div=1000000000;

	public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			long dp[][]=new long[(int) (K+1)][(int) (N+1)];
			for(int j=1;j<=N;j++){
				dp[1][j]=1;
			}
			
			for(int i = 1 ; i <=K ; i ++){
				dp[i][1]=i%div;
			}
			
			for(int i=2 ; i <=K ; i ++){
				for(int j = 2 ; j <=N ; j ++){
					dp[i][j]=(dp[i-1][j]%div+dp[i][j-1]%div)%div;
				}
			}
			System.out.println(dp[K][N]%div);
	}

}
