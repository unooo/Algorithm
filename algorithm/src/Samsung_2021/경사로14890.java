package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class °æ»ç·Î14890 {

	public static int N, L;
	public static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int ret = 0;
		for (int tun = 0; tun < 2; tun++) {
			int visit[][] = new int[N][N];
			Row:
			for (int i = 0; i < N; i++) {
				 for (int j = 1; j < N; j++) {
					 if(map[i][j] == map[i][j - 1])
						 continue;
					 else if (Math.abs(map[i][j] - map[i][j - 1]) > 1) {
						continue Row;
					}					 
					if (map[i][j] > map[i][j - 1]) {
						for (int k = j - 1; k > j - 1 - L; k--) {
							if (k < 0 || k >= N)
								continue Row;
							if (visit[i][k] == 1)
								continue Row;
							visit[i][k] = 1;
						}
					} else {
						for (int k = j; k < j + L; k++) {
							if (k < 0 || k >= N)
								continue Row;
							if (visit[i][k] == 1)
								continue Row;
							visit[i][k] = 1;
						}
					}
				}
				ret++;
			}

			spin();
		}
		System.out.println(ret);

	}

	public static void spin() {
		int newMap[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = map[N - j - 1][i];
			}
		}
		map=newMap;
	}
}
