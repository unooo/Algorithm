package baeckjoon;

import java.security.KeyStore.Entry;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;

public class Programmers_무지의먹방라이브 {

	public static void main(String[] args) {
		int[] food_times = {3,1,2};
		int k = 5;
		System.out.println(solution(food_times, k));
	}

	public static int solution(int[] food_times, long k) {
		int answer = -1;
		TreeMap<Integer, LinkedList<Integer>> map = new TreeMap<>();
		for(int i = 0 ; i < food_times.length ; i++){
			LinkedList<Integer> val = map.get(food_times[i]);
			if(val==null){
				val=new LinkedList<Integer>();
				map.put(food_times[i], val);
			}
			val.add(i);	
			
		}
		int prev=0;
		long total=food_times.length;
				
		while(true){
			if(k<0)
				break;
			int min;
			try{
				min=map.firstKey()-prev;
			}catch(Exception e){
				break;
			}
			long temp=k-total*min;
			if(temp<0){
				LinkedList<Integer> newList = new LinkedList<Integer>();
				map.forEach((key,val)->{
					newList.addAll(val);
				});
				Collections.sort(newList);
				return newList.get((int) ((k)%total))+1;
				
			}else{
				k=temp;
			}
			
			java.util.Map.Entry<Integer, LinkedList<Integer>> tp =map.pollFirstEntry();
			prev=tp.getKey();  
			total-=tp.getValue().size();
		}
		
		return answer;
	}

}
