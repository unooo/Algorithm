package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동16234 {
	
	static int N,L,R,map[][];
	static int dy[] = {-1,0,1,0};
	static int dx[] = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		
		for(int i = 0 ; i < N ; i ++){
			st=new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++){
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		while(true){
			int visit[][] = new int[N][N];
			int pivot=0;
			HashMap<Integer,Integer> hmapSum= new HashMap<>();
			HashMap<Integer,Integer> hmapCount= new HashMap<>();
			for(int i = 0 ; i < N ; i ++){
				for(int j = 0 ; j < N ; j ++){
					if(visit[i][j]==0){
						int tempSum=map[i][j];						
						pivot++;
						visit[i][j]=pivot;
						Queue<Node> que = new LinkedList<Node>();
						que.add(new Node(i,j));
						int ct=0;
						while(!que.isEmpty()){
							ct++;
							Node node = que.poll();
							for(int dir = 0 ; dir< 4 ; dir++){
								int nextI=node.r+dy[dir];
								int nextJ=node.c+dx[dir];
								if(nextI<0||nextJ<0||nextI>=N||nextJ>=N)
									continue;
								if(visit[nextI][nextJ]!=0)
									continue;
								int diff=map[nextI][nextJ]-map[node.r][node.c];
								diff=Math.abs(diff);
								if(diff>=L&&diff<=R){
									tempSum+=map[nextI][nextJ];
									visit[nextI][nextJ]=pivot;
									que.add(new Node(nextI,nextJ));
								}
							}
						}
						if(ct>1){
						hmapSum.put(pivot, tempSum);
						hmapCount.put(pivot, ct);
						}
						
					}
				}
			}
			if(pivot==N*N)
				break;
			
			for(int i = 0 ; i < N ; i++){
				for(int j = 0 ; j < N ; j++){
					int pv=visit[i][j];
					Integer val=hmapSum.get(pv);
					if(val==null)
						continue;
					map[i][j]=val/hmapCount.get(pv);
					
				}
			}
			
		ret++;
		}
		System.out.println(ret);
	}
	static int ret=0;
	static class Node{
		int r,c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
}
