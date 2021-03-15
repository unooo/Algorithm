package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
//Ball 얕은복사로 인한 에러 주의
public class 구슬탈출13460 {

	static int N, M;
	static int[][] map;
	static int[] dy = new int[] { -1, 0, 1, 0 };
	static int[] dx = new int[] { 0, 1, 0, -1 };
	static final int Wall = 1;
	static final int Blue = 2;
	static final int Red = 3;
	static final int Exit = 4;
	static Ball[] orgBallAry = new Ball[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		int ballIdx = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				switch (str.charAt(j)) {
				case '#':
					map[i][j] = Wall;
					break;
				case 'B':
					orgBallAry[ballIdx] = new Ball(i, j, false);
					ballIdx++;
					map[i][j] = Blue;
					break;
				case 'R':
					orgBallAry[ballIdx] = new Ball(i, j, true);
					ballIdx++;
					map[i][j] = Red;
					break;
				case 'O':
					map[i][j] = Exit;
					break;

				}
			}
		}
		dfs(0);
		if(ret==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ret+1);
	}

	// 중복순열
	static int path[] = new int[10];

	public static void dfs(int next) {

		if (next >= 10) {
			// todo
			/*if(path[0]==3&&path[1]==2&&path[2]==1&&path[3]==2)
				System.out.println();*/
			solve();
			return;
		}
		for (int i = 0; i < 4; i++) {
			path[next] = i;
			dfs(next + 1);
		}
	}

	public static void solve() {
		Ball balAry[] = new Ball[] { new Ball(orgBallAry[0].i,orgBallAry[0].j,orgBallAry[0].isRed), new Ball(orgBallAry[1].i,orgBallAry[1].j,orgBallAry[1].isRed)  };
		for (int i = 0; i < path.length; i++) {
			int dir = path[i];
			Comparator comparator = null;
			switch (dir) {
			case 0: // 위로이동
				comparator = new Comparator<Ball>() {
					@Override
					public int compare(Ball o1, Ball o2) {
						// TODO Auto-generated method stub
						return o1.i - o2.i;
					}
				};
				break;
			case 1: // 우로이동
				comparator = new Comparator<Ball>() {
					@Override
					public int compare(Ball o1, Ball o2) {
						// TODO Auto-generated method stub
						return o2.j - o1.j;
					}
				};
				break;
			case 2:
				comparator = new Comparator<Ball>() {
					@Override
					public int compare(Ball o1, Ball o2) {
						// TODO Auto-generated method stub
						return o2.i - o1.i;
					}
				};
				break;
			case 3:
				comparator = new Comparator<Ball>() {
					@Override
					public int compare(Ball o1, Ball o2) {
						// TODO Auto-generated method stub
						return o1.j - o2.j;
					}
				};
				break;
			}
			Arrays.sort(balAry, comparator);
			boolean redOut = false;
			for (int tun = 0; tun < 2; tun++) {
				Ball ball = balAry[tun];
				Ball otherBall = balAry[(tun + 1) % 2];
				int nextI = ball.i;
				int nextJ = ball.j;
				while (true) {
					nextI += dy[dir];
					nextJ += dx[dir];

					if (map[nextI][nextJ] == Exit) {
						if (ball.isRed)
							redOut = true;
						else
							return;
						break;
					}
					if (map[nextI][nextJ] == Wall || (nextI == otherBall.i && nextJ == otherBall.j)) {
						nextI -= dy[dir];
						nextJ -= dx[dir];
						break;
					}
				}
				ball.i=nextI;
				ball.j=nextJ;
			}
			if (redOut) {
				ret = Math.min(ret, i);
			}
		}
	}

	static int ret = Integer.MAX_VALUE;

	static class Ball {
		int i, j;
		boolean isRed;

		public Ball(int i, int j, boolean isRed) {
			super();
			this.i = i;
			this.j = j;
			this.isRed = isRed;
		}

	}
}
