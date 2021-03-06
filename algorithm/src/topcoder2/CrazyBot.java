package topcoder2;

import java.util.LinkedList;
import java.util.Queue;

public class CrazyBot {

	public static int[] dx= new int[]{1,0,-1,0};
	public static int[] dy= new int[]{0,1,0,-1};
	public static double ret = 0;
	public static double directionVal[] = new double[4];
	public static boolean[][] flag;
	public static int[][] flag2;
	public static Queue<Integer> queue = new LinkedList<Integer>();
	
	public static void main(String[] args){
		
		/*
		int n =1;
		int east=25;
		int west=25;
		int south=25;
		int north=25;
		*/
		
		
		int n =2;
		int east=25;
		int west=25;
		int south=25;
		int north=25;
			
		
		/*
		int n =2;
		int east=50;
		int west=0;
		int south=0;
		int north=50;
		*/
		
		getProbability(n, east, west, south, north);
		System.out.println(ret);
		
	}
	
	public static double getProbability(int n , int east , int west , int south , int north){

		double ret = 0 ;
		
		flag = new boolean[30][30];
		flag2= new int[30][30];
		
		directionVal[0]=(east/100.0);
		directionVal[1]=(west/100.0);
		directionVal[2]=(south/100.0);
		directionVal[3]=(north/100.0);
		dfs(n,15,15,1);	
		
		return ret;
	}
	
	
	public static void bfs(int step,int startX,int startY){
		
		int  nowX = startX ;
		int nowY=startY;
		int nowStep=0;
		
		
		while(!queue.isEmpty()){
			
			if(nowStep==step){
				
			}
			
			for(int i = 0 ; i < 4 ; i ++){
				if(flag2[nowX+dx[i]][nowY+dy[i]]==0){
					flag2[nowX+dx[i]][nowY+dy[i]]=flag2[nowX][nowY]+1;
				}
			}
			nowStep++;
		}
		
	}
	
	public static void dfs(int now, int nowX, int nowY, double nowVal){
		
		if(now<=0){
			
			return;
		}
		
		flag[nowX][nowY]=true;
		for(int i = 0 ; i < 4 ; i ++){
			if(flag[nowX+dx[i]][nowY+dy[i]])
				continue;
			
			if(now-1<=0){
				ret+=nowVal*directionVal[i];
				;
			}
				
			dfs(now-1,nowX+dx[i],nowY+dy[i],nowVal*directionVal[i]);
			
		}
		flag[nowX][nowY]=false;
	}
		
		
	
	
}
