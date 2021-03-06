package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baek_11053 {
	
	static int dp[];
	static int num[];
	static int path[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		dp = new int[n+1];
		num= new int[n+1];
		 path = new int[n+1];
		st = new StringTokenizer(br.readLine());
		
		for( int i=0 ; i < n ; i ++){
			num[i]= Integer.parseInt(st.nextToken());
		}
		
		dp[0]=1;
		for(int i = 1 ; i < n ; i ++){
			int max= 0;
			int maxIdx=i;
			for(int j = 0 ; j < i ; j ++){
				if(num[j]<num[i]){
					if(max<dp[j])
						maxIdx=j;
					max=Math.max(max, dp[j]);
					
				}
			}
			
			dp[i]=max+1;
			path[i]=maxIdx;
		}
		int maxIdx=0;
		for(int i = 0 ; i <=n ; i ++){
			if(dp[i]>dp[maxIdx]){
				maxIdx=i;
			}
		}
		System.out.println(dp[maxIdx]);
		print(maxIdx);
	}
	
	
	public static void print(int idx){
		if(idx==path[idx]){
			System.out.print(num[idx]+" ");
			return ;
		}
		
		print(path[idx]);
		System.out.print(num[idx]+" ");
	}

}
