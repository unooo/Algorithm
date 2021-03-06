package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SamsungEx_17135 {

	public static int N;
	public static int M;
	public static int D;
	public static int graph[][];
	public static int ret = Integer.MIN_VALUE;
	public static LinkedList<Enemy> enemyList = new LinkedList<Enemy>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		graph=new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 1) {
					enemyList.add(new Enemy(i, j, 0, 0));
				}
			}
		}
		int[] order=new int[3];
		dfs(0,order,0);
		System.out.println(ret);

	}

	public static int calc(int[] order,LinkedList<Enemy> enemyList) {
		int ret = 0; //
		while (true) {
			int endFlg=0;
			if(enemyList.size()==0)
				break;
			for (int i = 0; i < order.length; i++) {
				int archerY = N;
				int archerX = order[i];// i라고하는 실수 주의!

				for (Enemy enemy : enemyList) {
					int dis = distance(archerY, archerX, enemy.y, enemy.x);
					enemy.distance = dis;
				}

				Collections.sort(enemyList);
				if (enemyList.getFirst().distance <= D) {
					enemyList.getFirst().delFlg = 1;
				}

			}

			Iterator iterator = enemyList.iterator();
			while (iterator.hasNext()) {
				Enemy enemy = (Enemy) iterator.next();

				if (enemy.delFlg == 1){
					iterator.remove();
					ret++;
				}
			}
			iterator = enemyList.iterator();
			if(enemyList.size()==0){
				break;
			}
		
			while (iterator.hasNext()) {
				Enemy enemy = (Enemy) iterator.next();
				enemy.y+=1;
				if(enemy.y>=N){
					iterator.remove();
				}
			}
			/*
			if(endFlg==1){
				break;
			}
			*/
		}

		return ret;
	}

	public static int distance(int r1, int c1, int r2, int c2) {
		int ret = 0;

		ret = Math.abs(r1 - r2) + Math.abs(c1 - c2);

		return ret;
	}
	
	public static LinkedList<Enemy> cloneEnemyLst(LinkedList<Enemy> orgList){
		LinkedList<Enemy> retLst = new LinkedList<Enemy>();
		
		for(Enemy orgEnemy:enemyList){
		
			retLst.add(new Enemy(orgEnemy.y,orgEnemy.x,orgEnemy.distance,orgEnemy.delFlg));
		}	
		
		
		return retLst;
	}

	public static void dfs(int now, int[] order, int idx) {
		if (now >= 3) {
			// 시뮬을 진행
			//깊은복사해주기;
			ret=Math.max(ret, calc(order,cloneEnemyLst(enemyList)));
			return;
		}

		for (int i = idx; i < M; i++) {
			order[now] = i;
			dfs(now + 1, order, i + 1);
		}

	}

	static class Enemy implements Comparable<Enemy> {
		int y;
		int x;
		int distance;
		int delFlg;

		public Enemy(int y, int x, int distance, int delFlg) {
			super();
			this.y = y;
			this.x = x;
			this.distance = distance;
			this.delFlg = delFlg;
		}

		@Override
		public int compareTo(Enemy o) {
			if (this.distance > o.distance) {
				return 1;
			} else if (this.distance < o.distance) {
				return -1;
			} else {
				if (this.x > o.x) {
					return 1;
				} else if (this.x < o.x) {
					return -1;
				}
				return 0;
			}
		}

	}
}
