package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_17822 {

	public static int N;
	public static int M;
	public static int T;
	public static int map[][];
	public static int[][] order;
	public static int[] dy = new int[] { 1, -1, 0, 0 };
	public static int[] dx = new int[] { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order = new int[T][3]; // 0번째 값의 배수, 1번째값으로 회전방향, 2번째값으로 칸수

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				order[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0 ; i < T ; i ++){
			int inumer=1;
			while(true){				
				int x = inumer*order[i][0];
				if(x>N)
					break;
				spin(x-1,order[i][1],order[i][2]);
				inumer++;
				
			}
			check();
			
		}
		
		int ret = 0;
		for (int i = 0; i < N; i++) {
			
			for (int j = 0; j < M; j++) {
				if(map[i][j]!=-1){
					ret+=map[i][j];
				}
			}
		}
		System.out.println(ret);

	}

	public static void spin(int x,int dir, int k) {
		int temp;
		for (int m = 0; m < k; m++) {// 시간초과나면 여길 고칠것

			switch (dir) {
			// 시계

			case 0:
				temp = map[x][M - 1];

				for (int i = 0; i < M - 1; i++) {
					map[x][M - 1 - i] = map[x][M - 1 - i - 1];
				}
				map[x][0] = temp;
				;
				break;
			// 반시계
			case 1:
				temp = map[x][0];
				for (int i = 0; i < M - 1; i++) {
					map[x][i] = map[x][i + 1];
				}
				map[x][M - 1] = temp;
				;
				break;
			}
		}
	}

	public static int check() {
		int ret = 0; // 인접인경우는 1 인접아닌경우는 0;

		int[][] visitFlag = new int[N][M];// 방문한 곳을 체크
		int[][] removeFlag = new int[N][M];// 지워야할곳을 체크

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (visitFlag[i][j] == 1) {
					continue;
				}
				Queue<Pair> queue = new LinkedList<Pair>();
				visitFlag[i][j] = 1;
				Pair pair = new Pair(i, j);
				queue.add(pair);

				while (!queue.isEmpty()) {
					pair = queue.poll();
					int startX = pair.x;
					int startY = pair.y;
					int nowX = pair.x;
					int nowY = pair.y;
					int newX = pair.x;
					int newY = pair.y;

					for (int k = 0; k < 4; k++) {
						newY = nowY + dy[k];
						newX = nowX + dx[k];

						if (newX >= M)
							newX = 0;
						if (newX < 0)
							newX = M - 1;
						if (newY < 0 || newY >= N)
							continue;
						
						if(visitFlag[newY][newX]==1)
							continue;

						if (map[nowY][nowX]!=-1&&map[nowY][nowX] == map[newY][newX]) {
							queue.add(new Pair(newY, newX));
							visitFlag[newY][newX] = 1;
							removeFlag[newY][newX] = 1;
							removeFlag[nowY][nowX] = 1;
							ret = 1;
						}

					}

				}

			}
		}
		
		//후처리 진횅

		if (ret == 1) {
			for (int i = 0; i < N; i++) {// 인접부분이 같은 숫자를 지운다(-1을 넣는다)
				for (int j = 0; j < M; j++) {
					if (removeFlag[i][j] == 1) {
						map[i][j] = -1;
					}
				}
			}
		} else {
			int sum = 0;
			int inumer=0;
			for (int i = 0; i < N; i++) {//
				for (int j = 0; j < M; j++) {
					if(map[i][j]!=-1){
						sum+=map[i][j];
						inumer++;
					}
				}
			}
			
			float mid = (float)sum/(float)inumer;
			for (int i = 0; i < N; i++) {//
				for (int j = 0; j < M; j++) {
					if(map[i][j]==-1){
						continue;
					}
					if(map[i][j]>mid){
						map[i][j]-=1;
					}else if(map[i][j]<mid){
						map[i][j]+=1;
					}
				}
			}
			
		}

		return ret;
	}

	static class Pair {
		int y;
		int x;

		Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
