import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek_16933 {
	static int N,M,K,map[][];
	static int dy[]={-1,0,1,0,0};
	static int dx[]={0,1,0,-1,0};
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
		visit[K][0 ][0]=1;
		que.add(new Node(0,0,K,1,0));
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
					ret=Math.min(ret, node.step);
				}
				
				if(map[nextI][nextJ]==1){
					if(node.nowK>0&&node.day%2==0){
						visit[node.nowK-1][nextI][nextJ]=1;
						que.add(new Node(nextI,nextJ,node.nowK-1,node.step+1,node.day+1));
					}
					
				}else{
					visit[node.nowK][nextI][nextJ]=1;
					que.add(new Node(nextI,nextJ,node.nowK,node.step+1,node.day+1));
				}
			}	
			if(node.day%2==1)
			que.add(new Node(node.i,node.j,node.nowK,node.step+1,node.day+1));
			
		} 
		if(ret==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ret+1);
		
	}
	
	static class Node{
		int i , j,nowK,step,day;

		public Node(int i, int j, int nowK, int step, int day) {
			super();
			this.i = i;
			this.j = j;
			this.nowK = nowK;
			this.step = step;
			this.day = day;
		}
	}

}
