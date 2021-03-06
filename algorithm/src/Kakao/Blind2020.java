package Kakao;

public class Blind2020 {
	public static void main(String[] args){
		
		int[][] key=new int[][]{{0,0,0},{1,0,0},{0,1,1}};
		int[][] lock=new int[][]{{1,1,1},{1,1,0},{1,0,1}};
		System.out.println(solution(key, lock));
		
	}

	public static boolean solution(int[][] key, int[][] lock) {
		boolean answer = false;
		int M = key.length;
		int N = lock.length;

		int mainBoardLen = (M * 2) + N - 2;
		int[][] mainBoard = new int[mainBoardLen][mainBoardLen];
		// 메인보드의 정가운데에 락을 설정
		int idx = 0;
		for (int i = M - 1; i < M + N - 1; i++) {
			for (int j = M - 1; j < M + N - 1; j++) {
				mainBoard[i][j] = lock[idx / N][idx % N];
				idx++;
			}
		}

		for (int i = 0; i < M + N - 1; i++) {
			for (int j = 0; j < M + N - 1; j++) {
				
				for (int sp = 0; sp < 4; sp++) {
					int[][] copyedMainBoard = copyBoard(mainBoard);
					key=spin(key);
					idx = 0;
					for (int k = i; k < i + M; k++) {
						for (int l = j; l < j + M; l++) {
							copyedMainBoard[k][l] += key[idx / M][idx % M];
							idx++;
						}
					}					
					answer = checkAnswer(copyedMainBoard,M,N);
					if(answer==true)
						return true;
					}

			}
		}

		return answer;
	}
	public static boolean checkAnswer(int[][] board,int M,int N){
		boolean res = true;
		for (int i = M - 1; i < M + N - 1; i++) {
			for (int j = M - 1; j < M + N - 1; j++) {
				if(board[i][j]!=1){
					res=false;
					return res;
				}
					
				
			}
		}
		return res;

	}
	public static int[][] copyBoard(int[][] board) {
		int[][] newBoard = new int[board.length][board.length];
		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board.length; k++) {
				newBoard[i][k] = board[i][k];
			}
		}
		return newBoard;
	}

	public static int[][] spin(int[][] key) {
		int[][] newMap = new int[key.length][key.length];
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key.length; j++) {
				newMap[i][j] = key[j][key.length - i - 1];
			}
		}
		return newMap;
	}

}
