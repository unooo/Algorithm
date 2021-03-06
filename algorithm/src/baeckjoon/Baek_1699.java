package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1699 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int dp[]=new int[N+1];
		
		dp[1]=1;
		if(N>=2)
		dp[2]=2;
		if(N>=3)
		dp[3]=3;
		
		for(int i =4 ;i<=N;i++){
			int pivot =(int) Math.floor(Math.sqrt(i));
			int min = Integer.MAX_VALUE;
			for(int j = pivot ; j >=2 ; j--){
				min = Math.min(min, 1+dp[i-(int)Math.pow(j, 2)]);
			}
			dp[i]=min;
		}
		System.out.println(dp[N]);
		
	}
}
