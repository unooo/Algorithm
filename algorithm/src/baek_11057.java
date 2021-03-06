import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_11057 {


	static int N;
	static long dp[][]=new long[1000+1][10];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		
		Arrays.fill(dp[0], 1);
		for(int i = 1 ; i <= N ; i ++){
			dp[i][0]=1;
			for(int j = 1 ; j <=9;j++){
				dp[i][j]=(dp[i-1][j]+dp[i][j-1])%10007;
			}
		}
				
		System.out.println(dp[N][9]);
	}
}

