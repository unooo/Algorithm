package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_13460_2 {

	public static int N;
	public static int M;
	public static int graph[][];
	public static Pair pair1;
	public static Pair pair2;

	public static int dy[] = new int[] { 0, 0, 1, -1 };
	public static int dx[] = new int[] { 1, -1, 0, 0 };
	public static int ret = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = new String(br.readLine());

			for (int j = 0; j < M; j++) {

				switch (str.charAt(j)) {
				case '#':
					graph[i][j] = 1;
					break;
				case 'B':
					graph[i][j] = 2;
					pair2 = new Pair(i, j);
					break;
				case 'R':
					graph[i][j] = 3;
					pair1 = new Pair(i, j);
					break;
				case 'O':
					graph[i][j] = 4;
					break;
				}
			}
		}

		int[] order = new int[10];
		dfs(0, order);
		System.out.println(ret);
	}

	public static void dfs(int now, int order[]) {

		if (now == 10) {
			int num = check(order);
			if (num != -1) {
				if (ret == -1)
					ret = num;
				else
					ret = Math.min(ret, num);

			}

			return;
		}

		for (int i = 0; i < 4; i++) {

			order[now] = i;
			dfs(now + 1, order);

		}

	}

	public static int check(int[] order) {
		int ret = -1;

		int nowY1 = pair1.y;
		int nowX1 = pair1.x;

		int nowY2 = pair2.y;
		int nowX2 = pair2.x;

		int nextY1;
		int nextX1;

		int nextY2;
		int nextX2;

		for (int i = 0; i < order.length; i++) {
			int rOutFlg = 0;
			int bOutFlg = 0;
			
			
			int startY1=nowY1;
			int startX1=nowX1;
			int startY2=nowY2;
			int startX2=nowX2;
			
			
			
			while (true) {
				nextY1 = nowY1 + dy[order[i]];
				nextX1 = nowX1 + dx[order[i]];

				if (graph[nextY1][nextX1] == 1) {
					nextY1 = nowY1;
					nextX1 = nowX1;
					break;
				}

				if (graph[nextY1][nextX1] == 4) {
					rOutFlg = 1;
					break;
				}
				nowY1 = nextY1;
				nowX1 = nextX1;
			}

			while (true) {
				nextY2 = nowY2 + dy[order[i]];
				nextX2 = nowX2 + dx[order[i]];
				if (graph[nextY2][nextX2] == 1) {
					nextY2 = nowY2;
					nextX2 = nowX2;
					break;
				}

				if (graph[nextY2][nextX2] == 4) {
					bOutFlg = 1;
					break;
				}

				nowY2 = nextY2;
				nowX2 = nextX2;
			}

			if (bOutFlg == 1) {
				bOutFlg = 0;
				rOutFlg = 0;
				ret = -1;
				return -1;
			} else if (rOutFlg == 1) {
				ret = i + 1;
				break;
			}

			if (nextY1 == nextY2 && nextX1 == nextX2) {

				switch (order[i]) {
				case 2: // y 1 로 이동시
					if (startY1 > startY2)
						nextY2 -= 1;
					else
						nextY1 -= 1;
					break;
				case 3:
					if (startY1> startY2)
						nextY1 += 1;
					else
						nextY2 += 1;

					break;
				case 0: // x로 1 이동시
					if (startX1 > startX2)
						nextX2 -= 1;
					else
						nextX1 -= 1;
					break;
				case 1: // x로 -1 이동시
					if (startX1 > startX2)
						nextX1 += 1;
					else
						nextX2 += 1;
					break;
				}
			}

			nowY2 = nextY2;
			nowX2 = nextX2;
			nowY1 = nextY1;
			nowX1 = nextX1;
		}

		return ret;
	}

	static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}
}
