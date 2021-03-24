package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ≥¨Ω√ø’17143 {

	static int R,C,M;
	static Shark sharkMap[][] ;
	static int dy[] = new int[] {-1,1,0,0};
	static int dx[] = new int[] {0,0,1,-1};
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		sharkMap= new Shark[R][C];
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken());
			
			sharkMap[r][c]=(new Shark( s, d, z));			
		}
		int ret=0;
		for(int tun=0;tun<C;tun++) {
			for(int i = 0 ; i <R;i++ ) {
				if(sharkMap[i][tun]==null)
					continue;
				ret+=sharkMap[i][tun].z;
				sharkMap[i][tun]=null;
				break;
			}
			LinkedList<Shark> newSharkMap[][]=  new LinkedList[R][C];
			for(int i = 0 ; i < R ; i++) {
				for(int j = 0 ; j < C ;j++) {
					if(sharkMap[i][j]==null)
						continue;
					Shark shark=sharkMap[i][j];
					int nextI=i;
					int nextJ=j;
					int newSpeed=shark.s;
					if(shark.d==0||shark.d==1) {
						newSpeed=newSpeed%((R-1)*2);
					}else {
						newSpeed=newSpeed%((C-1)*2);
					}
					
					for(int k=0;k<newSpeed;k++) {
						nextI=nextI+dy[shark.d];
						nextJ=nextJ+dx[shark.d];
						
						if(nextI<0||nextJ<0||nextI>=R||nextJ>=C) {
							//πÊ«‚¿¸»Ø
							switch(shark.d) {
							case 0:
								shark.d=1;
								break;
							case 1:
								shark.d=0;
								break;
							case 2:
								shark.d=3;
								break;
							case 3:
								shark.d=2;
								break;
							}
							nextI=nextI+dy[shark.d]*2;
							nextJ=nextJ+dx[shark.d]*2;
							
						}
						
					}
					if(newSharkMap[nextI][nextJ]==null)
						newSharkMap[nextI][nextJ]=new LinkedList<>();
					newSharkMap[nextI][nextJ].add(shark);
						
					
				}
			}
			sharkMap=new Shark[R][C];
			for(int i = 0 ; i < R ; i++) {
				for(int j = 0 ; j < C ;j++) {
					if(newSharkMap[i][j]==null||newSharkMap[i][j].size()==0)
						continue;
					Collections.sort(newSharkMap[i][j]);
					sharkMap[i][j]=newSharkMap[i][j].getFirst();
				}
			}

		}
		
		System.out.println(ret);
		
	}
	
	static class Shark implements Comparable<Shark>{
		int s,d,z;

		public Shark( int s, int d, int z) {
			super();
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			// TODO Auto-generated method stub
			return o.z-this.z;
		}
		
	}
}
