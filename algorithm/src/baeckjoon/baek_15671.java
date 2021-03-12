package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baek_15671 {
	static int dy[] = new int[]{-1,-1,0,1,1,1,0,-1};
	static int dx[] = new int[]{0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int map[][] = new int[6][6];
		map[2][2]=2;
		map[3][3]=2; //백돌은 2
		map[2][3]=1;
		map[3][2]=1; //흑돌은 1
		
		for(int i = 0 ; i < N ; i++){
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken())-1;
			int C= Integer.parseInt(st.nextToken())-1;
			int dol = i%2==0?1:2;
			map[R][C]=dol;
			for(int dir=0 ; dir< 8 ; dir++){
				int nextR=R;
				int nextC=C;
				LinkedList<Node> list = new LinkedList<Node>();
				while(true){
					nextR+=dy[dir];
					nextC+=dx[dir];
					if(nextR<0||nextC<0||nextR>=6||nextC>=6)
						break;
					if(map[nextR][nextC]==0)
						break;
					if(map[nextR][nextC]!=dol){
						list.add(new Node(nextR,nextC));
						
					}else{
						for(Node node : list){
							map[node.i][node.j]=dol;
						}
						break;
					}
					
				}
			}
			int bCount=0;
			int wCount=0;
			for(int k = 0 ; k < 6 ; k++){
				for(int j = 0 ; j < 6 ; j ++){
					if(map[k][j]==1)
						bCount++;
					else if(map[k][j]==2)
						wCount++;
				}
			}
			if(bCount==0||wCount==0)
				break;
			
		}
		int bCount=0;
		int wCount=0;
		for(int i = 0 ; i < 6 ; i++){
			for(int j = 0 ; j < 6 ; j ++){
				if(map[i][j]==1){
					bCount++;
					System.out.print("B");
				}
				else if(map[i][j]==2){
					wCount++;
					System.out.print("W");
				}else{
					System.out.print(".");
				}
			}
			System.out.println();
		}
		if(bCount>wCount)
			System.out.println("Black");
		else
			System.out.println("White");
		
	}
	
	static class Node{
		int i , j ;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}

}

