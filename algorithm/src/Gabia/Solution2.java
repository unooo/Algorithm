package Gabia;

import java.util.HashSet;

public class Solution2 {
	
	public static void main(String[] args) {
		String s = "zxzxz";
		System.out.println(solution(s));
	}

	public static int solution(String s) {
        int answer = 0;
        HashSet<String> retSet = new HashSet<>();
        int len = s.length();
        for(int i = 1 ; i <= len ; i++) {
        	
        	Middle:
        	for(int j = 0 ; j+i <=len ; j++) {
        		boolean bucket[] = new boolean[40];
        		String tpStr= s.substring(j,j+i);
        		int strLen=tpStr.length();
        		for(int k = 0 ; k < strLen ; k++) {
        			if(bucket[tpStr.charAt(k)-'a']==true) {
        				continue Middle;
        			}else {
        				bucket[tpStr.charAt(k)-'a']=true;
        			}
        		}
        		retSet.add(tpStr);
        		
        	}
        }
        
        
        return retSet.size();
    }
}
