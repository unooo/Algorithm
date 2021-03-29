

import java.util.LinkedList;

public class ¼ýÀÚ¸¦ÇÑ±Û·Î {

	public static void main(String[] args) {
		String[] digit={"","ÀÏ", "ÀÌ", "»ï","»ç","¿À","À°","Ä¥","ÆÈ","±¸"};
		String[] digit2 = {"","½Ê","¹é","Ãµ"};
		String[] digit3={"","¸¸","¾ï"};
		
		
		int number=100000002;	
		int pivot=1;
		String ret="";
		while(true){
			int target=number%10;
			number=number/10;
			String temp = new String();
			if(pivot==2 &&target==1){
				
			}else
				temp+=digit[target];
			
			if(target!=0)
				temp=temp+digit2[(pivot-1)%4];		
			
			if(target!=0&&(pivot-1)%4==0&&(pivot-1)/4>0)
				temp=temp+digit3[(pivot-1)/4];
			ret=temp+ret;
			pivot++;
			if(number==0)
				break;
		}
	}

}
