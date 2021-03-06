package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Greedy1541 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/*
		String st = new String(br.readLine());
		int len = st.length();
		String newStr=new String();
	
		int openFlag=0;
		for(int i = 0 ; i < len ; i ++){
			if(st.charAt(i)=='-'){
				newStr+=st.charAt(i);
				if(openFlag==0){
				newStr+='(';
				openFlag=1;
				}else{
					newStr+='(';
					openFlag=0;
					
				}
			}else{
				newStr+=st.charAt(i);
			}
		}
		
		if(openFlag==1)
			newStr+=')';
		System.out.println(newStr);
		*/	
		String st = new String(br.readLine());
		String[] strAry=st.split("-");
		
		int num=0;;
	
		for(int i = 0 ; i < strAry.length; i ++){
			String temp = new String(strAry[i]);
			String[] tpStrAry=temp.split("\\+");			
		
			int ipNum=0;
			
			
			
			for(int j = 0 ; j < tpStrAry.length;j++){
				
				if(i==0){
					num+=Integer.parseInt(tpStrAry[j]);
				}else{
					ipNum+=Integer.parseInt(tpStrAry[j]);
				}
						
			}
			num-=ipNum;
		}
		System.out.println(num);
	}
}
