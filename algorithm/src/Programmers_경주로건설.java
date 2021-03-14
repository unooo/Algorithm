import java.util.LinkedList;
import java.util.Queue;

public class Programmers_경주로건설 {
	
	public static void main(String[] args){
		int[][] board=new int[][]{{0,0,0},{0,0,0},{0,0,0}};
		System.out.println(solution(board));
		
	}

	static int dy[] = new int[]{-1,0,1,0};
	static int dx[] = {0,1,0,-1};
	 public static int solution(int[][] board) {
	        int answer = Integer.MAX_VALUE;
	        int N=board.length;
	        int visit[][]=new int[N][N];
	        Queue<Node> que = new LinkedList<Node>();
	        visit[0][0]=1;
	        
	        if(board[0][1]!=1){
	        	que.add(new Node(0,1,1));
	        	  visit[0][1]=100;
	        }
	        if(board[1][0]!=1){
	        	que.add(new Node(1,0,2));
	        	visit[1][0]=100;
	        }
	      
	        
	        while(!que.isEmpty()){
	        	
	        	Node node = que.poll();
	        	for(int i = 0 ; i < 4 ; i++){
	        		//int nextDir=(node.dir+i)%4;
	        		int nextDir=i;
	        		int nextI=node.i+dy[nextDir];
	        		int nextJ=node.j+dx[nextDir];
	        		
	        		if(nextI<0||nextJ<0||nextI>=N||nextJ>=N)
	        			continue;
	        		if(board[nextI][nextJ]==1)
	        			continue;
	        		if(nextI==0&&nextJ==0)
	        			continue;
	        		
	        		int addCost=0;
	        		
	        		if(nextDir==node.dir){
	        			addCost=100;
	        		}else{
	        			addCost=600;
	        		}
	        		
	        		if(visit[nextI][nextJ]==0){	        			
	        			visit[nextI][nextJ]=visit[node.i][node.j]+addCost;	        			
	        			que.add(new Node(nextI,nextJ,nextDir));	        			
	        		}else
	        			if(visit[nextI][nextJ]>=visit[node.i][node.j]+addCost){
	        				visit[nextI][nextJ]=visit[node.i][node.j]+addCost;	        			
		        			que.add(new Node(nextI,nextJ,nextDir));   
	        			}
	        		
	        		
	        	}
	        	
	        }
	        
	        return visit[N-1][N-1];
	 }
	 static class Node{
		 int i , j,dir;
		public Node(int i, int j, int dir) {
			super();
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
	 }
}
