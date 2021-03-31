package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class ½ºÅ¸Æ®ÅÃ½Ã19238 {
	static int N, M, fuel;
	static int[][] map;
	static int startI, startJ;
	static Node[] alliveInfo;
	static int dy[] = { -1, 0, 0, 1 };
	static int dx[] = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		startI = Integer.parseInt(st.nextToken()) - 1;
		startJ = Integer.parseInt(st.nextToken()) - 1;
		alliveInfo = new Node[M + 2];
		int idx = 2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = idx;
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			alliveInfo[idx] = new Node(r, c);
			idx++;
		}
		Outer: for (int tun = 0; tun < M; tun++) {
			int visit[][] = new int[N][N];
			Queue<Node> queue = new LinkedList<Node>();
			visit[startI][startJ] = 1;
			queue.add(new Node(startI, startJ));
			while (!queue.isEmpty()) {

				int size = queue.size();
				LinkedList<Node> candidate = new LinkedList<>();
				for (int s = 0; s < size; s++) {
					Node node = queue.poll();

					for (int dir = 0; dir < 4; dir++) {
						int nextI = node.i + dy[dir];
						int nextJ = node.j + dx[dir];
						if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N || map[nextI][nextJ] == 1)
							continue;
						if (visit[nextI][nextJ] != 0)
							continue;
						if (map[nextI][nextJ] != 0 || (map[startI][startJ] != 0)) {
							// ½Â°´Ã£À½
							if (map[startI][startJ] != 0) {
								nextI = startI;
								nextJ = startJ;
								visit[nextI][nextJ] = 0;
							}
							candidate.add(new Node(nextI, nextJ));	
							visit[nextI][nextJ]=visit[node.i][node.j];
							// continue Outer;
						} else {
							visit[nextI][nextJ] = visit[node.i][node.j] + 1;
							queue.add(new Node(nextI, nextJ));
						}
					}
				}
				
				if(candidate.size()!=0) {
					Collections.sort(candidate, new Comparator<Node>() {
						@Override
						public int compare(Node o1, Node o2) {
							if(o1.i>o2.i)
								return 1;
							else if(o1.i<o2.i)
								return -1;
							else
								return o1.j-o2.j;
							
						}
					});
					Node node = candidate.getFirst();
					int nextI=node.i;
					int nextJ=node.j;
					int findIdx = map[nextI][nextJ];
					fuel -= visit[node.i][node.j];

					queue = new LinkedList<Node>();
					visit = new int[N][N];
					visit[nextI][nextJ] = 1;
					map[nextI][nextJ] = 0;
					queue.add(new Node(nextI, nextJ));
					while (!queue.isEmpty()) {
						node = queue.poll();
						for (int dir = 0; dir < 4; dir++) {
							nextI = node.i + dy[dir];
							nextJ = node.j + dx[dir];
							if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N || map[nextI][nextJ] == 1)
								continue;

							if (visit[nextI][nextJ] != 0)
								continue;
							if (nextI == alliveInfo[findIdx].i && nextJ == alliveInfo[findIdx].j) {

								alliveInfo[findIdx] = null;
								fuel -= visit[node.i][node.j];
								if (fuel < 0) {
									System.out.println(-1);
									return;
								}
								fuel += visit[node.i][node.j] * 2;
								startI = nextI;
								startJ = nextJ;
								continue Outer;
							} else {
								visit[nextI][nextJ] = visit[node.i][node.j] + 1;
								queue.add(new Node(nextI, nextJ));
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < alliveInfo.length; i++) {
			if (alliveInfo[i] != null) {
				System.out.println(-1);
				return;
			}
		}

		System.out.println(fuel);
		return;
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
