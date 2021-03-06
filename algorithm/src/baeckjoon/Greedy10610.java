package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Greedy10610 {

	
	public static int[] numAry;
	static long ret=-1;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer st = new StringBuffer(br.readLine());

		 
		 int zeroFlag=0;
		 int len = st.length();
		 numAry = new int[10];
		 int sum=0;
		 
		for(int i = 0 ; i <len ; i++){
			int num=st.charAt(i)-'0';
			if(num==0)
				zeroFlag=1;
			numAry[num]++;
			sum+=num;
		}
		
		if(zeroFlag==0||sum%3!=0||sum==0){
			System.out.println(-1);
			return;
		}
		
		StringBuffer resStr = new StringBuffer();
		for(int i = 9 ; i >=0 ; i --){
			
			while(true){
				if(numAry[i]==0)
					break;
				resStr.append(i);
				numAry[i]-=1;
			}
		}
		
	
		
		System.out.println(resStr);
	}

}
