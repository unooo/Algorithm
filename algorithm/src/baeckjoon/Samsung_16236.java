package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_16236 {

	public static int N;
	public static int graph[][];
	public static int flag[][];
	public static int dy[] = new int[] { -1, 0, 1, 0 }; // 위, 왼, 아래, 오른
	public static int dx[] = new int[] { 0, -1, 0, 1 };
	public static int time = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		flag = new int[N][N];
		int startY = 0;
		int startX = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 9) {
					startY = i;
					startX = j;

				}
			}
		}

		int nowY = startY;
		int nowX = startX;
		graph[startY][startX] = 0;
		int sharkSize = 2;
		int sizeStack = 0;
		time = 0;

		while (true) {

			Queue<Integer> queueY = new LinkedList<Integer>();
			Queue<Integer> queueX = new LinkedList<Integer>();
			queueY.add(nowY);
			queueX.add(nowX);

			LinkedList<Shark> sharkList = new LinkedList<Shark>();

			flag = new int[N][N];
			int findFlag = 0;
			int nowLevel = flag[nowY][nowX];
			int checkLevel = flag[nowY][nowX];
			while (!queueY.isEmpty()) {
				nowY = queueY.poll();
				nowX = queueX.poll();

				int newY;
				int newX;
				for (int i = 0; i < 4; i++) {
					newY = nowY + dy[i];
					newX = nowX + dx[i];

					if (newX < 0 || newY < 0 || newX >= N || newY >= N)
						continue;

					if (flag[newY][newX] != 0) // 지나간 경로라면 다시검사하지않는다
						continue;
					if (graph[newY][newX] > sharkSize) {// 사이즈더큰 고기만나면 못지나감
						continue;
					} else if (graph[newY][newX] == sharkSize || graph[newY][newX] == 0) {
						// 사이즈같으면 지나갈 순 있음
						flag[newY][newX] = flag[nowY][nowX] + 1;
						queueX.add(newX);
						queueY.add(newY);

					} else if (graph[newY][newX] < sharkSize) {// 물고기 사이즈 작으면
																// 잡아먹음

						flag[newY][newX] = flag[nowY][nowX] + 1;
						if (findFlag == 0) {
							findFlag = 1;
							checkLevel = flag[nowY][nowX] + 1;
						}
						if (flag[newY][newX] <= checkLevel) {
							sharkList.add(new Shark(newX, newY, flag[nowY][nowX] + 1));

							// queueX.add(newX);
							// queueY.add(newY);
						}

					}
				}
			}
			if (findFlag == 1) {
				Shark ret = check(sharkList);
				nowY = ret.y;
				nowX = ret.x;
				sizeStack++;
				if (sizeStack == sharkSize) {
					sharkSize++;
					sizeStack = 0;
				}

			} else if (findFlag == 0)
				break;
		}

		System.out.println(time);

	}

	public static Shark check(LinkedList<Shark> sharkList) {

		int targetY = sharkList.get(0).y;
		int targetX = sharkList.get(0).x;
		int level = sharkList.get(0).level;
		;
		for (int j = 0; j < sharkList.size(); j++) {
			if (targetY > sharkList.get(j).y) {
				targetY = sharkList.get(j).y;
				targetX = sharkList.get(j).x;
				level = sharkList.get(j).level;

			} else if (targetY == sharkList.get(j).y) {
				if (targetX > sharkList.get(j).x) {
					targetY = sharkList.get(j).y;
					targetX = sharkList.get(j).x;
					level = sharkList.get(j).level;
				}
			}
		}
		graph[targetY][targetX] = 0;
		time += level;
		return new Shark(targetX, targetY, 0);
	}

	static class Shark {
		int x;
		int y;
		int level;

		Shark(int x, int y, int level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}
	}

}
