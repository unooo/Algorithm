import java.util.HashMap;

public class Programmers_¿ß¿Â {

	 public int solution(String[][] clothes) {
		 	
		 HashMap<String,Integer> hMap = new HashMap<String,Integer>();
		 for(String[] tpAry:clothes){
			 Integer value = hMap.get(tpAry[1]);
			 if(value==null){
				 hMap.put(tpAry[1], 1);
			 }else{
				 hMap.put(tpAry[1], value+1);
			 }
		 }
		 int res=1;
		 
		 for(String key : hMap.keySet()){
			 int v = hMap.get(key);
			 res*=(v+1);
		 }
		 return res-1;

	    }
}
