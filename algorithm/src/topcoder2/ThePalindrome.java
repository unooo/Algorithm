package topcoder2;

public class ThePalindrome {

	public static void main(String[] args){
		System.out.println(find("qwerty"));
	}
	
	public static int find(String s){
		int ret = 0 ; 
		
		
		
		
		String temp = new String(s);
		ret=temp.length();
		for(int i = 0 ; i <s.length() ; i ++){
			if(check(temp)==true)
				break;
			temp+=s.charAt(i);
			ret++;
			
			
		}
		
		
		
		
		
		
		return ret;
	}
	
	public static boolean check(String s){
		boolean ret = true;
		
		for(int i = 0 ; i < s.length()/2 ;  i ++){
			if(s.charAt(i)!=s.charAt(s.length()-i-1)){
				ret=false;
				break;
			}
		}
		return ret;
	}

}
