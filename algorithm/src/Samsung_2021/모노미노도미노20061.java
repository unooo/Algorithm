package Samsung_2021;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모노미노도미노20061 {

	static int N;
	static int map[][];
	static int score=0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[10][10];

		for (int order = 0; order < N; order++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			Node[] block = null;
			switch (t) {
			case 1:
				block = new Node[1];
				block[0] = new Node(x, y);
				break;
			case 2:
				block = new Node[2];
				block[0] = new Node(x, y);
				block[1] = new Node(x, y + 1);
				;
				break;
			case 3:
				block = new Node[2];
				block[0] = new Node(x, y);
				block[1] = new Node(x + 1, y);
				;
				break;
			}

			for (int tun = 0; tun < 2; tun++) {
				// 이동 가능한지 확인 후
				int startI,endI;
				if(tun==0) {
					startI=0;
					endI=4;
				}else {
					startI=6;
					endI=10;
				}
				int minJ = Integer.MAX_VALUE;
				Outer: for (int blockNum = 0; blockNum < block.length; blockNum++) {
					int nextJ = block[blockNum].j;
					while (true) {
						nextJ += 1;
						if (nextJ >= 4 && (nextJ >= 10 || map[block[blockNum].i][nextJ] == 1)) {
							minJ = Math.min(minJ, nextJ - 1);
							continue Outer;
						}
					}

				}
				// 이동처리
				for (int blockNum = 0; blockNum < block.length; blockNum++) {
					Node node = block[blockNum];
					if (map[node.i][minJ] == 0)
						map[node.i][minJ] = 1;
					else
						map[node.i][minJ - 1] = 1;
					map[node.i][node.j] = 0;
				}

				for (int j = 9; j >= 6; j--) {
					int cnt = 0;
					for (int i = startI; i < endI; i++) {
						if (map[i][j] == 1)
							cnt++;
					}
					if (cnt == 4) {
						score++;
						for (int r = startI; r < endI; r++) {
							for (int c = j; c >= 4; c--) {
								map[r][c] = map[r][c - 1];
							}
						}

						j++;
					}
				}
				Outer: for (int j = 5; j >= 4; j--) {

					for (int i = startI; i < endI; i++) {
						if (map[i][j] == 1) {

							for (int r = startI; r < endI; r++) {
								for (int c = 9; c >= 4; c--) {
									map[r][c] = map[r][c - 1];
								}
							}
							j++;
							continue Outer;
						}
					}

				}
				spinLeft();
				for(int blockNum = 0; blockNum < block.length; blockNum++) {
					Node node = block[blockNum];
					int nextI=10-node.j-1;
					int nextJ=node.i;
					
					node.i=nextI;
					node.j=nextJ;
				}
				
			}
			spinLeft();
			spinLeft();
			
		}
		int cnt=0;
		for(int i = 0 ; i < 10 ; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				if(map[i][j]==1)
					cnt++;
			}
		}
		System.out.println(score);
		System.out.println(cnt);

	}

	public static void spinLeft() {
		int[][] newMap = new int[10][10];
		int idx = 0;
		for (int j = 9; j >= 0; j--) {
			for (int i = 0; i < 10; i++) {
				newMap[idx / 10][idx % 10] = map[i][j];
				idx++;
			}
		}
		map = newMap;
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
