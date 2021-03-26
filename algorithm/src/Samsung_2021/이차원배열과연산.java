package Samsung_2021;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 이차원배열과연산 {

	static int r, c, k, map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		map = new int[3][3];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int step = 0;
		while (true) {
			if (step >= 100)
				break;
			int R = map.length;
			int C = map[0].length;
			if (R > r && C > c) {
				if (map[r][c] == k) {
					System.out.println(step);
					return;

				}
			}

			boolean isSpined = false;
			if (R < C) {
				spinRight();
				int temp = C;
				C = R;
				R = temp;
				isSpined = true;
			}
			if(R>100)
				R=100;
			LinkedList<Integer>[] row = new LinkedList[R];
			int maxLen = 0;
			for (int i = 0; i < R; i++) {
				HashMap<Integer, Integer> hMap = new HashMap<>();
				for (int j = 0; j < C; j++) {
					hMap.merge(map[i][j], 1, (old, newV) -> old + newV);
				}
				List<Integer> keyList = new LinkedList(hMap.keySet());
				Collections.sort(keyList, new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO Auto-generated method stub
						if (hMap.get(o1) > hMap.get(o2)) {
							return 1;
						} else if (hMap.get(o1) < hMap.get(o2))
							return -1;
						else {
							return o1 - o2;
						}
					}
				});
				LinkedList<Integer> temp = new LinkedList<Integer>();
				for (Integer key : keyList) {
					if (key == 0)
						continue;
					temp.add(key);
					temp.add(hMap.get(key));

					
				}
				row[i] = temp;
				maxLen = Math.max(maxLen, temp.size());
			}
			if(maxLen>100)
				maxLen=100;
			map = new int[R][maxLen];
			for (int i = 0; i < R; i++) {				
				for (int j = 0; j < row[i].size(); j++) {
					if(j>=100)
						break;
					map[i][j] = row[i].get(j);
				}
			}

			if (isSpined) {
				int[][] newMap = new int[maxLen][R];
				int idx = 0;
				for (int j = 0; j < maxLen; j++) {
					for (int i = 0; i < R; i++) {

						newMap[idx / R][idx % R] = map[i][j];
						idx++;
					}
				}
				map = newMap;
			}

			step++;
		}
		System.out.println(-1);
	}

	public static void spinRight() {

		int R = map.length;
		int C = map[0].length;

		int newMap[][] = new int[C][R];
		int idx = 0;

		for (int j = 0; j < C; j++) {
			for (int i = R - 1; i >= 0; i--) {
				newMap[idx / R][idx % R] = map[i][j];
				idx++;
			}
		}
		map = newMap;
	}

}
