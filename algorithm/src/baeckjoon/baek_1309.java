package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_1309 {


	static int N;
	static long dp[][]=new long[100000+1][3];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		
		Arrays.fill(dp[0], 1);
		for(int i = 1 ; i < N ; i ++){
			dp[i][0]=(dp[i-1][1]+ dp[i-1][2])%9901;
			dp[i][1]=(dp[i-1][0]+ dp[i-1][2])%9901;
			dp[i][2]=(dp[i-1][0]+ dp[i-1][1]+ dp[i-1][2])%9901;
		}
				
		System.out.println(Arrays.stream(dp[N-1]).sum()%9901);
	}
}

