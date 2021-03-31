package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 어른상어19237 {

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static Shark sharkMap[][];
	static Scent scentMap[][];
	static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		sharkMap = new Shark[N][N];
		scentMap = new Scent[N][N];
		Shark[] sharkStore = new Shark[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int idx = Integer.parseInt(st.nextToken());

				if (idx != 0) {
					Shark shark = new Shark(i, j, idx);
					sharkMap[i][j] = (shark);
					sharkStore[idx - 1] = shark;
					scentMap[i][j] = new Scent(idx, K);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < M; idx++) {
			sharkStore[idx].nowDir = Integer.parseInt(st.nextToken()) - 1;
		}
		for (int idx = 0; idx < M; idx++) {
			int[][] priority = new int[4][4];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					priority[i][j] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
			sharkStore[idx].priority = priority;
		}

		int step = 0;

		while (true) {
			//상어이동
			Outer: for (int idx = sharkStore.length - 1; idx >= 0; idx--) {				
				Shark shark = sharkStore[idx];
				if(shark==null)
					continue;
				int i = shark.i;
				int j = shark.j;

				for (int dir = 0; dir < 4; dir++) {
					int nextI = i + dy[shark.priority[shark.nowDir][dir]];
					int nextJ = j + dx[shark.priority[shark.nowDir][dir]];
					if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N)
						continue;
					if (scentMap[nextI][nextJ] != null)
						continue;
					shark.nowDir = shark.priority[shark.nowDir][dir];
					if (sharkMap[nextI][nextJ] != null) {
						sharkStore[sharkMap[nextI][nextJ].idx - 1] = null;
					}
					shark.i=nextI;
					shark.j=nextJ;
					sharkMap[i][j] = null;
					sharkMap[nextI][nextJ] = (shark);
					continue Outer;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nextI = i + dy[shark.priority[shark.nowDir][dir]];
					int nextJ = j + dx[shark.priority[shark.nowDir][dir]];
					if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N)
						continue;

					if (scentMap[nextI][nextJ].sharkIdx == shark.idx) {
						shark.nowDir = shark.priority[shark.nowDir][dir];
						shark.i=nextI;
						shark.j=nextJ;
						sharkMap[nextI][nextJ] = (shark);
						sharkMap[i][j] = null;
						continue Outer;
					}
				}

			}
			//냄새감소
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					Scent scent = scentMap[i][j];
					if (scent == null)
						continue;
					scent.now--;
					if (scent.now == 0)
						scentMap[i][j] = null;
				}
			}
			//신규위치냄새추가
			int sum = 0;
			for (int i = 0; i < M; i++) {
				Shark shark=sharkStore[i];
				if (shark != null) {
					scentMap[shark.i][shark.j]=new Scent(shark.idx, K);							
					sum++;
				}
			}
			step++;
			if (sum == 1) {
				System.out.println(step);
				return;
			}
			if (step >= 1000) {
				System.out.println(-1);
				return;
			}
		}

	}

	static class Scent {
		int sharkIdx, now;

		public Scent(int sharkIdx, int now) {
			super();
			this.sharkIdx = sharkIdx;
			this.now = now;
		}

	}

	static class Shark {
		int i, j;
		int idx, nowDir;
		int[][] priority;

		public Shark(int i, int j, int idx) {
			super();
			this.i = i;
			this.j = j;
			this.idx = idx;
		}

	}

}
