package Samsung_2021;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 새로운게임2 {

	static int N, K;
	static int[][] map;
	static final int White = 0;
	static final int Blue = 2;
	static final int Red = 1;
	static int dy[] = { 0, 0, -1, 1 };
	static int dx[] = { 1, -1, 0, 0 };
	static LinkedList<Horse>[][] horseMap;
	static ArrayList<Horse> horseAry = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		horseMap = new LinkedList[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				horseMap[i][j] = new LinkedList<>();
			}
		}

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;
			Horse horse = new Horse(k, r, c, dir);
			horseAry.add(horse);
			horseMap[r][c].add(horse);
		}

		int step = 1;

		while (true) {

			for (int hIdx = 0; hIdx < horseAry.size(); hIdx++) {
				Horse horse = horseAry.get(hIdx);
				int nextI = horse.i + dy[horse.dir];
				int nextJ = horse.j + dx[horse.dir];

				if (isBlue(nextI, nextJ, horse)) {
					dirChange(horse);
					nextI = horse.i + dy[horse.dir];
					nextJ = horse.j + dx[horse.dir];
					if (isBlue(nextI, nextJ, horse)) {
						continue;
					}

				}

				int fromIndex = findIdx(horseMap[horse.i][horse.j], horse);
				int toIndex = horseMap[horse.i][horse.j].size();
				LinkedList<Horse> tempList = new LinkedList<>(horseMap[horse.i][horse.j].subList(fromIndex, toIndex));
				if (map[nextI][nextJ] == Red)
					Collections.reverse(tempList);
				horseMap[nextI][nextJ].addAll(tempList);
				horseMap[horse.i][horse.j].subList(fromIndex, toIndex).clear();
				for (Horse tp : tempList) {
					tp.i = nextI;
					tp.j = nextJ;
				}
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (horseMap[i][j].size() >= 4) {
							System.out.println(step);
							return;
						}
					}
				}

			}
			step++;
			if (step > 1000) {
				System.out.println(-1);
				return;
			}
		}
		
	}

	public static int findIdx(LinkedList<Horse> horseList, Horse horse) {
		int idx = -1;
		for (int i = 0; i < horseList.size(); i++) {
			Horse tp = horseList.get(i);
			if (tp.idx == horse.idx) {
				idx = i;
				return idx;
			}
		}
		return idx;
	}

	public static boolean isBlue(int nextI, int nextJ, Horse horse) {
		boolean ret = false;
		if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N || map[nextI][nextJ] == Blue) {
			ret = true;
		}
		return ret;
	}

	public static void dirChange(Horse horse) {
		int dir = horse.dir;
		switch (dir) {
		case 0:
			horse.dir = 1;
			break;
		case 1:
			horse.dir = 0;
			break;
		case 2:
			horse.dir = 3;
			break;
		case 3:
			horse.dir = 2;
			break;

		}
	}

	static class Horse {
		int idx, i, j, dir;

		public Horse(int idx, int i, int j, int dir) {
			super();
			this.idx = idx;
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
	}
}
