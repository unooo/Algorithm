package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11053 {
	public static int N;
	public static long dp[];
	public static int numAry[];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		dp=new long[N+1];
		numAry=new int[N+1];
		
		
		for(int i = 1 ; i <=N ; i ++){
			int base= numAry[i];
			for(int j = i ; j <=N ; j++){
				
			}
		}
		
	}
}
