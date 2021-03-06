package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_3987_2 {

	public static int N;
	public static int M;
	public static int graph[][];
	public static int flag[][];
	public static int dy[] = new int[] { -1, 0, 1, 0 };// 위 오른 아래 왼
	public static int dx[] = new int[] { 0, 1, 0, -1 };
	public static int dirChange[][] = new int[][] { { 3, 2, 1, 0 }// "\방향"
	, { 1, 0, 3, 2 }// "/"방향
	};
	public static int retNum = Integer.MIN_VALUE;
	public static int retDir = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		flag = new int[N][M];
		graph = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = new String(br.readLine());
			for (int j = 0; j < M; j++) {
				flag[i][j] = -1;
				switch (str.charAt(j)) {
				case '.':
					graph[i][j] = 0;
					break;
				case '\\':
					graph[i][j] = 1;
					break;
				case '/':
					graph[i][j] = 2;
					break;
				case 'C':
					graph[i][j] = 3;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken()) - 1;
		int x = Integer.parseInt(st.nextToken()) - 1;


		boolean infinity=false;
		boolean breakFlag=false;
		for (int i = 0; i < 4; i++) {
			if(breakFlag==true)
				break;
			for (int p = 0; p < N; p++) {
				for (int L = 0; L < M; L++) {
					flag[p][L] = -1;
				}
			}
			flag[y][x] = 0;
			
			int startDir=i;
			int startY=y;
			int startX=x;
			int nowY = startY;
			int nowX = startX;
			int nowStep=0;
			int nextDir=i;
			int nowDir=i;
		
			while(true){
				int nextY = nowY + dy[nowDir];
				int nextX = nowX + dx[nowDir];
				
				
				if (nextY < 0 || nextX < 0 || nextY >= N || nextX >=M||graph[nextY][nextX]==3) {
					int orgTp=retNum;
					retNum=Math.max(retNum, nowStep);
					if(orgTp!=retNum)
					retDir=i;
					break;
				}
				
				if(flag[nextY][nextX]!=-1){
				
					if(nowY==startY&&nowX==startX&&nowDir==startDir){
					// 무한궤도처리	
						infinity=true;
						retNum=-1;
						retDir=i;
						breakFlag=true;
						break;
					}
					
				}
				
				if (graph[nextY][nextX] == 1) { // '\'방향
					nextDir=dirChange[0][nowDir];
					
				} else if (graph[nextY][nextX] == 2) {
					nextDir= dirChange[1][nowDir];
				
				}
				
				nowY=nextY;
				nowX=nextX;
				nowDir=nextDir;
				flag[nextY][nextX] = flag[nowY][nowX] + 1;
				nowStep++;
			
			}
			
			
			
		}
		

		switch (retDir) {
		case 0:
			System.out.println("U");
			break;
		case 1:
			System.out.println("R");
			break;
		case 2:
			System.out.println("D");
			break;
		case 3:
			System.out.println("L");
			break;
			
		}
		if(infinity){
			System.out.println("Voyager");
		}else{
			System.out.println(retNum + 1);
		}
		

	}

	
}
