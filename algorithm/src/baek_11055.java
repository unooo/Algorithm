import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_11055 {

	static int N;
	static long dp[]=new long[1001];
	static long numAry[]=new long[1001];
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++){			
			numAry[i]=Integer.parseInt(st.nextToken());
		}
		dp[0]=numAry[0];
		for(int i = 1 ; i < N ; i++){
			long tempMax=0;
			for(int j = 0 ; j <i ; j ++){
				if(numAry[i]>numAry[j]){
					tempMax=Math.max(tempMax, dp[j]);
				}
			}
			dp[i]=tempMax+numAry[i];
		}
		
		System.out.println(Arrays.stream(dp).max().getAsLong());
		
	}
}
