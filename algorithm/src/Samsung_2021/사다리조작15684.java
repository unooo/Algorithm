package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사다리조작15684 {

	static int N, M, H;
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			map[r][c] = 1;
		}
		for(int i = 0 ; i <=3 ; i++){
			path=new int[i];
			dfs(0,(N-1)*H,i,0);
			if(ret!=Integer.MAX_VALUE)
				break;
		}
		if(ret==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ret);
		
	}

	public static boolean solve(int r) {
		int newMap[][] = copyMap();
		for (int i = 0; i < r; i++) {
			int num = path[i];
			newMap[num / (N-1)][num % (N-1)] = 1;
		}

		for(int j = 0 ; j < N ; j++){
			int nextI = 0;
			int nextJ =j;
			while(true){
				
				if(newMap[nextI][nextJ]==0){
					if(nextJ>=1&&newMap[nextI][nextJ-1]==1){
						nextJ--;
					}
				}else if(newMap[nextI][nextJ]==1){
					nextJ++;
				}
				nextI++;
				
				if(nextI>=H){
					if(j!=nextJ)
						return false;
					break;
				}
			}
		}
		return true;

	}

	public static int[][] copyMap() {
		int newMap[][] = new int[H][N];
		for (int i = 0; i < H; i++)
			for (int j = 0; j < N; j++)
				newMap[i][j] = map[i][j];
		return newMap;
	}

	// 조합
	public static int path[];

	public static int ret = Integer.MAX_VALUE;
	public static void dfs(int next, int n, int r, int idx) {
		if (next >= r) {
			/*if(r>=3&&path[0]==2&&path[1]==11&&path[2]==13){
				System.out.println();
			}*/
			boolean flag =solve(r);
			if(flag)
				ret=Math.min(ret, r);
			return;
		}

		for (int i = idx; i < n; i++) {

			path[next] = i;
			dfs(next + 1, n, r, i + 1);
		}

	}

}
