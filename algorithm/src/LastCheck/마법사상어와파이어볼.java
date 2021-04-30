package LastCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 마법사상어와파이어볼 {

	 static int N,M,K;
	 static ArrayList<Shark>[][] map;
	 static int dy[] = {-1,-1,0,1,1,1,0,-1};
	 static int dx[] = {0,1,1,1,0,-1,-1,-1};
	 
	 public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map=new ArrayList[N][N];
		for(int i = 0 ; i < N ; i++)
			for(int j = 0  ; j < N ; j++)
				map[i][j]=new ArrayList<>();
		
		for(int i = 0 ; i < M ;i++){
			st = new StringTokenizer(br.readLine());
			int r= Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			int m=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			Shark shark = new Shark(m, s, d);
		
			map[r][c].add(shark);
		}
		
		for(int tun = 0 ; tun < K ; tun++){
			
			ArrayList<Shark>[][] newMap=new ArrayList[N][N];
			for(int i = 0 ; i < N ; i++)
				for(int j = 0  ; j < N ; j++)
					newMap[i][j]=new ArrayList<>();
			
			for(int i = 0 ; i < N ; i++){
				for(int j = 0 ; j < N ; j++){
					if(map[i][j].isEmpty())
						continue;
					for(int idx=0;idx<map[i][j].size();idx++){
						Shark shark= map[i][j].get(idx);
						int nextI= (i+dy[shark.d]*shark.s)%N;
						nextI = (nextI<0?nextI+N:nextI);						
						int nextJ=(j+dx[shark.d]*shark.s)%N;
						nextJ= (nextJ<0?nextJ+N:nextJ);
						newMap[nextI][nextJ].add(shark);						
					}
				}
			}
			map=newMap;
			newMap=new ArrayList[N][N];
			for(int i = 0 ; i < N ; i++)
				for(int j = 0  ; j < N ; j++)
					newMap[i][j]=new ArrayList<>();
			
			for(int i = 0 ; i < N ; i++){
				for(int j = 0 ; j < N ; j++){
					if(map[i][j].size()<2){
						newMap[i][j]=map[i][j];
						continue;
					}
					boolean evenFlag=false;
					boolean oddFlag=false;
					int totalM=0,totalS=0,totalNum=map[i][j].size();
					for(int idx=0;idx<map[i][j].size();idx++){
						Shark shark=map[i][j].get(idx);
						totalM+=shark.m;
						totalS+=shark.s;
						if(shark.d%2==0)
							evenFlag=true;
						else
							oddFlag=true;
					}
					int newM=totalM/5;
					int newS=totalS/totalNum;					
					if(newM<=0)
						continue;
					
					for(int dir=0;dir<8;dir++){
						if(evenFlag!=oddFlag){
							if(dir%2==1)
								continue;
						}else{
							if(dir%2==0)
								continue;
						}
//						int nextI=i+dy[dir];
//						nextI = (nextI<0?nextI+N:nextI);	
//						int nextJ=j+dx[dir];
//						nextJ= (nextJ<0?nextJ+N:nextJ);
						newMap[i][j].add(new Shark(newM,newS,dir));
					}
				}
			}
			map=newMap;			
		}
		int ret=0;
		for(int i = 0 ; i < N ; i++)
			for(int j = 0  ; j < N ; j++)
				if(!map[i][j].isEmpty())
					for(int k = 0 ; k < map[i][j].size();k++)
						ret+=map[i][j].get(k).m;
		System.out.println(ret);
	 }
	 
	 static class Shark{
		 int m,s,d;

		public Shark(int m, int s, int d) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
		}
		 
	 }
}
