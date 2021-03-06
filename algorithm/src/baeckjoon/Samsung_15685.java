package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_15685 {

	public static int N;
	public static int x;
	public static int y;
	public static int d;
	public static int g;
	public static int dy[] = new int[] { 0, -1, 0, 1 };
	public static int dx[] = new int[] { 1, 0, -1, 0 };
	public static int spin[] = new int[]{3,0,1,2};
	public static int reverse[] = new int[]{2,3,0,1};
	/*
	 * 0: x좌표가 증가하는 방향 (→)
	 *  1: y좌표가 감소하는 방향 (↑)
	 *   2: x좌표가 감소하는 방향 (←)
	 *    3: y좌표가 증가하는방향 (↓)
	 */
	public static int[][] graph = new int[101][101];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g  = Integer.parseInt(st.nextToken());
			draw(y, x, d, g);
		}
		System.out.println(sum());
	}

	public static void draw(int y, int x, int d, int g) {
		int order1[] = new int[10000000];
		int order2[] = new int[10000000];
		int endY=y;
		int endX=x;
		int inumer=0;
		graph[endY][endX]=1;
		
		order1[0]=d;
		order2[0]=reverse[d];		
		inumer++;
		endY+=dy[d];
		endX+=dx[d];
		graph[endY][endX]=1;
		for(int i = 1 ; i <= g ; i ++){			//차수까지 반복
			
			
			for(int j = inumer-1 ;j>=0 ; j -- ){
				order1[inumer]=spin[order2[j]];
				order2[inumer]=reverse[order1[inumer]];
				
				endY+=dy[order1[inumer]];
				endX+=dx[order1[inumer]];
				if(endY>=101||endX>=101||endY<0||endX<0)
					break;
				inumer++;
				graph[endY][endX]=1;
			}
			
		}
	
	}
	
	public static int sum(){
		int ret = 0 ;
		for(int i = 0 ; i < 100 ; i ++){
			for(int j = 0 ; j < 100 ; j ++){
				if(graph[i][j]==1&&graph[i][j+1]==1&&graph[i+1][j]==1&&graph[i+1][j+1]==1)
					ret++;
			}
		}
		return ret;
	}
}
