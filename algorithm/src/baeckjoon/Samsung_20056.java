package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Samsung_20056 {

	static int N, M, K;
	static LinkedList<FireBall>[][] board;
	static int[] dy = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new LinkedList[N][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int 질량 = Integer.parseInt(st.nextToken());
			int 속력 = Integer.parseInt(st.nextToken());
			int 방향 = Integer.parseInt(st.nextToken());
			if (board[r][c] == null) {
				board[r][c] = new LinkedList<FireBall>();
			}
			board[r][c].add(new FireBall(질량, 속력, 방향));
		}

		for (int tun = 0; tun < K; tun++) {
			// 1.모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
			// 이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
			LinkedList<FireBall>[][] newBoard = new LinkedList[N][N];
			;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == null || board[i][j].size() == 0)
						continue;
					for (int k = 0; k < board[i][j].size(); k++) {
						FireBall fireBall = board[i][j].get(k);
						int nextI = i + dy[fireBall.방향] * fireBall.속력;
						int nextJ = j + dx[fireBall.방향] * fireBall.속력;

						nextI = nextI % N < 0 ? N + nextI % N : nextI % N;
						nextJ = nextJ % N < 0 ? N + nextJ % N : nextJ % N;

						if (newBoard[nextI][nextJ] == null)
							newBoard[nextI][nextJ] = new LinkedList<>();
						newBoard[nextI][nextJ].add(fireBall);
					}

				}
			}
			// 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
			board = new LinkedList[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (newBoard[i][j] == null||newBoard[i][j].size()==0)
						continue;
					int 질량합 = 0, 속력합 = 0, 총개수 = newBoard[i][j].size();
					if (총개수 < 2) {
						if (board[i][j] == null)
							board[i][j] = new LinkedList<>();
						board[i][j].add(newBoard[i][j].getFirst());
					} else {
						
						boolean isEven = false, isOdd = false;
						for (int k = 0; k < newBoard[i][j].size(); k++) {
							FireBall fireBall = newBoard[i][j].get(k);
							질량합 += fireBall.질량;
							속력합 += fireBall.속력;
							if (fireBall.방향 % 2 == 0)
								isEven = true;
							else
								isOdd = true;
						}
						if (질량합/5 == 0)
							continue;
						int new질량 = 질량합 / 5;
						int new속력 = 속력합 / 총개수;
						int newDir = -1;
						if (isEven == isOdd) {
							newDir = 1;
						} else {
							newDir = 0;
						}
						for (int p = 0; p < 4; p++) {
							
							if (board[i][j] == null)
								board[i][j] = new LinkedList<>();
							board[i][j].add(new FireBall(new질량, new속력, newDir));
							newDir +=  2;
						}
					}

				}
			}
		}
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == null)
					continue;
				for (int k = 0; k < board[i][j].size(); k++)
					total += board[i][j].get(k).질량;
			}
		}
		System.out.println(total);

	}

	static class FireBall {
		int 질량, 속력, 방향;

		public FireBall(int 질량, int 속력, int 방향) {
			super();
			this.질량 = 질량;
			this.속력 = 속력;
			this.방향 = 방향;
		}

	}
}
