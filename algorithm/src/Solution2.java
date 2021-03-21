
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution2 {
	public static void main(String [] args){
		String inp_str="-";
		solution(inp_str);
	}
	
	  public static int[] solution(String inp_str) {
	        int[] answer = {};
	        
	        LinkedList<Integer> ret = new LinkedList<>();
	        
	        if(inp_str.length()<8||inp_str.length()>15)
	        	ret.add(1);
	        
	        Pattern first = Pattern.compile("[^0-9a-zA-Z~!@#$%^&*]");
	        Matcher matcher = first.matcher(inp_str);
	        if(matcher.find()){
        		ret.add(2);
        	}
	        
	        int tpSum=0;
	        Pattern second = Pattern.compile("[0-9]");
	        matcher = second.matcher(inp_str);
	        if(matcher.find()){
	        	tpSum++;
        	}	        
	        second = Pattern.compile("[a-z]");
	        matcher = second.matcher(inp_str);
	        if(matcher.find()){
	        	tpSum++;
        	}
	        second = Pattern.compile("[A-Z]");
	        matcher = second.matcher(inp_str);
	        if(matcher.find()){
	        	tpSum++;
        	}
	        second = Pattern.compile("[~!@#$%^&*]");
	        matcher = second.matcher(inp_str);
	        if(matcher.find()){
	        	tpSum++;
        	}
	        if(tpSum<3)
	        	ret.add(3);
	        
	        int dup=1;
	        for(int i = 1 ; i <inp_str.length();i++){
	        	if(inp_str.charAt(i)==inp_str.charAt(i-1)){
	        		dup++;
	        	}else{
	        		dup=1;
	        	}
	        	if(dup>=4){
	        		ret.add(4);
	        		break;
	        	}
	        }
	        
	        if(dup>=4&&!ret.contains(4)){
	        	ret.add(4);
	        }
	        HashMap<Character, Integer> map = new HashMap<>();
	        for(int i = 0 ; i <inp_str.length();i++){
	        	Integer val = map.get(inp_str.charAt(i));
	        	if(val==null)
	        		map.put(inp_str.charAt(i), 1);
	        	else{
	        		map.put(inp_str.charAt(i), val+1);
	        		if(val+1>=5){
		        		ret.add(5);
		        		break;
		        	}
	        	}
	        	
	        		
	        }
	        if(ret.size()==0)
	        	ret.add(0);
	        
	        return ret.stream().mapToInt(Integer::intValue).toArray();
	    }

}
