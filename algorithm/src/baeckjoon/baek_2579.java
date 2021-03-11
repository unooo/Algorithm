package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek_2579 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int ary[] = new int[N];
		int dp[] = new int[N];
		for(int i = 0 ; i < N ; i ++){
			 st = new StringTokenizer(br.readLine());
			ary[i]=Integer.parseInt(st.nextToken());
		}		
		
		if (N >= 1) {
            dp[0] = ary[0];
        }
        // 滴儡老 版快
        if (N >= 2) {
            dp[1] = ary[0] + ary[1];
        }
        // 技儡老 版快
        if (N >= 3) {
            dp[2] =  Math.max(dp[0] + ary[2], ary[1] + ary[2]);
        }

		for(int i =3 ; i <N ; i++){
			
			dp[i]=Math.max( dp[i-3]+ary[i]+ary[i-1],dp[i-2]+ary[i]);
			
		}
		System.out.println(dp[N-1]);
		
	}
}
