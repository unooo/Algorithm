import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baek_2580 {

	public static int[][] board = new int[9][9];

	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static LinkedList<Node> nodeList = new LinkedList<Node>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = 0;
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 0) {
					len++;
					nodeList.add(new Node(i, j));
				}
			}
		}
		solve(0, len);
	}

	public static void solve(int next, int len) {

		if (next >= len) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					int num = board[i][j];
					System.out.print(num + " ");
				}
				System.out.println();
			}
			System.exit(0);

			return;
		}
		int r = nodeList.get(next).r;
		int c = nodeList.get(next).c;
		for (int i = 1; i <= 9; i++) {			
			board[r][c] = i;
			if (check(r,c,next))
				solve(next + 1, len);
			board[r][c] = 0;
		}

	}

	public static boolean check(int r, int c,int next) {

		
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

}
