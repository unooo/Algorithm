package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Samsung_17837 {

	
	public static int N;
	public static int K;
	public static int[][]graph ;	
	public static int[] dy = new int[]{0,0,-1,1};
	public static int[] dx = new int[]{1,-1,0,0};
	public static int ret = 0;
	public static  Horse[] hAry;
	public static LinkedList<Horse>[][] hMap;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		graph=new int[N][N];
		hMap= new LinkedList[N][N];
		hAry = new Horse[K];
	
		for(int i = 0 ; i < N ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++){
				graph[i][j]=Integer.parseInt(st.nextToken());//0ÀºÈò»ö,1Àº»¡°­,2´ÂÆÄ¶û
				hMap[i][j]= new LinkedList<Horse>();
			}
		}
		
		for(int i = 0 ; i <K ; i ++	){
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken())-1;
			Horse horse = new Horse(y,x,dir,i);
			hAry[i]=horse;
			
			hMap[y][x].add(horse);
		}
		int kIdx=0;
		int realRet=1;
		while(true){
			if(realRet>1000){
				System.out.println(-1);
				return;
			}
			
			int nowY=hAry[kIdx].y;
			int nowX=hAry[kIdx].x;
			int newY=dy[hAry[kIdx].direction]+nowY;
			int newX=dx[hAry[kIdx].direction]+nowX;
			
			if(newY<0||newX<0||newY>=N||newX>=N||graph[newY][newX]==2){
				dirChange(hAry[kIdx]);
				newY=dy[hAry[kIdx].direction]+hAry[kIdx].y;
				newX=dx[hAry[kIdx].direction]+hAry[kIdx].x;
							
				
			}
			
			if(newY<0||newX<0||newY>=N||newX>=N||graph[newY][newX]==2){
				ret++;
				kIdx=ret%(K);
				if(kIdx==0){
					realRet++;
				}
				continue;
			}
			
			
			int nowSIdx = 0;
			int nowEIdx=hMap[nowY][nowX].size();
			for(Horse tpHrs : hMap[nowY][nowX]){
				if(tpHrs.order==kIdx){
					break;
				}
			 	nowSIdx++;
			 }
			
			if(graph[newY][newX]==0){ //È­ÀÌÆ®ÀÎ°æ¿ì			
				LinkedList<Horse> tpHlist= new LinkedList(hMap[nowY][nowX].subList(nowSIdx, nowEIdx));
				hMap[newY][newX].addAll(tpHlist);
				hMap[nowY][nowX].subList(nowSIdx, nowEIdx).clear();
			}else if(graph[newY][newX]==1){
				LinkedList<Horse> tpHlist= new LinkedList(hMap[nowY][nowX].subList(nowSIdx, nowEIdx));
				ListIterator<Horse> iterator= tpHlist.listIterator(tpHlist.size());
				while(iterator.hasPrevious()){
					hMap[newY][newX].add(iterator.previous());
				}
				hMap[nowY][nowX].subList(nowSIdx, nowEIdx).clear();
			}
			
			for(Horse tpHrs : hMap[newY][newX]){
				tpHrs.y=newY;
				tpHrs.x=newX;
			}
		
			if(hMap[newY][newX].size()>=4){
				System.out.println(realRet);
				return;
			}
			
			ret++;
			kIdx=ret%(K);
			if(kIdx==0){
				realRet++;
			}
		}
		
	}
	
	
	
	
	public static void dirChange(Horse horse){
		
		switch(horse.direction){
		case 0 :
			horse.direction=1;
			break;
		case 1 :
			horse.direction=0;
			break;
		case 2 :
			horse.direction=3;
			break;
		case 3 :
			horse.direction=2;
			break;
		}
	}
	
	public static class Horse{
		
		int y;
		int x;
		int direction;
		int order;
		Horse(int y , int x, int direction,int order){	
			this.x=x;
			this.y=y;
			this.direction=direction;	
			this.order=order;
		}
	}
}
