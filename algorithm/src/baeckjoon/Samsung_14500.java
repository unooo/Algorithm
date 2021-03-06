package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_14500 {
	public static int N;
	public static int M;
	public static int graph[][];
	public static int flag[][];
	public static int ret = 0;
	public static int[] dy={1,0,-1,0};
	public static int[] dx={0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		graph= new int[N][M];
		flag= new int[N][M];
		for(int i = 0 ; i < N ; i ++){
			st= new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++){
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0 ; i < N ; i ++){
			
			for(int j = 0 ; j < M ; j ++){
				flag[i][j]=1;
				dfs(i,j,0,0);
				flag[i][j]=0;
			}
		}
		
		for(int i = 0 ; i < N ; i ++){
			
			for(int j = 0 ; j < M ; j ++){
				checkSpecial(i, j);
			}
		}
		
		System.out.println(ret);
		
	}
	
	public static void dfs(int nowY, int nowX, int nowStep, int temp){
		
		
		if(nowStep>=4){
			ret=Math.max(ret, temp);
			return;
		}
		
		temp+=graph[nowY][nowX];
		for(int i = 0 ; i < 4 ; i ++){
			
			if(nowY+dy[i]<0||nowX+dx[i]<0||nowY+dy[i]>=N||nowX+dx[i]>=M)
				continue;
			if(flag[nowY+dy[i]][nowX+dx[i]]==0){
				flag[nowY+dy[i]][nowX+dx[i]]=1;
				dfs(nowY+dy[i],nowX+dx[i],nowStep+1,temp);
				flag[nowY+dy[i]][nowX+dx[i]]=0;
			}
			
		}
		
	}
	
	public static void checkSpecial(int nowY, int nowX){

		
		for(int i = 0 ; i < 4 ; i ++){
			int sum=0;
			int temp=0;
			int newY=0;
			int newX=0;
			
			for(int j = 0 ; j < 3 ; j ++){
				 temp=(j+i)%4;
				 newY=nowY+dy[temp];
				 newX=nowX+dx[temp];
				if(newY<0||newX<0||newY>=N||newX>=M){
					break; //난 컨티뉴로해서 틀렸음
				}
				
				sum+=graph[newY][newX];
			}
			
			sum+=graph[nowY][nowX];
			ret=Math.max(ret, sum);
		}
		
		
				
	}

}
