import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek_14502 {
	static int[][] map;
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		path = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,N,M);
		System.out.println(max);
	}

	static int path[];
	static int max = Integer.MIN_VALUE;

	public static void dfs(int next, int N, int M) {
		if (next >= 3) {
			// todo
			int[][] newMap = new int[N][M];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					newMap[i][j] = map[i][j];

			for (int i = 0; i < next; i++) {
				int r = path[i] / M;
				int c = path[i] % M;
				if (newMap[r][c] != 0)
					return;
				newMap[r][c] = 1;
			}
			Queue<Node> que = new LinkedList<Node>();
			int[][] visit = new int[N][M];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++) {
					if (newMap[i][j] == 2) {
						visit[i][j] = 1;
						que.add(new Node(i, j));
					}
				}

			while (!que.isEmpty()) {
				Node node = que.poll();
				for (int i = 0; i < 4; i++) {
					int nextY = node.i + dy[i];
					int nextX = node.j + dx[i];
					if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= M)
						continue;
					if (visit[nextY][nextX] != 0)
						continue;
					if (newMap[nextY][nextX] == 1)
						continue;
					visit[nextY][nextX] = 1;
					que.add(new Node(nextY, nextX));
				}
			}
			int count = 0;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++) {
					if (newMap[i][j] == 0 && visit[i][j] == 0)
						count++;
				}
			
			max=Math.max(max, count);
			return;
		}

		for (int i = 0; i < N * M; i++) {
			path[next] = i;
			dfs(next + 1, N, M);
		}
	}

	static class Node {
		int i, j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}
}
