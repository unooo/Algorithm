package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Samsung_20056_2 {

	static int N;
	static int M;
	static int K;
	static int dy[] = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dx[] = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ArrayList<FireBall> fireBallList = new ArrayList<FireBall>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireBallList.add(new FireBall(y, x, m, s, d));
		}
		for (int i = 0; i < K; i++) {
			moveFireBall(fireBallList);
			fireBallList = solv(fireBallList);
		}
		int sum = 0;
		for (int i = 0; i < fireBallList.size(); i++) {
			sum += fireBallList.get(i).m;
		}

		System.out.println(sum);
	}

	static void moveFireBall(ArrayList<FireBall> fireBallList) {
		for (int i = 0; i < fireBallList.size(); i++) {
			FireBall fireBall = fireBallList.get(i);
			int nextY = fireBall.y + dy[fireBall.d] * (fireBall.s % N);
			int nextX = fireBall.x + dx[fireBall.d] * (fireBall.s % N);
			if (nextY >= N)
				nextY -= N;
			if (nextY < 0)
				nextY += N;

			if (nextX >= N)
				nextX -= N;
			if (nextX < 0)
				nextX += N;

			fireBall.y = nextY;
			fireBall.x = nextX;
		}
	}

	static ArrayList<FireBall> solv(ArrayList<FireBall> fireBallList) {
		ArrayList<FireBall> newFireBallList = new ArrayList<>();

		while (!fireBallList.isEmpty()) {
			FireBall fireBall = fireBallList.remove(0);
			int mSum = 0, sSum = 0, numSum = 0;
			mSum = fireBall.m;
			sSum = fireBall.s;
			int isEvenOrOdd = fireBall.d % 2;
			boolean dFlag = true;
			numSum = 1;
			int idx = 0;
			int size=fireBallList.size();
			for (int i = 0; i <size; i++) {
				if (fireBallList.get(idx).y == fireBall.y && fireBallList.get(idx).x == fireBall.x) {
					mSum += fireBallList.get(idx).m;
					sSum += fireBallList.get(idx).s;
					if (fireBallList.get(idx).d % 2 != isEvenOrOdd)
						dFlag = false;
					numSum++;
					fireBallList.remove(idx);
					idx--;
				}
				idx++;
			}
			if (numSum > 1) {
				for (int i = 0; i < 4; i++) {
					int newM = mSum / 5;
					int newS = sSum / numSum;
					int newD;
					if (dFlag == true) {
						newD = i * 2;
					} else {
						newD = i * 2 + 1;
					}

					if (newM != 0)
						newFireBallList.add(new FireBall(fireBall.y, fireBall.x, newM, newS, newD));
				}
			} else {
				newFireBallList.add(fireBall);
			}
		} 
		return newFireBallList;

	}

	static class FireBall {
		int y, x, m, s, d;

		// 질량, 속력, 방향
		public FireBall(int y, int x, int m, int s, int d) {
			super();
			this.y = y;
			this.x = x;
			this.m = m;
			this.s = s;
			this.d = d;
		}

	}
}
