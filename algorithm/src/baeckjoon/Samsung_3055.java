package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.StringTokenizer;

public class Samsung_3055 {

	public static int R;
	public static int C;
	public static int graph[][];
	public static int waterGraph[][];
	public static int dochiGraph[][];
	public static int dy[] = new int[] { 1, -1, 0, 0 };
	public static int dx[] = new int[] { 0, 0, 1, -1 };
	public static int ret = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new int[R][C];
		waterGraph = new int[R][C];
		dochiGraph = new int[R][C];
		Pair dochi = null;
		ArrayList<Pair> waterAry = new ArrayList<Pair>();
		for (int i = 0; i < R; i++) {
			String str = new String(br.readLine());
			for (int j = 0; j < C; j++) {
				waterGraph[i][j] = -1;
				dochiGraph[i][j] = -1;
				switch (str.charAt(j)) {
				case '.':
					graph[i][j] = 0;
					break;
				case 'X':
					graph[i][j] = 1;// 벽
					break;
				case '*':
					graph[i][j] = 2;// 물
					waterAry.add(new Pair(i, j));
					waterGraph[i][j] = 0;
					break;
				case 'D':
					graph[i][j] = 3;// 비버의 굴
					break;
				case 'S':
					graph[i][j] = 4;// 시작지
					dochi = new Pair(i, j);
					dochiGraph[i][j] = 0;
					break;
				}
			}
		}

		Queue<Pair> que = new LinkedList<Pair>();

		for (Pair temp : waterAry) {
			que.add(temp);
		}

		while (!que.isEmpty()) {

			Pair pair = que.poll();
			int nowY = pair.y;
			int nowX = pair.x;

			int nextY;
			int nextX;

			for (int i = 0; i < 4; i++) {
				nextY = nowY + dy[i];
				nextX = nowX + dx[i];

				if (nextY < 0 || nextX < 0 || nextY >= R || nextX >= C)
					continue;
				if (waterGraph[nextY][nextX] == -1) {
					if (graph[nextY][nextX] == 0 || graph[nextY][nextX] == 4) {
						waterGraph[nextY][nextX] = waterGraph[nowY][nowX] + 1;
						que.add(new Pair(nextY, nextX));
					}
				}
			}

		}

		que = new LinkedList<Pair>();

		que.add(dochi);
		while (!que.isEmpty()) {
			Pair pair = que.poll();
			int nowY = pair.y;
			int nowX = pair.x;

			int nextY;
			int nextX;
			for (int i = 0; i < 4; i++) {
				nextY = nowY + dy[i];
				nextX = nowX + dx[i];

				if (nextY < 0 || nextX < 0 || nextY >= R || nextX >= C)
					continue;

				if (dochiGraph[nextY][nextX] == -1) {

					if (graph[nextY][nextX] == 0) {

						if (dochiGraph[nowY][nowX] + 1 < waterGraph[nextY][nextX]||waterGraph[nextY][nextX]==-1) {
							dochiGraph[nextY][nextX] = dochiGraph[nowY][nowX] + 1;
							que.add(new Pair(nextY, nextX));
						}

					} else if (graph[nextY][nextX] == 3) {
						int num = dochiGraph[nowY][nowX] + 1;
						ret = Math.min(ret, num);
					}
				}
			}
		}
		if (ret == Integer.MAX_VALUE)
			System.out.println("KAKTUS");
		else
			System.out.println(ret);

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
//도치그래프와 워터그래프 -1 초기화처리에대한 114번째줄 ||waterGraph[nextY][nextX]==-1) 부분이 빠져서 틀림. 이부분주의 기억하장
/*
..XXXXX.X.*....
X.....X.X..*...
.X.S..X.X......
D.X...X.XXXXXXX
.X....X........
.X....X.XXXXXXX
.XXXXXX.X......
........X......
XXXXXXXXX...*..
*/