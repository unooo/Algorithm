package Samsung_2021;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소3 {
	
	static int N,M;
	static int map[][];
	static int path[];
	static int dy[] = {-1,0,1,0};
	static int dx[] = {0,1,0,-1};
	static LinkedList<Node> virusList=new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		path=new int[M];
		for(int i = 0 ; i < N ; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j = 0 ; j <N ; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2)
					virusList.add(new Node(i,j));
			}
		}
		dfs(0,virusList.size(),0);
		if(ret==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ret-1);
		
	}
	
	public static void solve() {
		int visit[][] = new int[N][N];
		Queue<Node> que = new LinkedList<Node>();
		
		for(int i = 0 ; i < M ; i++) {
			Node node= virusList.get(path[i]);
			visit[node.i][node.j]=1;
			que.add(node);
		}
		
		while(!que.isEmpty()) {
			Node node =que.poll();
			
			for(int dir=0;dir<4;dir++) {
				int nextI=node.i+dy[dir];
				int nextJ=node.j+dx[dir];
				
				if(nextI<0||nextJ<0||nextI>=N||nextJ>=N||map[nextI][nextJ]==1)
					continue;
				if(visit[nextI][nextJ]!=0)
					continue;
				
				visit[nextI][nextJ]=visit[node.i][node.j]+1;
				que.add(new Node(nextI,nextJ));
			}
			
		}
		int maxStep=1;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(visit[i][j]==0&&map[i][j]!=1) {
					return;
				}
				if(map[i][j]!=2)
				maxStep=Math.max(maxStep, visit[i][j]);
			}
		}
		ret=Math.min(ret, maxStep);
	}
	static int ret = Integer.MAX_VALUE;
	//조합
	public static void dfs(int next, int n ,int idx) {
		
		if(next>=M) {
			//todo
			solve();
			return;
		}
		
		for(int i = idx ; i <n ; i++) {
			path[next]=i;
			dfs(next+1,n,i+1);
		}
		
	}
	
	static class Node{
		int i , j ;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}

}
