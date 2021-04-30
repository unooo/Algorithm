package LastCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기2 {
	static int N, M, R, map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int order = Integer.parseInt(st.nextToken());
			switch (order) {
			case 1:
				method1();
				break;
			case 2:
				method2();
				break;
			case 3:
				method3();
				break;
			case 4:
				method4();
				break;
			case 5:
				method5();
				break;
			case 6:
				method6();
				break;
			}
		}

		for (int i = 0; i < map.length; i++) {

			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void method1() {
		int N = map.length;
		int M = map[0].length;
		int newMap[][] = new int[N][M];
		int idx = 0;
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				newMap[idx / M][idx % M] = map[i][j];
				idx++;
			}
		}
		map = newMap;
	}

	public static void method2() {
		int N = map.length;
		int M = map[0].length;
		int newMap[][] = new int[N][M];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = M - 1; j >= 0; j--) {
				newMap[idx / M][idx % M] = map[i][j];
				idx++;
			}
		}
		map = newMap;
	}

	public static void method3() {
		int N = map.length;
		int M = map[0].length;
		int newMap[][] = new int[M][N];
		int idx = 0;
		for (int j = 0; j < M; j++) {
			for (int i = N - 1; i >= 0; i--) {
				newMap[idx / N][idx % N] = map[i][j];
				idx++;
			}
		}
		map = newMap;
	}

	public static void method4() {
		int N = map.length;
		int M = map[0].length;
		int newMap[][] = new int[M][N];
		int idx = 0;
		for (int j = M - 1; j >= 0; j--) {
			for (int i = 0; i < N; i++) {
				newMap[idx / N][idx % N] = map[i][j];
				idx++;
			}
		}
		map = newMap;
	}

	public static void method5() {
		int N = map.length;
		int M = map[0].length;
		int r[]={0,0,N/2,N/2};
		int c[]={0,M/2,M/2,0};
		int newMap[][] = new int[N][M];
		for(int i = 0 ; i < 4 ; i++){
			move(newMap,r[i],c[i],r[(i+1)%4],c[(i+1)%4]);
		}
		map=newMap;
	}

	public static void move(int newMap[][],int r1, int c1, int r2, int c2) {
		int N = map.length;
		int M = map[0].length;
		
		for (int i = 0; i <  N / 2; i++) {
			for (int j = 0; j <  M / 2; j++) {
				newMap[r2+i][c2+j]=map[r1+i][c1+j];
			}
		}
		
	}
	
	public static void method6() {
		int N = map.length;
		int M = map[0].length;
		int r[]={0,N/2,N/2,0};
		int c[]={0,0,M/2,M/2};
		int newMap[][] = new int[N][M];
		for(int i = 0 ; i < 4 ; i++){
			move(newMap,r[i],c[i],r[(i+1)%4],c[(i+1)%4]);
		}
		map=newMap;
	}


}
