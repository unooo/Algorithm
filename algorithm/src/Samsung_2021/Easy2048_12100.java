package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Easy2048_12100 {

	static int N;
	static int orgMap[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		orgMap=new int[N][N];
		for (int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				orgMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		System.out.println(ret);
	}

	// dfs 구현
	// 중복순열
	static int[] path = new int[5];

	public static void dfs(int next) {
		if (next >= 5) {
			// todo
			solve();
			return;
		}
		for (int i = 0; i < 4; i++) {
			path[next] = i;
			dfs(next + 1);
		}

	}
	static int ret = Integer.MIN_VALUE;
	
	static int[][] map;
	public static void solve(){
		map=new int[N][N];
		for(int i = 0 ; i < N ; i ++)
			for(int j = 0 ; j < N ;j++)
				map[i][j]=orgMap[i][j];
		for(int i = 0 ; i < 5 ; i++){
			int dir=path[i];
			for(int turn = 0 ; turn <dir;turn++){
				turnRight();
			}
			
			move();
			sum();
			
			for(int turn=0;turn<4-dir;turn++)
				turnRight();
				
		}
		
		for(int i = 0 ; i < N ; i++){
			for(int j = 0 ; j < N ;j++)
				ret=Math.max(ret, map[i][j]);
		}
	}

	// 오른쪽으로 쭉 밀기 구현 -- 탬플릿으로 써도됨
	public static void move() {
		for (int i = 0; i < N; i++) {
			for (int j = N - 2; j >= 0; j--) {
				if (map[i][j] != 0) {
					int nextI = i;
					int nextJ = j;
					while (true) {
						nextJ += 1;
						if (nextI<0||nextJ<0||nextI>=N||nextJ>=N||map[nextI][nextJ] != 0) {
							nextJ -= 1;
							break;
						}
					}
					if(nextI==i&&nextJ==j)// 매우중요!
						continue;
					map[nextI][nextJ] = map[i][j];
					map[i][j] = 0;
				}
			}
		}

	}

	// 밀고난 후 합성 및 합성 후 한칸식 당기기 루프 구현
	public static void sum() {
		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j >= 1; j--) {
				if (map[i][j] != 0 && map[i][j] == map[i][j - 1]) {
					map[i][j] *= 2;
					for (int k = j - 1; k >= 1; k--) {
						map[i][k] = map[i][k - 1];
					}
					map[i][0] = 0;
				}
			}
		}
	}

	// 오른쪽으로 회전 구현
	public static void turnRight() {
		int[][] newMap = new int[N][N];
		int idx = 0;
		for (int j = 0; j < N; j++) {
			for (int i = N - 1; i >= 0; i--) {
				newMap[idx/N][idx%N]=map[i][j];
				idx++;
			}			
		}
		map=newMap;
	}

}
