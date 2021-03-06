package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Samsung_12100 {

	static String[] dir = new String[] { "up", "down", "left", "right" };
	static int ret = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int size = Integer.parseInt(st.nextToken());

		int[][] grid = new int[size][size];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < dir.length; i++) {
			dfs(0, dir[i], grid, size);
		}
		System.out.println(ret);

	}

	public static void dfs(int now, String nowDir, int[][] nowGrid,int size){
		if(now>=5){
			for(int i = 0 ; i < size ; i ++){
				for(int j = 0 ; j < size ; j++){
					ret=Math.max(ret, nowGrid[i][j]);
					
				}
			}
			
			return;
		}
		
		int[][] nextGrid = new int[size][size];
		
		for(int i = 0 ; i < size ; i ++){
			for(int j = 0 ; j < size ; j++){
				nextGrid[i][j]=nowGrid[i][j];
			}
		}
		
		setGrid(nowDir, nextGrid, size);
		
		for(int i = 0 ; i < dir.length ; i ++){
			dfs(now+1,dir[i],nextGrid,size);
		}
		
		
		
	}

	public static void setGrid(String dir, int[][] nowGrid, int size) {
		switch (dir) {
		case "up":

			for (int i = 0; i < size; i++) {
				int[] temp = new int[size];
				int iter = 0;

				for (int j = 0; j < size; j++) {
					if (nowGrid[j][i] != 0) {
						temp[iter] = (nowGrid[j][i]);
						iter++;
					}
				}

				for (int j = 1; j < iter; j++) {
					if (temp[j - 1] == temp[j]) {
						temp[j - 1] *= 2;
						temp[j] = 0;
					}
				}

				iter = 0;
				for (int j = 0; j < size; j++) {
					nowGrid[j][i] = 0;
					if (temp[j] != 0) {
						nowGrid[iter][i] = temp[j];
						iter++;
					}

				}

			}

			break;
		case "down":

			for (int i = 0; i < size; i++) {
				int[] temp = new int[size];
				int iter = 0;

				for (int j = size - 1; j >= 0; j--) {
					if (nowGrid[j][i] != 0) {
						temp[iter] = (nowGrid[j][i]);
						iter++;
					}
				}

				for (int j = 1; j < iter; j++) {
					if (temp[j - 1] == temp[j]) {
						temp[j - 1] *= 2;
						temp[j] = 0;
					}
				}

				iter = 0;
				for (int j = 0; j < size; j++) {
					nowGrid[size - 1 - j][i] = 0;
					if (temp[j] != 0) {
						nowGrid[size - 1 - iter][i] = temp[j];
						iter++;
					}
				}
			}

			break;

		case "left":

			for (int i = 0; i < size; i++) {
				int[] temp = new int[size];
				int iter = 0;

				for (int j = 0; j < size; j++) {
					if (nowGrid[i][j] != 0) {
						temp[iter] = (nowGrid[i][j]);
						iter++;
					}
				}

				for (int j = 1; j < iter; j++) {
					if (temp[j - 1] == temp[j]) {
						temp[j - 1] *= 2;
						temp[j] = 0;
					}
				}

				iter = 0;
				for (int j = 0; j < size; j++) {
					nowGrid[i][j] = 0;
					if (temp[j] != 0) {
						nowGrid[i][iter] = temp[j];
						iter++;
					}

				}

			}
			break;

		case "right":

			for (int i = 0; i < size; i++) {
				int[] temp = new int[size];
				int iter = 0;

				for (int j = size - 1; j >= 0; j--) {
					if (nowGrid[i][j] != 0) {
						temp[iter] = (nowGrid[i][j]);
						iter++;
					}
				}

				for (int j = 1; j < iter; j++) {
					if (temp[j - 1] == temp[j]) {
						temp[j - 1] *= 2;
						temp[j] = 0;
					}
				}

				iter = 0;
				for (int j = 0; j < size; j++) {
					nowGrid[i][size - 1 - j] = 0;
					if (temp[j] != 0) {
						nowGrid[i][size - 1 - iter] = temp[j];
						iter++;
					}

				}

			}
			break;
		}
	}

}
