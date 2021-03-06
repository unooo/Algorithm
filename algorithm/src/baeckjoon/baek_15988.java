package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek_15988 {
	
	
	static int T;
	static long dp[]=new long[1000000+1];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		T=Integer.parseInt(st.nextToken());
		dp[1]=1;
		dp[2]=2;
		dp[3]=4;
		for(int i = 4 ; i <=1000000 ; i++){
			dp[i]=(dp[i-1]%1000000009+dp[i-2]%1000000009+dp[i-3]%1000000009)%1000000009;
		}
		
		for(int i = 0 ; i < T ; i++){
			st = new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			System.out.println(dp[num]%1000000009);
		}
	}

}
