package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Samsung_17825_2 {

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
		int[] order = new int[10];
		 dfs(0,order );
		System.out.println(ret);
	}

	public static void dfs(int now, int[] order) {
		if (now >= 10) {
			 ret = Math.max(ret, check(order));
			 if(ret==218){
//				 System.out.println();
			 }
			 hList.clear();
			 for (int i = 0; i < 4; i++) {
				hList.add(new Horse(0, 0, 1, 0, 1));
			}
			 
			 
			return;
		}

		for (int i = 0; i < 4; i++) {
			Horse horse = hList.get(i);
			if (horse.life == 0) {
				continue;
			}
			order[now] = i;

			dfs(now + 1, order);

		}

	}

	public static int check(int order[]) {
		int ret = 0;
		int[][] flag1 = new int[6][6];
		int[][] flag2 = new int[7][9];
		if(order[0]==0&&order[1]==0&&order[2]==0&&order[3]==1&&order[4]==1&&order[5]==1&&order[6]==2&&order[7]==2&&order[8]==2&&order[9]==3){
//			System.out.println();
		}
		for (int i = 0; i < 10; i++) {

			Horse horse = hList.get(order[i]);
			if (horse.life == 0) {
				return -1; // �� ���� ���� �� ���� �����
			}

			int startX = horse.x;
			int startY = horse.y;
			int nowX = horse.x;
			int nowY = horse.y;
			int newX = 0;
			int newY = 0;
			int jumpNum = diceNum[i];
			for (int j = 0; j < jumpNum; j++) {
				newX = nowX + dx[horse.dir];
				newY = nowY + dy[horse.dir];

				if (horse.nowMap == 1) { // 1���� ���°��
					if (newY < 0 || newX < 0 || newY >= 6 || newX >= 6) {
						switch (horse.dir) {
						case 0:
							horse.dir = 3;
							break;
						case 1:
							horse.dir = 2;
							break;
						case 2:
							// ������ �絵��!
							horse.life = 0;
							break;
						case 3:
							horse.dir = 1;
							break;
						}
						newX = nowX + dx[horse.dir];
						newY = nowY + dy[horse.dir];
					}
					nowX = newX;
					nowY = newY;

				} else {// 2���� ���°��

					if (newY < 0) {
						// ������ �絵��!
						horse.life = 0;
						break;
					}

					if (nowY == 3 && nowX == 4) {// map2[nowY][nowX]==25���
						horse.dir = 1;
						newX = nowX + dx[horse.dir];
						newY = nowY + dy[horse.dir];
					}
					nowX = newX;
					nowY = newY;
				}
			}

			if (horse.life == 0) {
				
				if (horse.nowMap == 1) { 
					flag1[startY][startX] = 0;// �̵��ϱ� �� ��ġ�� ǥ�ø� ����
					if(startY==0&&startX==0){
						flag2[0][4]=0;
					}
					
					
				}else{
					flag2[startY][startX] = 0;// �̵��ϱ� �� ��ġ�� ǥ�ø� ����
					if(startY==0&&startX==4){
						flag1[0][0]=0;
					}
				}
				
				
				
				
			} else {
				
				if (horse.nowMap == 1) { // ���� ������ ��ġ�� �̹� ���� �ִ°�� �ش� �������� ����ع�����
					if (flag1[nowY][nowX] == 1) {
						return -1;
					}
				} else if (horse.nowMap == 2) {
					if (flag2[nowY][nowX] == 1) {
						return -1;
					}
				}

				if (horse.nowMap == 1) { // �÷��׿� ���� ��ġ�� ������ش�
					// ����� ���� 1���������°��
					flag1[startY][startX] = 0;// �̵��ϱ� �� ��ġ�� ǥ�ø� ����
					flag1[nowY][nowX] = 1;// �̵��� ��ġ�� ǥ��
					if (nowX == 0 && nowY == 5) { // �̵��� ��ġ�� �������� ��쿡�� �ʰ� ��������
													// �������ش�+2���ʿ��� ǥ��
						horse.x = 0;
						horse.y = 3;
						horse.dir = 3;
						horse.nowMap = 2;
						flag2[horse.y][horse.x] = 1;
						//flag1[nowY][nowX] = 0;// �̵��ϱ��� ��ġ�� ǥ�ô� �����Ѵ� ������ 2������
												// ���Ŵϱ�
					} else if (nowX == 5 && nowY == 5) {
						horse.x = 4;
						horse.y = 6;
						horse.dir = 1;
						horse.nowMap = 2;
						flag2[horse.y][horse.x] = 1;
						//flag1[nowY][nowX] = 0;// �̵��ϱ��� ��ġ�� ǥ�ô� �����Ѵ� ������ 2������
												// ���Ŵϱ�
					} else if (nowX == 5 && nowY == 0) {
						horse.x = 8;
						horse.y = 3;
						horse.dir = 2;
						horse.nowMap = 2;
						flag2[horse.y][horse.x] = 1;
						//flag1[nowY][nowX] = 0;// �̵��ϱ��� ��ġ�� ǥ�ô� �����Ѵ� ������ 2������
												// ���Ŵϱ�
					} else if(nowX == 0 && nowY == 0){
						flag2[0][4] = 1;
						horse.x = nowX;
						horse.y = nowY;
					}else {
						horse.x = nowX;
						horse.y = nowY;
					}
				} else {
					// ���� ���� 2������ ���°��
					flag2[startY][startX] = 0;
					flag2[nowY][nowX] = 1;
					
					if (startX  == 0 && startY  == 3) {
						flag1[5][0]=0;
					
					}else if (startX  == 4 && startY  == 6) {
						flag1[5][5]=0;
						
					}else if (startX  == 8 && startY  == 3) {
						flag1[0][5]=0;
						
					}else if (startX  == 4 && startY  == 0) {
						flag1[0][0]=0;
					}
					
					if (nowX  == 0 && nowY  == 3) {
						flag1[5][0]=1;
					
					}else if (nowX  == 4 && nowY  == 6) {
						flag1[5][5]=1;
						
					}else if (nowX  == 8 && nowY  == 3) {
						flag1[0][5]=1;
						
					}else if (nowX  == 4 && nowY  == 0) {
						flag1[0][0]=1;
					}

					horse.x = nowX;
					horse.y = nowY;
				}
				
				int newScore = 0;

				if (horse.nowMap == 1) {
					newScore += map1[horse.y][horse.x];
				} else {
					
					newScore += map2[horse.y][horse.x];
				}
				ret+=newScore;
			}

		}

		return ret;
	}
}
