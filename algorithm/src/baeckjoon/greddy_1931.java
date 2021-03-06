package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class greddy_1931 {

	
	public static void main(String[] args) throws NumberFormatException, IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int money = Integer.parseInt(br.readLine());
		
		money=1000-money;
		
		
		int[] coinAry= {500,100,50,10,5,1};
		int res=0;
		
		for(int i = 0 ; i < 6 ; i ++){
			
			res+=money/coinAry[i];
			money=money%coinAry[i];
			//System.out.println(money);
		}
		System.out.println(res);
		
		
	}
}
