package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_3987 {

	public static int N;
	public static int M;
	public static int graph[][];
	public static int flag[][];
	public static int dy[] = new int[] { -1, 0, 1, 0 };// �� ���� �Ʒ� ��
	public static int dx[] = new int[] { 0, 1, 0, -1 };
	public static int dirChange[][] = new int[][] { { 3, 2, 1, 0 }// "\����"
	, { 1, 0, 3, 2 }// "/"����
	};
	public static int retNum = Integer.MIN_VALUE;
	public static int retDir = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		flag = new int[N][M];
		graph = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = new String(br.readLine());
			for (int j = 0; j < M; j++) {
				flag[i][j] = -1;
				switch (str.charAt(j)) {
				case '.':
					graph[i][j] = 0;
					break;
				case '\\':
					graph[i][j] = 1;
					break;
				case '/':
					graph[i][j] = 2;
					break;
				case 'C':
					graph[i][j] = 3;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken()) - 1;
		int x = Integer.parseInt(st.nextToken()) - 1;

		Queue<Ship> que = new LinkedList<Ship>();

		for (int i = 0; i < 4; i++) {

			for (int p = 0; p < N; p++) {
				for (int L = 0; L < M; L++) {
					flag[p][L] = -1;
				}
			}
			flag[y][x] = 0;
			que.add(new Ship(y, x, i));
			while (!que.isEmpty()) {
				Ship ship = que.poll();
				int startDir = ship.dir;
				int nowY = ship.y;
				int nowX = ship.x;
				int nowDir = ship.dir;

				int nextY = nowY + dy[nowDir];
				int nextX = nowX + dx[nowDir];
				if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= M) {
					if (flag[nowY][nowX] > retNum) {
						retNum = flag[nowY][nowX];
						retDir = i;
					} else if (flag[nowY][nowX] == retNum) {
						retDir = Math.min(retDir, i);
					}
					continue;
					// ����ó������
				}

				boolean infinityFlag = false;
				if (graph[nextY][nextX] == 0 || graph[nextY][nextX] == 1 || graph[nextY][nextX] == 2) {

					if (flag[nextY][nextX] != -1&&nowDir == i && nextY == y && nextX == x) {// �湮���� ����ĭ
						
							infinityFlag = true;

						
					}else {

						if (graph[nextY][nextX] == 0) {
							que.add(new Ship(nextY, nextX, nowDir));
							flag[nextY][nextX] = flag[nowY][nowX] + 1;
						} else if (graph[nextY][nextX] == 1) { // '\'����
							que.add(new Ship(nextY, nextX, dirChange[0][nowDir]));
							flag[nextY][nextX] = flag[nowY][nowX] + 1;
						} else if (graph[nextY][nextX] == 2) {
							que.add(new Ship(nextY, nextX, dirChange[1][nowDir]));
							flag[nextY][nextX] = flag[nowY][nowX] + 1;
						} else if (graph[nextY][nextX] == 3) {
							// ����ó����
							if (flag[nowY][nowX] > retNum) {
								retNum = flag[nowY][nowX];
								retDir = i;
							} else if (flag[nowY][nowX] == retNum) {
								retDir = Math.min(retDir, i);
							}
						}
					}
				}
				if (infinityFlag == true) {

					switch (i) {
					case 0:
						System.out.println("U");
						break;
					case 1:
						System.out.println("R");
						break;
					case 2:
						System.out.println("D");
						break;
					case 3:
						System.out.println("L");
						break;
					}
					System.out.println("Voyager");
					return;
					// voyager ���̽���
				}

			}
		}

		switch (retDir) {
		case 0:
			System.out.println("U");
			break;
		case 1:
			System.out.println("R");
			break;
		case 2:
			System.out.println("D");
			break;
		case 3:
			System.out.println("L");
			break;
		}
		System.out.println(retNum + 1);

	}

	static class Ship {
		int y;
		int x;
		int dir;

		public Ship(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}

	}
}
