package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_19235 {

	static int N;
	static Block[] blockAry;
	static int map[][] = new int[10][10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		blockAry = new Block[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Block block = new Block();
			block.t = Integer.parseInt(st.nextToken());
			block.y = Integer.parseInt(st.nextToken());
			block.x = Integer.parseInt(st.nextToken());
			blockAry[i] = block;
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {

			for (int j = 0; j < 2; j++) {
				Block nowBlock = blockAry[i];
				map[nowBlock.y][nowBlock.x] = 1;
				switch (nowBlock.t) {
				case 1:
					;
					break;
				case 2:
					map[nowBlock.y][nowBlock.x + 1] = 1;
					break;
				case 3:
					map[nowBlock.y + 1][nowBlock.x] = 1;
					break;
				}

				if (j == 1) // 회전시켜서 한번더
					map = rotate(map, 1);
				// 블록을 위치시킨후 첫 이동시킴
				initialMove(map, j);

				// 점수낼수있나 확인
				while (true) {
					int ret = 0; // 점수결과
					ret = checkPoint(map, j);

					if (ret == 0)
						break;
					else
						sum += ret;
				}

				// 특수구간 체크
				deleteSpecial(map, j);

				if (j == 1) // 반대로회전하여 방향원복
					map = rotate(map, 0);
			}

		}
		System.out.println(sum);
		int count=0;
		for(int i = 0 ; i < 10 ; i ++)
			for(int j = 0 ;  j < 10 ; j ++){
				if(map[i][j]==1)
					count++;
			}
		System.out.println(count);
	}

	static int deleteOne(int[][] map, int startX, int caseNum) {
		int ret=0;
		int startY = 0;
		if (caseNum == 1) {
			startY = 6;
		}
		int endY = startY + 4;

		for (int i = startY; i < endY; i++) {						
			map[i][startX]=0;
			for (int j = startX-1; j > 3; j--) {
				
				if(map[i][j]==1){
					for(int k = j+1 ; k < 10 ; k++){
						if(map[i][k]==1){
					
							map[i][k-1]=1;
						}
						if(k==9)
							map[i][k]=1;
					}		
					map[i][j]=0;
				}
			}
		}
		return ret;
	}

	static int checkPoint(int[][] map, int caseNum) {
		int ret = 0;
		int startY = 0;
		if (caseNum == 1) {
			startY = 6;
		}
		int endY = startY + 4;
		int startLine = 0;
		for (int i = 6; i < 10; i++) {
			int temp = 0;
			for (int j = startY; j < endY; j++) {
				if (map[j][i] == 1) {
					temp++;
				}
			}
			if (temp == 4) {
				ret++;
				ret+=deleteOne(map, i, caseNum);
			}

		}

		return ret;
	}

	static void deleteSpecial(int[][] nowMap, int caseNum) {
		int startY = 0;
		if (caseNum == 1) {
			startY = 6;
		}
		int endY = startY + 4;
		int ret = 0;
		for (int i = 4; i < 6; i++) {
			for (int j = startY; j < endY; j++) {
				if (nowMap[j][i] == 1) {
					ret++;
					break;
				}
			}
		}
		if (ret == 0)
			return;
		if (caseNum == 0) {
			startY = 0;
		} else {
			startY = 6;
		}
		endY = startY + 4;
		for (int i = startY; i < endY; i++) {
			for (int j = 9; j > 3; j--) {
				map[i][j] = map[i][j - ret];
				map[i][j - ret] = 0;
			}
		}

	}

	public static void initialMove(int[][] nowMap, int caseNum) {

		int startY = 0;

		if (caseNum == 1) {
			startY = 6;
		}
		int endY = startY + 4;
		int moveStep = Integer.MAX_VALUE;
		int checkY = -1;
		int checkX = -1;
		
		for (int i = startY; i < endY; i++) {
			for (int j = 3; j >= 0; j--) {
				if (nowMap[i][j] == 1) {
					checkY = i;
					checkX = j;
					int temp = 0;
					while (true) {
						checkX++;
						if (checkX >= 10 || nowMap[checkY][checkX] == 1) {
							if(checkX<=3){
								continue;
							}
							moveStep=Math.min(moveStep, temp);
							break;
							
						} else {
							temp++;
						}
					}
					
				}
			}
		}
		if(moveStep==Integer.MAX_VALUE)
			moveStep=0;
		
		for (int i = startY; i < endY; i++) {
			for (int j = 3; j >= 0; j--) {
				if (nowMap[i][j] == 1) {
					nowMap[i][j] = 0;
					nowMap[i][j + moveStep] = 1;
				}
			}
		}
	}

	public static int[][] rotate(int[][] nowMap, int dir) {
		int[][] temp = new int[10][10];

		if (dir == 0) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					temp[j][10 - i - 1] = nowMap[i][j];
				}
			}
		} else {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					temp[10 - j - 1][i] = nowMap[i][j];
				}
			}
		}

		return temp;

	}

	static class Block {
		int t;
		int y;
		int x;
	}
}
