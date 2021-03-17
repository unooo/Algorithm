package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ·Îº¿Ã»¼Ò±â14503 {

	static int dy[]={-1,0,1,0};
	static int dx[] = {0,1,0,-1};
	static int [][]map;
	static int N,M,r,c,dir;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		 st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		map=new int[N][M];
		for(int i = 0 ; i < N ; i++){
			 st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++){
				switch(Integer.parseInt(st.nextToken())){
				case 0 :
					map[i][j]=0;
					break;
				case 1:
					map[i][j]=1;
					break;
				}
			}
		}
		Node node = new Node(r,c,dir);
		int clearNum=0;
		
		Outer:
		while(true){
			clearNum+=1;
			map[node.i][node.j]=-1;
			int turn=0;
			
			while(true){
				if(turn==4){
					int backI=node.i+dy[(node.dir+2)%4];
					int backJ=node.j+dx[(node.dir+2)%4];
					if(backI<0||backJ<0||backI>=N||backJ>=M||map[backI][backJ]==1){
						System.out.println(clearNum);
						return;
					}else{
						node.i=backI;
						node.j=backJ;
						turn=0;
						continue;
					}
				}
				
				int nextDir=(node.dir+3)%4;
				int nextI=node.i+dy[nextDir];
				int nextJ=node.j+dx[nextDir];				
				if(nextI<0||nextJ<0||nextI>=N||nextJ>=M||map[nextI][nextJ]==1||map[nextI][nextJ]==-1){
					node.dir=nextDir;
					turn++;
					continue;
				}
				if(map[nextI][nextJ]==0){
					node.i=nextI;
					node.j=nextJ;
					node.dir=nextDir;
					continue Outer;
				}
			}
			
			
		}
		
	}
	static class Node{ 
		int i,j,dir;

		public Node(int i, int j, int dir) {
			super();
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
	}
}
