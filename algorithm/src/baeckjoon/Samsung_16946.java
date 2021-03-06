package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_16946 {

	public static int N;
	public static int M;
	public static int[][] graph;

	public static int dy[] = new int[] { 1, -1, 0, 0 };
	public static int dx[] = new int[] { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str=br.readLine();
			for(int j = 0 ; j < M ; j ++)
				graph[i][j]=Integer.parseInt(str.charAt(j)+"");
		}

		int flag[][] = new int[N][M];
		
		int[] idxFlag= new int[N*M+1];

		int idx = 1;
		Queue<int[]> queue = new LinkedList<int[]>();
		for (int i = 0; i < N; i++) {

			for (int j = 0; j < M; j++) {
				if (graph[i][j] == 1 || flag[i][j] != 0) {

					continue;
				}

				

				queue.add(new int[]{i, j});
				idxFlag[idx]=1;
				flag[i][j] = idx;
	
				while (!queue.isEmpty()) {
					int[] pair = queue.poll();
					int nowY = pair[0];
					int nowX = pair[1];
					int nextY;
					int nextX;

					for (int k = 0; k < 4; k++) {
						nextY = nowY + dy[k];
						nextX = nowX + dx[k];

						if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= M) {

							continue;
						}

						if (graph[nextY][nextX] == 0 && flag[nextY][nextX] == 0) {

							queue.offer(new int[]{nextY, nextX});
							idxFlag[idx]+=1;
							flag[nextY][nextX] = idx;
						}

					}

				}
				idx++;

			}

		}
		
	
		int ln=idxFlag.length;
		StringBuilder sb = new StringBuilder();
		//LinkedList<Integer> groupAdded = new LinkedList<>();
		int[] groupAdded;
		int [][] out = new int[N][M];
		for (int i = 0; i < N; i++) {

			for (int j = 0; j < M; j++) {
				if (graph[i][j] == 0) {
				
					out[i][j]=0;
				}else{
				int num = 1;
				
				// 반복문 안에서 계속 뉴를 하면 시간초과걸림. stop the world
				//groupAdded.clear();
				groupAdded=new int[N*M+1];
				for (int k = 0; k < 4; k++) {
					int nextY = i + dy[k];
					int nextX = j + dx[k];
					if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= M) {
						continue;
					}
					if (flag[nextY][nextX] != 0) {
						int tp=flag[nextY][nextX];
						/*if (!groupAdded.contains(tp)) {
							num += idxFlag[tp];
							groupAdded.add(tp);
						}*/
						if (groupAdded[tp]!=0) {
							num += idxFlag[tp];
							groupAdded[tp]=1;
						}
					}
				}
				//sb.append((num % 10));
				out[i][j]=num%10;
				}
				
			}
			//sb.append("\n");
		}
		
		for(int i = 0 ;  i <  N ; i ++){
			for(int j = 0 ; j <  M ; j ++){
				//System.out.print(out[i][j]);
				sb.append(out[i][j]);
			}
			
		//	System.out.println(sb.toString());
			sb.append("\n");
		}
		
	     System.out.print(sb.toString());
	     br.close();

	}

	

}
