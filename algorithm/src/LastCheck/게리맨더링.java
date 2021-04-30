package LastCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 게리맨더링 {

	static int N, map[][],total=0;
	public static int ret = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		path=new int[N];
		for (int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total+=map[i][j];
			}
		}
		dfs(0);
		System.out.println(ret);

	}

	// 중복조합

	static int[] path;

	public static void dfs(int next) {
		if (next >= 2) {
			// todo
			int d1 = path[0];
			int d2=path[1];
			for(int r = 0 ; r < N ; r++){
				for(int c = 0 ;c < N ; c++){
					
					if((r+d1+d2)>=N||(c-d1)<0||(c+d2)>=N)
						continue;
					int[][] visit = new int[N][N];
					
					for(int piv = 0 ; piv <= d1 ;piv++){
						visit[r+piv][c-piv]=1;
					}
					for(int piv=0;piv<=d2;piv++){
						visit[r+piv][c+piv]=1;
					}
					for(int piv=0;piv<=d2;piv++){
						visit[r+d1+piv][c-d1+piv]=1;
					}
					for(int piv=0;piv<=d1;piv++){
						visit[r+d2+piv][c+d2-piv]=1;
					}
					for(int i = r+1 ; i <r+d1+d2;i++) {
						boolean flag=false;
						for(int j = 0 ; j<N ;j++) {
							if(flag==false&&visit[i][j]==1) {
								flag=true;						
							}else if(flag==true&&visit[i][j]==0) {
								visit[i][j]=1;
							}else if(flag==true&&visit[i][j]==1) {
								break;
							}
						}
					}
					int tempSum[] = new int[5];		
					for (int i = 0; i < r + d1; i++) {
						for (int j = 0; j <= c; j++) {
							if (visit[i][j] == 1)
								continue;
							tempSum[0] += map[i][j];
						}
					}
					tempSum[1] = 0;
					for (int i = 0; i <= r + d2; i++) {
						for (int j = c + 1; j < N; j++) {
							if (visit[i][j] == 1)
								continue;
							tempSum[1] += map[i][j];
						}
					}
					
					for (int i = r + d1; i < N; i++) {
						for (int j = 0; j < c - d1 + d2; j++) {
							if (visit[i][j] == 1)
								continue;
							tempSum[2] += map[i][j];
						}
					}

					
					for (int i = r + d2 + 1; i < N; i++) {
						for (int j = c - d1 + d2; j < N; j++) {

							if (visit[i][j] == 1)
								continue;
							tempSum[3] += map[i][j];

						}
					}
					tempSum[4]=total-(tempSum[0]+tempSum[1]+tempSum[2]+tempSum[3]);
					ret=Math.min(ret, Arrays.stream(tempSum).max().getAsInt()-Arrays.stream(tempSum).min().getAsInt());
					
					
				}
			}

			return;
		}

		for (int i = 1; i <= N; i++) {
			path[next]=i;
			dfs(next + 1);
		}
	}
}
