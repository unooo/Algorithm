package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 마법사상어와파이어볼20056 {
	static int N,M,K;
	static ArrayList<FireBall>[][] map ;
	static int dy[]= {-1,-1,0,1,1,1,0,-1};
	static int dx[] = {0,1,1,1,0,-1,-1,-1};
	static ArrayList<FireBall> ballStore=new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		map=new ArrayList[N][N];
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());			
			map[r][c]=new ArrayList<FireBall>();
			map[r][c].add(new FireBall(r,c,m,s,d));
			ballStore.add(map[r][c].get(0));
			
		}
		for(int step=0;step<K;step++) {
			
			for(FireBall ball : ballStore) {
				int nextI=ball.i+dy[ball.d]*ball.s;
				/*
				 * if(nextI>=0) { nextI=nextI%N; }else { nextI=(N-((nextI+1)*-1)%N)-1; }
				 */
				int nextJ=ball.j+dx[ball.d]*ball.s;
				/*
				 * if(nextJ>=0) { nextJ=nextJ%N; }else { nextJ=(N-((nextJ+1)*-1)%N)-1; }
				 */
				nextI = nextI % N < 0 ? N + nextI % N : nextI % N;
				nextJ = nextJ % N < 0 ? N + nextJ % N : nextJ % N;
				
				if(map[nextI][nextJ]==null)
					map[nextI][nextJ]=new ArrayList<>();
				map[ball.i][ball.j].remove(ball);
				map[nextI][nextJ].add(ball);
				ball.i=nextI;
				ball.j=nextJ;
			}
			
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					ArrayList<FireBall> list = map[i][j];
					if(list==null)
						continue;
					if(list.size()>=2) {
						int totM=0,totS=0,totNum=list.size();
						boolean isEven=false,isOdd=false;
						for(FireBall fb : list) {
							totM+=fb.m;
							totS+=fb.s;
							if(fb.d%2==0)
								isEven=true;
							else
								isOdd=true;
						}
						ballStore.removeAll(list);
						list.clear();
						for(int k=0;k<4;k++) {
							int newM=totM/5;
							if(newM==0)
								break;
							int newS=totS/totNum;
							int newD=-1;
							if(isEven!=isOdd) {
								newD=2*k;
							}else
								newD=2*k+1;
							FireBall tp=new FireBall(i, j, newM, newS, newD);
							list.add(tp);
							ballStore.add(tp);
						}
					}
				}
			}
			
		}
		int sum=0;
		for(int i = 0 ; i < ballStore.size();i++)
			sum+=ballStore.get(i).m;
		System.out.println(sum);
	}
	
	static class FireBall{
		int i,j,m,s,d;

		public FireBall(int i, int j, int m, int s, int d) {
			super();
			this.i = i;
			this.j = j;
			
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
