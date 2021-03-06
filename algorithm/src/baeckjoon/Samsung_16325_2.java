package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Samsung_16325_2 {
	public static int N;
	public static int M;
	public static int K;

	public static int A[][];
	public static int originalA[][];
	public static int tree[][][];
	public static int[] dy = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
	public static int[] dx = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		originalA = new int[N][N];
		tree = new int[N][N][];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {

				A[i][j] = 5;
				originalA[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			tree[y][x] = new int[1];
			tree[y][x][0] = Integer.parseInt(st.nextToken());
		}

		int year = 0;
		while (year < K) {

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (tree[i][j] != null) {
						int[] treeAry = tree[i][j];
						Arrays.sort(treeAry);
						int dead = 0;
						for (int k = 0; k < treeAry.length; k++) {
							if (treeAry[k] == -1) {
								continue;
							} else if (A[i][j] < treeAry[k]) {
								dead += treeAry[k] / 2;
								treeAry[k] = -1;
							} else {
								A[i][j] -= treeAry[k];
								treeAry[k]++;
							}
						}
						A[i][j] += dead;

					}
				}
			}

			// °¡À»

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (tree[i][j] != null) {
						int[] treeAry = tree[i][j];
						for (int k = 0; k < treeAry.length; k++) {
							if (treeAry[k] != 5) {
								continue;
							}
							for (int l = 0; l < 8; l++) {
								int nowY = i;
								int nowX = j;
								int newY = nowY + dy[l];
								int newX = nowX + dx[l];
								if (newY >= N || newX >= N || newY < 0 || newX < 0) {
									continue;
								}
								//
								tree[newY][newX] = setting(tree[newY][newX]);
							}

						}
					}
				}
			}

			for (int i = 0; i < N; i++) {

				for (int j = 0; j < N; j++) {
					A[i][j] += originalA[i][j];
				}
			}
			year++;

		}

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int[] treeAry = tree[i][j];
				if (treeAry != null)
					for (int k = 0; k < treeAry.length; k++) {
						if (treeAry[k] != -1)
							sum++;
					}
			}
		}

		System.out.println(sum);
	}

	public static int[] setting(int[] temp) {
		int newTp[];
		if (temp != null) {
			newTp = new int[temp.length + 1];

			for (int i = 0; i < temp.length; i++) {
				newTp[i] = temp[i];
			}

			newTp[temp.length] = 1;
		}else{
			newTp = new int[]{1};
		}
		return newTp;
	}
}
