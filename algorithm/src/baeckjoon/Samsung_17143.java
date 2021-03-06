package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Samsung_17143 {

	public static int R;// 가로
	public static int C;// 세로
	public static int M;// 상어의 수

	public static int r, c, s, d, z;// (r,c) ,속력,이동방향, 상어크기
	public static int dy[] = new int[] { -1, 1, 0, 0 }; // 위 아래 오른쪽 왼쪽
	public static int dx[] = new int[] { 0, 0, 1, -1 };
	public static Shark graph[][];
	public static Shark newGraph[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new Shark[R][C];
		newGraph = new Shark[R][C];
		ArrayList<Shark> sharkList = new ArrayList<Shark>();
		ArrayList<Shark> newSharkList = new ArrayList<Shark>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken()) - 1;
			z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(r, c, s, d, z);
			graph[r][c] = shark;
			sharkList.add(shark);
		}

		int sum = 0;
		for (int i = 0; i < C; i++) {
			newGraph = new Shark[R][C];
			newSharkList = new ArrayList<Shark>();
			Collections.sort(sharkList);
			for(int j = 0 ; j < sharkList.size() ; j ++){
				if(sharkList.get(j).x==i){
					sum+=sharkList.get(j).size;
					graph[sharkList.get(j).y][sharkList.get(j).x]=null;
					sharkList.remove(j);
					break;
				}
			}
			
			int size = sharkList.size();
			for (int k = 0; k < size; k++) {
	
				Shark shark = sharkList.get(k);

				int nowSharkY = shark.y;
				int nowSharkX = shark.x;
				int newSharkY = nowSharkY;
				int newSharkX = nowSharkX;

				
				int temp = shark.speed;
				int tempD = shark.d;
			
				switch (tempD) {
			
				case 0:
					temp=temp % (2*(R-1));
				
			
					break;
				case 1:
				
					temp=temp % (2*(R-1));
				
					break;
				case 2:
			
					temp=temp % (2*(C-1));
				
					break;
				case 3:
				
					temp=temp % (2*(C-1));
					
					break;
				}
				
				for (int j = 0; j < temp; j++) {
					newSharkY = nowSharkY + dy[tempD];
					newSharkX = nowSharkX + dx[tempD];
					if (newSharkY < 0 || newSharkX < 0 || newSharkY >= R || newSharkX >= C) {
						int newd = -1;
						switch (tempD) {
						case 0:
							newd = 1;
							break;
						case 1:
							newd = 0;
							break;
						case 2:
							newd = 3;
							break;
						case 3:
							newd = 2;
							break;
						}
						shark.d = newd;
						tempD = newd;
						newSharkY = nowSharkY + dy[tempD];
						newSharkX = nowSharkX + dx[tempD];
					}
					nowSharkY = newSharkY;
					nowSharkX = newSharkX;
				}
				shark.y = newSharkY;
				shark.x = newSharkX;
				 
				if (newGraph[newSharkY][newSharkX] == null) {
					newGraph[newSharkY][newSharkX] = shark;
					newSharkList.add(shark);
				} else {
					Shark tp = newGraph[newSharkY][newSharkX];
					if (shark.size > tp.size) {
					
						tp.y = shark.y;
						tp.x = shark.x;
						tp.size = shark.size;
						tp.speed = shark.speed;
						tp.d = shark.d;

					
					}
				}

			}
			graph = newGraph;
			sharkList = newSharkList;

		}
		System.out.println(sum);
	}



	static class Shark implements Comparable<Shark> {
		int x;
		int y;
		int speed;
		int d;
		int size;

		Shark(int y, int x, int speed, int d, int size) {
			this.y = y;
			this.x = x;
			this.speed = speed;
			this.d = d;
			this.size = size;
		}

		@Override
		public int compareTo(Shark o) {
			// TODO Auto-generated method stub
			if(this.x==o.x){
				if(this.y==o.y){
					return this.size<o.size?1:-1;
				}else{
					return this.y<o.y?-1:1;
				}
				
				
			}
			return this.x<o.x?-1:1;
		}

	}

}
