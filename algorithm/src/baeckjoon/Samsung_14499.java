package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_14499 {
	
	public static int[] dx={0,1,-1,0,0};//동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
	public static int[] dy={0,0,0,-1,1};
	public static int dice[][] = new int[4][3];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][]graph =new int[N][M];
		
		int sy = Integer.parseInt(st.nextToken());
		int sx= Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < N ; i++){
			st   = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++){
				graph[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		int moveDiceRecd[]=new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < K ; i++){
			moveDiceRecd[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0 ; i < K ; i++){
			sx+=dx[moveDiceRecd[i]];
			sy+=dy[moveDiceRecd[i]];
			if(sx<0||sy<0||sx>=M||sy>=N){
				sx-=dx[moveDiceRecd[i]];
				sy-=dy[moveDiceRecd[i]];
				continue;
			}
			moveDice(moveDiceRecd[i]);
			if(graph[sy][sx]==0){ //주사위와 맞닿는 지도의 면이 0 인 경 우
				graph[sy][sx]=dice[3][1];
				
			}else{
				dice[3][1]=graph[sy][sx];
				graph[sy][sx]=0;
			}
			System.out.println(dice[1][1]);
		}
		
		
		
	}
	
	public static void moveDice(int direction){
		switch(direction){
		case 1 :
			int temp= dice[3][1];
			dice[3][1]=dice[1][2];
			dice[1][2]=dice[1][1];
			dice[1][1]=dice[1][0];
			dice[1][0]=temp;
			break;
		case 2:
			int temp2= dice[3][1];
			dice[3][1]=dice[1][0];
			dice[1][0]=dice[1][1];
			dice[1][1]=dice[1][2];
			dice[1][2]=temp2;
			break;
		case 3:
			int temp3= dice[0][1];
			dice[0][1]=dice[1][1];
			dice[1][1]=dice[2][1];
			dice[2][1]=dice[3][1];
			dice[3][1]=temp3;
			break;
		case 4:
			int temp4=dice[3][1];
			dice[3][1]=dice[2][1];
			dice[2][1]=dice[1][1];
			dice[1][1]=dice[0][1];
			dice[0][1]=temp4;
			break;
		}
	}

}
