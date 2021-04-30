package LastCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 어른상어 {

	static int N, M, K;
	static ArrayList<Shark> sharkMap[][];
	static Scent scentMap[][];
	static int dy[]={-1,1,0,0};
	static int dx[]={0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		sharkMap=new ArrayList[N][N];
		scentMap=new Scent[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				sharkMap[i][j] = new ArrayList<>();
				int idx = Integer.parseInt(st.nextToken());
				if (idx == 0)
					continue;
				Shark shark = new Shark();
				shark.idx = idx;
				shark.pri=new int[4][4];
				sharkMap[i][j].add(shark);
				
				scentMap[i][j]=new Scent(idx,K);				
			}
		}
		int dirAry[] = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			dirAry[i] = Integer.parseInt(st.nextToken());
		}

		for (int idx = 0; idx < M; idx++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(sharkMap[i][j].size()==0)
						continue;
					Shark shark=sharkMap[i][j].get(0);
					if(shark.idx!=idx+1)
						continue;
					shark.dir=dirAry[idx]-1;
					
					for(int r = 0 ; r < 4 ; r++){
						st = new StringTokenizer(br.readLine());
						for(int c = 0 ; c < 4 ; c++){
							shark.pri[r][c]=Integer.parseInt(st.nextToken())-1;
						}
					}
				}
			}
		}
		
		int step=1;
		while(true){
			
			
			int sharkNum=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(sharkMap[i][j].size()==0)
						continue;
					sharkNum+=sharkMap[i][j].size();
				}
			}
			if(sharkNum==1){
				System.out.println(step);
				return;
			}
			step++;
			if(step>1000){
				System.out.println(-1);
				return;
			}
			
			
		}
		
	}
	
	public static void move(){
		 ArrayList<Shark> newSharkMap[][]= new ArrayList[N][N];
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < N ; j++){
				if(sharkMap[i][j].isEmpty())
					continue;
				Shark shark=sharkMap[i][j].get(0);
				
			}
		}
	}
	
	static class Scent{
		int idx, remain;

		public Scent(int idx, int remain) {
			super();
			this.idx = idx;
			this.remain = remain;
		}
		
	}
	static class Shark {
		int idx, dir;
		int pri[][];

	}
}
