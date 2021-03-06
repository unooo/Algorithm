package Kakao;

public class KaKao2020_3_2 {
	public static void main(String[] args) {
		int[][] key = new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };

		System.out.println(solution(key, lock));
	}

	public static boolean solution(int[][] key, int[][] lock) {
		boolean answer = false;

		int len = lock.length;
		int keyLen = key.length;
		int[][] copyLock;
		for (int s = 0; s < 4; s++) {
			for (int i = 0; i <= len * 3 - keyLen; i++) {

				for (int j = 0; j <= len * 3 - keyLen; j++) {

					copyLock = new int[len * 3][len * 3];
					for (int O = 0; O < len; O++) {
						for (int P = 0; P < len; P++) {
							copyLock[O + len][P + len] = lock[O][P];
						}
					}

					for (int k = 0; k < keyLen; k++) {

						for (int L = 0; L < keyLen; L++) {

							copyLock[i + k][j + L] += key[k][L];

						}

					}

					int successFlg = 0;
					for (int k = len; k < len * 2; k++) {
						for (int L = len; L < len * 2; L++) {
							if (copyLock[k][L] != 1) {
								successFlg = 1;
							}
						}
					}

					if (successFlg == 0) {
						return true;
					}

				}

			}
			spin(key);
		}

		System.out.println();

		return answer;
	}

	public static void spin(int[][] graph) {

		int N = graph.length;
		int[][] temp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = graph[N - j - 1][i];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = temp[i][j];
			}
		}
	}
}
