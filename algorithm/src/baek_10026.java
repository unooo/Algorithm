import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek_10026 {
	static int dy[] = new int[] { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };
	static int[][] map;
	static int N;
	static final int Red = 1;
	static final int Green = 2;
	static final int Blue = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				switch (str.charAt(j)) {
				case 'R':
					map[i][j] = Red;
					break;
				case 'G':
					map[i][j] = Green;
					break;
				case 'B':
					map[i][j] = Blue;
					break;
				}

			}
		}
		int ret[] = new int[2];
		for (int mode = 0; mode < 2; mode++) {
			int[][] visit = new int[N][N];
			int range = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visit[i][j] != 0)
						continue;
					range++;
					Queue<Node> que = new LinkedList<>();
					visit[i][j] = range;
					que.add(new Node(i, j));
					while (!que.isEmpty()) {
						Node node = que.poll();
						for (int dir = 0; dir < 4; dir++) {
							int nextI = node.i + dy[dir];
							int nextJ = node.j + dx[dir];

							if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N)
								continue;
							if(visit[nextI][nextJ]!=0)
								continue;
							
							if(mode==0){
								if(map[nextI][nextJ]!=map[node.i][node.j])
									continue;
								
							}else{
								if(map[nextI][nextJ]!=map[node.i][node.j]&&(map[nextI][nextJ]==Blue||map[node.i][node.j]==Blue)){
									continue;
								}								
							}
							
							visit[nextI][nextJ]=range;
							que.add(new Node(nextI,nextJ));

						}
					}
				}
			}
			ret[mode]=range;
		}
		System.out.println(ret[0]+" "+ret[1]);
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
