package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek_15658 {
	
	static int N;
	static int[] numAry;
	static int[] op;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		numAry=new int[N];
		op=new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++){
			numAry[i]=Integer.parseInt(st.nextToken());			
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i <4 ; i++)
			op[i]=Integer.parseInt(st.nextToken());
		
		dfs(0,4,N-1);
		System.out.println(max);
		System.out.println(min);
	}
	
	//중복순열
	static int[] path = new int[10];
	static int max=Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	public static void dfs(int next, int n , int r){
		
		if(next>=r){
			//todo
			
			int tempRet=numAry[0];
			int tempOp[]=new int[4];
			for(int i = 0 ; i <4 ; i++)
				tempOp[i]=op[i];
			for(int i = 0 ; i < r ; i ++){			
				int opIdx=path[i];
				if(tempOp[opIdx]<=0)
					return;
				tempOp[opIdx]--;
				
				switch(opIdx){
				case 0:
					tempRet+=numAry[i+1];
					break;
				case 1:
					tempRet-=numAry[i+1];
					break;
				case 2:
					tempRet*=numAry[i+1];
					break;
				case 3:
					tempRet/=numAry[i+1];
				}
			}
			max=Math.max(max, tempRet);
			min=Math.min(min, tempRet);
			return;
		}
		
		for(int i = 0 ; i < 4 ; i ++){
			
			path[next]=i;
			dfs(next+1,n,r);
		}
		
	}

}
