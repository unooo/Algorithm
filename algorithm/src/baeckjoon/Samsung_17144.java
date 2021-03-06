package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_17144 {
	
	public static int R; //���ǰ���
	public static int C; //���ǰ���
	public static int T; //�۵��ð���
	public static int orgGraph[][];
	public static int newGraph[][];
	public static int air1Y=-1;
	public static int air1X=-1;
	public static int air2Y=-1;
	public static int air2X=-1;
	public static int dy[]=new int[]{-1,0,1,0};//�ð����:��,��,��,��
	public static int dx[]=new int[]{0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C= Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		
		orgGraph=new int[R][C];
		newGraph=new int[R][C];
		
		for(int i = 0 ; i < R ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j <C ; j ++){
				orgGraph[i][j]=Integer.parseInt(st.nextToken());
				if(orgGraph[i][j]==-1){
					if(air1Y==-1){
						air1Y=i;
						air1X=j;
					}else{
						air2Y=i;
						air2X=j;
					}
				}
			}
		}
		
		for(int i = 0 ; i < T ; i ++){
			spread();
			spin(air1Y,air1X,0);
			spin(air2Y,air2X,1);
			orgGraph=newGraph;
			newGraph=new int[R][C];
		}
		
		int sum=0;
		for(int i = 0 ; i < R ; i ++){
			for(int j = 0 ; j <C ; j ++){
				if(orgGraph[i][j]!=-1)
					sum+=orgGraph[i][j];
			}
		}
	
		System.out.println(sum);
	}
	
	//�̼����� Ȯ�� �Լ�
	public static void spread(){
		
		for(int i = 0 ; i < R ; i ++){
			
			for(int j = 0 ; j <C ; j ++){
				if(orgGraph[i][j]<=0){
					continue;
				}
				int divideNum=orgGraph[i][j]/5;
				int stack=0;
				for(int k = 0 ; k < 4 ; k ++){
					int newY= i+dy[k];
					int newX=j+dx[k];
					
					if(newY<0||newX<0||newY>=R||newX>=C)
						continue;
					if(orgGraph[newY][newX]==-1)
						continue;
					stack++;
					newGraph[newY][newX]+=divideNum;
					
				}
				newGraph[i][j]+=orgGraph[i][j]-(divideNum*stack);
				
			}
		}		
	}
	
	public static void spin(int startY , int startX , int dir){
		
		int index=0;
		if(dir==1){
			index=2;
		}
		int nowY=startY;
		int nowX=startX;
		int firstNum=newGraph[startY][startX];
		for(int i = 0 ; i < 4 ; i++ ){
			
			if(dir==1){//�ݽð������//������
				if(i!=0){
					index=(index+3)%4;
			
				}
			}else{
				index=i;
			}
			
			
			while(true){
				
				int newY=nowY+dy[index];
				int newX=nowX+dx[index];
				if(newY<0||newX<0||newY>=R||newX>=C){
					break;
				}
				if(dir==0&&newY>startY){
					break;
				}
				if(dir==1&&newY<startY)
					break;
				newGraph[nowY][nowX]=newGraph[newY][newX];
				nowY=newY;
				nowX=newX;
			}
			
			
			
		}
		
		
		newGraph[startY][startX+1]=0;
		newGraph[startY][startX]=-1;
	}

}
