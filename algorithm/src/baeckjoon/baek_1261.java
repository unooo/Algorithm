package baeckjoon;

import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek_1261 {

	static int dy[] = new int[]{-1,0,1,0};
	static int dx[] = new int[]{0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int map[][] = new int[N][M];
		
		for(int i = 0 ; i < N ; i++){
			String str = new String(br.readLine());
			for(int j = 0 ; j < M ; j++){
				map[i][j]=str.charAt(j)-'0';
			}
		}
		
		int visit[][] = new int[N][M];
		Queue<Node> que = new LinkedList<Node>();
		visit[0][0]=1;
		que.add(new Node(0,0));
		while(!que.isEmpty()){
			Node node = que.poll();
			for(int i = 0 ; i < 4 ; i++){
				int nextI= node.i+dy[i];
				int nextJ=node.j+dx[i];
				
				if(nextI<0||nextJ<0||nextI>=N||nextJ>=M)
					continue;
				
				if(visit[nextI][nextJ]==0){
					visit[nextI][nextJ]=visit[node.i][node.j]+map[nextI][nextJ];
					que.add(new Node(nextI,nextJ));
				}else{
					if(visit[nextI][nextJ]>visit[node.i][node.j]+map[nextI][nextJ]){
						visit[nextI][nextJ]=visit[node.i][node.j]+map[nextI][nextJ];
						que.add(new Node(nextI,nextJ));
					}else{
						continue;
					}
					
				}
			}
			
		}
		System.out.println(visit[N-1][M-1]-1);
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
