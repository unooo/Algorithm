package Kakao;

import java.util.HashMap;
import java.util.LinkedList;

public class Programmers_æ–√‡ {
	
	public static void main(String[] args){
		String msg="TOBEORNOTTOBEORTOBEORNOT";
		System.out.println(solution(msg));
	}

	 public static int[] solution(String msg) {
	        int[] answer = {};
	        LinkedList<Integer>  ret = new LinkedList<>();
	        HashMap<String, Integer> index = new HashMap<String, Integer>();
	        for(int i = 0 ; i < 26 ; i++){
	            index.put(((char)('A'+i))+"",i+1);
	        }
	        int stIdx=-1;
            int endIdx=-1;
            Loop:
	        for(int i = 0 ; i < msg.length() ; i ++){
	             stIdx=i;
	             endIdx=stIdx+1;
	            while(endIdx<=msg.length()){
	                String tp = msg.substring(stIdx,endIdx);
	                Integer val = index.get(tp);
	                if(val==null){
	                    index.put(tp,index.size()+1);
	                    ret.add(index.get(msg.substring(stIdx,endIdx-1)));
	                    i=endIdx-2;
	                    continue Loop;
	                }
	                endIdx++;
	            }
	            break;
	            //System.out.println();
	        }
	        ret.add(index.get(msg.substring(stIdx,endIdx-1)));
	        return ret.stream().mapToInt(Integer::intValue).toArray();
	        
	    }
}
