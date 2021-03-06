package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SamsungEx_17070 {
	
	public static int N;
	public static int[][] map;
	public static int ret=0;
	
	public static int dy[]=new int[]{0,1,1};//0은가로,1은세로,2는대각
	public static int dx[]=new int[]{1,0,1};//0은가로,1은세로,2는대각
	//또 엑스와이 잘못 헷갈린 실수함.
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		
		
		for(int i = 0 ; i < N ; i ++){
			st= new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++){
				map[i][j]= Integer.parseInt(st.nextToken());
				
			}
		}
		
		Pipe pipe = new Pipe(0,0,0,1,0);
		dfs(pipe);
		System.out.println(ret);
		
	}
	
	public static void dfs(Pipe pipe){
		
		if(pipe.rearY==N-1&&pipe.rearX==N-1){
			ret+=1;
		}
		
		
		
		for(int i = 0 ; i < 3 ; i ++){
			
			if(pipe.ctg==0&&i==1)
				continue;
			if(pipe.ctg==1&&i==0)
				continue;
			
			int orgHeadY=pipe.headY;
			
			int orgHeadX=pipe.headX;
			int orgRearY=pipe.rearY;
			int orgRearX=pipe.rearX;
			int orgI=pipe.ctg;
			
			int newHeadY=pipe.rearY;
			int newHeadX=pipe.rearX;
			
			int newRearY=newHeadY+dy[i];
			int newRearX=newHeadX+dx[i];
			
			
			if(newRearY<0||newRearX<0||newRearY>=N||newRearX>=N)
				continue;
			if(map[newRearY][newRearX]==1)
				continue;
			
			if(i==2){
				if(map[newRearY-1][newRearX]==1||map[newRearY][newRearX-1]==1)
					continue;
			}
		
			
			pipe.ctg=i;
			pipe.headY=newHeadY;
			pipe.headX=newHeadX;
			pipe.rearY=newRearY;
			pipe.rearX=newRearX;
			
			dfs(pipe); //#new pipe 해서 넣으면 메모리스택오버플로우남
			
			pipe.ctg=orgI;
			pipe.headY=orgHeadY;
			pipe.headX=orgHeadX;
			pipe.rearY=orgRearY;
			pipe.rearX=orgRearX;
			//#뒤에 값 원상복귀안함
			
		}
		
		
	}
	
	static class Pipe{
		int headY;
		int headX;
		int rearY;
		int rearX;
		int ctg;
		public Pipe(int headY, int headX, int rearY, int rearX, int ctg) {
			super();
			this.headY = headY;
			this.headX = headX;
			this.rearY = rearY;
			this.rearX = rearX;
			this.ctg = ctg; //0은 가로, 1은 세로 2는 대각
		}
		
	}

}
