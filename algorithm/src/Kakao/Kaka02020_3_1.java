package Kakao;


import java.util.Iterator;
import java.util.LinkedList;

public class Kaka02020_3_1 {

	public static void main(String[] args) {

		int[][] key = new int[][] { { 0, 0, 0 }, { 1, 1, 0 }, { 0, 1, 1 } };
		int[][] lock = new int[][] { { 1, 1, 1 }, { 1, 1, 0  }, { 1, 0, 1  }  };


		System.out.println(solution(key, lock));
	}

	public static boolean solution(int[][] key, int[][] lock) {
		boolean answer = false;
		int keyNum = 0;
		int M = key.length;
		
		int N = lock.length;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (lock[i][j] == 0)
					keyNum++;
			}
		}

		for (int i = 0; i < 4; i++) {
			spin(key);
			LinkedList<Posi> pairList = new LinkedList<Posi>();

			for (int j = 0; j < M; j++) {
				for (int k = 0; k < M; k++) {
					if (key[j][k] == 1)
						pairList.add(new Posi(j, k));
				}
			}

			for (int j = N *(-1); j <= N; j++) {

				for (int  k = N*(-1) ; k <= N; k++) {

					
					int nowNum = 0;
					int failFlag=0;
					for(int L=0;L<pairList.size();L++){
						
						Posi pair = pairList.get(L);
						int startY = pair.y;
						int startX = pair.x;
						int newY = startY + j;
						int newX = startX + k;
						
						if (newY < 0 || newX < 0 || newY >= N || newX >= N) {
							continue;
						}
						
						if (lock[newY][newX] == 0){
							nowNum++;
						}else{
							failFlag=1;
							answer=false;
							break;
						}
						
						if(nowNum==keyNum){
							answer=true;
						}
					
					}
					
					if(answer==true)
						return true;
					
					

				}
			}

		}
	

		return answer;
	}

	public static void spin(int[][] graph) {

		int N = graph.length;
		int[][] temp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = graph[N-j-1][i];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = temp[i][j];
			}
		}
	}

	static class Posi {
		int y;
		int x;

		public Posi(int y, int x) {
	
			this.y = y;
			this.x = x;
		}

	}

}
