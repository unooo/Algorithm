package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 미세먼지안녕17144 {

	static int R, C, T;
	static int map[][];
	static LinkedList<Node> cleaner = new LinkedList<>();
	static int dy[]={-1,0,1,0};
	static int dx[] = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					cleaner.add(new Node(i, j));
				}
			}
		}
		int step = 0;
		while (true) {
			if (step >= T)
				break;

			int newMap[][] = new int[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[i][j]==-1)
						continue;
					int tpCount=0;
					for(int dir=0;dir<4;dir++){
						int nextI=i+dy[dir];
						int nextJ=j+dx[dir];
						if(nextI<0||nextJ<0||nextI>=R||nextJ>=C||map[nextI][nextJ]==-1)
							continue;
						tpCount++;
						newMap[nextI][nextJ]+=map[i][j]/5;
					}
					newMap[i][j]+=map[i][j]-(map[i][j]/5)*tpCount;
					
				}
			}
			map=newMap;
			
			// 위
			Node up=cleaner.getFirst();
			Node down=cleaner.getLast();
			for(int i =up.i;i>=1;i-- ){
				map[i][0]=map[i-1][0];
			}
			for(int j=0;j<=C-2;j++){
				map[0][j]=map[0][j+1];
			}
			for(int i=0;i<=up.i-1;i++){
				map[i][C-1]=map[i+1][C-1];
			}
			for(int j=C-1; j>=1;j--){
				map[up.i][j]=map[up.i][j-1];
			}
			map[up.i][up.j]=-1;
			map[up.i][up.j+1]=0;
			
			//아래
			for(int i = down.i;i<=R-2;i++)
				map[i][0]=map[i+1][0];
			
			for(int j=0;j<=C-2;j++)
				map[R-1][j]=map[R-1][j+1];
			
			for(int i = R-1 ; i>=down.i+1;i--){
				map[i][C-1]=map[i-1][C-1];
			}
			for( int j = C-1;j>=1;j--)
				map[down.i][j]=map[down.i][j-1];
			map[down.i][down.j]=-1;
			map[down.i][1]=0;
			
			
			step++;
		}
		int ret=0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]==-1)
					continue;
				ret+=map[i][j];
			}
		}
		System.out.println(ret);
		
	}

	static class Node {
		int i, j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

}
