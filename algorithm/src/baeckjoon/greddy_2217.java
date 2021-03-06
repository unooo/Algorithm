package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class greddy_2217 {

	public static void main (String args[]) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ropeNum=Integer.parseInt(br.readLine());
		
		int[] ary = new int[ropeNum];
		
		for(int i = 0 ; i < ropeNum ; i ++){
			ary[i]=Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(ary);
		int max=0;
		int tmp=0;
		for(int i = 0 ; i < ropeNum ; i ++){
			tmp=ary[i]*(ropeNum-i);
			if(tmp>max)
				max=tmp;
		}
		System.out.println(max);
		
	}
}
