package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class greddy_11047 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int coinNum = Integer.parseInt(st.nextToken());
		int totalMoney = Integer.parseInt(st.nextToken());
		//st= new StringTokenizer(br.readLine());
		int[] coinAry = new int[coinNum];
		for (int i = 0; i < coinNum; i++) {
			coinAry[i]=Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(coinAry);
		int rsCoinNum=0;
		for(int i = coinNum ; i > 0 ; i --){
		rsCoinNum+=totalMoney/coinAry[i-1];
		totalMoney=totalMoney%coinAry[i-1];			
		}
		
		System.out.println(rsCoinNum);

	}

}
