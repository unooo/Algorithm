package topcoder;

public class ThePalindrome {
	
	public static void main(String[] args){
		System.out.println(find("abcd"));
	}

	public static int find(String s) {
		int strLen = s.length();
		int res = 0;
		boolean flag = true;
		

		for (int j = 0; j < strLen; j++) {
			
			String temp = new String(s);
			for(int k =0 ; k< j ; k++){ //회문구조가 되도록 문자열 조정하는 포문
				temp=temp+s.charAt(strLen-1-k-1);
				//temp=temp+s.charAt(k);
			}			
			
			for (int i = 0; i < temp.length() / 2; i++) {
				if (temp.charAt(i) != temp.charAt(temp.length() - i - 1)) { //회문이 아닌경우
					flag = false;
					break;
				}
			}
			
			if(flag==false){//회문이 아닌경우
				flag=true;
			}else{ //회문인경우
				res=temp.length();
				break;
			}			
		}		
		return res;
	}

}
