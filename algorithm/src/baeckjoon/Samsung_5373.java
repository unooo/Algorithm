package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_5373 {

	
	public static int testCaseNum;
	public static int n;
	public static String []orderAry;
	
	public static char[][] cube = new char[12][9];
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		
		testCaseNum=Integer.parseInt(st.nextToken());
		initCube();
		System.out.println();
		for(int i = 0 ; i < testCaseNum ; i ++){
			st = new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			orderAry= new String[n];
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j <n ; j ++){
				orderAry[j]=new String(st.nextToken());
				changeCube(orderAry[j]);
			}
			print();
		}
	}
	
	public static void initCube(){
		for(int i =  0 ; i < 9 ; i ++){
			for(int j = 0 ; j < 12 ; j ++){
				
				if(i>=0&&i<=3&&j>=3&&j<=5)
					cube[i][j]='o';
				
				if(i>=3&&i<=5&&j>=0&&j<=2)
					cube[i][j]='g';
				
				if(i>=3&&i<=5&&j>=3&&j<=5)
					cube[i][j]='y';
				
				if(i>=3&&i<=5&&j>=6&&j<=8)
					cube[i][j]='b';
				if(i>=6&&i<=8&&j>=3&&j<=5)
					cube[i][j]='r';
				if(i>=9&&i<=11&&j>=3&&j<=5)
					cube[i][j]='w';
				
				
				
			}
		}
	}
	public static void print(){
		for(int i = 11 ; i >=9 ; i--){
			for(int j = 0 ;  j < 3 ; j ++){
				System.out.print(cube[i][j]);
			}
			System.out.println();
		}
	}
	public static void changeCube(String order ){
		char temp1,temp2,temp3;
		switch(order){
			case "D+":
				temp1=cube[3][3];
				temp2=cube[4][3];
				temp3=cube[5][3];
				cube[3][3]=cube[5][3];
				cube[4][3]=cube[5][4];
				cube[5][3]=cube[5][5];
				
				cube[5][3]=cube[5][5];
				cube[5][4]=cube[4][5];
				cube[5][5]=cube[3][5];
				
				cube[5][5]=cube[3][5];
				cube[4][5]=cube[3][4];
				cube[3][5]=temp3;
				
				cube[3][5]=temp1;
				cube[3][4]=temp2;
				cube[3][3]=temp3;
				;
				break;
			case "D-":
				temp1=cube[3][3];
				temp2=cube[3][4];
				temp3=cube[3][5];
				cube[3][3]=cube[3][5];
				cube[3][4]=cube[4][5];
				cube[3][5]=cube[5][5];
				
				cube[3][5]=cube[5][5];
				cube[4][5]=cube[5][4];
				cube[5][5]=cube[5][3];
				
				cube[5][5]=cube[5][3];
				cube[5][4]=cube[4][3];
				cube[5][3]=temp1;
				
				cube[5][3]=temp1;
				cube[4][3]=temp2;
				cube[3][3]=temp3;
				
				;
				break;
			case "F+":
				temp1=cube[6][3];
				temp2=cube[7][3];
				temp3=cube[8][3];
				cube[6][3]=cube[8][3];
				cube[7][3]=cube[8][4];
				cube[8][3]=cube[8][5];
				
				cube[8][3]=cube[8][5];
				cube[8][4]=cube[8][5];
				cube[8][5]=cube[8][5];
				
				cube[8][5]=cube[6][5];
				cube[7][5]=cube[6][4];
				cube[6][5]=temp3;
				
				cube[6][5]=temp1;
				cube[6][4]=temp2;
				cube[6][3]=temp3;
				;
				break;
			case "F-":
				temp1=cube[6][3];
				temp2=cube[6][4];
				temp3=cube[6][5];
				cube[6][3]=cube[6][5];
				cube[6][4]=cube[7][5];
				cube[6][5]=cube[8][5];
				
				cube[6][5]=cube[8][5];
				cube[7][5]=cube[8][4];
				cube[8][5]=cube[8][3];
				
				cube[8][5]=cube[8][3];
				cube[8][4]=cube[7][3];
				cube[8][3]=temp1;
				
				cube[8][3]=temp1;
				cube[7][3]=temp2;
				cube[6][3]=temp3;
				
				;
				break;
			case "U+":
				temp1=cube[9][3];
				temp2=cube[10][3];
				temp3=cube[11][3];
				cube[9][3]=cube[11][3];
				cube[10][3]=cube[11][4];
				cube[11][3]=cube[11][5];
				
				cube[11][3]=cube[11][5];
				cube[11][4]=cube[11][5];
				cube[11][5]=cube[11][5];
				
				cube[11][5]=cube[9][5];
				cube[10][5]=cube[9][4];
				cube[9][5]=temp3;
				
				cube[9][5]=temp1;
				cube[9][4]=temp2;
				cube[9][3]=temp3;
				;
				break;
			case "U-":
				temp1=cube[9][3];
				temp2=cube[9][4];
				temp3=cube[9][5];
				cube[9][3]=cube[9][5];
				cube[9][4]=cube[10][5];
				cube[9][5]=cube[11][5];
				
				cube[9][5]=cube[11][5];
				cube[10][5]=cube[11][4];
				cube[11][5]=cube[11][3];
				
				cube[11][5]=cube[11][3];
				cube[11][4]=cube[10][3];
				cube[11][3]=temp1;
				
				cube[11][3]=temp1;
				cube[10][3]=temp2;
				cube[9][3]=temp3;
				
				;
				break;
			case "R+":
				temp1=cube[3][6];
				temp2=cube[4][6];
				temp3=cube[5][6];
				
				cube[3][6]=cube[5][6];
				cube[4][6]=cube[5][7];
				cube[5][6]=cube[5][8];
				
				cube[5][6]=cube[5][8];
				cube[5][7]=cube[4][8];
				cube[5][8]=cube[3][8];
				
				cube[5][8]=cube[3][8];
				cube[4][8]=cube[3][7];
				cube[3][8]=temp1;
				
				cube[3][8]=temp1;
				cube[3][7]=temp2;
				cube[3][6]=temp3;
				break;
			case "R-" :
				temp1=cube[5][6];
				temp2=cube[4][6];
				temp3=cube[3][6];
				
				cube[5][6]=cube[3][6];
				cube[4][6]=cube[3][7];
				cube[3][6]=cube[3][8];
				
				cube[3][6]=cube[3][8];
				cube[3][7]=cube[4][8];
				cube[3][8]=cube[5][8];
				
				cube[3][8]=cube[5][8];
				cube[4][8]=cube[5][7];
				cube[5][8]=temp1;
				
				cube[5][8]=temp1;
				cube[5][7]=temp2;
				cube[5][6]=temp3;
				;
				break;
				
			case "L+":
				temp1=cube[5][2];
				temp2=cube[4][2];
				temp3=cube[3][2];
				
				cube[5][2]=cube[3][2];
				cube[4][2]=cube[3][1];
				cube[3][2]=cube[3][0];
				
				cube[3][2]=cube[3][0];
				cube[3][1]=cube[4][0];
				cube[3][0]=cube[5][0];
				
				cube[3][0]=cube[5][0];
				cube[4][0]=cube[5][1];
				cube[5][0]=temp1;
				
				cube[5][0]=temp1;
				cube[5][1]=temp2;
				cube[5][2]=temp3;
				;
				break;
				
			case "L-":
				temp1=cube[3][2];
				temp2=cube[4][2];
				temp3=cube[5][2];
				
				cube[3][2]=cube[5][2];
				cube[4][2]=cube[5][1];
				cube[5][2]=cube[5][0];
				
				cube[5][2]=cube[5][0];
				cube[5][1]=cube[4][0];
				cube[5][0]=cube[3][0];
				
				cube[5][0]=cube[3][0];
				cube[4][0]=cube[3][1];
				cube[3][0]=temp1;
				
				cube[3][0]=temp1;
				cube[3][1]=temp2;
				cube[3][2]=temp3;
				;
				break;
				
			case "B+":
				temp1=cube[2][3];
				temp2=cube[2][4];
				temp3=cube[2][5];
				
				cube[2][3]=cube[2][5];
				cube[2][4]=cube[1][5];
				cube[2][5]=cube[0][5];
				
				cube[2][5]=cube[0][5];
				cube[1][5]=cube[0][4];
				cube[0][5]=cube[0][3];
				
				cube[0][5]=cube[0][3];
				cube[0][4]=cube[1][3];
				cube[0][3]=temp1;
				
				cube[0][3]=temp1;
				cube[1][3]=temp2;
				cube[2][3]=temp3;
				break;
			case "B-":
				temp1=cube[2][5];
				temp2=cube[2][4];
				temp3=cube[2][3];
				
				cube[2][5]=cube[2][3];
				cube[2][4]=cube[1][3];
				cube[2][3]=cube[0][3];
				
				cube[2][3]=cube[0][3];
				cube[1][3]=cube[0][4];
				cube[0][3]=cube[0][5];
				
				cube[0][3]=cube[0][5];
				cube[0][4]=cube[1][5];
				cube[0][5]=temp1;
				
				cube[0][5]=temp1;
				cube[1][5]=temp2;
				cube[2][5]=temp3;
				break;
				
		}
	}
}

