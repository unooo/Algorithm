package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Greedy1931_3 {

	public static int N;
	public static int[][] ary;
	static int ret = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		ary = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			ary[i][0] = start;
			ary[i][1] = end;

		}

		sort(ary, 0, N - 1, 0);
		int startTime = -1;
		int endTime = -1;
		int stIdx = 0;

		while (true) {

			sort(ary, stIdx, N - 1, 1);
			endTime = ary[stIdx][1];
			startTime = ary[stIdx][0];

			sort(ary, stIdx, N - 1, 0);
			int tpIdx = stIdx;

			for (int i = tpIdx; i < N; i++) {
				if (ary[i][0] == startTime && ary[i][1] == endTime) {
					tpIdx = i;
					break;
				}
			}
			ret++;
			stIdx = tpIdx + 1;
			if (stIdx >= N)
				break;

			for (int i = stIdx; i < N; i++) {
				if (ary[i][0] >= endTime) {
					stIdx = i;
					break;
				}
			}

		}

		System.out.println(ret);

	}

public static void sort(int[][] ary, int left, int right, int k){
		
		int pl=left;
		int pr=right;
		int pv = ary[(left+right)/2][k];
		do{
			while(ary[pl][k]<pv) pl++;
			while(ary[pr][k]>pv) pr--;
			if(pl<=pr){
				int temp=ary[pl][0];
				ary[pl][0]=ary[pr][0];
				ary[pr][0]=temp;
				
				temp=ary[pl][1];
				ary[pl][1]=ary[pr][1];
				ary[pr][1]=temp;
				pl++;
				pr--;
			}				
		
		}while(pl<=pr);
		
		if(left<pr) sort(ary,left,pr,k);
		if(pl<right) sort(ary,pl,right,k);
	}

}
