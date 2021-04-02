package Samsung_2021;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마법사상어와토네이토 {

	static int N, map[][];
	static int dy[] = { 0, 1, 0, -1 };
	static int dx[] = { -1, 0, 1, 0 };
	static int windDy[][] = { { 0, -1, -2, 1, 2, -1, 1, -1, 1 }, { 2, 0, 0, 0, 0, 1, 1, -1, -1 },
			{ 0, -1, -2, 1, 2, -1, 1, -1, 1 }, { -2, 0, 0, 0, 0, -1, -1, 1, 1 } };
	static int windDx[][] = { { -2, 0, 0, 0, 0, -1, -1, 1, 1 }, { 0, 1, 2, -1, -2, -1, 1, -1, 1 },
			{ 2, 0, 0, 0, 0, 1, 1, -1, -1 }, { 0, 1, 2, -1, -2, 1, -1, 1, -1 } };

	static int percent[] = { 5, 7, 2, 7, 2, 10, 10, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int nextI = N / 2;
		int nextJ = N / 2;

		int dir = 0;
		int distance = 1;
		int sum = 0;
		while (true) {

			for (int tun = 0; tun < 2; tun++) {
				for (int move = 0; move < distance; move++) {
					nextI += dy[dir % 4];
					nextJ += dx[dir % 4];

					if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) {
						System.out.println(sum);
						return;
					}
					int totSend = map[nextI][nextJ];
					int totAwaySend = 0;
					for (int sendDir = 0; sendDir < 9; sendDir++) {
						int sendI = nextI + windDy[dir % 4][sendDir];
						int sendJ = nextJ + windDx[dir % 4][sendDir];
						int awaySend = (int) (totSend * (percent[sendDir] / 100.0));
						totAwaySend += awaySend;
						if (sendI < 0 || sendJ < 0 || sendI >= N || sendJ >= N) {
							sum += awaySend;
							continue;
						}
						map[sendI][sendJ]+=awaySend;
						
						
					}
					int sendI = nextI + dy[dir % 4];
					int sendJ = nextJ + dx[dir % 4];
					int awaySend = totSend - totAwaySend;
					if (sendI < 0 || sendJ < 0 || sendI >= N || sendJ >= N) {					
						sum += awaySend;
					} else {
						map[sendI][sendJ]+=awaySend;
					}
					map[nextI][nextJ]=0;
				}

				dir++;
			}

			distance++;
		}

	}

}
