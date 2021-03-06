package Kakao;

import java.util.HashMap;
import java.util.LinkedList;

public class Kakao20213 {

	public static void main(String[] args) {
		String[] info = new String[] { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };

		String[] query = new String[] { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		solution(info, query);
	}
	
	public static int[] solution(String[] info, String[] query) {
        int[] answer = {};        
       
        
        return answer;
    }
	

	/*final static int CPP = 0;
	final static int Java = 1;
	final static int Python = 2;
	final static int Backend = 0;
	final static int Frontend = 1;
	final static int Junior = 0;
	final static int Senior = 1;
	final static int Chicken = 0;
	final static int Pizza = 1;
	static LinkedList<Integer> board[][][][] = new LinkedList[3][2][2][2];

	public static int[] solution(String[] info, String[] query) {
		int[] answer = {};
		answer = new int[query.length];
		for (int i = 0; i < info.length; i++) {
			StringTokenizer st = new StringTokenizer(info[i]);
			String language = st.nextToken();
			String position = st.nextToken();
			String experience = st.nextToken();
			String food = st.nextToken();
			int score = Integer.parseInt(st.nextToken());
			int languageIdx = -1;
			switch (language) {
			case "java":
				languageIdx = Java;
				break;
			case "cpp":
				languageIdx = CPP;
				break;
			case "python":
				languageIdx = Python;
				break;
			}
			int posIdx = position.equals("backend") ? Backend : Frontend;
			int expIdx = experience.equals("junior") ? Junior : Senior;
			int foodIdx = food.equals("chicken") ? Chicken : Pizza;
			if (board[languageIdx][posIdx][expIdx][foodIdx] == null)
				board[languageIdx][posIdx][expIdx][foodIdx] = new LinkedList<Integer>();
			board[languageIdx][posIdx][expIdx][foodIdx].add(score);

		}
		for (int i = 0; i < query.length; i++) {
			String sql = query[i];
			String condAry[] = sql.split(" ");
			String language = condAry[0];
			String position = condAry[2];
			String experience = condAry[4];
			String food = condAry[6];
			int score = Integer.parseInt(condAry[7]);

			int languageIdx = -1;
			int languageIdxEnd = -1;
			switch (language) {
			case "java":
				languageIdx = Java;
				languageIdxEnd = languageIdx + 1;
				break;
			case "cpp":
				languageIdx = CPP;
				languageIdxEnd = languageIdx + 1;
				break;
			case "python":
				languageIdx = Python;
				languageIdxEnd = languageIdx + 1;
				break;
			case "-":
				languageIdx = 0;
				languageIdxEnd = 3;
			}

			int posIdx = -1;
			int posIdxEnd = -1;
			if (position.equals("-")) {
				posIdx = 0;
				posIdxEnd = 2;
			} else {
				posIdx = position.equals("backend") ? Backend : Frontend;
				posIdxEnd = posIdx + 1;
			}

			int expIdx = -1;
			int expIdxEnd = -1;
			if (experience.equals("-")) {
				expIdx = 0;
				expIdxEnd = 2;
			} else {
				expIdx = experience.equals("junior") ? Junior : Senior;
				expIdxEnd = expIdx + 1;
			}

			int foodIdx = -1;
			int foodIdxEnd = -1;
			if (food.equals("-")) {
				foodIdx = 0;
				foodIdxEnd = 2;
			} else {
				foodIdx = food.equals("chicken") ? Chicken : Pizza;
				foodIdxEnd = foodIdx + 1;
			}
			for (int a = languageIdx; a < languageIdxEnd; a++) {
				for (int b = posIdx; b < posIdxEnd; b++) {
					for (int c = expIdx; c < expIdxEnd; c++) {
						for (int d = foodIdx; d < foodIdxEnd; d++) {
							 if(board[a][b][c][d]!=null)
								 Collections.sort(board[a][b][c][d]);
						}
					}
				}
			}

			int sum = 0; 
			for (int a = languageIdx; a < languageIdxEnd; a++) {
				for (int b = posIdx; b < posIdxEnd; b++) {
					for (int c = expIdx; c < expIdxEnd; c++) {
						for (int d = foodIdx; d < foodIdxEnd; d++) {
							if (board[a][b][c][d] == null)
								continue;
							LinkedList<Integer> tpList = new LinkedList<Integer>(board[a][b][c][d]);
							tpList.add(score);
							// Collections.sort(tpList);
							int idx = lower_idx(tpList, score, tpList.size());
							sum += tpList.size() - idx - 1;
						}
					}
				}
			}
			answer[i] = sum;
		}
		return answer;
	}

	public static <T extends LinkedList<Integer>> int lower_idx(T list, int target, int len) {
		int st = 0;
		int en = len;
		while (st < en) {
			int mid = (st + en) / 2;
			if (list.get(mid) >= target)
				en = mid;
			else
				st = mid + 1;
		}
		return st;
	}*/
}
