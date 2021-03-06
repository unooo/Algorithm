package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class greedy_1120 {

	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder strA = new StringBuilder(st.nextToken());
		StringBuilder strB = new StringBuilder(st.nextToken());
		
		int term = strB.length()-strA.length();
		int sameNum=0;
		int tmpSameNum=0;
		for(int i = 0 ; i < term+1 ; i ++){
			for(int j = 0 ; j <strA.length();j++){
				if(strA.charAt(j)==strB.charAt(j+i)){
					tmpSameNum++;
				}
			}
			if(tmpSameNum>sameNum)
				sameNum=tmpSameNum; 
			tmpSameNum=0;
			
		}
		
		System.out.println(sameNum);
		
		
		
		
		
	}
}
