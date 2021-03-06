package Programmers;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Programmers_완전탐색_모의고사 {
	public static void main(String[] args){
		int[] answers = new int[]{1,2,3,4,5};
		solution(answers);
	}

	 public static int[] solution(int[] answers) {
	        int[] answer = {};
	        int len = answers.length;
	        
	        int sol[][]=new int[][]{
	        	{1,2,3,4,5},
	        	{2,1,2,3,2,4,2,5},
	        	{3,3,1,1,2,2,4,4,5,5}
	        };
	       answer=new int[3];
	       int max=Integer.MIN_VALUE;
	       for(int i = 0 ; i < 3 ; i ++){
	    	   int tempSum=0;
	    	   for(int j = 0 ; j < len ; j ++){
	    		   if(answers[j]==sol[i][j%(sol[i].length)]){
	    			   tempSum++;
	    		   }
	    	   }
	    	   max=Math.max(max, tempSum);
	    	   answer[i]=tempSum;
	       }
	    
	       LinkedList<Integer> ret = new LinkedList<>();
	       for(int i = 0 ; i < answer.length ; i++){
	    	   if(answer[i]==max)
	    		   ret.add(i+1);
	       }
	       
	        return ret.stream().mapToInt(Integer::intValue).toArray(); 
	    }
}
