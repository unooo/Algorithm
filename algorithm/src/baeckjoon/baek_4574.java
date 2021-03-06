package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baek_4574 {

	public static int[][] board;
	static int N;
	static int dy[] = new int[] { 1, -1, 0, 0 };
	static int dx[] = new int[] { 0, 0, 1, -1 };
	static HashSet<String> visit;

	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static LinkedList<Node> nodeList;
	static int len;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int step = 1;
		while (true) {
			nodeList = new LinkedList<Node>();
			len = 0;
			board = new int[9][9];
			visit = new HashSet<String>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0)
				return;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				String tempStr = st.nextToken();
				int r1 = tempStr.charAt(0) - 'A';
				int c1 = tempStr.charAt(1) - '0' - 1;

				int num2 = Integer.parseInt(st.nextToken());
				tempStr = st.nextToken();
				int r2 = tempStr.charAt(0) - 'A';
				int c2 = tempStr.charAt(1) - '0' - 1;

				board[r1][c1] = num1;
				board[r2][c2] = num2;
				visit.add("" + num1 + num2);
				visit.add("" + num2 + num1);

			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 9; i++) {
				String tempStr = st.nextToken();
				int r = tempStr.charAt(0) - 'A';
				int c = tempStr.charAt(1) - '0' - 1;
				board[r][c] = i;
			}

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[i][j] == 0) {
						len++;
						nodeList.add(new Node(i, j));
					}
				}
			}
			System.out.println("Puzzle " + step);
			flag=false;
			solve(0, len);
			step++;
		}

	}
	static boolean flag=false;
	public static void solve(int next, int len) {
		if(flag)
			return;
		if (next >= len ) {
			print();
			flag=true;
			return;
		}
		int r = nodeList.get(next).r;
		int c = nodeList.get(next).c;
/*		int next2=next;
		while(board[r][c]!=0){
			next2++;
			 r = nodeList.get(next2).r;
			 c = nodeList.get(next2).c;
		}
*/
		if(board[r][c]!=0){
			solve(next+1,len);
		}else
		
		for (int i = 1; i <= 9; i++) {
			board[r][c] = i;
			if (check(r, c, next)) {
				for (int dir = 0; dir < 4; dir++) {
					int nextR = r + dy[dir];
					int nextC = c + dx[dir];
					if (nextR < 0 || nextC < 0 || nextR >= 9 || nextC >= 9)
						continue;
					if (board[nextR][nextC] != 0)
						continue;

					for (int k = 1; k <= 9; k++) {

						String set1 = "" + i + k;
						String set2 = "" + k + i;
						boolean flag1 = visit.add(set1);
						boolean flag2 = visit.add(set2);

						if (flag1 == true && flag2 == true) {
							board[nextR][nextC] = k;
							if (check(nextR, nextC, next)) {
								solve(next + 1, len);
							}
							board[nextR][nextC] = 0;
							visit.remove(set1);
							visit.remove(set2);

						}
					}

				}

			}
			board[r][c] = 0;
		}

	}

	public static boolean check(int r, int c, int next) {

		int bucket[] = new int[10];
		for (int j = 0; j < 9; j++) {
			int num = board[r][j];
			if (num == 0)
				continue;
			if (bucket[num] == 0) {
				bucket[num] = 1;
			} else {
				return false;
			}
		}
		bucket = new int[10];
		for (int i = 0; i < 9; i++) {
			int num = board[i][c];
			if (num == 0)
				continue;
			if (bucket[num] == 0) {
				bucket[num] = 1;
			} else {
				return false;
			}
		}
		int startI = -1, startJ = -1;
		if (r <= 2) {
			startI = 0;
		} else if (r <= 5) {
			startI = 3;
		} else {
			startI = 6;
		}
		if (c <= 2) {
			startJ = 0;
		} else if (c <= 5) {
			startJ = 3;
		} else {
			startJ = 6;
		}
		bucket = new int[10];
		for (int i = startI; i < startI + 3; i++) {
			for (int j = startJ; j < startJ + 3; j++) {
				int num = board[i][j];
				if (num == 0)
					continue;
				if (bucket[num] == 0) {
					bucket[num] = 1;
				} else {
					return false;
				}
			}
		}

		return true;

	}

	public static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int num = board[i][j];
				System.out.print(num);
			}
			System.out.println();
		}
	}

}
