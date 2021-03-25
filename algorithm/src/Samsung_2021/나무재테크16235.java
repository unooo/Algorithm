package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 나무재테크16235 {

	static int N, M, K, refill[][];
	static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dx[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int map[][];
	static LinkedList<Tree>[][] treeMap;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		refill = new int[N][N];
		map = new int[N][N];
		treeMap = new LinkedList[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				refill[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				treeMap[i][j]=new LinkedList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			treeMap[r][c].add(new Tree(r, c, age));
		}

		for (int i = 0; i < N; i++)
			Arrays.fill(map[i], 5);
		
		

		int step=0;
		
		while (true) {
			if(step==K)
				break;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (treeMap[i][j] == null)
						continue;

					LinkedList<Tree> treeList = treeMap[i][j];
					int len =treeList.size();
					int k=0;
					for(Tree tree:treeList){
						if (tree.age <= map[i][j]) {
							map[i][j] -= tree.age;
							tree.age++;
						} else {
							List<Tree> tpList = treeList.subList(k, treeList.size());
							int sum = tpList.stream().map(treeTp -> treeTp.age / 2).mapToInt(Integer::intValue).sum();
							map[i][j] += sum;
							tpList.clear();
							break;
						}
						k++;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (treeMap[i][j] == null)
						continue;
					LinkedList<Tree> treeList = treeMap[i][j];
					int len=treeList.size();
					for(Tree tree:treeList){
						if (tree.age % 5 == 0) {

							for (int dir = 0; dir < 8; dir++) {
								int nextI = tree.i + dy[dir];
								int nextJ = tree.j + dx[dir];
								if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N)
									continue;
								if (treeMap[nextI][nextJ] == null)
									treeMap[nextI][nextJ] = new LinkedList<>();
								treeMap[nextI][nextJ].addFirst(new Tree(nextI, nextJ, 1));

							}

						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] += refill[i][j];
				}
			}
			step++;
		}
		int count=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (treeMap[i][j] == null)
					continue;
				LinkedList<Tree> treeList = treeMap[i][j];
				count+=treeList.size();
			}
		}
		
		System.out.println(count);

	}

	static class Tree implements Comparable<Tree> {
		int i, j, age;

		public Tree(int i, int j, int age) {
			super();
			this.i = i;
			this.j = j;
			this.age = age;
		}

		@Override
		public int compareTo(Tree arg0) {
			// TODO Auto-generated method stub
			return this.age - arg0.age;
		}

	}
}
