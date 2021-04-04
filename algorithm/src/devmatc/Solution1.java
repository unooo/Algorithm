package devmatc;

import java.util.HashSet;

public class Solution1 {
	public static void main(String[ ] args) {
		
		int[] lottos= {0, 0, 0, 0, 0, 0};
		int[] win_nums= {38, 19, 20, 40, 15, 25};
		solution(lottos, win_nums);
	}
	public static int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];

		HashSet<Integer> win = new HashSet<>();
		for (int tp : win_nums) {
			win.add(tp);
		}
		int zeroNum = 0;
		int matchNum = 0;
		for (int tp : lottos) {
			if (tp == 0) {
				zeroNum++;

			} else if (win.contains(tp)) {
				matchNum++;
			}
		}
		int max = zeroNum + matchNum;
		answer[0]=getRank(max); // √÷∞Ì¡°
		answer[1]=getRank(matchNum);
		return answer;
	}

	public static int getRank(int score) {
		int ret = 0;
		switch (score) {
		case 6:
			ret = 1;
			break;
		case 5:
			ret = 2;
			break;
		case 4:
			ret=3;
			break;
		case 3:			
			ret=4;
			break;
		case 2:
			ret=5;
			break;
		case 1:
			;
		case 0:
			ret=6;
			break;
		}
		return ret;
	}
}
