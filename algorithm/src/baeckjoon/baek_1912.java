package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_1912 {

	static long dp[];
	static long num[];
	//static int path[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		dp = new long[n];
		num= new long[n];
		// path = new int[n+1];
		st = new StringTokenizer(br.readLine());
		
		for( int i=0 ; i < n ; i ++){
			num[i]= Integer.parseInt(st.nextToken());
		}
		dp[0]=num[0];
		for(int i  = 1 ; i <n ; i ++){
			dp[i]=Math.max(num[i], num[i]+dp[i-1]);
		}
		Arrays.sort(dp);
		System.out.println(dp[dp.length-1]);
	}
}
