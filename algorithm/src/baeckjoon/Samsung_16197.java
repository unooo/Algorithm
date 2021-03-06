package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_16197 {

	public static int N;
	public static int M;
	public static int [][] graph;

	
	public static int[] dy=new int[]{-1,1,0,0};
	public static int[] dx=new int[]{0,0,1,-1};
	public static int ret = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		
		graph=new int[N][M];

		ArrayList<Pair> pairAry = new ArrayList<Pair>();
		for(int i = 0 ; i < N ; i ++){
			String str = new String(br.readLine());
			for(int j = 0 ; j < M ; j ++){
				if(str.charAt(j)=='o'){
					graph[i][j]=2; //¹öÆ°
					pairAry.add(new Pair(i,j));
				}else if(str.charAt(j)=='.'){
					graph[i][j]=0; //ºóÄ­
				}else if(str.charAt(j)=='#'){
					graph[i][j]=1; //º®
				}
			}
		}
		
		dfs(0,pairAry.get(0),pairAry.get(1));
			
	
		System.out.println(ret);
		
		
	}
	
	public static void dfs(int now, Pair pair1, Pair pair2){
		
		if(now>=10){
			if(ret==Integer.MAX_VALUE){
				ret=-1;
			}
			return;
		}
		
		int startY1=pair1.y;
		int startX1=pair1.x;
		
		int startY2=pair2.y;
		int startX2=pair2.x;
		
		int nowY1=pair1.y;
		int nowX1=pair1.x;
		
		int nowY2=pair2.y;
		int nowX2=pair2.x;
		
		int nextY1;
		int nextX1;
		int nextY2;
		int nextX2;
		
		for(int i = 0 ; i < 4 ; i ++){
			
			nextY1=nowY1+dy[i];
			nextX1=nowX1+dx[i];
			
			nextY2=nowY2+dy[i];
			nextX2=nowX2+dx[i];
			
			int flag1=0;
			int flag2=0;
			
			if(nextX1<0||nextY1<0||nextX1>=M||nextY1>=N){
				flag1=1;
			}
			
			if(nextX2<0||nextY2<0||nextX2>=M||nextY2>=N){
				flag2=1;
			}
			
			
			
			if(flag1==1&&flag2==1){
					continue;
			}else if(flag1!=flag2){
				if(ret==-1){
					ret=now+1;
				}else{
				ret = Math.min(ret, now+1)  ;
				}
				return;
			}
			
			if(graph[nextY1][nextX1]==1){
				nextY1=nowY1;
				nextX1=nowX1;
			}
			if(graph[nextY2][nextX2]==1){
				nextY2=nowY2;
				nextX2=nowX2;
			}
			pair1.y=nextY1;
			pair1.x=nextX1;
			pair2.y=nextY2;
			pair2.x=nextX2;
			dfs(now+1,pair1,pair2);
			pair1.y=startY1;
			pair1.x=startX1;
			pair2.y=startY2;
			pair2.x=startX2;
		
		}
		
		
		
		
	}
	
	
	static class Pair{
		int y  ;
		int x;
		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
