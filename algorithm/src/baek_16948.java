import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek_16948 {

	static int dy[] = new int[]{-2,-2,0,0,2,2};
	static int dx[] = new int[]{-1,1,-2,2,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		int r1=Integer.parseInt(st.nextToken());
		int c1=Integer.parseInt(st.nextToken());
		int r2=Integer.parseInt(st.nextToken());
		int c2=Integer.parseInt(st.nextToken());
		if(r1==r2&&c1==c2){
			System.out.println(0);
			return;
		}
			
		Queue<Node> que =new LinkedList<>();
		int visit[][] = new int[N][N];
		visit[r1][c1]=1;
		que.add(new Node(r1,c1));
		while(!que.isEmpty()){
			Node node = que.poll();
			for(int dir=0;dir<6;dir++){
				int nextY=node.i+dy[dir];
				int nextX=node.j+dx[dir];
				if(nextY<0||nextX<0||nextY>=N||nextX>=N)
					continue;
				if(visit[nextY][nextX]!=0)
					continue;
				if(nextY==r2&&nextX==c2){
					System.out.println(visit[node.i][node.j]);
					return;
				}
				visit[nextY][nextX]=visit[node.i][node.j]+1;
				que.add(new Node(nextY,nextX));
			}
		}
	System.out.println(-1);	
	}
	
	static class Node{
		int i,j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}
}
