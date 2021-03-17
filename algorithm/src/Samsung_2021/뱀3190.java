package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 뱀3190 {

	static int N, K, L;
	static int[][] map;
	static HashMap<Integer, Character> dirChange = new HashMap<>();
	static int[] dy = new int[]{-1,0,1,0};
	static int[] dx= new int[]{0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			char val = st.nextToken().charAt(0);
			dirChange.put(key, val);
		}

		int step = 0;
		LinkedList<Node> snake = new LinkedList<>();
		snake.add(new Node(0,0));
		map[0][0]=2;
		int headDir=1;
		
		while (true) {
			Node head = snake.peekLast();
			int nextI=head.i+dy[headDir];
			int nextJ=head.j+dx[headDir];
			if(nextI<0||nextJ<0||nextI==N||nextJ==N||map[nextI][nextJ]==2){
				System.out.println(step+1);
				return;
			}
			
			snake.add(new Node(nextI,nextJ));			
			if(map[nextI][nextJ]!=1){
				Node tail=snake.pollFirst();				
				map[tail.i][tail.j]=0;
			}			
			map[nextI][nextJ]=2;
			step++;
			//방향전환 처리
			Character newDir=dirChange.get(step);
			if(newDir!=null){
				switch(newDir){
				case 'L':
					headDir=(headDir+3)%4;
					break;
				case 'D':
					headDir=(headDir+1)%4;
					break;
				}
			}
		}

	}

	static class Node {
		int i, j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}
}
