package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ¡÷ªÁ¿ß±º∏Æ±‚14499 {

	static int[][] dice=new int[4][3];
	static int dy[]={0,0,-1,1};
	static int dx[]={1,-1,0,0};
	static int N,M,r,c,k;
	static int[][]map;
	static int order[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map=new int[N][M];
		for(int i = 0 ; i < N ; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++){
				map[i][j]=  Integer.parseInt(st.nextToken());
			}
		}
		order=new int[k];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < k ; i++){
			order[i]=Integer.parseInt(st.nextToken())-1;
		}
		Node node= new Node(r,c);
		for(int i = 0 ; i < k ; i++){			
			int dir=order[i];
			int nextI=node.i+dy[dir];
			int nextJ=node.j+dx[dir];
			if(nextI<0||nextJ<0||nextI>=N||nextJ>=M){
				continue;
			}
			rollDice(dir);
			System.out.println(dice[1][1]);
			node.i=nextI;
			node.j=nextJ;
			
			if(map[nextI][nextJ]==0){
				map[nextI][nextJ]=dice[3][1];
			}else{
				dice[3][1]=map[nextI][nextJ];
				map[nextI][nextJ]=0;
			}
		}
		
	}
	public static void rollDice(int dir){
		int temp=-1;
		switch(dir){//µøº≠∫œ≥≤
		case 0:
			 temp=dice[1][2];
			dice[1][2]=dice[1][1];
			dice[1][1]=dice[1][0];
			dice[1][0]=dice[3][1];
			dice[3][1]=temp;
			break;
		case 1:
			 temp=dice[1][0];
			dice[1][0]=dice[1][1];
			dice[1][1]=dice[1][2];
			dice[1][2]=dice[3][1];
			dice[3][1]=temp;
			break;
		case 2:
			temp=dice[0][1];
			for(int i=0;i<=2 ; i++){
				dice[i][1]=dice[i+1][1];
			}
			dice[3][1]=temp;
			break;
		case 3:
			temp=dice[3][1];
			for(int i=3;i>=1;i--){
				dice[i][1]=dice[i-1][1];
			}
			dice[0][1]=temp;
			;
			break;
			
		}
	}
	
	static class Node{
		int i  , j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}
}
