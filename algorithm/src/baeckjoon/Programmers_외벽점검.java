package baeckjoon;

import java.util.Arrays;

public class Programmers_외벽점검 {
	
	
	public static void main(String[] args){
		int n = 200;
		int[] weak = new int[]{0, 10, 50, 80, 120, 160};
		int dist[] = new int[]{1,10,5,40,30};
		System.out.println(solution(n, weak, dist));
	}

	public static int solution(int n, int[] weak, int[] dist) {
		int answer = 0;
		int[] flagAry = new int[n];
		for (int i = 0; i < weak.length; i++) {
			flagAry[weak[i]] = 1;
		}
		path=new int[dist.length];
		res=Integer.MAX_VALUE;
		dfs(flagAry, n, weak, dist, 0, 2, dist.length, 0);
		
		return res==Integer.MAX_VALUE?-1:res;
	}

	static int[] path;
	static int res;

	public static void dfs(int[] flagAry, int N, int[] weak, int[] dist, int next, int n, int r, int idx) // 조합인경우
																											// int
																											// idx를
																											// 추가
	{
		if (next >= r) {
			// todo
			int ret = solve(N, weak, dist, flagAry);
			res = Math.min(res, ret);
			return;
		}
		for (int i = idx; i < n; i++) {
			path[next] = i;
			dfs(flagAry, N, weak, dist, next + 1, n, r, idx ); // 중복조합인경우는
																	// idx+1이 아닌
																	// idx
		}
	}

	public static int solve(int N, int[] weak, int[] dist, int[] tempAry) {
		int[] flag = new int[tempAry.length];
		for (int i = 0; i < tempAry.length; i++)
			flag[i] = tempAry[i];
		for (int i = 0; i < path.length; i++) {
			if(path[i]!=1)
				continue;
			int windowSize = dist[i];
			int maxIdx = 0;
			int maxValue = 0;
			for (int j = 0; j < N; j++) {
				int temp = 0;
				for (int k = j; k <= j + windowSize; k++) {
					temp += flag[k%N];
				}
				if (maxValue < temp){
					maxIdx = j;
					maxValue=temp;
				}
			}

			for (int j = 0; j <= windowSize; j++) {
				flag[(maxIdx + j) % 12] = 0;
			}
		}

		long count = Arrays.stream(flag).filter(i -> {
			return i == 1 ? true : false;
		}).count();
		if (count == 0)
			return (int) Arrays.stream(path).filter(i -> {

				return i == 1 ? true : false;
			}).count();
		return Integer.MAX_VALUE;
	}
}
