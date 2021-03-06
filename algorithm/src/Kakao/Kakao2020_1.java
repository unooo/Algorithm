package Kakao;

import java.util.Comparator;
import java.util.Stack;
import java.util.TreeSet;

public class Kakao2020_1 {

	public static void main(String[] args){
		System.out.println(solution("a"));
	}
	
	
	  
	 
	 public static  int solution(String s) {
	        int answer = 0;
	        TreeSet<String> set = new TreeSet<String>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					return o1.length()-o2.length();
				}
			});
	        
	        for(int i = 1 ; i <=s.length()/2 ; i++){
	        	Stack<String> stack = new Stack<String>();	  
	        	StringBuffer retStr = new StringBuffer();
	        	for(int j = 0 ; j <s.length();j+=i){
	        		int startIdx=j;
	        		int endIdx=j+i;
	        		if(endIdx>s.length())
	        			endIdx=s.length();
	        		String tempStr= s.substring(startIdx, endIdx);
	        		
	        		if(stack.size()==0){
	        			stack.add(tempStr);
	        			continue;
	        		}
	        		String prevStr=stack.peek();
	        		if(prevStr.equals(tempStr)){
	        			stack.add(tempStr);
	        		}else {	        		
	        			int compactNum=stack.size();
	        			stack.clear();
	        			stack.add(tempStr);
	        			if(compactNum==1){
	        				retStr.append(prevStr);
	        			}else{
	        				retStr.append(compactNum+prevStr);
	        			}	        			
	        		}	        		
	        	}
	        	if(!stack.isEmpty()){
	        		int compactNum=stack.size();
	        		String lastStr=stack.pop();
	        		if(compactNum==1){
        				retStr.append(lastStr);
        			}else{
        				retStr.append(compactNum+lastStr);
        			}
	        	}
	        	set.add(retStr.toString());
	        }
	        answer=set.first().length();
	        return answer;
	    }
}
