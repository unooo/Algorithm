package topcoder2;

import java.util.LinkedList;
import java.util.Queue;

public class MazeMaker {

	public static String[] maze;
	public static int startRow;
	public static int startCol;
	public static int[] moveRow;
	public static int[] moveCol;
	public static int[][] flag;
	public static int ret = 0;

	public static void main(String[] args) {

		
		/*  maze= new String[]{"...","...","..."}; 
		  startRow=0; 
		  startCol=1;
		  moveRow=new int[]{1,0,-1,0}; 
		  moveCol=new int[]{0,1,0,-1};*/
		 

		
		  
		/*    String[] maze={"...","...","..."}; 
		    int startRow = 0 ; 
			int startCol =1; 
		  int moveRow[]={1,0,-1,0,1,1,-1,-1}; 
		  int moveCol[]={0,1,0,-1,1,-1,1,-1};*/
		 

		

		maze = new String[] { ".......", "X.X.X..", "XXX...X", "....X..", "X....X.", "......." };
		startRow = 5;
		startCol = 0;
		moveRow = new int[] { 1, 0, -1, 0, -2, 1 };
		moveCol = new int[] { 0, -1, 0, 1, 3, 0 };
		
	/*	maze = new String[] { "......." };
		startRow = 0;
		startCol = 0;
		moveRow = new int[] { 1,0,1,0,1,0};
		moveCol = new int[] { 0,1,0,1,0,1 };*/

		longestPath(maze, startRow, startCol, moveRow, moveCol);

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length(); j++) {
				ret = Math.max(ret, flag[i][j]);
			}
		}

		System.out.println(ret);

	}

	public static int longestPath(String[] maze, int startRow, int startCol, int[] moveRow, int[] moveCol) {
		int ret = 0;
		MazeMaker.maze = maze;
		MazeMaker.startCol = startCol;
		MazeMaker.startRow = startRow;
		MazeMaker.moveRow = moveRow;
		MazeMaker.moveCol = moveCol;
		flag = new int[maze.length][maze[0].length()];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length(); j++) {
				flag[i][j] = -1;
			}
		}

		flag[startRow][startCol] = 0;
		//dfs(startRow, startCol);
		bfs(startRow, startCol);

		return ret;

	}

	public static void bfs(int startRow, int startCol) {
		Queue<Integer> queueX = new LinkedList<Integer>();
		Queue<Integer> queueY = new LinkedList<Integer>();

		queueX.add(startCol);
		queueY.add(startRow);

		while (!queueX.isEmpty()) {
			int nowX = queueX.poll();
			int nowY = queueY.poll();

			for (int i = 0; i < moveRow.length; i++) {

				if (nowY + moveRow[i] < 0 || nowY + moveRow[i] >= maze.length || nowX + moveCol[i] < 0|| nowX + moveCol[i] >= maze[0].length())
					continue;
				
				if (maze[nowY + moveRow[i]].charAt(nowX + moveCol[i]) == 'X')
					continue;
				
				
				if (flag[nowY + moveRow[i]][nowX + moveCol[i]] == -1) {
					flag[nowY + moveRow[i]][nowX + moveCol[i]] = flag[nowY][nowX] + 1;
					queueY.add(nowY + moveRow[i]);
					queueX.add(nowX + moveCol[i]);
				}
			}
		}

	}

	public static void dfs(int nowY, int nowX) {

		for (int i = 0; i < moveRow.length; i++) {

			if (nowY + moveRow[i] < 0 || nowY + moveRow[i] >= maze.length || nowX + moveCol[i] < 0
					|| nowX + moveCol[i] >= maze[0].length())
				continue;

			if (maze[nowY + moveRow[i]].charAt(nowX + moveCol[i]) == 'X')
				continue;

			if (flag[nowY + moveRow[i]][nowX + moveCol[i]] != -1
					&& flag[nowY][nowX] + 1 > flag[nowY + moveRow[i]][nowX + moveCol[i]])
				continue;

			flag[nowY + moveRow[i]][nowX + moveCol[i]] = flag[nowY][nowX] + 1;
			dfs(nowY + moveRow[i], nowX + moveCol[i]);
		}

	}
}
