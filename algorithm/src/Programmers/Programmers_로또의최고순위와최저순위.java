package Programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Programmers_로또의최고순위와최저순위 {
	public int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = {};
		int[] rate = { 6, 5, 4, 3, 2, 1 };
		int zeroNum = 0;
		int correctNum = 0;
		HashSet<Integer> checker = new HashSet<>(Arrays.stream(win_nums).boxed().collect(Collectors.toList()));

		for (int i = 0; i < 6; i++) {
			if (lottos[i] == 0) {
				zeroNum++;
			}
			boolean checkFlag = checker.contains(lottos[i]);
			if (checkFlag)
				correctNum++;
		}

		answer[0] = rate[zeroNum + correctNum];
		answer[1] = rate[correctNum];

		return answer;
	}
}
