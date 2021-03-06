package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_2072 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
	

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for (int j = 0; j < 10; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp % 2 == 1)
					sum += temp;
			}
			System.out.println("#"+i+" "+sum);
		}

	}
}
