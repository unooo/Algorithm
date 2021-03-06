package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_13460 {
	public static String[] graph;
	public static int[][] graphR;
	public static int[][] graphB;
	public static int ret = -1;
	
	public static int[] dx = new int[]{0,1,0,-1};
	public static int[] dy = new int[]{1,0,-1,0};
	

	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int temp =Integer.parseInt(st.nextToken());
		graph= new String[temp];
		graphR = new int[temp][];
		graphB= new int[temp][];
		
		
		for(int i = 0 ; i < graph.length ; i ++){
			String temp2 = new String(br.readLine());
			graph[i]= new String(temp2);
			graphR[i]= new int[temp2.length()];
			graphB[i]= new int[temp2.length()];
	
		}
		
		int bx = 0,by = 0,rx = 0,ry = 0,ox = 0,oy = 0;
		
		for(int i = 0 ; i < graph.length ; i ++){
			for(int j = 0 ; j < graph[i].length() ; j ++){
				switch(graph[i].charAt(j)){
				case 'B':
					bx=j;
					by=i;
					break;
				case 'R':
					rx=j;
					ry=i;
					break;
				case 'O':
					ox=j;
					oy=i;
					break;
				case '#':
					graphR[i][j]=-1;
					graphB[i][j]=-1;
					break;
					
				}
			}
		}
		
		
		bfs(bx, by, rx, ry, ox, oy);
		
		if(ret>10)
			ret=-1;
		System.out.println(ret);
	}
	
	public static void bfs(int bx,int by,int rx,int ry,int ox,int oy){
		Queue<Integer> queueBx = new LinkedList<Integer>();
		Queue<Integer> queueBy = new LinkedList<Integer>();
		Queue<Integer> queueRx = new LinkedList<Integer>();
		Queue<Integer> queueRy = new LinkedList<Integer>();
		
		queueBx.add(bx);
		queueBy.add(by);
		queueRx.add(rx);
		queueRy.add(ry);	
		
		graphR[ry][rx]=0;
		graphB[by][bx]=0;
		
		
		
		while(!queueRx.isEmpty()){
			int nowRx = queueRx.poll();
			int nowRy=queueRy.poll();
			int nowBx =queueBx.poll();
			int nowBy=queueBy.poll();
			
		
			
			for(int i = 0 ; i <dx.length ; i ++){
				int newRx =nowRx;
				int newRy =nowRy;
				int newBx = nowBx;
				int newBy = nowBy;
				int redOut=0;
				int blueOut=0;
				int rInum=0;
				int bInum=0;
				
				while(graph[newRy+dy[i]].charAt(newRx+dx[i])!='#'){
					newRx+=dx[i];
					newRy+=dy[i];
					if(newRx==ox&&newRy==oy){
						redOut=1;
					}
					rInum++;
						
				}
				
				while(graph[newBy+dy[i]].charAt(newBx+dx[i])!='#'){
					newBx+=dx[i];
					newBy+=dy[i];
					if(newBx==ox&&newBy==oy){
						blueOut=1;
					}
					bInum++;
				}				
				
				if(blueOut==1){
					continue;
				}else if(redOut==1){
					//찾던결과이므로값 리턴
					ret=graphR[nowRy][nowRx]+1;
					return;
				}
				
				if(newRx==newBx&&newRy==newBy){
					if(rInum>bInum){
						newRx-=dx[i];
						newRy-=dy[i];
					}else{
						newBx-=dx[i];
						newBy-=dy[i];
					}
				}
				
				if(nowRx==newRx&&nowRy==newRy&&nowBx==newBx&&nowBy==newBy){
					continue;
				}
				
				//if(graphR[newRy][newRx]==0){
					queueBx.add(newBx);
					queueBy.add(newBy);
					queueRx.add(newRx);
					queueRy.add(newRy);
				
							
						graphR[newRy][newRx]=graphR[nowRy][nowRx]+1;
						graphB[newBy][newBx]=graphB[nowBy][nowBx]+1;
					
				//}
				
			}
			
		}
		
	}
	
	
}
