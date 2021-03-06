package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;



public class Greedy1932_4 {

	public static int N;
	public static LinkedList<Pair> ary;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		ary = new LinkedList<Pair>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			ary.add(new Pair(start, end));

		}
		int startTime = 0;
		int endTime = 0;
		int stIdx = 0;
		int ret = 1;
		while(true){
			Collections.sort(ary);
			for(int i = stIdx+1; i < N ; i ++){
				if(ary.get(i).startTime>=ary.get(stIdx).endTime&&ary.get(i).endTime>=ary.get(stIdx).endTime){
					stIdx=i;
					ret++;
					break;
				}
			}
			
			if(stIdx+1>=N)
				break;
		}
		System.out.println(ret);
	}
	
	
	static class Pair implements Comparable<Pair> {
		int startTime;
		int endTime;

		public Pair(int startTime, int endTime) {
			super();
			this.startTime = startTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.endTime > o.endTime) {
				return 1;
			} else if (this.endTime < o.endTime) {
				return -1;
			} else {
				if (this.startTime > o.startTime) {
					return 1;
				} else if (this.startTime < o.startTime) {
					return -1;
				}
				return 0;
			}
		}
	}
}
