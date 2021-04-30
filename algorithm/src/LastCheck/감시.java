package LastCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 감시 {

	static int N, M;
	static int map[][];
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };
	static LinkedList<Node> cctvList = new LinkedList<>();
	static int ret=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5)
					cctvList.add(new Node(i, j, map[i][j]));
			}
		}
		ret=N*M-cctvList.size();
		path = new int[cctvList.size()];
		dfs(0, cctvList.size());
		System.out.println(ret);
	}

	// 중복순열

	public static int path[];
	
	public static void dfs(int next, int r) {
		if (next >= r) {
			// todo
			int visit[][] = new int[N][M];
			for (int tun = 0; tun < path.length; tun++) {
				Node node =cctvList.get(tun);
				int nodeDir=path[tun];
				visit[node.r][node.c]=1;
				
				for(int dir=0;dir<4;dir++){
					if(node.category==1&&dir!=0)
						continue;
					if(node.category==2&&(dir==1||dir==3))
						continue;
					if(node.category==3&&(dir==1||dir==2))
						continue;
					if(node.category==4&&dir==1)
						continue;
					
					int targetDir= (nodeDir+dir)%4;
					int nextI=node.r;
					int nextJ=node.c;
					while(true){
						nextI+=dy[targetDir];
						nextJ+=dx[targetDir];
						if(nextI<0||nextJ<0||nextI>=N||nextJ>=M)
							break;
						if(map[nextI][nextJ]==6){
							visit[nextI][nextJ]=1;
							break;
						}
						visit[nextI][nextJ]=1;						
					}
				}
			}
			int temp=0;
			for(int i = 0 ; i < N ; i++)
				for(int j = 0 ; j < M ;j++){
					if(map[i][j]!=6&&visit[i][j]==0)
						temp++;
				}
			ret=Math.min(ret, temp);
			return;
		}
		for (int i = 0; i < 4; i++) {
			path[next] = i;
			dfs(next + 1, r);
		}
	}

	static class Node {
		int r, c, category;

		public Node(int r, int c, int category) {
			super();
			this.r = r;
			this.c = c;
			this.category = category;
		}

	}

}
