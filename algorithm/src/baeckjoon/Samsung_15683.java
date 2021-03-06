package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_15683 {

	public static int ret = Integer.MAX_VALUE;
	public static int N;
	public static int M;
	public static int graph[][];
	public static int cctv[][][] = new int[][][] { { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } },
			{ { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 1, 1 }, { 0, 0, 1, 1 } },
			{ { 1, 0, 0, 1 }, { 0, 1, 1, 0 }, { 1, 0, 1, 0 }, { 0, 1, 0, 1 } },
			{ { 1, 1, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 0 }, { 1, 0, 1, 1 } },
			{ { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 } } };
	public static int[] dy = new int[] { 0, 0, 1, -1 };
	public static int[] dx = new int[] { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		int cctvNum=0;
		for (int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j]>0&&graph[i][j]<6){
					cctvNum++;
				}
			}
		}
		int[][]cctvLocation = new int[cctvNum][2];
		int inumer=0;
		for (int i = 0; i < N; i++) {			
			for (int j = 0; j < M; j++) {
				if(graph[i][j]>0&&graph[i][j]<6){
					
					cctvLocation[inumer][0]=i;
					cctvLocation[inumer][1]=j;
					inumer++;
				}
			}
		}
		int [][]map=mapCopy(graph);
		dfs(cctvLocation,inumer,0,map);
		System.out.println(ret);
	}

	public static void dfs(int[][] cctvLocation, int goalScore, int nowStep, int[][] map) {

		if (nowStep >= goalScore) {
			// 점수확인
			checkScore(map);
			return;
		}

		for (int i = 0; i < 4; i++) { // cctv의 방향이 동서남북일때를 기준으로 spread 시킨다
			int[][] tempMap = mapCopy(map);
			int nowCCtvY=cctvLocation[nowStep][0];
			int nowCCtvX=cctvLocation[nowStep][1];
			for(int j = 0 ; j < 4 ; j ++){
				int flag=cctv[(graph[nowCCtvY][nowCCtvX]-1)][i][j];
				if(flag==1){
					spread(tempMap,j,nowCCtvY,nowCCtvX);
					
				}
			}
			dfs(cctvLocation,goalScore,nowStep+1,tempMap);
		}
	}

	public static void spread(int[][] map,int direction,int y, int x){
		int nowY=y;
		int nowX=x;
		while(true){
			int newY=nowY+dy[direction];
			int newX=nowX+dx[direction];
			
			if(newY<0||newX<0||newY>=N||newX>=M)
				break;
			if(map[newY][newX]==6)
				break;
			if(map[newY][newX]==0){
			map[newY][newX]=-1;
			}
			nowY=newY;
			nowX=newX;
			
		}
	}
	public static int[][] mapCopy(int[][] map) {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j]=map[i][j];
			}

		}
		return newMap;
	}

	public static void checkScore(int[][] map) {
		int sum = 0;
		for (int i = 0; i < N; i++) {

			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					sum++;
			}
		}
		ret = Math.min(ret, sum);
	}
}

//1. map과 graph 혼용사용실수, j대신i적는실수, 조건미비(0일때만 #을 적을것)
