package Programmers;
import java.util.HashMap;

public class Programmers_�����������Ѽ��� {
	
	public static void main(String[] args){
		
	}

	 public String solution(String[] participant, String[] completion) {
		 HashMap<String , Integer> map = new HashMap<String , Integer>();
		 
		 for(String temp : completion){
			 Integer value = map.get(temp);
			 if(value==null){
				 map.put(temp, 1);
			 }else{
				 map.put(temp, value+1);
			 }
		 }
		 
		 for(String temp : participant){
			 Integer value = map.get(temp);
			 if(value==null){
				 return temp;
			 }else{
				 if(value==1){
					 map.remove(temp);
				 }else{
					 map.put(temp, value-1);
				 }
				 
			 }
		 }
		 
	        String answer = "";
	        return answer;
	   }
}
