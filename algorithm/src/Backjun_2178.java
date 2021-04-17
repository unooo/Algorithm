

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjun_2178 {

	static int N,M;
	static int board[][];
	static int dy[] = new int[]{-1,0,1,0};
	static int dx[] = new int[]{0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		board=new int[N][M];
		for(int i = 0 ; i < N ; i ++){
			String str = new String(br.readLine());
			for(int j = 0 ; j < M ; j++){
				board[i][j]=Integer.parseInt(str.charAt(j)+"");
			}
		}
		
		Pair pair = new Pair(0,0);
		int[][] visit = new int[N][M];
		visit[pair.i][pair.j]=1;
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(pair);
		while(!queue.isEmpty()){
			
			pair = queue.poll();
			
			for(int i = 0 ; i < 4 ; i ++){
				int nextY = pair.i+dy[i];
				int nextX = pair.j+dx[i];
				if(nextY<0||nextX<0||nextY>=N||nextX>=M)
					continue;
				if(visit[nextY][nextX]!=0)
					continue;
				if(board[nextY][nextX]==0)
					continue;
				visit[nextY][nextX] = visit[pair.i][pair.j]+1;
				queue.add(new Pair(nextY,nextX));
			}
			
		}
		System.out.println(visit[N-1][M-1]);
		
		
	}
	
	
	static class Pair{
		int i , j;

		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}
	
}
