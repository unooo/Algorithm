package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baek_16235 {

	static int N, M, K;
	static Space board[][];
	static int[][] 양분보드;
	static int dy[] = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int dx[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		양분보드 = new int[N][N];
		board = new Space[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				양분보드[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = new Space(new LinkedList<Tree>(), 5);
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			board[r][c].treeList.add(new Tree(age, true));
		}

		int tum = 0;
		while (true) {
			// 봄
			tum++;
			LinkedList<Tree> fiveList = new LinkedList<Tree>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					Space space = board[i][j];
					if (space.treeList.size() == 0)
						continue;
					// Collections.sort(space.treeList);
					int len=space.treeList.size();
					Loop: for (int k = 0; k < len; k++) {
						Tree tree = space.treeList.get(k);
						if (space.양분 >= tree.age) {
							space.양분 -= tree.age;
							tree.age++;
							if (tree.age % 5 == 0) {
								tree.r = i;
								tree.c = j;
								fiveList.add(tree);
							}
						} else {
							LinkedList<Tree> tempList = new LinkedList<>(
									space.treeList.subList(k, space.treeList.size()));
							space.treeList.subList(k, space.treeList.size()).clear();
							for (Tree tpTree : tempList) {
								space.양분 += tpTree.age / 2;
							}

							break Loop;
						}
					}
				}
			}

			for (Tree tree : fiveList) {
				for (int k = 0; k < 8; k++) {
					int nextI = tree.r + dy[k];
					int nextJ = tree.c + dx[k];
					if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N)
						continue;
					board[nextI][nextJ].treeList.addFirst(new Tree(1, true));
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					board[i][j].양분 += 양분보드[i][j];
				}
			}
			if (tum == K)
				break;
		}

		int tempSum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Space space = board[i][j];
				if (space.treeList.size() == 0)
					continue;
				for (Tree tree : space.treeList) {
					// if(tree.isAlive)
					tempSum++;
				}

			}
		}
		System.out.println(tempSum);

	}

	static class Space {
		LinkedList<Tree> treeList;
		int 양분;

		public Space(LinkedList<Tree> treeList, int 양분) {
			super();
			this.treeList = treeList;
			this.양분 = 양분;
		}

	}

	static class Tree implements Comparable<Tree> {
		int age;
		boolean isAlive;
		int r, c;

		public Tree(int age, boolean isAlive) {
			super();
			this.age = age;
			this.isAlive = isAlive;
		}

		public Tree(int age, boolean isAlive, int r, int c) {
			super();
			this.age = age;
			this.isAlive = isAlive;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Tree o) {
			// TODO Auto-generated method stub
			return this.age - o.age;
		}

	}
}
