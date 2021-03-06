package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Samsung_17825 {

	public static int[] diceNum = new int[10];
	public static int[] dy = new int[] { 1, -1, 0, 0 }; // ��, �� ,�� ,��
	public static int[] dx = new int[] { 0, 0, -1, 1 };
	public static int ret = Integer.MIN_VALUE;
	public static LinkedList<Horse> hList;
	public static int[][] map1 = new int[][] { { 40, 38, 36, 34, 32, 30 }, { 2, -1, -1, -1, -1, 28 },
			{ 4, -1, -1, -1, -1, 26 }, { 6, -1, -1, -1, -1, 24 }, { 8, -1, -1, -1, -1, 22 },
			{ 10, 12, 14, 16, 18, 20 } };

	public static int[][] map2 = new int[][] { { -1, -1, -1, -1, 40, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, 35, -1, -1, -1, -1 }, { -1, -1, -1, -1, 30, -1, -1, -1, -1 },
			{ 10, 13, 16, 19, 25, 26, 27, 28, 30 }, { -1, -1, -1, -1, 24, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, 22, -1, -1, -1, -1 }, { -1, -1, -1, -1, 20, -1, -1, -1, -1 } };

	static class Horse {
		int y;
		int x;
		int life;
		int dir;
		int nowMap;

		Horse(int y, int x, int life, int dir, int nowMap) {
			this.y = y;
			this.x = x;
			this.life = life; // 1 ����̺�, 0 ���� ;
			this.dir = dir;
			this.nowMap = nowMap;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		hList = new LinkedList<Horse>();
		for (int i = 0; i < 4; i++) {
			hList.add(new Horse(0, 0, 1, 0, 1));
		}

		for (int i = 0; i < 10; i++) {
			diceNum[i] = Integer.parseInt(st.nextToken());
		}
		int[][] flag1 = new int[6][6];
		int[][] flag2 = new int[7][9];
		dfs(0, flag1, flag2, 0);
		System.out.println(ret);
	}

	public static void dfs(int now, int flag1[][], int flag2[][], int score) {
		if (now >= 10) {
			ret = Math.max(ret, score);
			return;
		}

		for (int i = 0; i < 4; i++) {
			Horse nowHorse = hList.get(i);
			if (nowHorse.life == 0) {
				continue;
			}
			int startX=nowHorse.x;
			int startY=nowHorse.y;
			int startDir=nowHorse.dir;
			int startLife=nowHorse.life;
			int startMap=nowHorse.nowMap;
			int nowX = nowHorse.x;
			int nowY = nowHorse.y;
			int jumpNum = diceNum[now];
			for (int j = 0; j < jumpNum; j++) {
				int newX = nowX + dx[nowHorse.dir];
				int newY = nowY + dy[nowHorse.dir];

				if (nowHorse.nowMap == 1) { // 1���� ���°��
					if (newY < 0 || newX < 0 || newY >= 6 || newX >= 6) {
						switch (nowHorse.dir) { 
						case 0:
							nowHorse.dir = 3;
							break;
						case 1:
							nowHorse.dir = 2;
							break;
						case 2:
							// ������ �絵��!
							nowHorse.life=0;
							break;
						case 3:
							nowHorse.dir = 1;
							break;
						}
						newX = nowX + dx[nowHorse.dir];
						newY = nowY + dy[nowHorse.dir];
					}
					nowX = newX;
					nowY = newY;

				} else {// 2���� ���°��

					if (newY < 0) {
						// ������ �絵��!
						nowHorse.life=0;
						break;
					}

					if (nowY == 3 && nowX == 4) {// map2[nowY][nowX]==25���
						nowHorse.dir = 1;
						newX = nowX + dx[nowHorse.dir];
						newY = nowY + dy[nowHorse.dir];
					}
					nowX = newX;
					nowY = newY;
				}

			}
			
			if(nowHorse.life==0){
				if (nowHorse.nowMap == 1) {	
					flag1[nowHorse.y][nowHorse.x ]=0;
				}else{
					flag2[nowHorse.y][nowHorse.x ]=0;
					if (nowHorse.x  == 0 && nowHorse.y  == 3) {
						flag1[0][5]=0;
					}else if (nowHorse.x  == 4 && nowHorse.y  == 6) {
						flag1[5][5]=0;
					}if (nowHorse.x  == 8 && nowHorse.y  == 3) {
						flag1[5][0]=0;
					}
				}
				dfs(now + 1, flag1, flag2, score);
				if (nowHorse.nowMap == 1) {	
					flag1[nowHorse.y][nowHorse.x ]=0;
					flag1[startY][startX]=1;
				}else{
					flag2[nowHorse.y][nowHorse.x ]=0;
					
					if (nowHorse.x  == 0 && nowHorse.y  == 3) {
						flag1[5][0]=0;
					
					}else if (nowHorse.x  == 4 && nowHorse.y  == 6) {
						flag1[5][5]=0;
						
					}else if (nowHorse.x  == 8 && nowHorse.y  == 3) {
						flag1[0][5]=0;
					
					}else{
						flag2[startY][startX]=1;
					}
				}
				
				
				nowHorse.x=startX;
				nowHorse.y=startY;
				nowHorse.dir=startDir;
				nowHorse.life=startLife;
				nowHorse.nowMap=startMap;
				
				continue;
			}

			// ���� ������ ���� ��ǥ ���� �Ϸ�

			if (nowHorse.nowMap == 1) { // ���� ������ ��ġ�� �̹� ���� �ִ°�� �ش� �������� ����ع�����
				if (flag1[nowY][nowX] == 1) {
					continue;
				}
			} else if (nowHorse.nowMap == 2) {
				if (flag2[nowY][nowX] == 1) {
					continue;
				}
			}

			// ���� ��ǥ�� �����濡 �귯�� ��ǥ�ΰ�� ��ǥ, �� ���� �� �a���׼���
			
			if (nowHorse.nowMap == 1) {		
				flag1[nowHorse.y][nowHorse.x ]=0;
				flag1[nowY][nowX] = 1;
				if (nowX == 0 && nowY == 5) {
					nowHorse.x = 0;
					nowHorse.y = 3;
					nowHorse.dir = 3;
					nowHorse.nowMap = 2;
					flag2[nowHorse.y][nowHorse.x] = 1;
				} else if (nowX == 5 && nowY == 5) {
					nowHorse.x = 4;
					nowHorse.y = 6;
					nowHorse.dir = 1;
					nowHorse.nowMap = 2;
					flag2[nowHorse.y][nowHorse.x] = 1;
				} else if (nowX == 5 && nowY == 0) {
					nowHorse.x = 8;
					nowHorse.y = 3;
					nowHorse.dir = 2;
					nowHorse.nowMap = 2;
					flag2[nowHorse.y][nowHorse.x] = 1;
				} else {
					nowHorse.x = nowX;
					nowHorse.y = nowY;
				}
			}else{
				flag2[nowHorse.y][nowHorse.x ]=0;
				flag2[nowY][nowX] = 1;
				
				if (nowHorse.x == 0 && nowHorse.y == 3) {
					flag1[5][0]=0;
				}else if (nowHorse.x == 4 && nowHorse.y == 6) {
					flag1[5][5]=0;
				}if (nowHorse.x == 8 && nowHorse.y == 3) {
					flag1[0][5]=0;
				}
				
				nowHorse.x = nowX;
				nowHorse.y = nowY;
			}

			if (nowHorse.nowMap == 2) {
				flag2[nowHorse.y][nowHorse.x] = 1;
			} 
			

			// �絵�� ���ھ� �ջ�
			int newScore = score;

			if (nowHorse.nowMap == 1) {
				newScore += map1[nowHorse.y][nowHorse.x];
			} else {
				newScore += map2[nowHorse.y][nowHorse.x];
			}

			dfs(now + 1, flag1, flag2, newScore);
			
			if (nowHorse.nowMap == 1) {	
				flag1[nowHorse.y][nowHorse.x ]=0;
				flag1[startY][startX]=1;
			}else{
				flag2[nowHorse.y][nowHorse.x ]=0;
				
				if (nowHorse.x  == 0 && nowHorse.y  == 3) {
					flag1[5][0]=0;
					flag1[startY][startX]=1;
				}else if (nowHorse.x  == 4 && nowHorse.y  == 6) {
					flag1[5][5]=0;
					flag1[startY][startX]=1;
				}else if (nowHorse.x  == 8 && nowHorse.y  == 3) {
					flag1[0][5]=0;
					flag1[startY][startX]=1;
				}else{
					flag2[startY][startX]=1;
				}
			}
			
			nowHorse.x=startX;
			nowHorse.y=startY;
			nowHorse.dir=startDir;
			nowHorse.life=startLife;
			nowHorse.nowMap=startMap;
			

		}

	}
}
