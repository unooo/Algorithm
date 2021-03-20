
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;



public class Solution1 {
	
	public static void main(String[] args){
		String[] table={"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
				//{"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
		String[] languages={"JAVA", "JAVASCRIPT"};
				//{"PYTHON", "C++", "SQL"};
		int[] preference={7,5};
				//{7, 5, 5};
		System.out.println(solution(table, languages, preference));
	}

	
	
	
	public static String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";
        TreeMap<Integer, LinkedList<String>> candidate = new TreeMap<>(Comparator.reverseOrder());
        
        HashMap<String,HashMap<String,Integer>> tableMap = new HashMap<>();
        for(int i = 0 ; i < table.length ; i++){
        	
        	String[] tpAry= table[i].split(" ");
        	HashMap<String,Integer>  tpMap = new HashMap<>();
        	for(int j = 1 ; j < tpAry.length ; j++){
        		tpMap.put(tpAry[j], 6-j);
        	}
        	tableMap.put(tpAry[0], tpMap);        	
        }
        
        for(String key : tableMap.keySet()){
        	HashMap<String,Integer>  tpMap = tableMap.get(key);
        	int sum=0;
        	for(int i = 0 ; i < languages.length ;i++){
        		if(tpMap.get(languages[i])==null){
        			continue;
        		}
        		sum+=tpMap.get(languages[i])*preference[i];        		
        	}
        	LinkedList<String> val = candidate.get(sum);
        	if(val==null){
        		val=new LinkedList<String> ();
        		val.add(key);
        		candidate.put(sum, val);
        	}else{
        		val.add(key);
        	}  	
        	
        }
        
        LinkedList<String> retList = candidate.firstEntry().getValue();
        Collections.sort(retList);
        answer=retList.getFirst();
        
        return answer;
    }
}
