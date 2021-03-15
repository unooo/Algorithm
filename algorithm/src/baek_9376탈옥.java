import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek_9376Å»¿Á {
	static int dy[] = new int[] { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };
	static int[][] map;
	static int K, N, M;
	static final int Wall = 1;
	static final int Door = 2;
	static final int Prisoner = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		Node prisonerAry[] = new Node[2];
		int pIdx = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				switch (str.charAt(j)) {
				case '*':
					map[i][j] = Wall;
					break;
				case '#':
					map[i][j] = Door;
					break;
				case '$':
					map[i][j] = Prisoner;
					prisonerAry[pIdx] = new Node(i, j,new LinkedList<>());
					pIdx++;
					break;
				}

			}
		}
		int ret[] = new int[K];
		Arrays.fill(ret, Integer.MAX_VALUE);
		for (int k = 0; k < K; k++) {
			int[][] visit = new int[N][M];
			Queue<Node> que = new LinkedList<>();
			visit[prisonerAry[0].i][prisonerAry[0].j] = 1;
			que.add(new Node(prisonerAry[0].i,prisonerAry[0].j,new LinkedList<>()));
			while (!que.isEmpty()) {
				Node node = que.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nextI = node.i + dy[dir];
					int nextJ = node.j + dx[dir];

					if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) {
						System.out.println();
						continue;
						
						// todo
				/*
						for(Node tp : node.doorList){
							map[tp.i][tp.j]=0;
						}
						
						
						{
							boolean[][] visit2 = new boolean[N][M];
							Queue<Node> que2 = new LinkedList<>();
							visit2[prisonerAry[1].i][prisonerAry[1].j] = true;
							que2.add(new Node(prisonerAry[1].i,prisonerAry[1].j,new LinkedList<>()));
							while (!que2.isEmpty()) {
								Node node2 = que2.poll();
								for (int dir2 = 0; dir2 < 4; dir2++) {
									int nextI2 = node2.i + dy[dir2];
									int nextJ2 = node2.j + dx[dir2];

									if (nextI2 < 0 || nextJ2 < 0 || nextI2 >= N || nextJ2 >= M) {
										// todo
										ret[k]=Math.min(ret[k], node.doorList.size()+node2.doorList.size());
										
										continue;
									}
									if (visit2[nextI2][nextJ2] == true)
										continue;
									if (map[nextI2][nextJ2] == Wall)
										continue;
									if (map[nextI2][nextJ2] == Door) {
										node2.doorList.add(new Node(nextI2, nextJ2, null));
									}
									visit2[nextI2][nextJ2] = true;
									que2.add(new Node(nextI2, nextJ2, new LinkedList<>(node2.doorList)));

								}

							}
						}
						
						for(Node tp : node.doorList){
							map[tp.i][tp.j]=Wall;
						}
						continue;
						*/
					
					}
					if (map[nextI][nextJ] == Wall)
						continue;
					
					if (visit[nextI][nextJ] == 0||(visit[nextI][nextJ] != 0&&(visit[nextI][nextJ]>visit[node.i][node.j]+1))){
						if(map[nextI][nextJ] == Door){
							node.doorList.add(new Node(nextI, nextJ, null));
							visit[nextI][nextJ] = visit[node.i][node.j]+1;
							que.add(new Node(nextI, nextJ, new LinkedList<>(node.doorList)));
						}else{
							visit[nextI][nextJ]=visit[node.i][node.j];
							que.add(new Node(nextI, nextJ, new LinkedList<>(node.doorList)));
						}
					}
					
					

				}

			}
		}
		System.out.println();
	}

	static class Node {
		int i, j;
		LinkedList<Node> doorList;

		public Node(int i, int j, LinkedList<Node> doorList) {
			super();
			this.i = i;
			this.j = j;
			this.doorList = doorList;
		}

	}
}
