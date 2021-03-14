import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class beak_14442 {
	static int N,M,K,map[][];
	static int dy[]={-1,0,1,0};
	static int dx[]={0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		for(int i = 0 ; i < N ; i++){
			String str= br.readLine();
			for(int j = 0 ; j < M ; j++){
				map[i][j]=str.charAt(j)-'0';
			}
		}
		if(N==1&&M==1){
			System.out.println(1);
			return;
		}
			
		Queue<Node> que = new LinkedList<Node>();
		int visit[][][] = new int[K+1][N][M];
		visit[K][0][0]=1;
		que.add(new Node(0,0,K));
		int ret = Integer.MAX_VALUE;
		while(!que.isEmpty()){
			Node node = que.poll();
			for(int i = 0 ; i < 4 ; i++){
				int nextI=node.i+dy[i];
				int nextJ=node.j+dx[i];
				if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) {
					continue;
				}
				if(visit[node.nowK][nextI][nextJ]!=0)
					continue;				
				if(nextI==N-1&&nextJ==M-1){
					ret=Math.min(ret, visit[node.nowK][node.i][node.j]);
				}
				
				if(map[nextI][nextJ]==1){
					if(node.nowK>0){
						visit[node.nowK-1][nextI][nextJ]=visit[node.nowK][node.i][node.j]+1;
						que.add(new Node(nextI,nextJ,node.nowK-1));
					}
					
				}else{
					visit[node.nowK][nextI][nextJ]=visit[node.nowK][node.i][node.j]+1;
					que.add(new Node(nextI,nextJ,node.nowK));
				}
			}
			
		} 
		if(ret==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ret+1);
		
	}
	
	static class Node{
		int i , j,nowK;

		public Node(int i, int j, int nowK) {
			super();
			this.i = i;
			this.j = j;
			this.nowK = nowK;
		}

	}

}
