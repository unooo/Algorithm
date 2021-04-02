package Samsung_2021;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사상어와파이어스톰20058 {

	static int N, Q, map[][], order[], newN;
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		newN = (int) Math.pow(2, N);
		map = new int[newN][newN];
		for (int i = 0; i < newN; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < newN; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order = new int[Q];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < Q; i++) {
			spin(order[i]);
		}
		
		int sum=0;
		for(int i = 0 ; i < newN ; i++) {
			for(int j = 0 ; j < newN ; j++)
				sum+=map[i][j];
		}
		System.out.println(sum);

		Queue<Node> queue = new LinkedList<Node>();
		int visit[][] = new int[newN][newN];
		int idx = 0;
		for (int i = 0; i < newN; i++) {
			for (int j = 0; j < newN; j++) {
				if (map[i][j]!=0&&visit[i][j] == 0) {
					idx++;
					visit[i][j] = idx;
					queue.add(new Node(i, j));
					int size = 0;
					while (!queue.isEmpty()) {
						Node node = queue.poll();
						size++;
						for (int dir = 0; dir < 4; dir++) {
							int nextI = node.i + dy[dir];
							int nextJ = node.j + dx[dir];
							if (nextI < 0 || nextJ < 0 || nextI >= newN || nextJ >= newN || visit[nextI][nextJ] != 0
									|| map[nextI][nextJ] == 0)
								continue;
							visit[nextI][nextJ] = idx;
							queue.add(new Node(nextI, nextJ));
						}
					}

					ret = Math.max(ret, size);
				}

			}
		}

		System.out.println(ret);

	}

	static int ret = Integer.MIN_VALUE;

	public static void spin(int L) {
		int newMap[][] = new int[newN][newN];

		for (int top = 0; top < newN; top += Math.pow(2, L)) {
			for (int left = 0; left < newN; left += Math.pow(2, L)) {
				int idx = 0;
				for (int j = 0 + left; j < left + Math.pow(2, L); j++) {
					for (int i = (int) (Math.pow(2, L) + top - 1); i >= top; i--) {

						newMap[(int) (idx / Math.pow(2, L)) + top][(int) (idx % Math.pow(2, L)) + left] = map[i][j];
						idx++;
					}
				}
			}
		}
		for(int i = 0 ; i< newN ; i++)
			for(int j = 0 ; j < newN; j++)
				map[i][j]=newMap[i][j];
		
	
		for (int i = 0; i < newN; i++) {
			for (int j = 0; j < newN; j++) {
				int cnt = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nextI = i + dy[dir];
					int nextJ = j + dx[dir];
					if (nextI < 0 || nextJ < 0 || nextI >= newN || nextJ >= newN)
						continue;
					if (newMap[nextI][nextJ] > 0)
						cnt++;
				}
				if (map[i][j]>0&&cnt < 3) {
					map[i][j]--;
				}
			}
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
