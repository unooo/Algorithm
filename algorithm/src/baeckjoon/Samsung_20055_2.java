package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_20055_2 {

	static int N,K;
	static int[] boxAry;
	static int[] robotAry;
	static int step=0;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		boxAry= new int[2*N];
		robotAry=new int[2*N];
		st =  new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 2*N ; i++){			
			boxAry[i]=Integer.parseInt(st.nextToken());
		}
		while(true){
			step++;
			rotateBelt();
			checkEndPoint();
			moveRobot();
			checkStartPoint();
			boolean endFlag=checkGameEnd();
			if(endFlag==true)
				break;
			
		}
		
		System.out.println(step);
	}
	
	public static void rotateBelt(){
	
		int temp=boxAry[2*N-1];
		for(int i =2*N-1 ; i>0 ; i--){
			boxAry[i]=boxAry[i-1];
		}		
		boxAry[0]=temp;
		
		for(int i = N-2 ; i >= 0 ; i--){			
				if(robotAry[i]==1){
					robotAry[i+1]=1;
					robotAry[i]=0;					
				}
		}	
		
	}
	
	public static void moveRobot(){
		for(int i = N-2 ; i >= 0 ; i--){
			if(robotAry[i]==1){
				if(boxAry[i+1]>0&&robotAry[i+1]==0){
					robotAry[i+1]=1;
					robotAry[i]=0;
					boxAry[i+1]--;
				}
			}
		}		
		
	}
	
	public static void checkStartPoint(){
		if(robotAry[0]==0&&boxAry[0]>0){
			robotAry[0]=1;
			boxAry[0]--;
		}
	}
	
	public static boolean checkGameEnd(){
		boolean res= false;
		
		int zeroNum=0;
		for(int i = 0 ; i < 2*N ; i ++)
			if(boxAry[i]==0)
				zeroNum++;
		if(zeroNum>=K)
			res=true;
		return res;
	}
	
	public static void checkEndPoint(){
		if(robotAry[N-1]==1)
			robotAry[N-1]=0;
	}
	
	
	
}


