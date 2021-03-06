package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_1149 {


	static int N;
	static long dp[][]=new long[1000+1][3];
	static int board[][];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		board=new int[N][3];
		for(int i = 0 ; i < N ; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 3 ; j++){
				board[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0]=board[0][0];
		dp[0][1]=board[0][1];
		dp[0][2]=board[0][2];
		for(int i = 1 ; i < N ; i ++){
			dp[i][0]=Math.min(dp[i-1][1], dp[i-1][2])+board[i][0];
			dp[i][1]=Math.min(dp[i-1][0], dp[i-1][2])+board[i][1];
			dp[i][2]=Math.min(dp[i-1][0], dp[i-1][1])+board[i][2];
		}
				
		System.out.println(Arrays.stream(dp[N-1]).min().getAsLong());
	}
}
