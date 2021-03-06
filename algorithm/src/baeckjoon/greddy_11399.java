package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class greddy_11399 {
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sb = new StringTokenizer(br.readLine());
		
		int first = Integer.parseInt(sb.nextToken());
		
		int [] ary = new int[first];
		sb= new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i < first ; i ++){
			
			ary[i]=Integer.parseInt(sb.nextToken());			
		}
		
		Arrays.sort(ary);
		
		int tmpResult = 0;
		int result=0;
		for(int tmp:ary){
			tmpResult+=tmp;
			result+=tmpResult;
			
		}
		
		System.out.println(result);
		
		
		
		
		
		
		
	}
	

}
