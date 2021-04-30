package LastCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사상어와파이어스톰 {

	static int N,Q, order[];
	static int map[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		Q=Integer.parseInt(st.nextToken());
		int powedN=(int) Math.pow(2, N);
		map = new int[powedN][powedN];
		
		for(int i = 0 ; i < powedN ; i ++){
			st= new StringTokenizer(br.readLine());
			for(int j = 0 ; j <powedN ; j++){
					map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		order = new int[Q];
		st= new StringTokenizer(br.readLine());
		for(int i = 0 ; i < Q ; i++){
			order [i]=Integer.parseInt(st.nextToken());
		}
		for(int i = 0 ; i< Q ;i++){
			spin(order[i]);
			melt(order[i]);
		}
		
		System.out.println(totalSum());
		System.out.println(maxSize());
	}
	
	public static void spin(int L){
		int powedN=(int) Math.pow(2, N);
		int powedL=(int) Math.pow(2, L);
		int[][] newMap = new int[powedN][powedN];
		
		for(int i = 0 ; i < powedN ; i+=powedL){
			for(int j = 0 ; j < powedN ;j+=powedL){
				
				int idx=0;
				for(int c = j ; c<j+powedL ; c++){
					for(int r = i+powedL-1 ; r>=i ;r--){
						int newI=i+(idx/powedL);
						int newJ=j+(idx%powedL);
						newMap[newI][newJ]=map[r][c];		
						idx++;
					}
				}
				
			}
		}		
		map=newMap;
	}
	static int dy[] = new int[]{-1,0,1,0};
	static int dx[] = new int[]{0,1,0,-1};
	public static void melt(int L){
		int powedN=(int) Math.pow(2, N);
		int powedL=(int) Math.pow(2, L);
		int[][] newMap = new int[powedN][powedN];
		for(int i = 0 ; i < powedN ; i++){
			for(int j = 0 ; j < powedN ;j++){
				if(map[i][j]==0)
					continue;
				int cnt=0;
				for(int dir=0;dir<4;dir++){
					int nextI=i+dy[dir];
					int nextJ=j+dx[dir];
					if(nextI<0||nextJ<0||nextI>=powedN||nextJ>=powedN||map[nextI][nextJ]==0){						
						continue;
					}
					cnt++;
				}			
				if(cnt<3)
					newMap[i][j]=map[i][j]-1;
				else
					newMap[i][j]=map[i][j];
			}
		}
		map=newMap;
	}
	public static int  totalSum(){
		int powedN=(int) Math.pow(2, N);
		int sum=0;
		for(int i = 0 ; i < powedN ; i++){
			for(int j = 0 ; j < powedN ;j++){
				sum+=map[i][j];
			}
		}
		return sum;
	}
	
	public static int maxSize(){
		int powedN=(int) Math.pow(2, N);
		int sum=0;
		int[][] visit = new int[powedN][powedN];
		Queue<Node> queue = new LinkedList<Node>();
		
		for(int i = 0 ; i < powedN ; i++){
			for(int j = 0 ; j < powedN ;j++){				
				if(visit[i][j]!=0)
					continue;
				if(map[i][j]==0){
					visit[i][j]=1;
					continue;
				}
				
				queue.add(new Node(i,j));
				visit[i][j]=1;
				int size=0;
				while(!queue.isEmpty()){
					size++;
					Node node = queue.poll();					
					for(int dir = 0 ; dir < 4 ; dir++){
						int nextI = node.r+dy[dir];
						int nextJ = node.c+dx[dir];
						if(nextI<0||nextJ<0||nextI>=powedN||nextJ>=powedN||visit[nextI][nextJ]!=0){						
							continue;
						}
						if(map[nextI][nextJ]==0)
							continue;
						visit[nextI][nextJ]=1;
						queue.add(new Node(nextI,nextJ));
					}
				}
				sum=Math.max(sum, size);
			}
		}
		return sum;
	}
	
	static class Node{
		int r , c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
}
