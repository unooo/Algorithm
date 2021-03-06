package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Samsung_16235 {

	public static int N;
	public static int M;
	public static int K;

	public static int A[][];
	public static int originalA[][];
	public static ArrayList<Integer>[] tree;
	public static int []dy=new int[]{-1,-1,-1,0,0,1,1,1};
	public static int []dx=new int[]{-1,0,1,-1,1,-1,0,1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N + 1][N + 1];
		originalA = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {

				A[i][j] =5;
				originalA[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		tree = new ArrayList[(N + 1) * (N + 1)];

		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			tree[y * (N+1) + x] = new ArrayList<Integer>();
			tree[y * (N+1) + x].add(Integer.parseInt(st.nextToken()));
		}

		int year = 0;
		while (year < K) {

			for (int i = 1; i < tree.length; i++) {
				if (tree[i] != null) {
					Collections.sort(tree[i]);
					int dead = 0;
					for (int j = 0; j < tree[i].size(); j++) {

						if (A[i / (N+1)][i % (N+1)] >= tree[i].get(j)) {
							A[i / (N+1)][i % (N+1)] -= tree[i].get(j);
							tree[i].set(j, tree[i].get(j)+1);
						} else {
							dead += (tree[i].remove(j)) / 2;
						}

					}
					A[i / (N+1)][i % (N+1)] += dead;

				}
			}
			//가을
			for (int i = 0; i < tree.length; i++) {
				if (tree[i] != null) {
					for (int j = 0; j < tree[i].size(); j++) {
						if(tree[i].get(j)%5==0){
							for(int k = 0 ; k < 8 ; k ++){
								int nowY=i / (N+1);
								int nowX=i % (N+1);
								int newY=nowY+dy[k];
								int newX=nowX+dx[k];
								if(newY>=N+1||newX>=N+1||newY<1||newX<1){
									continue;
								}
								if(tree[newY*(N+1)+newX]==null)
									tree[newY*(N+1)+newX]= new ArrayList<Integer>();
								tree[newY*(N+1)+newX].add(1);
							}
						}
					}
				}
			}
			//겨울
			
			for(int i = 1 ; i < N+1 ; i ++){
				
				for(int j =1 ; j < N+1 ; j ++){
					A[i][j]+=originalA[i][j];
				}
			}
			year++;

		}
		
		int sum=0;
		for (int i = 0; i < tree.length; i++) {
			if(tree[i]!=null)
			sum+=tree[i].size();
		}
		
		System.out.println(sum);
		

	}

}
