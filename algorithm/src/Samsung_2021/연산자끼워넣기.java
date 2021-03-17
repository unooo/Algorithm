package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자끼워넣기 {
	
	static int N;
	static int numAry[];
	static int opAry[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		numAry=new int[N];
		opAry=new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++){
			numAry[i]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 4 ; i++)
			opAry[i]=Integer.parseInt(st.nextToken());
		
		path=new int[N-1];
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}

	static int path[];
	//중복순열
	static int min=Integer.MAX_VALUE;
	static int max=Integer.MIN_VALUE;
	public static void dfs(int next){
		if(next>=N-1){
			int ret=numAry[0];
			int newOpAry[]=new int[4];
			for(int i = 0 ; i <4 ;i++){
				newOpAry[i]=opAry[i];
			}
			for(int i = 1 ; i < N ; i ++){
				int oper= path[i-1];
				if(newOpAry[oper]==0)
					return;
				newOpAry[oper]--;
				switch(oper){
				case 0:
					ret+=numAry[i];
					break;
				case 1:
					ret-=numAry[i];
					break;
				case 2:
					ret*=numAry[i];
					break;
				case 3:
					ret/=numAry[i];
					break;
				}
			}
			min=Math.min(min, ret);
			max=Math.max(max, ret);
			return;
		}
		
		for(int i = 0 ; i < 4 ; i++){
			path[next]=i;
			dfs(next+1);
		}
	}
}
