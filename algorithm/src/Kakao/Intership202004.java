package Kakao;

import java.util.LinkedList;
import java.util.Queue;

public class Intership202004 {
	static int dy[] = new int[] { -1, 1, 0, 0 };
	static int dx[] = new int[] { 0, 0, -1, 1 };
	static int N;
	static int path[] ;
	static int res=Integer.MAX_VALUE;
	public static void main(String[] args) {
		int[][] board= new int[][]	{{0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0}, {0, 0, 1, 0, 0, 0}, {1, 0, 0, 1, 0, 1}, {0, 1, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0}};
				//{{0,0,0},{0,0,0},{0,0,0}};
		
		N=board.length;
	System.out.println(solution(board));

	}

	public static int solution(int[][] board) {
		int answer = 0;
		int N = board.length;
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(0, 0));
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1)
					map[i][j] = -1;
				else
					map[i][j] = board[i][j];
			}
		map[0][0]=1;
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			int nowY = pair.y;
			int nowX = pair.x;
//			if(nowY==N-1&&nowX==N-1)
//				System.out.println(map[nowY][nowX]);
			for (int i = 0; i < 4; i++) {
				int nextY = nowY + dy[i];
				int nextX = nowX + dx[i];

				if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N)
					continue;
				if(map[nextY][nextX]!=0)
					continue;
				map[nextY][nextX]=map[nowY][nowX]+1;
				queue.add(new Pair(nextY,nextX));
				
			}

		}
		path=new int[N*N];
		findPath(0, map, 0, 0);
		return res;
	}

	static void findPath(int next,int[][] map, int nowY,int nowX){
		
		int nextY=-1;
		int nextX=-1;
		int N = map.length;
		if(nowY==N-1&&nowX==N-1){
			int pp=calc(next);
			res=Math.min(res, pp);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			nextY = nowY + dy[i];
			nextX = nowX + dx[i];
			if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) {
				continue;
			}
			if(map[nowY][nowX]+1!=map[nextY][nextX])
				continue;
			path[next]=i;
			findPath(next+1,map,nextY,nextX);
		}	
		
	}
	
	public static int calc(int next){
		int ret = 100 ;
		
		for(int i = 1 ; i < next ; i ++){
			ret+=100;
			if(path[i]==0||path[i]==1){
				
				if(path[i-1]==2||path[i-1]==3){
					ret+=500;
				}				
			}else if(path[i]==2||path[i]==3){
				if(path[i-1]==0||path[i-1]==1){
					ret+=500;
				}
			}
		}
		
		return ret;
	}

	static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
