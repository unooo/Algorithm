package baeckjoon;

import java.util.LinkedList;

public class Programmers_N진수게임 {

	public static void main(String[] args) {
		solution(2, 4, 2, 1);
	}

	public static String solution(int n, int t, int m, int p) {
		String answer = "";
		
		int inumer = 0;
		int tun = 0;
		LinkedList<Character> ret = new LinkedList<>();
		Outer: while (true) {
			StringBuilder target = make(n, inumer);

			while (true) {
				if (target.length() == 0)
					break;
				if (tun % m == (p - 1)) {
					ret.add(target.charAt(0));
					if (ret.size() == t)
						break Outer;
				}
				target.replace(0, 1, "");
				tun++;
			}
			inumer++;
		}
		StringBuilder retStr = new StringBuilder();
		ret.forEach(i -> retStr.append(i));
		return retStr.toString();
	}

	public static StringBuilder make(int n, int target) {

		StringBuilder sb = new StringBuilder();

		while (true) {
			int item = target % n;
			String itemStr = item + "";
			switch (item) {
			case 10:
				itemStr = "A";
				break;
			case 11:
				itemStr = "B";
				break;
			case 12:
				itemStr = "C";
				break;
			case 13:
				itemStr = "D";
				break;
			case 14:
				itemStr = "E";
				break;
			case 15:
				itemStr = "F";
				break;
			}

			sb.insert(0, (itemStr));
			if (target / n == 0)
				break;
			target = target / n;
		}

		return sb;
	}

}
