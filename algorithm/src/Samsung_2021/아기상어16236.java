package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어16236 {

	static int N;
	static int map[][];
	static int visit[][];
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		Node fish = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9)
					fish = new Node(i, j, 2,0,0);
			}
		}

		visit = new int[N][N];
		Queue<Node> que = new LinkedList<Node>();
		que.add(fish);
		visit[fish.r][fish.c] = 1;
		map[fish.r][fish.c] = 0;

		while (!que.isEmpty()) {
			LinkedList<Node> candidate = new LinkedList<>();
			int size=que.size();
			for (int tun = 0; tun < size; tun++) {
				
				Node node = que.poll();
				for (int i = 0; i < 4; i++) {
					int nextI = node.r + dy[i];
					int nextJ = node.c + dx[i];

					if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N)
						continue;
					if (visit[nextI][nextJ] != 0)
						continue;
					if(map[nextI][nextJ]>node.size)
						continue;
					
					if(map[nextI][nextJ]==node.size||map[nextI][nextJ]==0){
						visit[nextI][nextJ]=1;
						que.add(new Node(nextI,nextJ,node.size,node.time+1,node.eatNum));
						continue;
					}
					
					if(map[nextI][nextJ]<node.size){
						visit[nextI][nextJ]=1;
						candidate.add(new Node(nextI,nextJ,node.size,node.time+1,node.eatNum+1));
					}

				}
			}
			if(candidate.size()!=0){
				Collections.sort(candidate);
				visit=new int[N][N];
				Node node = candidate.getFirst();
				map[node.r][node.c]=0;
				que.clear();
				que.add(node);
				visit[node.r][node.c]=1;
				ret=node.time;
				if(node.eatNum==node.size){
					node.size++;
					node.eatNum=0;
				}
				
				
			}

		}
		
		System.out.println(ret);

	}
	static int ret=0;
	static class Node implements Comparable<Node> {
		int r, c, size,time, eatNum;

		public Node(int r, int c, int size, int time, int eatNum) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.time = time;
			this.eatNum = eatNum;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if(this.r>o.r){
				return 1;
			}else if(this.r==o.r){
				return this.c-o.c;
			}else{
				return -1;
			}
		}

	}

}
