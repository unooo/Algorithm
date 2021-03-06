package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_1182 {

	static int N, S;
	static int numAry[];
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		S=Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		numAry=new int[N];
		for(int i = 0 ; i < N ; i++){
			numAry[i]=Integer.parseInt(st.nextToken());
		}
		path=new int[N];
		dfs(0);
		System.out.println(ret);
	}
	static int path[];
	static int ret=0;
	public static void dfs(int next){
		
		
		if(next>=N){
			int tempSum=0;
			
			long count = Arrays.stream(path).filter(num->{
				return num==0;
			}).count();
			if(count==path.length)
				return;
			
			
			
			for(int i = 0 ; i < path.length ; i++){
				if(path[i]==1){
					tempSum+=numAry[i];
				}
			}
			if(tempSum==S){
				ret++;
			}
			return;
		}
		
		path[next]=0;
		dfs(next+1);
		path[next]=1;
		dfs(next+1);
		
	}
}
