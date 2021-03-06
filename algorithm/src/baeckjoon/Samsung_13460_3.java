package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_13460_3 {

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
		
		dfs(0);
		if(min==Integer.MAX_VALUE)
			System.out.println(-1);
		else
		System.out.println(min+1);
	}
	
	public static void dfs(int nowStep){
		if(nowStep>=10){
			
			return ;
		}
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
				if(map[i][j]==3){
					prevBlueX=j;
					prevBlueY=i;
				}
				
				if(map[i][j]==2){
					prevRedX=j;
					prevRedY=i;
				}
			}
		}
		
		
		for(int i = 0 ; i <4 ; i ++){
			int blueGoalIn=0;
			int redGoalIn=0;
			nextBlueY=prevBlueY;
			nextBlueX=prevBlueX;
			nextRedY=prevRedY;
			nextRedX=prevRedX;
			
			while(true){
				nextBlueY+=dy[i];
				nextBlueX+=dx[i];
				if(map[nextBlueY][nextBlueX]==1){
					nextBlueY-=dy[i];
					nextBlueX-=dx[i];
					break;
				}
				if(map[nextBlueY][nextBlueX]==4){//°ñÀÎ°æ¿ì
					blueGoalIn=1;
					break;
				}
			}
			while(true){
				nextRedY+=dy[i];
				nextRedX+=dx[i];
				if(map[nextRedY][nextRedX]==1){
					nextRedY-=dy[i];
					nextRedX-=dx[i];
					break;
				}
				if(map[nextRedY][nextRedX]==4){//°ñÀÎ°æ¿ì
					redGoalIn=1;
					break;
				}
			}
			
			if(blueGoalIn==1){
				//½ÇÆÐ
				continue;
			}else if(redGoalIn==1){
				//¼º°ø
				min=Math.min(min, nowStep);
				continue;
			}
			
			if(nextRedY==nextBlueY&&nextRedX==nextBlueX){
				if(i==0){//»ó					
					if(prevRedY>prevBlueY){
						nextRedY-=dy[i];
					}else{
						nextBlueY-=dy[i];
					}					
				}else if(i==1){//ÇÏ
					if(prevRedY>prevBlueY){
						nextBlueY-=dy[i];
					}else{
						nextRedY-=dy[i];
					}					
				}else if(i==2){//ÁÂ
					if(prevRedX>prevBlueX){
						nextRedX-=dx[i];
					}else{
						nextBlueX-=dx[i];
					}
				}else if(i==3){//¿ì
					if(prevRedX>prevBlueX){
						nextBlueX-=dx[i];
					}else{
						nextRedX-=dx[i];
					}
				}
			}
			
			map[prevRedY][prevRedX]=0;
			map[prevBlueY][prevBlueX]=0;
			map[nextRedY][nextRedX]=2;
			map[nextBlueY][nextBlueX]=3;		
			
			dfs(nowStep+1);
			
			map[prevRedY][prevRedX]=2;
			map[prevBlueY][prevBlueX]=3;
			map[nextRedY][nextRedX]=0;
			map[nextBlueY][nextBlueX]=0;	
				
		}
		
		
	}
}
