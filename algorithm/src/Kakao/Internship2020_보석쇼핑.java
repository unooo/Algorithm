package Kakao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Internship2020_보석쇼핑 {

		public static void main(String[] args){
			String[] ary={"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
			
			System.out.println(solution(ary));
		}


	    public static int[] solution(String[] ary) {

	        HashSet<String> set = new HashSet<String>();
	        for(int i = 0 ; i < ary.length ; i ++){
	            set.add(ary[i]);
	        }

	        int end=0;
	        int start=0;
	        HashMap<String,Integer> list = new HashMap<String,Integer>();
	        LinkedList<Integer[]> candidate = new LinkedList<Integer[]>();
	        int temp=0;
	        for ( start = 0; start < ary.length; start++) {

				// end값을 가능한 만큼 증가시킨 다음
				while (set.size()>temp && end < ary.length) {
					Integer val = list.get(ary[end]);
					if(val==null){
						list.put(ary[end],1);
						temp++;
					}else{
						list.put(ary[end], val+1);
					}
					
					end += 1;
				}
				
				// 부분 합이 m이라면 카운트를 증가시킨다.
				if (set.size()==temp) {
					candidate.add(new Integer[]{start+1,end});
				}
				
				// start값을 1 증가시키기 전에 해당 수열의 값을 빼준다.
				Integer val = list.get(ary[start])-1;
				if(val==0){
					list.remove(ary[start]);
					temp--;
				}else{
					list.put(ary[start], val);
				}
				

			}
	        
	        Integer[] tp=candidate.stream().sorted((a,b)->{
	        	return (a[1]-a[0])-(b[1]-b[0]);
	        }).findFirst().get();
	        
	        return Arrays.stream(tp).mapToInt(Integer::intValue).toArray();
	   
	}
}
