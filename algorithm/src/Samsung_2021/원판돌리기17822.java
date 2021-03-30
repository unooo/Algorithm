package Samsung_2021;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 원판돌리기17822 {

	static int N, M, T;
	static int map[][];
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map=new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			for (int piv = 1; piv <= N; piv++) {
				if (piv % x != 0) {
					continue;
				}
				for (int rot = 0; rot < k; rot++) {
					if (d == 0) {// 시계방향
						int temp = map[piv - 1][M - 1];
						for (int j = M - 1; j >= 1; j--) {
							map[piv - 1][j] = map[piv - 1][j - 1];
						}
						map[piv - 1][0] = temp;
					} else {// 반시계방향
						int temp = map[piv - 1][0];
						for (int j = 0; j < M - 1; j++) {
							map[piv - 1][j] = map[piv - 1][j + 1];
						}
						map[piv - 1][M - 1] = temp;
					}
				}

			}
			int newMap[][]= new int[N][M];
			boolean masterFlag=true;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					boolean flag = false;
					for (int dir = 0; dir < 4; dir++) {
						int nextI = r + dy[dir];
						int nextJ = (c + dx[dir]) % M;
						if(nextJ==-1)
							nextJ=M-1;
						if (nextI < 0 || nextI >= N)
							continue;

						if (map[r][c]!=0&&map[nextI][nextJ] == map[r][c]) {
							newMap[nextI][nextJ] = 1;
							flag = true;
							masterFlag=false;
						}
					}
					if (flag == true)
						map[r][c] = 0;

				}
			}
			if(masterFlag) {
				float sum=0;
				float mom=0;
				for(int r = 0 ; r< N ; r++) {
					for(int c = 0 ; c < M ; c++) {
						if(map[r][c]!=0) {
							mom++;
							sum+=map[r][c];
						}						
					}
				}
				float avg=sum/mom;
				for(int r = 0 ; r< N ; r++) {
					for(int c = 0 ; c < M ; c++) {
						if(map[r][c]!=0) {
							if(map[r][c]<avg)
								map[r][c]++;
							else if(map[r][c]>avg)
								map[r][c]--;
						}						
					}
				}
			}else {
				for(int r = 0 ; r< N ; r++) {
					for(int c = 0 ; c < M ; c++) {
						if(newMap[r][c]==1) {
							map[r][c]=0;
						}						
					}
				}
			}

		}
		int sum=0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				sum+=map[i][j];
			}
		}
		System.out.println(sum);
	}
}
