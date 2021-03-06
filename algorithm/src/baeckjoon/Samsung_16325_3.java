package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Samsung_16325_3 {
	public static int N;
	public static int M;
	public static int K;

	public static int A[][];
	public static int originalA[][];

	public static int[] dy = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
	public static int[] dx = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };
	public static LinkedList<Tree> treeList = new LinkedList<Tree>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		originalA = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {

				A[i][j] = 5;
				originalA[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int  x= Integer.parseInt(st.nextToken()) - 1;
			int energy = Integer.parseInt(st.nextToken()) ;
			treeList.add(new Tree(y, x, energy, 1));
		}
		
		int year = 0;
		while (year < K) {
			// º½
			Collections.sort(treeList);
			for (Tree tree : treeList) {
				if ( A[tree.y][tree.x] >= tree.year) {
					A[tree.y][tree.x] -= tree.year;
					tree.year++;
				} else {
					tree.life = 0;
				}
			}
			// ¿©¸§
			/*
			for (Tree tree : treeList) {
				if (tree.life == 0) {
					A[tree.y][tree.x] += tree.year / 2;
				}
			}*/
			for(Iterator<Tree> itt = treeList.iterator(); itt.hasNext();){
				Tree t = itt.next();
				if(t.life==0){
					A[t.y][t.x]+=t.year/2;
					itt.remove();
				}
			}
			// °¡À»
			LinkedList<Tree> treeListTemp = new LinkedList<Tree>();
			for (Tree tree : treeList) {
				if (tree.year % 5 == 0 ) {
					for (int i = 0; i < 8; i++) {
						int nowY = tree.y;
						int nowX = tree.x;
						int newY = nowY + dy[i];
						int newX = nowX + dx[i];
						
						if (newY >= N || newX >= N || newY < 0 || newX < 0) {
							continue;
						}
						treeListTemp.add(new Tree(newY,newX,1,1));
					}
				}
			}
			
			for (Tree tree : treeListTemp) {
				treeList.add(tree);
			}
			/*
			treeList.addAll(0,treeListTemp);
			*/
			for (int i = 0; i < N; i++) {

				for (int j = 0; j < N; j++) {
					A[i][j] += originalA[i][j];
				}
			}
			year++;
		}
		
		System.out.println(treeList.size());
		
	}
	
	static class Tree implements Comparable<Tree> {
		int y;
		int x;
		int year;
		int life;

		Tree(int y, int x, int year, int life) {
			this.y = y;
			this.x = x;
			this.year = year;
			this.life = life;
		}

		@Override
		public int compareTo(Tree o) {
			// TODO Auto-generated method stub
			if(this.year>o.year){
				return 1;
			}else if(this.year<o.year){
				return -1;
			}else{
				return 0;
			}
		}

	}

}

