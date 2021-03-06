package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dp_1463 {

	public static int num;
	public static int dp[] = new int[1000000 + 1];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		num = Integer.parseInt(st.nextToken());
		//System.out.println(dfs(num));
		clac();
	}
	
	public static void clac(){
		dp[1]=0;
		for(int i = 2 ; i <= num ; i ++){
			int num1=Integer.MAX_VALUE,num2=Integer.MAX_VALUE,num3=Integer.MAX_VALUE;
			if(i%3==0){
				num1=dp[i/3];
			}
			if(i%2==0){
				num2=dp[i/2];
			}
			num3=dp[i-1];
			
			dp[i]=Math.min(num1, num2);
			dp[i]=Math.min(dp[i], num3)+1;
			
		}
		System.out.println(dp[num]);
	}

	public static int dfs(int now) {
		if (now == 1) {
			return 0;
		}
		int[] num = new int[3];
		Arrays.fill(num, -1);
		if (dp[now] == 0) {
			if (now % 3 == 0) {
				num[0] = dfs(now / 3);
			} 
			if (now % 2 == 0) {
				num[1] = dfs(now / 2);
			} 
			{
				num[2] = dfs(now - 1);
			}
			Arrays.sort(num);
			int temp=0;
			for(int i = 0 ; i < 3 ; i ++){
				if(num[i]!=-1){
					temp=num[i];
					break;
				}
			}
			return dp[now]=temp+1;
		}else{
			return dp[now];
		}
	}
}
