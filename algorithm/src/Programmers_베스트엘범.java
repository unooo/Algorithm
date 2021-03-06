import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Programmers_º£½ºÆ®¿¤¹ü {
	
	public static void main(String[] args){
		String[] genres = new String[]{"classic"};
		int[] plays = new int[]{500};
		solution(genres, plays);
	}

	public  static int[] solution(String[] genres, int[] plays) {
		
		 HashMap<String,Integer> hMap = new HashMap<String,Integer>();
		 for(int i = 0 ; i < genres.length ; i ++){
			 String temp= genres[i];
			 Integer value = hMap.get(temp);
			 if(value==null){
				 hMap.put(temp, plays[i]);
			 }else{
				 hMap.put(temp, value+plays[i]);
			 }
		 }
		 List<String> keySetList = new ArrayList<>(hMap.keySet());
		 
	
		 
		 Collections.sort(keySetList,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				
				return hMap.get(o1).compareTo(hMap.get(o2))*-1;
			}
		});
		 
		 HashMap<Integer,Integer>[] hMapAry = new HashMap[hMap.size()];
		 int f = 0;
		 for(String key :keySetList){
			 hMapAry[f] = new HashMap<Integer,Integer>();
			 HashMap<Integer,Integer> tempMap=hMapAry[f];
			 
			 for(int i = 0 ; i < genres.length ; i ++){
				 
				 String temp= genres[i];
				 if(!temp.equals(key))
					 continue;
				 
				 tempMap.put(i, plays[i]);
			 }
			 f++;
		 }
		 List<Integer> retList = new LinkedList<Integer>();
		 for(HashMap<Integer,Integer> tpMap : hMapAry){
			 List<Integer> tpSetList = new ArrayList<>(tpMap.keySet());
			 Collections.sort(tpSetList,new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						
						if(tpMap.get(o1)>tpMap.get(o2)){
							return -1;
						}else if(tpMap.get(o1)==tpMap.get(o2)){
							return o1.compareTo(o2);
						}else{
							return 1;
						}
					}
				});
			 int endIdx=2;
			 if(tpSetList.size()<2)
				 endIdx=tpSetList.size();
			 retList.addAll(tpSetList.subList(0, endIdx));
		 }
		 
		 Integer[] answer = {};
		 answer = ( retList.toArray(new Integer[retList.size()]));
		 
		 int[] retAry = new int[answer.length];
		for(int i=0;i<answer.length;i++){
			retAry[i]=answer[i];
		}
       
        return retAry;
    }
}
