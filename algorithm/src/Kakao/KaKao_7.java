package Kakao;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class KaKao_7 {
	public static int flag[][][];
	public static int dy[] = new int[] { 1, -1, 0, 0 };
	public static int dx[] = new int[] { 0, 0, 1, -1 };

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 },
				{ 0, 0, 0, 0, 0 } };
		System.out.println(solution(board));
	}

	public static int solution(int[][] board) {
		int answer = -1;
		int N = board.length;
		flag=new int[N][N][2];
	
		Drone drone = new Drone(0, 0, 0, 1, 1, 0);
		flag[0][0][0]=1;
		Queue<Drone> que = new LinkedList<Drone>();
		que.add(drone);


		while (!que.isEmpty()) {

			drone = que.poll();

			int nowY1 = drone.y1;
			int nowX1 = drone.x1;
			int nowY2 = drone.y2;
			int nowX2 = drone.x2;
			int dir = drone.dir;
			int nowStep = drone.nowStep;
		
			if (nowY1 == N - 1 && nowX1 == N - 1) {
				return nowStep;
			}
			if (nowY2 == N - 1 && nowX2 == N - 1) {
				return nowStep;
			}
			int nextY1;
			int nextY2;
			int nextX1;
			int nextX2;
			int nextStep;

			for (int i = 0; i < 4; i++) {
				nextY1 = nowY1 + dy[i];
				nextX1 = nowX1 + dx[i];
				nextY2 = nowY2 + dy[i];
				nextX2 = nowX2 + dx[i];
				nextStep = nowStep + 1;
				if (nextY1 < 0 || nextX1 < 0 || nextY2 < 0 || nextX2 < 0 || nextY1 >= N || nextX1 >= N || nextY2 >= N
						|| nextX2 >= N)
					continue;
				if (board[nextY1][nextX1] == 1 || board[nextY2][nextX2] == 1)
					continue;
				
				 if(dir==1){
					 if(flag[nextY1][nextX1][0]==1){
						 continue;
					 }else{
						 flag[nextY1][nextX1][0]=1;
					 }
				}else{
					 if(flag[nextY1][nextX1][1]==1){
						 continue;
					 }else{
						 flag[nextY1][nextX1][1]=1;
					 }
				}
				
				Drone  temp =new Drone(nextY1, nextX1, nextY2, nextX2, dir, nextStep);	
				que.add(temp);
				
				
				
			}

			for (int i = 0; i < 4; i++) {
				if (i < 2) {
					if (drone.dir == -1)
						continue;
				} else {
					if (drone.dir == 1)
						continue;
				}
				
				for (int j = 0; j < 2; j++) {
					nextY1 = drone.ary[j][0];
					nextX1 = drone.ary[j][1];
					nextY2 = nextY1 + dy[i];
					nextX2 = nextX1 + dx[i];
					nextStep = nowStep + 1;
					if (drone.y1 + dy[i] < 0 || drone.x1 + dx[i] < 0 || drone.y2 + dy[i] < 0 || drone.x2 + dx[i] < 0
							|| drone.y1 + dy[i] >= N || drone.x1 + dx[i] >= N || drone.y2 + dy[i] >= N
							|| drone.x2 + dx[i] >= N) {

						continue;
					}
					if (board[drone.y1 + dy[i]][drone.x1 + dx[i]] == 1
							|| board[drone.y2 + dy[i]][drone.x2 + dx[i]] == 1)
						continue;
		
					Drone temp = null;
				 if(dir==1){
					 if(i==0){
						 if(flag[nextY1][nextX1][1]==1){
							 continue;
						 }else{
							 flag[nextY1][nextX1][1]=1;
							 temp=new Drone(nextY1, nextX1, nextY2, nextX2, dir * -1, nextStep);
						 }
					 }else if(i==1){
						 if(flag[nextY2][nextX2][1]==1){
							 continue;
						 }else{
							 flag[nextY2][nextX2][1]=1;
							 temp=new Drone(nextY2, nextX2, nextY1, nextX1, dir * -1, nextStep);
						 }
					 }
						 
				}else{
					if(i==2){
						 if(flag[nextY1][nextX1][0]==1){
							 continue;
						 }else{
							 flag[nextY1][nextX1][0]=1;
							 temp=new Drone(nextY1, nextX1, nextY2, nextX2, dir * -1, nextStep);
						 }
					 }else if(i==3){
						 if(flag[nextY2][nextX2][0]==1){
							 continue;
						 }else{
							 flag[nextY2][nextX2][0]=1;
							 temp=new Drone(nextY2, nextX2, nextY1, nextX1, dir * -1, nextStep);
						 }
					 }
				}
					
					
				
					
					
					que.add(temp);
				
				
				
				}

			}
			
		}

		return answer;
	}
	
	/*
	public static boolean checkDuplicate(Drone temp){
		boolean ret=false;
		
		if(flag[temp.y2][temp.x2]==null)
			return ret;
		if(flag[temp.y2][temp.x2].contains(temp))
			return true;
		else return false;
		
	
	}
*/

	static class Drone {
		int y1;
		int x1;
		int y2;
		int x2;
		int dir;// 1가로//-1세로
		int nowStep;
		int ary[][];

		public Drone(int y1, int x1, int y2, int x2, int dir, int nowStep) {
			super();
			this.y1 = y1;
			this.x1 = x1;
			this.y2 = y2;
			this.x2 = x2;
			this.dir = dir;
			this.nowStep = nowStep;
			ary = new int[][] { { y1, x1 }, { y2, x2 }, };
		}
		
		

	}

}
