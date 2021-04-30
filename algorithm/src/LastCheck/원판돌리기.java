package LastCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 원판돌리기 {

	static int N, M, T;
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int tun = 0; tun < T; tun++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			spin(x,d,k);
			if(!remove()){
				float avg=getAvg();
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if(map[i][j]==0)
							continue;
						if(map[i][j]<avg)
							map[i][j]++;
						else if(map[i][j]>avg)
							map[i][j]--;
					}
				}
			}

		}
		
		
		System.out.println(getSum());

	}
	public static float getAvg(){
		float sum=0;
		float cnt=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==0)
					continue;
				sum+=map[i][j];
				cnt++;
			}
		}
		return sum/cnt;
	}
	
	public static int getSum(){
		int sum=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sum+=map[i][j];
			}
		}
		return sum;
	}

	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	public static boolean remove() {
		int newMap[][] = new int[N][M];
		boolean flag=false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j]=map[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==0)
					continue;
				for (int dir = 0; dir < 4; dir++) {
					int nextI = (i + dy[dir]);
					int nextJ = (j + dx[dir]) % M;
					nextJ = nextJ < 0 ? nextJ + M : nextJ;
					if(nextI<0||nextI>=N)
						continue;
					if(map[i][j]==map[nextI][nextJ]){
						newMap[i][j]=0;
						newMap[nextI][nextJ]=0;
						flag=true;
					}
				}
			}
		}
		map=newMap;
		return flag;
	}

	public static void spin(int x, int d, int k) {

		for (int i = 0; i < N; i++) {
			if ((i + 1) % x != 0)
				continue;
			for (int tun = 0; tun < k; tun++) {
				if (d == 0) {
					int temp = map[i][M - 1];
					for (int j = M - 1; j > 0; j--) {
						map[i][j] = map[i][j - 1];
					}
					map[i][0] = temp;
				} else {
					int temp = map[i][0];
					for (int j = 0; j < M - 1; j++) {
						map[i][j] = map[i][j + 1];
					}
					map[i][M - 1] = temp;
				}
			}
		}

	}
}
