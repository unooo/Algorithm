package baeckjoon;

import java.util.LinkedList;

public class Test {

	
	
	public static void main(String args[]){

		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.add(4);
		
		for(Integer temp:ll){
			temp=2;
		}
		
		for(Integer temp:ll)
			System.out.println(temp);
		
		
	}
}
