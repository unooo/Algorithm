package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bkek_12101 {

	static int dp[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		dp=new int[n+1];
		dp[1]=1;
	
		for(int idx= 2 ; idx <=n ; idx++){
			int res=0;
			for(int i = idx-1 ; i >=1 ; i --){
				res+=dp[i];
				
			}
			dp[idx]=res+1;
		}
		System.out.println(dp[n]);
	}

}
