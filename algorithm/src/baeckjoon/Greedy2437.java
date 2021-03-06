package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Greedy2437 {

	public static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int[] ary = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(ary); 

		HashSet<Long> hset = new HashSet<Long>();
		hset.add(new Long(0));
		

		for (int i = 0; i < ary.length; i++) {
			Iterator<Long> iter = hset.iterator();
			HashSet<Long> tempset = new HashSet<Long>();
			while (iter.hasNext()) {
				Long value = iter.next();
				tempset.add(value+ary[i]);
			}
			hset.addAll(tempset);
		}
		
		
	}
}
