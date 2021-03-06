package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Samsung_15686 {

	public static int N;
	public static int M;
	public static int graph[][] ;
	public static ArrayList<int[]> ary;
	public static int ret = Integer.MAX_VALUE;
	public static int flag[];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		
		
		graph=new int[N+1][N+1];
		ary = new ArrayList<int[]>();
		for(int i =1 ; i < N+1 ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j =1 ; j < N+1 ; j++){
				graph[i][j]=Integer.parseInt(st.nextToken());
				if(graph[i][j]==2){
					ary.add(new int[]{i,j});
				}
			}
		}
		flag=new int[ary.size()];
		int[] selectedNumAry = new int[M];
		dfs(0,selectedNumAry,0);
		System.out.println(ret);
		
	}
	
	public static void dfs(int nowStep, int[] selectedNumAry, int nowIdx){
		
		if(nowStep>=M){
			
			//기저사건 진행
			chickenDistance(selectedNumAry);
			return;
		}
		
		
		for(int i = nowIdx ; i < ary.size() ; i ++){
			if(flag[i]==1)
				continue;
			selectedNumAry[nowStep]=i;
			flag[i]=1;
			dfs(nowStep+1,selectedNumAry,i+1);//	dfs(nowStep+1,selectedNumAry,nowIdx+1); ->nowIdx+1이건 내 의도와는 달른 표현이어서 시간초과낫다.
			flag[i]=0;
		}
		
	}
	
	public static void chickenDistance(int[] selectedNumAry){
		int sum=0;
		for(int i = 1 ; i < N+1; i ++){
			
			for(int j = 1 ; j < N+1 ; j ++){
				int temp = Integer.MAX_VALUE ;
				if(graph[i][j]==1){
					
					for(int k = 0 ; k < M ; k ++){
						int tpAry[]=ary.get(selectedNumAry[k]);
						int r1=i;
						int c1=j;
						int r2=tpAry[0];
						int c2=tpAry[1];
						temp=Math.min(temp,chickenDistanceCal(r1, c1, r2, c2));
					}
					sum+=temp;
				}
			}
			
		}
		ret=Math.min(ret, sum);
	}
	
	public static int chickenDistanceCal(int r1,int c1, int r2, int c2){
		int ret = 0 ;
		
		ret= Math.abs(r2-r1)+Math.abs(c2-c1);
		
		return ret;
	}
}
