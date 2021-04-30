

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_2667 {
	static int N;
	static int[][] map;
	static int dy[] = new int[]{1,-1,0,0};
	static int dx[] = new int[]{0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String str = new String(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = (str.charAt(j) - '0');
			}
		}

		int[][] visit = new int[N][N];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && visit[i][j] == 0) {
					idx++;
					Pair startNode = new Pair(i, j);
					Queue<Pair> queue = new LinkedList<Pair>();
					queue.add(startNode);
					visit[i][j] = idx;

					while (!queue.isEmpty()) {
						Pair pair = queue.poll();
						for (int dir = 0; dir < 4; dir++) {
							int nextI = pair.i + dy[dir];
							int nextJ = pair.j + dx[dir];
							if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N)
								continue;
							if (visit[nextI][nextJ] != 0)
								continue;
							if (map[nextI][nextJ] == 0)
								continue;
							visit[nextI][nextJ]=idx;
							queue.add(new Pair(nextI, nextJ));
						}
					}

				}
			}
		}

		int digitFlag[] = new int[10000];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] != 0) {
					digitFlag[visit[i][j]]++;
				}
			}
		}
		System.out.println(idx);
		for (int i = 0; i < digitFlag.length; i++) {
			if (digitFlag[i] == 0) {
				digitFlag[i]=Integer.MAX_VALUE;
			}
		}
		Arrays.sort(digitFlag);
		for (int i = 0; i < digitFlag.length; i++) {
			if (digitFlag[i] == Integer.MAX_VALUE) {
				return;
			}else{
				System.out.println(digitFlag[i]);
			}
		}
	}

	static class Pair {
		int i, j;

		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

}
