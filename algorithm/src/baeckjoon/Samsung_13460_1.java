package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_13460_1 {
	
	static int[] dy={-1,1,0,0};
	static int[] dx={0,0,-1,1};
	static int[][] map ; //1º®0ÀººóÄ­ 2´Â»¡°£°ø, 3ÀºÆÄ¶õ°ø, 4°¡ Ãâ±¸
	static int N;
	static int M;
	static int min=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0 ; i < N ; i ++){
			
			StringBuffer str = new StringBuffer(br.readLine());
			for(int j = 0 ; j < M  ; j ++){
				char ch = str.charAt(j);
				switch(ch){
				case '#' :
					map[i][j]=1;
					break;
				case '.' :
					map[i][j]=0;
					break;
				case 'B':
					map[i][j]=3;
				break;
				case 'R':
					map[i][j]=2;
					break;
				case 'O':
					map[i][j]=4;
					break;
				}
			}
		}
		int[] dirAry=new int[10];
		dfs(0,dirAry);
		if(min==Integer.MAX_VALUE)
			System.out.println(-1);
		else
		System.out.println(min+1);
	}
	
	public static void copyMap(int[][] newMap){
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < M ; j++){
				newMap[i][j]=map[i][j];
			}
		}
	}
	
	public static void calc(int[] dirAry, int[][] newMap){
		int prevBlueX=-1;
		int prevBlueY=-1;
		int nextBlueX=-1;
		int nextBlueY=-1;		
		int prevRedX=-1;
		int prevRedY=-1;
		int nextRedX=-1;
		int nextRedY=-1;
		
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < M ; j++){
				if(newMap[i][j]==3){
					prevBlueX=j;
					prevBlueY=i;
				}
				
				if(newMap[i][j]==2){
					prevRedX=j;
					prevRedY=i;
				}
			}
		}
		nextBlueY=prevBlueY;
		nextBlueX=prevBlueX;
		nextRedY=prevRedY;
		nextRedX=prevRedX;
		
		for(int k = 0 ; k < dirAry.length ; k ++){	
			
			int blueGoalIn=0;
			int redGoalIn=0;
			prevBlueY=nextBlueY;
			prevBlueX=nextBlueX;
			prevRedY=nextRedY;
			prevRedX=nextRedX;
			
			while(true){
				nextBlueY+=dy[dirAry[k]];
				nextBlueX+=dx[dirAry[k]];
				if(newMap[nextBlueY][nextBlueX]==1){
					nextBlueY-=dy[dirAry[k]];
					nextBlueX-=dx[dirAry[k]];
					break;
				}
				if(newMap[nextBlueY][nextBlueX]==4){//°ñÀÎ°æ¿ì
					blueGoalIn=1;
					break;
				}
			}
			while(true){
				nextRedY+=dy[dirAry[k]];
				nextRedX+=dx[dirAry[k]];
				if(newMap[nextRedY][nextRedX]==1){
					nextRedY-=dy[dirAry[k]];
					nextRedX-=dx[dirAry[k]];
					break;
				}
				if(newMap[nextRedY][nextRedX]==4){//°ñÀÎ°æ¿ì
					redGoalIn=1;
					break;
				}
			}
			
			if(blueGoalIn==1){
				return;
			}else if(redGoalIn==1){
				min=Math.min(min, k);
				return;
			}
			
			if(nextRedY==nextBlueY&&nextRedX==nextBlueX){
				if(dirAry[k]==0){//»ó					
					if(prevRedY>prevBlueY){
						nextRedY-=dy[dirAry[k]];
					}else{
						nextBlueY-=dy[dirAry[k]];
					}					
				}else if(dirAry[k]==1){//ÇÏ
					if(prevRedY>prevBlueY){
						nextBlueY-=dy[dirAry[k]];
					}else{
						nextRedY-=dy[dirAry[k]];
					}					
				}else if(dirAry[k]==2){//ÁÂ
					if(prevRedX>prevBlueX){
						nextRedX-=dx[dirAry[k]];
					}else{
						nextBlueX-=dx[dirAry[k]];
					}
				}else if(dirAry[k]==3){//¿ì
					if(prevRedX>prevBlueX){
						nextBlueX-=dx[dirAry[k]];
					}else{
						nextRedX-=dx[dirAry[k]];
					}
				}
			}
			newMap[prevRedY][prevRedX]=0;
			newMap[prevBlueY][prevBlueX]=0;
			newMap[nextRedY][nextRedX]=2;
			newMap[nextBlueY][nextBlueX]=3;	

			
		}		
		
	}
	
	public static void dfs(int nowStep, int[] dirAry){
		
		if(nowStep>=10){
			//todo
			int[][] newMap=new int[N][M];
			copyMap(newMap);
			//dirAry=new int[]{2,1,3,1,3,1,2};
			calc(dirAry, newMap);
			return;
		}
		
		for(int i = 0 ; i < 4;i++){
			dirAry[nowStep]=i;
			dfs(nowStep+1,dirAry);
		}
		
	}
}
