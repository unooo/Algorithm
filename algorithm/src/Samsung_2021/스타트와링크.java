package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스타트와링크 {

	static int N;
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		path = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		dfs(0);
		System.out.println(ret);
	}

	// 중복순열
	public static int path[];
	static int ret = Integer.MAX_VALUE;

	public static void dfs(int next) {

		if (next >= N) {
			// todo;
			int sum1 = 0;
			int sum2 = 0;
			for (int i = 0; i < N; i++) {
				if (path[i] == 0)
					sum1++;
				else
					sum2++;
			}
			if (sum1!=sum2)
				return;
			int start = 0;
			int link = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (path[i] == path[j]) {

						if (path[i] == 0) {
							start += map[i][j];
						} else {
							link += map[i][j];
						}
					}
				}
			}
			ret = Math.min(ret, Math.abs(start - link));
			return;
		}

		path[next] = 1;
		dfs(next + 1);
		path[next] = 0;
		dfs(next + 1);
	}

}
