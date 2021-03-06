package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11726 {

	public static int N;
	public static long  dp[];
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		if(N==1){
			System.out.println(1);
			return;
		}
			
		dp=new long[N+1];
		dp[1]=1;
		dp[2]=2;
		for(int i =3 ; i <=N ; i++){
			dp[i]=dp[i-1]%10007+dp[i-2]%10007;
		}
		System.out.println(dp[N]%((long)(10007)));
	}
	
	public static int calc(int totNum, int setNum){
		if(setNum==0||setNum*2==totNum)
			return 1;
		int set1Num=totNum-setNum*2;
		int tp=1;
		tp=factorial(set1Num+setNum);
		/*
		for(int i = set1Num+setNum; i >=1 ; i --){
			tp*=i;
		}
		*/
		tp/=factorial(setNum);
		tp/=factorial(set1Num);
		return tp;
		
	}
	
	public static int factorial(int n ){
		int ret = 1;
		for(int i = n ; i >=1 ; i --){
			ret*=i;
		}
		return ret;
	}
}
