

import java.util.LinkedList;

public class 같은문자쌍 {

	public static void main(String[] args){
		String str1 = "hanwha";
		String str2= "whahaa";
		int bucket[] = new int[30];
		
		for(int i = 0 ; i < str1.length() ; i ++){
			char ch = str1.charAt(i);
			
			bucket[ch-'a']++;
			
		}
		
		for(int i = 0 ; i < str2.length() ; i ++){
			char ch = str2.charAt(i);
			bucket[ch-'a']--;
			if(bucket[ch-'a']<0){
				System.out.println("No");
				return;
			}
		}
	
		
		System.out.println("Yes");
		
		
		
		
	}
	
	public static void solution(){
		
	}
}
