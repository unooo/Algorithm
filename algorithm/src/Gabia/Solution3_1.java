package Gabia;

import java.util.LinkedList;

public class Solution3_1 {
	
	public static void main(String[] args) {
		int coffee_times[]= {4,2,2,5,3};
		int N=3;
		solution(N, coffee_times);
		
		
	}

	 public static int[] solution(int N, int[] coffee_times) {
	        int[] answer = new int[coffee_times.length];
	        LinkedList<Coffee> machine = new LinkedList<>();
	        LinkedList<Integer> ret = new LinkedList<>();
	        int coffeeIdx=0;
	        int completeIdx=0;
	        while(true) {
	        	if(coffeeIdx>=coffee_times.length&&machine.size()==0) {
	        		break;
	        	}
	        	
	        	if(machine.size()!=0) {
	        		for(int i = 0 ; i < machine.size() ;i++) {
	        			Coffee coffeeProcess = machine.get(i);
	        			coffeeProcess.time--;
	        			if(coffeeProcess.time==0) {
	        				ret.add(coffeeProcess.id+1);
	        				Coffee tpcoffee=machine.remove(i);
	        				answer[completeIdx]=tpcoffee.id+1;
	        				completeIdx++;
	        				i--;
	        			}
	        		}
	        	}
	        	
	        	if(machine.size()==N)
	        		continue;
	        	
	        	while(true) {
	        		if(coffeeIdx>=coffee_times.length || machine.size()==N)
		        		break;
	        		machine.add(new Coffee(coffeeIdx,coffee_times[coffeeIdx]));
	        		coffeeIdx++;
	        	}
	        	
	        }
	        
	      //  return ret.stream().mapToInt(Integer::intValue).toArray();
	        return answer;
	    }
	 
	 static class Coffee{
		 int id, time;

		public Coffee(int id, int time) {
			super();
			this.id = id;
			this.time = time;
		}
		 
	 }
}
