package Kakao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Intership202003 {

	public static void main(String[] args) {

		//String[] gems =//{"1", "2", "3", "4", "5"};
				//{"1", "2", "3", "1", "2"};
				//{"ZZZ", "YYY", "NNNN", "YYY", "BBB"}; 
				//{"DIA","EM","EM","RUB","DIA"};
				//new String[]{"XYZ", "XYZ", "XYZ"}; 
				//new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		//new String[]{"AA", "AB", "AC", "AA", "AC"}; 
//		int[] res = solution(gems);
//		System.out.println(res[0]+" "+res[1]);
	}

	public static int[] solution(String[] gems) {
		int[] answer = new int[2];

		int left = 0, right = 0;
		int sum = 0;
		HashSet<String> set = new HashSet<String>();
		for(int i = 0 ; i < gems.length ; i ++)
			set.add(gems[i]);
		HashMap<String, Integer> goal = new HashMap<String, Integer>();
		for(String temp : set){
			goal.put(temp, 0);
		}		
		ArrayList<Pair> candidate = new ArrayList<Pair>();		
		while(true){			
			
			if(sum>=set.size()){
				candidate.add(new Pair(left,right));
				int tp =goal.get(gems[left]);
				tp--;
				if(tp==0)
					sum--;
				goal.put(gems[left],tp);
				left++;
			}else if(right>=gems.length|| left>right)
				break;
			else{
				int tp = goal.get(gems[right]);
				if(tp==0)
					sum++;
				tp++;
				goal.put(gems[right], tp);
				
				right++;
			}			
			
			
				
		}
		Collections.sort(candidate);
		answer[0]=candidate.get(0).left+1;
		answer[1]=candidate.get(0).right;
		return answer;
	}
	
	static class Pair implements Comparable<Pair>{
		int left;
		int right;
		@Override
		public int compareTo(Pair arg0) {
			int num =(this.right-this.left)-(arg0.right-arg0.left);
			if(num>0)
				return 1;
			else if(num==0){
				return this.left-arg0.left;
			}
			else
				return -1;
		}
		public Pair(int left, int right){
			this.left=left;
			this.right=right;
		}
		
	}
}
