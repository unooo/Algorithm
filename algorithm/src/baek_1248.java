import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek_1248 {

	static int N;
	static int path[];
	static int numAry[];
	static char s[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int idx=0;
		numAry=new int[21];
		for(int i = -10 ; i <=10 ; i++){
			numAry[idx]=i;
			idx++;
		}
		N=Integer.parseInt(st.nextToken());
		String str = br.readLine();
		path=new int[N];
		solv(str);
		dfs(0);
	}
	
	static boolean res = false;
	public static void dfs(int next){
		if(next>=N){
			//todo
			
			for(int i = 0 ; i <path.length ; i++)
				System.out.print(numAry[path[i]]+" ");
			//System.out.println();
			res=true;
			return;
		}
		Loop:
		for(int i=0;i<21;i++){
			path[next]=i;
			
			
			for(int k = 0 ; k <=next ; k++){
				int tempSum=0;
				for(int f = k ; f<=next ;f++){
					tempSum+= numAry[path[f]];
				}
				char tempCahr=0;
				if(tempSum<0)
					tempCahr='-';
				else if(tempSum==0)
					tempCahr='0';
				else
					tempCahr='+';
				
				if(s[k][next]!=tempCahr)
					continue Loop;
			}
			dfs(next+1);
			if(res)
				return;
		}
	}
	
	public static void solv(String str){
		int idx=0;
		s=new char[N][N];
		for(int i = 0 ; i < N ; i ++){
			for(int j = i ;  j < N ; j++){
				s[i][j]=str.charAt(idx);
				idx++;
			}
		}
	
	}
}
