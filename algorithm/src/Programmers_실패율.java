import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Programmers_실패율 {
	public static void main(String[] args){
		int[] stages = new int[]{2, 1, 2, 6, 2, 4, 3, 3};
		Arrays.stream(solution(5, stages)).forEach(i->{
			System.out.println(i);
		});
	}

	public static int[] solution(int N, int[] stages) {
        int[] answer = {};
        int[] bucket = new int[N+1];
        Arrays.stream(stages).forEach(item->{
            if(item<=N)                
            bucket[item]++;
        });
        
        double total = stages.length;        
        TreeMap<Integer, Double> map = new TreeMap<Integer, Double>();
        
        for(int i = 1; i < bucket.length ; i ++){
        	double failure=0;
        	if(total!=0)        		
             failure= bucket[i]/total;
            map.put(i,failure);
            total=total-bucket[i];
        }
        
        List<Integer> keyList =  new ArrayList<>(map.keySet());
		//중요2
		Collections.sort(keyList,(a,b)->{
			//return map.get(a).compareTo(hMap.get(b))*-1;
            if(map.get(a)>map.get(b))
                return -1;
            else if(map.get(a).equals(map.get(b)))
                return a-b;
            else
                return 1;			
		});
      
        
        return keyList.stream().mapToInt(Integer::intValue).toArray();
    }
}
