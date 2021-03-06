package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_17142 {
	
	public static int N;
	public static int M;
	public static int[][] graph;
	public static LinkedList<Virus> virusList;
	public static int[] dy= new int[]{1,-1,0,0};
	public static int[] dx= new int[]{0,0,1,-1};
	public static int ret = Integer.MAX_VALUE;
	public static int[] flag;
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		virusList = new LinkedList<Virus>();
		graph=new int[N][N];
		
		for(int i = 0 ; i < N ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j <  N ; j ++){
				graph[i][j]=Integer.parseInt(st.nextToken());
				if(graph[i][j]==2){
					graph[i][j]=-2;					
					virusList.add(new Virus(i,j));
				}
				
				if(graph[i][j]==1){
					graph[i][j]=-1;
				}
			}
		}
		
		int[] idxAry = new int[M];
		flag=new int[virusList.size()];
		dfs(0,idxAry,0);
		
	
		if(ret<0)
			ret=0;
		
		if(ret==Integer.MAX_VALUE)
			ret=-1;
		
		System.out.println(ret);
	}
	
	public static void bfs(int[] idxAry){
		
		Queue<Virus> queue = new LinkedList<Virus>();
		int[][] temp = new int[N][N];
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < N ; j ++){
				temp[i][j]=graph[i][j];
			}
		}
		
		for(int i = 0 ; i < idxAry.length;i ++){
			Virus virus =virusList.get(idxAry[i]);
			temp[virus.y][virus.x]=-3;
			queue.add(virus);
		}
		//-3은 활성바이러스, -2는 비활성바이러스
		while(!queue.isEmpty()){
			Virus virus = queue.poll();
			int nowY=virus.y;
			int nowX=virus.x;
			
			for(int i = 0 ; i < 4 ; i ++){
				int newY=nowY+dy[i];
				int newX=nowX+dx[i];
				if(newY<0||newX<0||newY>=N||newX>=N){
					continue;
				}
				
				if(temp[newY][newX]==0||temp[newY][newX]==-2){
					
					if(temp[nowY][nowX]==-3){
						temp[newY][newX]=1;
					}else{
						temp[newY][newX]=temp[nowY][nowX]+1;
					}
					
					if(temp[newY][newX]>ret)
						return;
					queue.add(new Virus(newY,newX));
				}
				
				
				
			}
			
			
		}
		
		int num=Integer.MIN_VALUE;
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < N ; j++){
				if(graph[i][j]==-2)
					continue;
				num=Math.max(num, temp[i][j]);
				if(temp[i][j]==0){
					
					return;
				}
			}
		}
		ret=Math.min(ret, num);
		
		
	}
	
	public static void dfs(int now,int[] idxAry,int num){
		if(now>=M){
			return;
		}
		
		for(int i = num ; i <virusList.size() ; i ++ ){
			
			if(flag[i]==1)
				continue;
			
			idxAry[now]=i;			
			//flag[i]=1;
			dfs(now+1,idxAry,i+1);
			//flag[i]=0;
			
			if(now==M-1){
				//바이러스검사시작
				bfs(idxAry);
			}
		}
		
	}
	
	static class Virus{
		int y ;
		int x ;
		Virus(int y , int x){
			this.y=y;
			this.x=x;
		}
	}

}
