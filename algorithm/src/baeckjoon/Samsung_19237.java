package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_19237 {

	public static int dy[] = new int[] { -1, 1, 0, 0 };
	public static int dx[] = new int[] { 0, 0, -1, 1 };
	public static int map[][][];
	public static Shark sharkList[];
	public static int sharkNum;
	public static int step = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		sharkNum=M;
		map=new int[N][N][2];
		sharkList=new Shark[M];
		for(int i = 0 ; i < N ; i ++){
			st=new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++){
				int skNum=Integer.parseInt(st.nextToken());
				if(skNum!=0){
					Shark shark = new Shark();
					shark.y=i;
					shark.x=j;
					sharkList[skNum-1]=shark;
					map[i][j][0]=skNum;
					map[i][j][1]=K;
				}
			}
		}
		st=new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i++){
			sharkList[i].nowDir=Integer.parseInt(st.nextToken())-1;
		}
		
		for(int i = 0 ; i < M ; i ++){
			for(int j = 0 ; j < 4 ; j ++){
				st=new StringTokenizer(br.readLine());
				for(int k = 0 ; k < 4 ; k ++){
					sharkList[i].dirInfo[j][k]=Integer.parseInt(st.nextToken())-1;
				}
			}
			
		}
		
		
		while(true){
			
			
			
			if(sharkNum==1){
				System.out.println(step);
				return;
			}
			
			if(step>=1000){
				System.out.println(-1);
				return;
			}

			step++;
			
			
			//����̵�
			
			for(int i = 0 ; i < M ; i ++){//����ȣ��� �̵���Ŵ
				if(sharkList[i]==null)//�̹� ������
					continue;
				
				int emptyFlag=0;
				for(int j=0;j<4;j++){ //��ĭ������

					int nextY=sharkList[i].y+dy[sharkList[i].dirInfo[sharkList[i].nowDir][j]];
					int nextX=sharkList[i].x+dx[sharkList[i].dirInfo[sharkList[i].nowDir][j]];
					
					if(nextY<0||nextX<0||nextY>=N||nextX>=N){
						continue;
					}
					
					if(map[nextY][nextX][0]!=0){
						continue;
					}
					
					sharkList[i].y=nextY;
					sharkList[i].x=nextX;
				
					sharkList[i].nowDir=sharkList[i].dirInfo[sharkList[i].nowDir][j];
					emptyFlag=1;
					break;
					
				}
				
				if(emptyFlag==1)
					continue;
				if(sharkList[i]==null)//�̹̻�����
					continue;
				
				for(int j=0;j<4;j++){//��ĭ������ ������Ⳳ��ĭ����
					
					int nextY=sharkList[i].y+dy[sharkList[i].dirInfo[sharkList[i].nowDir][j]];					
					int nextX=sharkList[i].x+dx[sharkList[i].dirInfo[sharkList[i].nowDir][j]];
					if(nextY<0||nextX<0||nextY>=N||nextX>=N){
						continue;
					}

					
					if(map[nextY][nextX][0]==i+1){
						sharkList[i].y=nextY;
						sharkList[i].x=nextX;
						sharkList[i].nowDir=sharkList[i].dirInfo[sharkList[i].nowDir][j];
						break;
					}
				}
				
								
				
				
			}
			
			int[][] tempMap=new int[N][N];
			for(int i = 0 ; i < sharkList.length ; i ++){ //ĭ �ߺ���� ���̱�
				if(sharkList[i]==null)
					continue;
				int targetY=sharkList[i].y;
				int targetX=sharkList[i].x;
				
				if(tempMap[targetY][targetX]!=0){//�̹� �����Ǿ�����
					sharkList[i]=null;
					sharkNum--;
					continue;
				}
				tempMap[targetY][targetX]=1;
			}
			
			//�� ���̱�
			for(int i = 0 ; i < N ; i ++){
				for(int j = 0 ; j < N ; j++){
					if(map[i][j][0]!=0){					
						map[i][j][1]--;
						if(map[i][j][1]==0)
							map[i][j][0]=0;
					}
				}
			}
		
			for(int i = 0 ; i < sharkList.length ; i ++){ //�� �ʿ� ���
				if(sharkList[i]==null)
					continue;
				int targetY=sharkList[i].y;
				int targetX=sharkList[i].x;
				map[targetY][targetX][0]=i+1;
				map[targetY][targetX][1]=K;
			}
			
			
				
				
		}
		
		
		
	}
	
	
	static class Shark{
		int nowDir;
		int dirInfo[][]=new int[4][4];
		int y;
		int x;
	}
}
