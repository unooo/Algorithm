package Kakao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_Ä«Ä«¿ÀÇÁ·»ÁîÄÃ·¯¸µºÏ {
	
	public static void main(String[] args){
		int m = 6;
		int n = 4;
		int[][] picture={{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		System.out.println(solution(m, n, picture).toString());
	}
	
	static int[] dy = new int[]{-1,0,1,0};
	static int[] dx = new int[]{0,1,0,-1};
	 public static int[] solution(int m, int n, int[][] picture) {
	        int numberOfArea = 0;
	        int maxSizeOfOneArea = 0;
	        int visit[][] = new int[m][n];
	        int max=0;
	        int idx=0;
	        for(int i = 0 ; i < m ; i ++){
	        	for(int j = 0 ; j < n ; j++){
	        		if(visit[i][j]==0&&picture[i][j]!=0){
	        			idx++;
	        	        Queue<Node> queue = new LinkedList<Node>();	        	        
	        	        queue.add(new Node(i,j));
	        	        visit[i][j]=1;
	        	        int temp=0;
	        	        while(!queue.isEmpty()){
	        	        	temp++;
	        	        	Node node = queue.poll();
	        	        	for(int dir = 0 ; dir < 4 ; dir ++){
	        	        		int nextY = node.i+dy[dir];
	        	        		int nextX = node.j+dx[dir];
	        	        		
	        	        		if(nextY<0||nextX<0||nextY>=m||nextX>=n)
	        	        			continue;
	        	        		if(visit[nextY][nextX]!=0)
	        	        			continue;
	        	        		if(picture[nextY][nextX]==picture[i][j]){
	        	        			visit[nextY][nextX]=idx;
	        	        			queue.add(new Node(nextY,nextX));
	        	        		}
	        	        		
	        	        	}
	        	        }
	        	        max=Math.max(max, temp);
	        		}
	        	}
	        }	        
	       
	        int[] answer = new int[2];
	        answer[0] = idx;
	        answer[1] = max;//map.fi;
	        return answer;
	    }
	 static class Node{	        
	        int i , j;

			public Node(int i, int j) {
				super();
				this.i = i;
				this.j = j;
			}
	        
	        
	 }
}
