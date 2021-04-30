package LastCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기1 {
	static int N, M, R, map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int piv = Math.min(N, M);
		for (int r = 0; r < R; r++)
			for (int t = 0; t < piv / 2; t++) {
				int temp = map[t][t];

				for (int j = 0 + t; j < M - 1 - t; j++) {
					map[0 + t][j] = map[0 + t][j + 1];
				}
				for (int i = 0 + t; i < N - 1 - t; i++) {
					map[i][M - 1 - t] = map[i + 1][M - 1 - t];
				}
				for (int j = M - 1 - t; j > 0 + t; j--) {
					map[N - 1 - t][j] = map[N - 1 - t][j - 1];
				}
				for (int i = N - 1 - t; i > 0 + t; i--) {
					map[i][0 + t] = map[i - 1][0 + t];
				}
				map[t+1][t] = temp;
			}

		for (int i = 0; i < N; i++) {
			
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
