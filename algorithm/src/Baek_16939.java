

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_16939 {
	static int map[][] = new int[6][8];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i =0;i<6;i++){
			for(int j = 2 ; j <=3 ; j ++){
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 2 ; i <=3; i ++ ){
			for(int j = 0 ; j <=1 ; j ++){
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 2 ; i <=3 ; i ++){
			for(int j =4 ; j <=5 ; j ++){
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 2 ; i <=3 ; i ++){
			for(int j =6 ; j <=7 ; j ++){
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int newMap[][] = new int[6][8];
		mapCopy(newMap);
		int temp1=newMap[0][2];
		int temp2=newMap[1][2];
		for(int i = 0 ; i <=3 ; i ++){
			newMap[i][2]=newMap[i+2][2];
		}
		newMap[4][2]=newMap[3][7];
		newMap[5][2]=newMap[2][7];
		newMap[3][7]=temp1;
		newMap[2][7]=temp2;
		boolean flag=check(newMap);
		if(flag==true){
			System.out.println(1);
			return;
		}
		
		newMap = new int[6][8];
		mapCopy(newMap);
		 temp1=newMap[5][2];
		 temp2=newMap[4][2];
		for(int i = 5 ; i >=2 ; i --){
			newMap[i][2]=newMap[i-2][2];
		}
		newMap[0][2]=newMap[3][7];
		newMap[1][2]=newMap[2][7];
		newMap[3][7]=temp1;
		newMap[2][7]=temp2;
		
		 flag=check(newMap);
		if(flag==true){
			System.out.println(1);
			return;
		}
		newMap = new int[6][8];
		mapCopy(newMap);
		 temp1=newMap[2][7];
		 temp2=newMap[2][6];
		for(int j = 7 ; j>=2 ; j --){
			newMap[2][j]=newMap[2][j-2];
		}
		newMap[2][1]=temp1;
		newMap[2][0]=temp2;
		 flag=check(newMap);
		if(flag==true){
			System.out.println(1);
			return;
		}
		newMap = new int[6][8];
		mapCopy(newMap);
		 temp1=newMap[2][0];
		 temp2=newMap[2][1];
		for(int j = 0 ; j<=5 ; j ++){
			newMap[2][j]=newMap[2][j+2];
		}
		newMap[2][6]=temp1;
		newMap[2][7]=temp2;
		 flag=check(newMap);
		if(flag==true){
			System.out.println(1);
			return;
		}
		
		System.out.println(0);
	}
	
	public static void mapCopy(int [][] newMap){
		
		for(int i = 0 ; i < 6 ; i ++){
			for(int j = 0 ; j< 8 ; j ++){
				newMap[i][j]=map[i][j];
			}
		}
	}
	public static boolean check(int[][] newMap){
		boolean ret = true;
		for(int r = 0 ; r <=5 ; r+=2){
			int pivot=newMap[r][2];
			for(int i = r ; i <=r+1 ; i ++){
				for(int j = 2 ; j <=3 ; j ++){
					if(pivot!=newMap[i][j])
						return false;
				}
			}
		}
		for(int c = 0 ; c <= 7 ; c+=2){
			int pivot=newMap[2][c];
			for(int i = 2 ; i <=3 ; i ++){
				for(int j = c ; j <=c+1 ; j ++){
					if(pivot!=newMap[i][j])
						return false;
				}
			}
		}
		
		return ret;
	}
}
