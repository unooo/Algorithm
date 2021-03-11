package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_13398 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int ary[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++){
			ary[i]=Integer.parseInt(st.nextToken());
		}
		
		
		int dp1[]=new int[N];
		dp1[0]=ary[0];
		int dp2[]=new int[N];
		dp2[0]=ary[0];
		for(int i = 1 ; i < N ; i++){
			if(dp1[i-1]+ary[i]>ary[i]){
				dp1[i]=dp1[i-1]+ary[i];
			}else{
				dp1[i]=ary[i];
			}
			
			if(dp1[i-1]>dp2[i-1]+ary[i])
				dp2[i]=dp1[i-1];
			else{
				dp2[i]=dp2[i-1]+ary[i];
			}
		}
		
		System.out.println(Math.max(Arrays.stream(dp2).max().getAsInt(),Arrays.stream(dp2).max().getAsInt()));
	}

}
