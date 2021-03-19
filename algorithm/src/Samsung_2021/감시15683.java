package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 감시15683 {

	static int N, M;
	static int map[][];
	static int camNum;
	static int[] path;
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };
	static LinkedList<Node> camList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		camList = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tp = Integer.parseInt(st.nextToken());
				map[i][j] = tp;
				if (tp != 0 && tp != 6) {
					camList.add(new Node(i, j, tp));
					camNum++;
				}
			}
		}
		path = new int[camNum];
		dfs(0);
		System.out.println(ret);
	}

	// 중복조합
	static int ret = Integer.MAX_VALUE;
	public static void dfs(int next) {

		if (next >= camNum) {
			// todo
			int newMap[][] = copyMap();

			for (int i = 0; i < camNum; i++) {
				Node node = camList.get(i);

				for (int dir = 0; dir < 4; dir++) {

					if (dir == 0) { //전진
					} else if (dir == 1) { //우회전
						if (node.category == 1 || node.category == 2 || node.category == 3 || node.category == 4)
							continue;
					
					} else if (dir == 2) {//후진
						if (node.category == 1 || node.category == 3)
							continue;					
					} else {//좌회전
						if (node.category == 1 || node.category == 2)
							continue;
					}

					int nextI = node.i + dy[(path[i] + dir) % 4];
					int nextJ = node.j + dx[(path[i] + dir) % 4];

					while (true) {
						if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M || map[nextI][nextJ] == 6) {
							nextI = nextI - dy[(path[i] + dir) % 4];
							nextJ = nextJ - dx[(path[i] + dir) % 4];
							break;
						}
						newMap[nextI][nextJ] = -1;
						nextI += dy[(path[i] + dir) % 4];
						nextJ += dx[(path[i] + dir) % 4];
					}

				}

			}
			int count=0;
			for(int i = 0 ; i < N ; i++)
				for(int j = 0 ; j < M ; j++){
					if(newMap[i][j]==0)
						count++;
				}

			ret=Math.min(ret, count);
			return;
		}

		for (int i = 0; i < 4; i++) {
			path[next] = i;
			dfs(next + 1);
		}
	}

	static int[][] copyMap() {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}

	static class Node {
		int i, j, category;

		public Node(int i, int j, int category) {
			super();
			this.i = i;
			this.j = j;
			this.category = category;
		}

	}

}
