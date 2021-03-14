import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek_5014 {

	static int F, S, G ,U ,D;
	static int visit[];
	static int dp[];
	static int res=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken())-1;
		G = Integer.parseInt(st.nextToken())-1;
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		 visit = new int[F];
		 dp=new int[F];
		 dfs(S);
		 if(dp[S]>=2000000){
			 System.out.println("use the stairs");
		 }else{
			 System.out.println(dp[S]-1);
		 }
	}
	
	public static int dfs(int cur){
		if(cur==G){
			return dp[cur]=1;
		}		
		if(dp[cur]!=0)
			return dp[cur];
		int min=2000000;
		for(int i = 0 ; i < 2 ; i ++){
			int nextStair;
			if(i==0){
				nextStair=cur+U;
			}else{
				nextStair=cur-D;
			}
			if(nextStair>=F||nextStair<0){
				min = Math.min(min, 2000000);
				continue;
			}
			if(dp[nextStair]!=0)
				return dp[cur]=dp[nextStair]+1;
			
			if(visit[cur]==1)
				continue;
			visit[cur]=1;
			if(dp[nextStair]!=0){
				min = Math.min(min, dfs(nextStair));
			}else{
				min = Math.min(min, dfs(nextStair));
			}
			visit[cur]=0;
		}
		return dp[cur] =min+1;
	}
	
}