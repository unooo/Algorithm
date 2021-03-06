package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_1932 {

	static int N;
	static long dp[][]=new long[501][501];
	static long board[][]=new long[501][501];
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < N ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j <i+1;j++){
				board[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0]=board[0][0];
		for(int i = 0 ; i < N ; i ++){
			
			for(int j = 0 ; j <i+1;j++){
				dp[i+1][j]=Math.max(dp[i+1][j], dp[i][j]+board[i+1][j]);
				dp[i+1][j+1]=Math.max(dp[i+1][j+1], dp[i][j]+board[i+1][j+1]);
			}
		}
		
		System.out.println(Arrays.stream(dp[N-1]).max().getAsLong());
		
	}
}
