package Kakao;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Kakao202102 {

	public static void main(String[] args) {
		String[] orders = new String[] { "XYZ", "XWY", "WXA" };
		int[] course = new int[] { 2, 3, 4 };
		solution(orders, course);
	}

	static HashMap<String, Integer> hMap = new HashMap<String, Integer>();
	static int[] path = new int[10];

	public static String[] solution(String[] orders, int[] course) {
		String[] answer = {};
		for (int i = 0; i < orders.length; i++) {

			for (int j = 0; j < course.length; j++) {
				dfs(orders, course, i, course[j], 0, 0);
				// System.out.println("========================");
			}
		}
		int count[] = new int[11];
		LinkedList<String> res[] = new LinkedList[11];
		for (int i = 0; i < 11; i++) {
			res[i] = new LinkedList<String>();
		}

		for (String key : hMap.keySet()) {
			int len = key.length();
			if (count[len] == 0) {
				count[len] = hMap.get(key);
				res[len].add(key);
			} else {
				if (count[len] == hMap.get(key)) {
					res[len].add(key);
				} else if (count[len] < hMap.get(key)) {
					count[len] = hMap.get(key);
					res[len].clear();
					res[len].add(key);
				}
			}
		}
		LinkedList<String> resList = new LinkedList<String>();
		for (int i = 0; i < 11; i++) {
			if (count[i] < 2)
				continue;
			if (res[i] == null || res[i].size() == 0)
				continue;
			for (String temp : res[i]) {
				resList.add(temp);
			}
		}
		Collections.sort(resList);

		return resList.toArray(new String[resList.size()]);
	}

	public static void dfs(String[] orders, int[] course, int orderIdx, int courseNum, int nowNum, int idx) {

		if (nowNum >= courseNum) {
			StringBuffer sb = new StringBuffer();
			char ch[] = new char[orders[orderIdx].length()];
			for (int i = 0; i < orders[orderIdx].length(); i++) {
				ch[i] = orders[orderIdx].charAt(i);
			}
			Arrays.sort(ch);
			for (int i = 0; i < courseNum; i++) {
				sb.append(ch[path[i]]);
			}
			String str = new String(sb);
			Integer value = hMap.get(str);
			if (value == null) {
				hMap.put(str, 1);
			} else {
				hMap.put(str, value + 1);
			}
			// System.out.println(sb);
			return;//
		}

		for (int i = idx; i < orders[orderIdx].length(); i++) {
			path[nowNum] = i;
			dfs(orders, course, orderIdx, courseNum, nowNum + 1, i + 1);
		}
	}
}
