package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

public class Samsung_3190 {
	
	
	public static int[][] board ;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		board = new int[N+1][N+1];
		
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		
		for(int num = 0 ; num < K ; num ++){
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());;
			
			board[i][j] = 1;
		}
		
		Map<Integer,String> hmap = new HashMap<Integer,String>();
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		
		for(int num=0 ; num<L;num++){
			st = new StringTokenizer(br.readLine());
			hmap.put(Integer.parseInt(st.nextToken()),st.nextToken());
		}
		
		LinkedList<Integer> linkedListX = new LinkedList<Integer>();
		LinkedList<Integer> linkedListY = new LinkedList<Integer>();
		
		linkedListX.addFirst(1);
		linkedListY.addFirst(1);
		int time=0;
		
		int dx[] = new int[]{0,0,1,-1};
		int dy[] = new int[]{-1,1,0,0};
		
		int nowDir = 2;//¿ì¹æÇâ
		
		int dirGraph[][] = new int[4][2];
		dirGraph[0][0] =3;
		dirGraph[0][1] =2;
		dirGraph[1][0] = 2;
		dirGraph[1][1]	=3;
		dirGraph[2][0] =0;
		dirGraph[2][1] =1;
		dirGraph[3][0] =1;
		dirGraph[3][1] = 0;
		
		while(true){
			time++;
			
			
			linkedListX.addFirst(linkedListX.getFirst()+dx[nowDir]);
			linkedListY.addFirst(linkedListY.getFirst()+dy[nowDir]);
			
			int frontX =linkedListX.getFirst();
			int frontY = linkedListY.getFirst();
			
			if(frontX>N||frontY>N||frontX<1||frontY<1)
				break;
			
			for(int i = 1 ; i < linkedListY.size();i++){
				if(linkedListX.get(i)==frontX&&linkedListY.get(i)==frontY){
					System.out.println(time);
					return;
				}
			}
			
			
			if(board[frontY][frontX]!=1){
				
				linkedListX.removeLast();
				linkedListY.removeLast();
				
			}else{
				board[frontY][frontX]=0;
			}
			
			if(hmap.get(time) != null){
				String dir =hmap.get(time); 
				switch(dir){
				case "L" : nowDir = dirGraph[nowDir][0];
				break;
				case "D" : nowDir = dirGraph[nowDir][1];
				break;
				}
				
			}
		
			
			
		
			
			
		}
		
		
		System.out.println(time);
		
	}

}
