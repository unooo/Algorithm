package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 청소년상어19236 {

	static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dx[] = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static int ret = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		 Node[][] map = new Node[4][4];
		 Node[] fishAry = new Node[16];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int idx = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				Node fish = new Node(i, j, idx, dir, false);
				map[i][j] = fish;
				fishAry[idx - 1] = fish;
			}
		}

		Node eated = map[0][0];
		fishAry[eated.idx - 1] = null;
		Node shark = new Node(0, 0, -1, map[0][0].dir, true);
		map[0][0] = shark;
		dfs(map, fishAry, eated.idx);
		System.out.println(ret);
	}

	public static void dfs(Node[][] fishMap, Node[] fishAry, int sum) {
		moveFish(fishMap, fishAry);
		
		Node shark=null;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (fishMap[i][j] == null)
					continue;
				if (fishMap[i][j].isShark)
					shark = fishMap[i][j];
			}
		}


		for (int i = 1; i <= 3; i++) {
			Node[][] newMap =new Node[4][4];
			Node[] newAry = new Node[16];
			copyMapAndAry(fishMap, newMap, newAry);
			int nextI = shark.i + dy[shark.dir] * i;
			int nextJ = shark.j + dx[shark.dir] * i;
			if (nextI < 0 || nextJ < 0 || nextI >= 4 || nextJ >= 4 || newMap[nextI][nextJ] == null) {
				ret = Math.max(ret, sum);
				continue;
			}
			Node target = newMap[nextI][nextJ];
			Node newShark = new Node(nextI, nextJ, -1, target.dir, true);
			newAry[target.idx - 1] = null;
			newMap[shark.i][shark.j] = null;
			newMap[nextI][nextJ] = newShark;
			
			dfs(newMap, newAry, sum + target.idx);

		}
	}

	public static void moveFish(Node[][] fishMap, Node[] fishAry) {
		for (int i = 0; i < fishAry.length; i++) {
			Node fish = fishAry[i];
			if (fish == null)
				continue;

			for (int dir = 0; dir < 8; dir++) {
				int nextI = fish.i + dy[(fish.dir + dir) % 8];
				int nextJ = fish.j + dx[(fish.dir + dir) % 8];
				if (nextI < 0 || nextJ < 0 || nextI >= 4 || nextJ >= 4) {
					continue;
				}
				if (fishMap[nextI][nextJ] != null && fishMap[nextI][nextJ].isShark)
					continue;

				
				Node target = fishMap[nextI][nextJ];
				fish.dir = (fish.dir + dir) % 8;
				if (target != null) {
					target.i = fish.i;
					target.j = fish.j;
					fishMap[fish.i][fish.j] = target;
					fish.i = nextI;
					fish.j = nextJ;
					fishMap[nextI][nextJ] = fish;
				} else {
					if(fishMap[fish.i][fish.j].isShark)
						System.out.println();
					fishMap[fish.i][fish.j]=null;
					fish.i = nextI;
					fish.j = nextJ;
					fishMap[nextI][nextJ] = fish;
				}

				break
				;

			}
		}
	}

	public static void copyMapAndAry(Node[][] orgMap, Node[][]newMap, Node[] newAry) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Node node = orgMap[i][j];
				if (node == null||node.isShark)
					continue;
				newMap[i][j] = new Node(i, j, node.idx, node.dir, node.isShark);
				newAry[node.idx-1]=newMap[i][j];
			}
		}
		
	}

	static class Node {
		int i, j, idx, dir;
		boolean isShark;

		public Node(int i, int j, int idx, int dir, boolean isShark) {
			super();
			this.i = i;
			this.j = j;
			this.idx = idx;
			this.dir = dir;
			this.isShark = isShark;
		}

	}
}
