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
			for(int k =0 ; k< j ; k++){ //ȸ�������� �ǵ��� ���ڿ� �����ϴ� ����
				temp=temp+s.charAt(strLen-1-k-1);
				//temp=temp+s.charAt(k);
			}			
			
			for (int i = 0; i < temp.length() / 2; i++) {
				if (temp.charAt(i) != temp.charAt(temp.length() - i - 1)) { //ȸ���� �ƴѰ��
					flag = false;
					break;
				}
			}
			
			if(flag==false){//ȸ���� �ƴѰ��
				flag=true;
			}else{ //ȸ���ΰ��
				res=temp.length();
				break;
			}			
		}		
		return res;
	}

}
