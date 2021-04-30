package LastCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 청소년상어 {

	static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dx[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static Node[][] map = new Node[4][4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int idx = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				map[i][j] = new Node(idx, dir, false);
			}
		}
		
		Node eated=map[0][0];
		Node shark = new Node(-1, eated.dir, true);
		map[0][0]=shark;
		dfs(map,eated.idx);
		System.out.println(ret);
	}
static int ret= Integer.MIN_VALUE;
	public static void dfs(Node[][] nowMap, int sum) {

		// 물고기 이동
		move(nowMap);

		// 상어이동(먹잇감체크)
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Node node = nowMap[i][j];
				
				if (node==null||!node.isShark)
					continue;
				for (int k = 1; k <= 3; k++) {
					int nextI = i + (dy[(node.dir)])*k;
					int nextJ = j + (dx[(node.dir)])*k;
					if (nextI < 0 || nextJ < 0 || nextI >= 4 || nextJ >= 4 || nowMap[nextI][nextJ]==null){
						ret=Math.max(ret, sum);
						continue;
					}
					//맵카피
					Node[][] newMap=mapCopy(nowMap);
					//상어이동
					Node eated=newMap[nextI][nextJ];
					Node shark=newMap[i][j];
					shark.dir=eated.dir;
					newMap[nextI][nextJ]=shark;
					newMap[i][j]=null;
					
					
					dfs(newMap,sum+eated.idx);
				}
			}
		}
	}
	public static Node[][] mapCopy(Node[][] nowMap){
		Node[][] newMap = new Node[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Node node = nowMap[i][j];
				if(node==null)
					continue;
				newMap[i][j]=new Node(node.idx,node.dir,node.isShark);
			}
		}
		return newMap;
	}

	public static void move(Node[][] nowMap) {
		Outer:
		for (int idx = 1; idx <= 16; idx++) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					Node node = nowMap[i][j];
					if (node==null||node.idx != idx || node.isShark)
						continue;
					for (int dir = 0; dir < 8; dir++) {
						int nextI = i + dy[(node.dir + dir) % 8];
						int nextJ = j + dx[(node.dir + dir) % 8];
						if (nextI < 0 || nextJ < 0 || nextI >= 4 || nextJ >= 4 )
							continue;
						if(nowMap[nextI][nextJ]!=null&& nowMap[nextI][nextJ].isShark)
							continue;
						node.dir=(node.dir + dir) % 8;
						Node temp = nowMap[nextI][nextJ];
						nowMap[nextI][nextJ] = node;
						nowMap[i][j] = temp;
						continue Outer;
					}
				}
			}
		}
	}

	static class Node {
		int idx, dir;
		boolean isShark;

		public Node(int idx, int dir, boolean isShark) {
			super();
			this.idx = idx;
			this.dir = dir;
			this.isShark = isShark;
		}

	}
}
