package Programmers;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;

public class Programmers_정렬_가장큰수 {
	public static void main(String[] args){
		int[] numbers=new int[]{3,30,34,5,9};
		solution(numbers);
	}	
	 public static String solution(int[] numbers) {
		  final StringBuffer  answer = new StringBuffer();
	        LinkedList<String> strList = new LinkedList<String>();
	        Arrays.stream(numbers).forEach(num->{
	        	strList.add(num+"");
	        });
	        strList.stream().sorted( (o1, o2) -> (o2 + o1).compareTo(o1 + o2))
	        .forEach(num->{
	        	answer.append(num);
	        });
 	        return answer.toString();
	    }
}
