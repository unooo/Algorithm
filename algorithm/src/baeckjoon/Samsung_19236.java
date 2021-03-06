package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_19236 {

	public static int[] dy = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] dx = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int sum = Integer.MIN_VALUE;
	public static int[][] map = new int[4][4];
	public static Fish[] fishAry = new Fish[16];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int fishNum = Integer.parseInt(st.nextToken());
				int fishDir = Integer.parseInt(st.nextToken()) - 1;
				Fish fish = new Fish();
				fish.dir = fishDir;
				fish.y = i;
				fish.x = j;
				fish.alive = 1;
				fishAry[fishNum - 1] = fish;
				map[i][j] = fishNum;
			}
		}

		int sharkDir = fishAry[map[0][0] - 1].dir;
		fishAry[map[0][0] - 1] = null;
		map[0][0] = -1;
		dfs(map, fishAry, sharkDir, 0);
		System.out.println(sum);
	}

	public static void dfs(int[][] tpMap, Fish[] tpFishAry, int sharkDir, int score) {

		// 고기 이동
		int[][] newMap = new int[4][4];
		Fish[] newFishAry = new Fish[16];
		// 복사
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newMap[i][j] = tpMap[i][j];
			}
		}
		for (int i = 0; i < tpFishAry.length; i++)
			newFishAry[i] = tpFishAry[i];
		move(newMap, newFishAry);
		// 물고기 이동 완료
		// 상어 이동 시작
		// 상어 좌표 찾기
		int prevSharkY = -1, prevSharkX = -1, nextSharkY = -1, nextSharkX = -1;

		Outer: for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (newMap[i][j] == -1) {
					prevSharkY = i;
					prevSharkX = j;
					break Outer;
				}
			}
		}
		// 상어 좌표 찾기 완료
		// 상어는 최대
		for (int jump = 1; jump <= 3; jump++) {

			nextSharkY = prevSharkY + dy[sharkDir] * jump;
			nextSharkX = prevSharkX + dx[sharkDir] * jump;

			if (nextSharkY < 0 || nextSharkX < 0 || nextSharkY >= 4 || nextSharkX >= 4) { // 범위가
																							// //
																							// 넘어가는경우
				sum = Math.max(sum, score);
				break;
			}
			if (newMap[nextSharkY][nextSharkX] == 0) { // 먹을 물고기 없는경우

				continue;
			}
			int deleteFishNum = newMap[nextSharkY][nextSharkX];
			Fish deleteFish = newFishAry[deleteFishNum - 1];
			int newSharkDir = newFishAry[deleteFishNum - 1].dir;

			newMap[nextSharkY][nextSharkX] = -1;
			newMap[prevSharkY][prevSharkX] = 0;
			newFishAry[deleteFishNum - 1].alive = 0;
			dfs(newMap, newFishAry, newSharkDir, score + deleteFishNum);
			newMap[nextSharkY][nextSharkX] = deleteFishNum;
			newMap[prevSharkY][prevSharkX] = -1;
			newFishAry[deleteFishNum - 1].alive = 1;

		}

	}

	public static void move(int[][] newMap, Fish[] newFishAry) {// 물고기 이동시킴

		for (int i = 0; i < newFishAry.length; i++) {
			int prevY = -1, prevX = -1, nextY = -1, nextX = -1;
			if (newFishAry[i] == null)
				continue;
			prevY = newFishAry[i].y;
			prevX = newFishAry[i].x;

			for (int j = 0; j < 8; j++) {
				nextY = prevY + dy[(newFishAry[i].dir + j) % 8];
				nextX = prevX + dx[(newFishAry[i].dir + j) % 8];
				if (!(nextY < 0 || nextX < 0 || nextY >= 4 || nextX >= 4 || newMap[nextY][nextX] == -1)) {
					break;
				}
			}

			int temp = -1;
			temp = newMap[nextY][nextX];
			newMap[nextY][nextX] = newMap[prevY][prevX];
			newMap[prevY][prevX] = temp;
			if (temp != 0) {
				int tempX = -1;
				tempX = newFishAry[temp - 1].x;
				newFishAry[temp - 1].x = newFishAry[i].x;
				newFishAry[i].x = tempX;

				int tempY = -1;
				tempY = newFishAry[temp - 1].y;
				newFishAry[temp - 1].y = newFishAry[i].y;
				newFishAry[i].y = tempY;
			} else {
				newFishAry[i].x=nextX;
				newFishAry[i].y=nextY;
			}

		}

	}

	static class Fish {

		int y;
		int x;
		int dir;
		int alive;
	}

}
