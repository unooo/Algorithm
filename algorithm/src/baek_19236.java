import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek_19236 {

	static Fish[][] board = new Fish[4][4];
	static int dy[] = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dx[] = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int res = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int fishNum = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken()) - 1;
				board[i][j] = new Fish(fishNum, dir, false);
			}
		}
		res = board[0][0].num+1;
		Fish shark = new Fish(-1, board[0][0].dir, true);
		board[0][0] = shark;
		solv();
		System.out.println(max);
	}
	static int max= Integer.MIN_VALUE;
	public static void solv() {
		// 물고기이동
		 Fish[][] newBoard = new Fish[4][4];
		 for(int i = 0 ; i <4 ; i ++){
			 for(int j = 0 ; j < 4 ; j++){
				 Fish temp=board[i][j];
				 if(temp==null)
					 continue;
				 newBoard[i][j]=new Fish(temp.num,temp.dir,temp.isShark);
			 }
		 }
		for (int k = 0; k < 16; k++) {
			Fish fish = null;
			Loop: for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					fish = board[i][j];
					if(fish==null)
						continue;
					if (fish.num == k) {

						for (int dir = 0; dir < 8; dir++) {
							int nextI = i + dy[(fish.dir + dir) % 8];
							int nextJ = j + dx[(fish.dir + dir) % 8];

							if (nextI < 0 || nextJ < 0 || nextI >= 4 || nextJ >= 4)
								continue;
							if (board[nextI][nextJ]!=null&&board[nextI][nextJ].isShark)
								continue;
							fish.dir = (fish.dir + dir) % 8;
							Fish temp = board[i][j];
							board[i][j] = board[nextI][nextJ];
							board[nextI][nextJ] = temp;
							break Loop;
						}

					}
				}
			}
		}

		// 상어움직임//
		Fish shark=null;
		Outer:
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				shark = board[i][j];
				if(shark==null)
					continue;
				if (shark.isShark == true) {
					for(int tun=1;tun<=3;tun++){
						int nextI=i+dy[shark.dir]*tun;
						int nextJ=j+dx[shark.dir]*tun;
						if (nextI < 0 || nextJ < 0 || nextI >= 4 || nextJ >= 4)
							continue;
						if (board[nextI][nextJ]==null)
							continue;
						Fish temp=board[nextI][nextJ];
						res+=temp.num+1;
						int orgSharkDir=shark.dir;
						shark.dir=board[nextI][nextJ].dir;
						board[i][j]=null;
						board[nextI][nextJ]=shark;
						max=Math.max(max, res);
						solv();
						
						board[nextI][nextJ]=temp;
						board[i][j]=shark;
						shark.dir=orgSharkDir;
						res-=temp.num+1;
						
					}
					break Outer;
				}
			}
		}
		board=newBoard;
	
	}

	static class Fish {
		int num, dir;
		boolean isShark;

		public Fish(int num, int dir, boolean isShark) {
			super();
			this.num = num;
			this.dir = dir;
			this.isShark = isShark;
		}

	}
}
