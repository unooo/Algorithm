package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_19238 {
	
	public static int N;
	static int M;
	static int fuel;
	static int humanMap[][];
	static int arriveMap[][];
	static int startY;
	static int startX;
	
	static int[] dy={-1,1,0,0};
	static int[]dx={0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		fuel=Integer.parseInt(st.nextToken());
		humanMap=new int[N][N];
		arriveMap=new int[N][N];
		
		for(int i = 0 ; i < N ; i++){
			st= new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++){
				int num=Integer.parseInt(st.nextToken());
				humanMap[i][j]=num;
				arriveMap[i][j]=num;
			}
		}
		st= new StringTokenizer(br.readLine());
		startY=Integer.parseInt(st.nextToken())-1;
		startX=Integer.parseInt(st.nextToken())-1;
		for(int i = 0 ; i < M ; i ++){
			st= new StringTokenizer(br.readLine());
			int row=Integer.parseInt(st.nextToken());
			int col=Integer.parseInt(st.nextToken());
			humanMap[row-1][col-1]=i+2;
			row=Integer.parseInt(st.nextToken());
			col=Integer.parseInt(st.nextToken());
			arriveMap[row-1][col-1]=i+2;
		}
		
		int tempY=startY;
		int tempX=startX;
		Pair pair=null;
		
		for(int i = 0 ; i < M ; i ++){
			pair=findHumanBfs(tempY,tempX);
			if(pair==null){
				System.out.println(-1);
				return;
			}
			int findNum=humanMap[pair.y][pair.x];
			humanMap[pair.y][pair.x]=0;
			if(fuel<0){
				System.out.println(-1);
				return;
			}
			
			pair=findArriveBfs(pair.y,pair.x,findNum);
			if(pair==null){
				System.out.println(-1);
				return;
			}
			arriveMap[pair.y][pair.x]=0;
			tempY=pair.y;
			tempX=pair.x;
			
		}
		
		System.out.println(fuel);
		
	}
	public static Pair findArriveBfs(int sx, int xy,int findNum){
		
		Queue<Pair> queue = new LinkedList<Pair>();
		Pair pair = new Pair();
		pair.y=sx;
		pair.x=xy;
		queue.add(pair);
		
		int[][] visitFlag= new int[N][N];
		int step=1;
		visitFlag[pair.y][pair.x]=step;
		
		while(!queue.isEmpty()){
			pair=queue.poll();
			int prevX=pair.x;
			int prevY=pair.y;
			step=visitFlag[prevY][prevX]+1;
			
			
			if(arriveMap[prevY][prevX]==findNum){
				fuel-=step-2;
				if(fuel>=0)
					fuel+=(step-2)*2;
				return pair;
			}
			
			for(int i = 0 ; i < 4 ; i ++){
				
				int nextY=prevY+dy[i];
				int nextX=prevX+dx[i];
				if(nextY<0||nextX<0||nextY>=N||nextX>=N)
					continue;
				if(arriveMap[nextY][nextX]==1)
					continue;
				if(visitFlag[nextY][nextX]!=0)
					continue;
				
				pair=new Pair();
				pair.x=nextX;pair.y=nextY;queue.add(pair);
				visitFlag[nextY][nextX]=step;
			}
		}
		return null;
	}
	
	public static Pair findHumanBfs(int sx, int xy){
		
		Queue<Pair> queue = new LinkedList<Pair>();
		Pair pair = new Pair();
		pair.y=sx;
		pair.x=xy;
		queue.add(pair);
		
		int[][] visitFlag= new int[N][N];
		int step=1;
		visitFlag[pair.y][pair.x]=step;
		int findFlag=-1;
		ArrayList<Pair> pairList = new ArrayList<Pair>();
		while(!queue.isEmpty()){
			pair=queue.poll();
			int prevX=pair.x;
			int prevY=pair.y;
			step=visitFlag[prevY][prevX]+1;
			
			if(humanMap[prevY][prevX]>=2){
				//todo				
				if(findFlag==-1){
					findFlag=step-1;
				}else{
					if(step-1>findFlag)
						break;
				}
				pairList.add(pair);
			}
			
			for(int i = 0 ; i < 4 ; i ++){
			
				int nextY=prevY+dy[i];
				int nextX=prevX+dx[i];
				if(nextY<0||nextX<0||nextY>=N||nextX>=N)
					continue;
				if(humanMap[nextY][nextX]==1)
					continue;
				if(visitFlag[nextY][nextX]!=0)
					continue;
				
				pair=new Pair();
				pair.x=nextX;pair.y=nextY;queue.add(pair);
				visitFlag[nextY][nextX]=step;
			}
			
		}
		Collections.sort(pairList);
		
		if(pairList.size()==0){
			return null;
		}else{
			pair = pairList.get(0);
			//map[pair.y][pair.x]=0; 
			fuel-=findFlag-1;
			return pair;
		}
			
		
	}
	
	static class Pair implements Comparable<Pair>{
		int y;
		int x;
		@Override
		public int compareTo(Pair o) {
			if(this.y>o.y)
				return 1;
			else if(this.y<o.y){
				return -1;
			}else{
				if(this.x>o.x)
					return 1;
				else if(this.x<o.x)
					return -1;
				else 
					return 0;
			}
		}
		
		
	}

}
