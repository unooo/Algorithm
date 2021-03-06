package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_19236_3 {
	
	static int dy[]={-1,-1,0,1,1,1,0,-1};
	static int dx[]={0,-1,-1,-1,0,1,1,1};
	static int max= Integer.MIN_VALUE;
	public static int[][] map = new int[4][4];
	public static Fish[] fishInfo = new Fish[16];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0 ; i < 4 ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j  = 0 ;  j < 4 ; j ++){
				int fishNum=Integer.parseInt(st.nextToken());
				int fishDir=Integer.parseInt(st.nextToken())-1;
				Fish fish = new Fish();
				fish.y=i;fish.x=j;fish.dir=fishDir;
				fishInfo[fishNum-1]=fish;
				map[i][j]=fishNum;
			}
		}
		int[][] nowMap=copyMap(map);
		Fish[] nowFishInfo=copyFishInfo(fishInfo);
		int eatedFishNum=nowMap[0][0];
		int nowSharkDir=nowFishInfo[eatedFishNum-1].dir;
		nowFishInfo[eatedFishNum-1]=null;		
		nowMap[0][0]=-1;		
		moveFish(nowMap,nowFishInfo);
		
		dfs(nowMap,nowFishInfo,eatedFishNum,nowSharkDir);
		System.out.println(max);
	}
	
	static void dfs(int[][] nowMap, Fish[] nowFishInfo, int score, int sharkDir){
		
		//기저사건
		int prevSharkY=-1,prevSharkX=-1,nextSharkY=-1,nextSharkX=-1,nextSharkDir=-1;
		
		loop:
		for(int i = 0 ; i < 4 ; i ++){
			for(int j = 0 ; j < 4 ; j ++){
				if(nowMap[i][j]==-1){
					prevSharkY=i;
					prevSharkX=j;
					break loop;
				}
			}
		}		
		
		
		int inumer=1;
		while(true){
			nextSharkY=prevSharkY+dy[sharkDir]*inumer;
			nextSharkX=prevSharkX+dx[sharkDir]*inumer;
			
			if(nextSharkX<0||nextSharkY<0||nextSharkX>=4||nextSharkY>=4){//이게기저사건
				max=Math.max(max, score);
				break;
			}
			
			if(nowMap[nextSharkY][nextSharkX]==0){
				inumer++;
				continue;
			}
			int[][] nextMap=copyMap(nowMap);
			Fish[] nextFishInfo=copyFishInfo(nowFishInfo);
			
			int eatedFishNum=nowMap[nextSharkY][nextSharkX];			
			nextSharkDir=nowFishInfo[eatedFishNum-1].dir;			
			
			nextFishInfo[eatedFishNum-1]=null;
			nextMap[nextSharkY][nextSharkX]=-1;	
			nextMap[prevSharkY][prevSharkX]=0;		
			moveFish(nextMap,nextFishInfo);
			dfs(nextMap,nextFishInfo,score+eatedFishNum,nextSharkDir);
			inumer++;
		}
		
	}
	

	static void moveFish(int [][] nowMap,Fish[] nowFishInfo){
		
		for(int i = 0 ; i < nowFishInfo.length ; i ++){
			if(nowFishInfo[i]==null)
				continue;
			Fish fish = nowFishInfo[i]	;
			int prevY=fish.y;
			int prevX=fish.x;
			int prevDir=fish.dir;
			int nextY=-1,nextX=-1,nextDir=-1;
			for(int j = 0 ; j < 8 ; j ++){
				nextDir=(prevDir+j)%8;
				nextY=prevY+dy[nextDir];
				nextX=prevX+dx[nextDir];
				if(nextY<0||nextX<0||nextY>=4||nextX>=4)
					continue;
				if(nowMap[nextY][nextX]==-1)
					continue;
				break;
			}
			fish.dir=nextDir;
			if(nowMap[nextY][nextX]==0||nowMap[nextY][nextX]==-1){
			
				nowFishInfo[nowMap[prevY][prevX]-1].y=nextY;
				nowFishInfo[nowMap[prevY][prevX]-1].x=nextX;
				nowMap[nextY][nextX]=nowMap[prevY][prevX];
				nowMap[prevY][prevX]=0;
				
			}else{
				int tempX;
				int a=nowMap[nextY][nextX]-1;
				int b=nowMap[prevY][prevX]-1;
				tempX=nowFishInfo[a].x;
				nowFishInfo[a].x=nowFishInfo[b].x;
				nowFishInfo[b].x=tempX;
				
				int tempY;
				tempY=nowFishInfo[a].y;
				nowFishInfo[a].y=nowFishInfo[b].y;
				nowFishInfo[b].y=tempY;
				
					int temp;
				temp=nowMap[nextY][nextX];
				nowMap[nextY][nextX]=nowMap[prevY][prevX];
				nowMap[prevY][prevX]=temp;
				
			}
		}		
		
	}
	public static int[][] copyMap(int[][] org){
		int[][] ret = new int[4][4];
		for(int i = 0 ; i < 4 ; i ++){
			for(int j = 0 ; j < 4 ; j++){
				ret[i][j]=org[i][j];
			}
		}
		return ret;
	}
	
	public static Fish[] copyFishInfo(Fish[] org){
		Fish[] ret = new Fish[16];
		for(int i = 0 ; i < 16 ; i ++){
			if(org[i]==null)
				continue;
			Fish fish=new Fish();
			fish.y=org[i].y;
			fish.x=org[i].x;
			fish.dir=org[i].dir;
			ret[i]=fish;
		}
		return ret;
	}
	static class Fish {
		int y;
		int x;
		int dir;
		
		
	}

}
