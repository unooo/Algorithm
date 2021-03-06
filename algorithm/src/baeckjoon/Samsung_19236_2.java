package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import baeckjoon.Samsung_19236.Fish;

public class Samsung_19236_2 {
	
	static int dy[]={-1,-1,0,1,1,1,0,-1};
	static int dx[]={0,-1,-1,-1,0,1,1,1};
	static int max= Integer.MIN_VALUE;
	public static int[][] map = new int[4][4];
	public static Fish[] fishInfo = new Fish[17];
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
				fishInfo[fishNum]=fish;
				map[i][j]=fishNum;
			}
		}
		
	}
	
	
	static class Fish{
		int y;
		int x;
		int dir;
	}
}
