package Kakao;

public class Kakao_2 {

	public static void main(String[] args){
		String ret = new String("()))((()"		);
	
		System.out.println(	check(ret));
	}
	
	public static String check(String str){
		if(str==null||str.length()==0)
			return new String();
		int idx = checkBalance(str);
		String u = new String(str.substring(0, idx+1));
		String v = new String(str.substring(idx+1));
		if(checkRight(u)){
			String str2=check(v);
			return new String(u+str2);
		}else{
			String str3 = new String();
			str3+="(";
			str3+=check(v);
			str3+=")";
			String str4=new String(u.substring(1,u.length()-1));
			String str5=new String();
			for(int i = 0 ; i < str4.length() ; i ++){
				if(str4.charAt(i)=='(')
					str5+=")";
				else
					str5+="(";
			}
			str3+=str5;
			return str3;
		}
	}
	
	public static int checkBalance(String str){
	
		int num = 0 ;
		int idx=0;
		for(int i = 0 ; i<str.length() ; i ++){
			if(str.charAt(i)=='(')
				num++;
			else
				num--;
			if(num==0){
				idx=i;
				break;
			}
			
		}
		return idx;
		
		
		
	}
	public static boolean checkRight(String str){
		int num = 0;
		boolean ret = true;
		for(int i = 0 ; i < str.length() ; i ++){
			if(str.charAt(i)=='(')
				num++;
			else
				num--;
			if(num<0){
				ret=false;
				break;
			}
		}
		return ret;
	}
}
