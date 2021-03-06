package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Samsung_2174 {

	public static int A;
	public static int B;
	public static int N;
	public static int M;
	public static int graph[][];
	public static int dy[] = new int[] { 1, -1, 0, 0 }; // n,s,e,w
	public static int dx[] = new int[] { 0, 0, 1, -1 };
	public static int dirChange[][]=new int[][]{//L,R
		{3,2},
		{2,3},
		{0,1},
		{1,0},
		
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[B][A];
		
		ArrayList<Robot> robotAry = new ArrayList<Robot>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			graph[y][x]=i+1;
			int dir=-1;
			switch (st.nextToken().charAt(0)) {
			case 'N':
				dir = 0;
				break;
			case 'S':
				dir = 1;
				break;
			case 'E':
				dir = 2;
				break;
			case 'W':
				dir = 3;
				break;
			}
	
			robotAry.add(new Robot(y,x,dir));
		}
		ArrayList<Order> orderAry = new ArrayList<Order>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int robotIdx= Integer.parseInt(st.nextToken());
			char menu= st.nextToken().charAt(0);
			int num = Integer.parseInt(st.nextToken());
			orderAry.add(new Order(robotIdx, menu, num));
		}
		
		for(int i = 0 ; i < orderAry.size() ; i ++){
			Order ord = orderAry.get(i);
			Robot robot= robotAry.get(ord.robotIdx-1);
			int nowY=robot.y;
			int nowX=robot.x;
			int nextY;
			int nextX;
			int nowDir=robot.dir;
			int nextDir;
			for(int j = 0 ; j <ord.num ; j ++ ){
				
				switch(ord.menu){
				case 'F':
					nextY=nowY+dy[nowDir];
					nextX=nowX+dx[nowDir];
					if(nextY<0||nextX<0||nextY>=B||nextX>=A){
						System.out.println("Robot "+(ord.robotIdx)+" crashes into the wall");
						return;
					}
					
					if(graph[nextY][nextX]!=0){
						System.out.println("Robot "+(ord.robotIdx)+" crashes into robot "+(graph[nextY][nextX]));
						return;
					}
					nowY=nextY;
					nowX=nextX;
					;
					break;
				case 'L':
					nowDir=dirChange[nowDir][0];
					;
					break;
				case 'R':
					nowDir=dirChange[nowDir][1];
					;
					break;
				}
			}
			graph[nowY][nowX]=graph[robot.y][robot.x];
			graph[robot.y][robot.x]=0;
			robot.y=nowY;
			robot.x=nowX;
			robot.dir=nowDir;
		}
		System.out.println("OK");

	}

	static class Robot {
		int y;
		int x;
		int dir;

		public Robot(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}

	}
	static class Order{
		int robotIdx;
		char menu;
		int num;
		public Order(int robotIdx, char menu, int num) {
			super();
			this.robotIdx = robotIdx;
			this.menu = menu;
			this.num = num;
		}
		
	}
}
