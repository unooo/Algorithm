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
			int ���� = Integer.parseInt(st.nextToken());
			int �ӷ� = Integer.parseInt(st.nextToken());
			int ���� = Integer.parseInt(st.nextToken());
			if (board[r][c] == null) {
				board[r][c] = new LinkedList<FireBall>();
			}
			board[r][c].add(new FireBall(����, �ӷ�, ����));
		}

		for (int tun = 0; tun < K; tun++) {
			// 1.��� ���̾�� �ڽ��� ���� di�� �ӷ� siĭ ��ŭ �̵��Ѵ�.
			// �̵��ϴ� �߿��� ���� ĭ�� ���� ���� ���̾�� ���� ���� �ִ�.
			LinkedList<FireBall>[][] newBoard = new LinkedList[N][N];
			;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == null || board[i][j].size() == 0)
						continue;
					for (int k = 0; k < board[i][j].size(); k++) {
						FireBall fireBall = board[i][j].get(k);
						int nextI = i + dy[fireBall.����] * fireBall.�ӷ�;
						int nextJ = j + dx[fireBall.����] * fireBall.�ӷ�;

						nextI = nextI % N < 0 ? N + nextI % N : nextI % N;
						nextJ = nextJ % N < 0 ? N + nextJ % N : nextJ % N;

						if (newBoard[nextI][nextJ] == null)
							newBoard[nextI][nextJ] = new LinkedList<>();
						newBoard[nextI][nextJ].add(fireBall);
					}

				}
			}
			// ���� ĭ�� �ִ� ���̾�� ��� �ϳ��� ��������.
			board = new LinkedList[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (newBoard[i][j] == null||newBoard[i][j].size()==0)
						continue;
					int ������ = 0, �ӷ��� = 0, �Ѱ��� = newBoard[i][j].size();
					if (�Ѱ��� < 2) {
						if (board[i][j] == null)
							board[i][j] = new LinkedList<>();
						board[i][j].add(newBoard[i][j].getFirst());
					} else {
						
						boolean isEven = false, isOdd = false;
						for (int k = 0; k < newBoard[i][j].size(); k++) {
							FireBall fireBall = newBoard[i][j].get(k);
							������ += fireBall.����;
							�ӷ��� += fireBall.�ӷ�;
							if (fireBall.���� % 2 == 0)
								isEven = true;
							else
								isOdd = true;
						}
						if (������/5 == 0)
							continue;
						int new���� = ������ / 5;
						int new�ӷ� = �ӷ��� / �Ѱ���;
						int newDir = -1;
						if (isEven == isOdd) {
							newDir = 1;
						} else {
							newDir = 0;
						}
						for (int p = 0; p < 4; p++) {
							
							if (board[i][j] == null)
								board[i][j] = new LinkedList<>();
							board[i][j].add(new FireBall(new����, new�ӷ�, newDir));
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
					total += board[i][j].get(k).����;
			}
		}
		System.out.println(total);

	}

	static class FireBall {
		int ����, �ӷ�, ����;

		public FireBall(int ����, int �ӷ�, int ����) {
			super();
			this.���� = ����;
			this.�ӷ� = �ӷ�;
			this.���� = ����;
		}

	}
}
