package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek_14501 {

	static int N;
	static int info[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		 info = new int[N][2];
		
		for(int i = 0 ; i < N ; i++){
			st = new StringTokenizer(br.readLine());
			info[i][0]=Integer.parseInt(st.nextToken());
			info[i][1]=Integer.parseInt(st.nextToken());
		}
		path=new int[N];
		dfs(0);
		System.out.println(res);
	}
	
	static int[] path;
	
	
	public static void dfs(int next){
		if(next>=N){
			//todo
			solv();
			return;
		}
		
		path[next]=0;
		dfs(next+1);
		path[next]=1;
		dfs(next+1);
		
	}
	static int res =0;
	public static void solv(){
		int piv=0;
		int sum=0;
		for(int i = 0 ; i < path.length ; i ++){
			if(path[i]!=0){
				if(piv>i||i+info[i][0]>N){
					return;
				}else{
					sum+=info[i][1];
					piv=i+info[i][0];
				}
			}
		}
		
		res=Math.max(res, sum);
	}
}
