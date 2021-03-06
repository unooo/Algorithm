package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair2 {
    int x;
    int y;
    Pair2(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Samsung_14502 {

	public static int N;
	public static int M;
	public static int graph[][];
	public static int newGraph[][];
	public static int flagGraph[][];
	public static int tempGraph[][];
	public static int dy[] = new int[] { 1, 0, -1, 0 };
	public static int dx[] = new int[] { 0, 1, 0, -1 };
	public static int ret = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//graph = new int[N][M];
		//newGraph = new int[N][M];
		tempGraph = new int[N][M];
		flagGraph = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				//graph[i][j] = temp;
				//newGraph[i][j] = temp;
				tempGraph[i][j] = temp;
			}
		}

		selectBox(0);
		// selectBox2();
		// selectBox3();
		System.out.println(ret);

	}

	public static void selectBox(int nowStep) {
		if (nowStep >= 3) {
			int[][] tempGraph2 = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tempGraph2[i][j] = tempGraph[i][j];
				}
			}

			makeNewGraph(tempGraph2);
			printGraph(tempGraph2);
			return;
		}

		for (int i = 0; i < N; i++) {

			for (int j = 0; j < M; j++) {
				if ( tempGraph[i][j] == 0) {
					tempGraph[i][j] = 1;
					
					selectBox(nowStep + 1);
					tempGraph[i][j] = 0;
					
				}
			}
		}
	}

	public static void makeNewGraph(int[][] graph) {
		
	
		Queue<Pair2> queue = new LinkedList<Pair2>();
		

		for (int i = 0; i < N; i++) { 
			for (int j = 0; j < M; j++) {
				if (graph[i][j] == 2) {
					Pair2 pair = new Pair2(j,i);
					queue.add(pair);
				}
			}
		}

		while (!queue.isEmpty()) {
			Pair2 pair = queue.poll();
			int nowY =pair.y;
			int nowX = pair.x;
			graph[nowY][nowX] = 2;
			

			for (int k = 0; k < 4; k++) {
				int newY = nowY + dy[k];
				int newX = nowX + dx[k];

				if (newY >= 0 && newX >= 0 && newY < N && newX < M){
					if (graph[newY][newX] == 0 ) {
						queue.add(new Pair2(newX,newY));
					
					}
				}

				

			}

		}

		/*int[][] flagGraph = new int[N][M];
		Queue<Integer> y = new LinkedList<Integer>();
		Queue<Integer> x = new LinkedList<Integer>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (graph[i][j] == 2) {
					y.add(i);
					x.add(j);
				}
			}
		}

		while (!y.isEmpty()) {
			int nowY = y.poll();
			int nowX = x.poll();
			graph[nowY][nowX] = 2;
			flagGraph[nowY][nowX] = 1;

			for (int k = 0; k < 4; k++) {
				int newY = nowY + dy[k];
				int newX = nowX + dx[k];

				if (newY < 0 || newX < 0 || newY >= N || newX >= M)
					continue;

				if (graph[newY][newX] == 0 && flagGraph[newY][newX] == 0) {
					y.add(newY);
					x.add(newX);
				}

			}

		}*/

		/*
		 * int [][] flagGraph = new int[N][M];
		 * 
		 * for(int i = 0 ; i < N ; i ++){ for(int j = 0 ; j < M ; j ++){
		 * 
		 * if(graph[i][j]==2&&flagGraph[i][j]==0){
		 * 
		 * Queue<Integer> y = new LinkedList<Integer>(); Queue<Integer> x = new
		 * LinkedList<Integer>(); y.add(i); x.add(j);
		 * 
		 * while(!y.isEmpty()){ int nowY=y.poll(); int nowX=x.poll();
		 * graph[nowY][nowX]=2; flagGraph[nowY][nowX]=1;
		 * 
		 * for(int k = 0 ; k < 4 ; k ++){ int newY=nowY+dy[k]; int
		 * newX=nowX+dx[k];
		 * 
		 * if(newY<0||newX<0||newY>=N||newX>=M) continue;
		 * 
		 * if(graph[newY][newX]==0&&flagGraph[newY][newX]==0){ y.add(newY);
		 * x.add(newX); }
		 * 
		 * } }
		 * 
		 * } } }
		 */

	}

	public static void printGraph(int[][] graph) {
		int sum = 0;
		for (int i = 0; i < N; i++) {

			for (int j = 0; j < M; j++) {

				if (graph[i][j] == 0) {
					++sum;
				}
			}
		}
		ret = Math.max(ret, sum);
	}
/*
	public static void selectBox3() {
		int digitFlag[] = new int[M * N];
		for (int i = 0; i < M * N; i++) {
			if (digitFlag[i] == 1)
				continue;
			if (tempGraph[i / (M)][i % (M)] != 0)
				continue;
			digitFlag[i] = 1;
			for (int j = i; j < M * N; j++) {
				if (digitFlag[j] == 1)
					continue;
				if (tempGraph[j / (M)][j % (M)] != 0)
					continue;
				digitFlag[j] = 1;
				for (int k = j; k < M * N; k++) {
					if (digitFlag[k] == 1)
						continue;
					if (tempGraph[k / (M)][k % (M)] != 0)
						continue;
					digitFlag[k] = 1;
					int[][] tempGraph2 = new int[N][M];
					for (int l = 0; l < N; l++) {
						for (int m = 0; m < M; m++) {
							tempGraph2[l][m] = tempGraph[l][m];
						}
					}
					tempGraph2[i / (M)][i % (M)] = 1;
					tempGraph2[j / (M)][j % (M)] = 1;
					tempGraph2[k / (M)][k % (M)] = 1;

					makeNewGraph(tempGraph2);
					printGraph(tempGraph2);

					digitFlag[k] = 0;
				}
				digitFlag[j] = 0;
			}
			digitFlag[i] = 0;
		}
	}
*/
}
